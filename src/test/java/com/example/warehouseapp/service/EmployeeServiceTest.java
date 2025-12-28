package com.example.warehouseapp.service;

import com.example.warehouseapp.exception.NotFoundEntityException;
import com.example.warehouseapp.model.dto.EmployeeLoginRequestDTO;
import com.example.warehouseapp.model.dto.EmployeeResponseDTO;
import com.example.warehouseapp.model.entites.Employee;
import com.example.warehouseapp.model.entites.EmployeeCredentials;
import com.example.warehouseapp.model.mapper.EmployeeMapper;
import com.example.warehouseapp.repository.EmployeeRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class EmployeeServiceTest {

    @Mock
    private EmployeeRepository employeeRepository;
    @Mock
    private EmployeeMapper employeeMapper;
    @Mock
    private EmployeeCredentialsService credentialsService;
    @Mock
    private EmployeeRoleService roleService;
    @Mock
    private LocationService locationService;
    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private EmployeeService service;

    @Test
    void getAllEmployees_empty_throws() {
        when(employeeRepository.findAll()).thenReturn(List.of());

        assertThrows(NotFoundEntityException.class,
                () -> service.getAllEmployees());
    }

    @Test
    void getEmployeeById_success() {
        UUID id = UUID.randomUUID();
        Employee employee = Employee.builder().id(id).build();

        when(employeeRepository.findById(id))
                .thenReturn(Optional.of(employee));
        when(employeeMapper.mapToResponseDTO(employee))
                .thenReturn(EmployeeResponseDTO.builder().id(id.toString()).build());

        EmployeeResponseDTO dto = service.getEmployeeById(id);

        assertEquals(id.toString(), dto.getId());
    }

    @Test
    void login_wrongPassword_throws() {
        EmployeeCredentials creds = EmployeeCredentials.builder()
                .password("hashed")
                .build();

        when(credentialsService.findByEmail("a@b.com"))
                .thenReturn(creds);
        when(passwordEncoder.matches("raw", "hashed"))
                .thenReturn(false);

        assertThrows(BadCredentialsException.class,
                () -> service.login(
                        new EmployeeLoginRequestDTO("a@b.com", "raw")));
    }
}
