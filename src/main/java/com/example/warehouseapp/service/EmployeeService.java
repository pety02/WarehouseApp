package com.example.warehouseapp.service;

import com.example.warehouseapp.exception.InactiveEmployeeException;
import com.example.warehouseapp.exception.NotFoundEntityException;
import com.example.warehouseapp.model.dto.*;
import com.example.warehouseapp.model.entites.Employee;
import com.example.warehouseapp.model.entites.EmployeeCredentials;
import com.example.warehouseapp.model.entites.EmployeeRole;
import com.example.warehouseapp.model.entites.Location;
import com.example.warehouseapp.model.mapper.EmployeeMapper;
import com.example.warehouseapp.repository.EmployeeRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.UUID;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final EmployeeMapper employeeMapper;
    private final EmployeeCredentialsService employeeCredentialsService;
    private final EmployeeRoleService employeeRoleService;
    private final LocationService locationService;
    private final PasswordEncoder passwordEncoder;

    @Transactional(readOnly = true)
    public List<EmployeeResponseDTO> getAllEmployees() {
        List<Employee> employees = employeeRepository.findAll();

        if (employees.isEmpty()) {
            throw new NotFoundEntityException("No employees found");
        }

        return employees.stream()
                .map(employeeMapper::mapToResponseDTO)
                .toList();
    }

    @Transactional(readOnly = true)
    public EmployeeResponseDTO getEmployeeById(UUID id){
        Employee employee = employeeRepository.findById(id
        ).orElseThrow(() -> new NotFoundEntityException("Employee not found"));

        return this.employeeMapper.mapToResponseDTO(employee);
    }

    @Transactional(readOnly = true)
    public List<EmployeeResponseDTO> getAllEmployeesByLocationId(UUID id) {
        return this.employeeRepository.findAllByLocationId(id).stream()
                .map(employeeMapper::mapToResponseDTO)
                .toList();
    }

    public EmployeeResponseDTO createEmployee(EmployeeCreateRequestDTO employeeRequestDTO,
                                              String user) {
        LocalDate today = LocalDate.now();
        final EmployeeRole role = employeeRoleService.getEmployeeRoleByName(employeeRequestDTO.getRole());
        final Location location = locationService.getLocationByAddressAndName(
                employeeRequestDTO.getLocationAddress(),
                employeeRequestDTO.getLocationName()
        );
        EmployeeCredentials credentials = EmployeeCredentials
                .builder()
                .email(employeeRequestDTO.getEmail())
                .phoneNumber(employeeRequestDTO.getPhoneNumber())
                .password(this.passwordEncoder.encode(employeeRequestDTO.getPassword()))
                .createdBy(user)
                .updatedBy(null)
                .createdAt(today.atStartOfDay(ZoneId.systemDefault()).toInstant())
                .updatedAt(null)
                .build();
        final EmployeeCredentials employeeCredentials = employeeCredentialsService.saveCredentials(credentials);
        final Employee toBeSaved = this.employeeMapper.mapToEmployee(
                employeeRequestDTO,
                role, location, user, today
        );
        toBeSaved.setCredentials(employeeCredentials);
        toBeSaved.setActive(true);

        return this.employeeMapper.mapToResponseDTO(this.employeeRepository.save(toBeSaved));
    }

    public EmployeeResponseDTO updateEmployee(UUID id, EmployeeUpdateRequestDTO employeeRequestDTO, String user) {
        Employee toBeUpdated = this.employeeRepository.findById(id).orElseThrow(() ->
                new NotFoundEntityException("Employee not found"));
        LocalDate today = LocalDate.now();

        EmployeeCredentials credentials = toBeUpdated.getCredentials();
        credentials.setEmail(employeeRequestDTO.getEmail());
        credentials.setPassword(this.passwordEncoder.encode(employeeRequestDTO.getPassword()));
        credentials.setPhoneNumber(employeeRequestDTO.getPhoneNumber());
        this.employeeCredentialsService.saveCredentials(credentials);

        EmployeeRole role = employeeRoleService.getEmployeeRoleByName(employeeRequestDTO.getRole());
        Location location = locationService.getLocationByAddressAndName(
                employeeRequestDTO.getLocationAddress(),
                employeeRequestDTO.getLocationName()
        );

        this.employeeMapper.updateEmployee(
                toBeUpdated, employeeRequestDTO,
                role != null ? role : toBeUpdated.getRole(),
                location != null ? location : toBeUpdated.getLocation(),
                user, today
        );
        return  this.employeeMapper.mapToResponseDTO(this.employeeRepository.save(toBeUpdated));
    }

    public EmployeeLoginResponseDTO loginAndAuthenticate(EmployeeLoginRequestDTO dto, HttpServletRequest request) {

        EmployeeCredentials credentials = employeeCredentialsService.findByEmail(dto.getEmail());

        if (!passwordEncoder.matches(dto.getPassword(), credentials.getPassword())) {
            throw new BadCredentialsException("Invalid email or password");
        }

        Employee employee = employeeRepository.findEmployeeByEmail(dto.getEmail())
                .orElseThrow(() -> new NotFoundEntityException("Employee not found"));

        if (!employee.isActive()) {
            throw new InactiveEmployeeException("Employee inactive");
        }

        // 🔑 MANUAL SPRING AUTHENTICATION
        Authentication authentication = new UsernamePasswordAuthenticationToken(
                employee.getCredentials().getEmail(),
                null,
                List.of(new SimpleGrantedAuthority("ROLE_EMPLOYEE"))
        );

        SecurityContext context = SecurityContextHolder.createEmptyContext();
        context.setAuthentication(authentication);

        HttpSession session = request.getSession(true);
        session.setAttribute(
                HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY,
                context
        );

        SecurityContextHolder.setContext(context);

        return EmployeeLoginResponseDTO.builder()
                .id(employee.getId().toString())
                .fullName(employee.getName() + " " + employee.getSurname())
                .email(employee.getCredentials().getEmail())
                .locationId(employee.getLocation().getId().toString())
                .build();
    }

    public void deleteEmployeeById(UUID id){
        this.employeeRepository.findById(id).ifPresent(employee -> employee.setActive(false));
    }
}
