package com.example.warehouseapp.service;

import com.example.warehouseapp.model.entites.EmployeeCredentials;
import com.example.warehouseapp.model.mapper.EmployeeCredentialsMapper;
import com.example.warehouseapp.repository.EmployeeCredentialsRepository;
import com.example.warehouseapp.repository.EmployeeRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.server.ResponseStatusException;

import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class EmployeeCredentialsServiceTest {

    @Mock
    private EmployeeCredentialsRepository employeeCredentialsRepository;

    @Mock
    private EmployeeRepository employeeRepository;

    @Mock
    private EmployeeCredentialsMapper employeeCredentialsMapper;

    @InjectMocks
    private EmployeeCredentialsService service;

    @Test
    void findByEmail_success() {
        EmployeeCredentials creds = EmployeeCredentials.builder()
                .email("test@test.com")
                .build();

        when(employeeCredentialsRepository.findByEmail("test@test.com"))
                .thenReturn(Optional.of(creds));

        EmployeeCredentials result = service.findByEmail("test@test.com");

        assertEquals("test@test.com", result.getEmail());
    }

    @Test
    void findByEmail_notFound() {
        when(employeeCredentialsRepository.findByEmail(any()))
                .thenReturn(Optional.empty());

        assertThrows(ResponseStatusException.class,
                () -> service.findByEmail("missing@test.com"));
    }

    @Test
    void saveCredentials_success() {
        EmployeeCredentials creds = EmployeeCredentials.builder().build();

        when(employeeCredentialsRepository.save(creds)).thenReturn(creds);

        EmployeeCredentials saved = service.saveCredentials(creds);

        assertNotNull(saved);
    }
}
