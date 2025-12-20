package com.example.warehouseapp.service;

import com.example.warehouseapp.exception.NotFoundEntityException;
import com.example.warehouseapp.model.dto.EmployeeCreateRequestDTO;
import com.example.warehouseapp.model.dto.EmployeeCredentialsCreateRequestDTO;
import com.example.warehouseapp.model.dto.EmployeeResponseDTO;
import com.example.warehouseapp.model.entites.Employee;
import com.example.warehouseapp.model.entites.EmployeeCredentials;
import com.example.warehouseapp.model.entites.EmployeeRole;
import com.example.warehouseapp.model.entites.Location;
import com.example.warehouseapp.model.mapper.EmployeeMapper;
import com.example.warehouseapp.repository.EmployeeCredentialsRepository;
import com.example.warehouseapp.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.UUID;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final EmployeeCredentialsRepository employeeCredentialsRepository;
    private final EmployeeMapper employeeMapper;
    private final EmployeeCredentialsService employeeCredentialsService;
    private final EmployeeRoleService employeeRoleService;
    private final LocationService locationService;
    private final PasswordEncoder passwordEncoder;

    public List<EmployeeResponseDTO> getAllEmployees() {
        List<Employee> employees = employeeRepository.findAll();

        if (employees.isEmpty()) {
            throw new NotFoundEntityException("No employees found");
        }

        return employees.stream()
                .map(employeeMapper::mapToResponseDTO)
                .toList();
    }

    public EmployeeResponseDTO getEmployeeById(UUID id){
        Employee employee = employeeRepository.findById(id
        ).orElseThrow(() -> new NotFoundEntityException("Employee not found"));

        return this.employeeMapper.mapToResponseDTO(employee);
    }

    public EmployeeResponseDTO createEmployee(EmployeeCreateRequestDTO employeeRequestDTO,
                                              String user) {
        LocalDate today = LocalDate.now();
        final EmployeeRole role = employeeRoleService.getEmployeeRoleByName(employeeRequestDTO.getRole());
        final Location location = locationService.getLocationByAddressAndName(
                employeeRequestDTO.getLocationAddress(),
                employeeRequestDTO.getLocationName()
        );
        EmployeeCredentialsCreateRequestDTO credentialsRequestDTO = EmployeeCredentialsCreateRequestDTO
                .builder()
                .email(employeeRequestDTO.getEmail())
                .phoneNumber(employeeRequestDTO.getPhoneNumber())
                .password(this.passwordEncoder.encode(employeeRequestDTO.getPassword()))
                .build();
        final EmployeeCredentials employeeCredentials = employeeCredentialsService.createCredentials(
                credentialsRequestDTO,
                user, today
        );
        final Employee toBeSaved = this.employeeMapper.mapToEmployee(
                employeeRequestDTO,
                role, location, user, today
        );
        toBeSaved.setCredentials(employeeCredentials);

        return this.employeeMapper.mapToResponseDTO(this.employeeRepository.save(toBeSaved));
    }

    public void deleteEmployeeById(UUID id){
        employeeRepository.deleteById(id);
    }
}
