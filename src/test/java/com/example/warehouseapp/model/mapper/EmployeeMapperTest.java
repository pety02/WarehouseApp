package com.example.warehouseapp.model.mapper;

import com.example.warehouseapp.model.dto.EmployeeCreateRequestDTO;
import com.example.warehouseapp.model.dto.EmployeeResponseDTO;
import com.example.warehouseapp.model.dto.EmployeeUpdateRequestDTO;
import com.example.warehouseapp.model.entites.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.Instant;
import java.time.LocalDate;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class EmployeeMapperTest {

    private EmployeeMapper mapper;

    @BeforeEach
    void setUp() {
        mapper = new EmployeeMapper();
    }

    @Test
    void mapToResponseDTO_shouldMapAllFieldsCorrectly() {
        UUID employeeId = UUID.randomUUID();
        UUID locationId = UUID.randomUUID();

        EmployeeCredentials credentials = new EmployeeCredentials();
        credentials.setEmail("jane@example.com");
        credentials.setPhoneNumber("+359999999");

        EmployeeRole role = new EmployeeRole();
        role.setName("Warehouse Manager");

        Location location = new Location();
        location.setId(locationId);
        location.setName("Sofia");

        Employee employee = new Employee();
        employee.setId(employeeId);
        employee.setName("Jane");
        employee.setSurname("Doe");
        employee.setUidNo("1234567890");
        employee.setHireDate(LocalDate.now());
        employee.setFireDate(LocalDate.now().plusYears(1));
        employee.setCredentials(credentials);
        employee.setRole(role);
        employee.setLocation(location);

        EmployeeResponseDTO dto = mapper.mapToResponseDTO(employee);

        assertEquals(employeeId.toString(), dto.getId());
        assertEquals("Jane", dto.getName());
        assertEquals("Doe", dto.getSurname());
        assertEquals("1234567890", dto.getUidNo());
        assertEquals(credentials.getEmail(), dto.getEmail());
        assertEquals(credentials.getPhoneNumber(), dto.getPhoneNumber());
        assertEquals("Warehouse Manager", dto.getRole());
        assertEquals(locationId.toString(), dto.getLocationId());
        assertEquals("Sofia", dto.getLocationName());
    }

    @Test
    void mapToEmployee_shouldCreateEmployeeCorrectly() {
        EmployeeCreateRequestDTO request = new EmployeeCreateRequestDTO();
        request.setName("Ivan");
        request.setSurname("Ivanov");
        request.setUidNo("9999999999");

        EmployeeRole role = new EmployeeRole();
        Location location = new Location();
        LocalDate today = LocalDate.now();

        Employee employee = mapper.mapToEmployee(
                request,
                role,
                location,
                "system",
                today
        );

        assertEquals("Ivan", employee.getName());
        assertEquals("Ivanov", employee.getSurname());
        assertEquals("9999999999", employee.getUidNo());
        assertEquals(today, employee.getHireDate());
        assertNull(employee.getFireDate());
        assertEquals(role, employee.getRole());
        assertEquals(location, employee.getLocation());
        assertEquals("system", employee.getCreatedBy());
        assertEquals(null, employee.getUpdatedBy());
    }

    @Test
    void updateEmployee_shouldUpdateMutableFieldsOnly() {
        Employee employee = new Employee();
        EmployeeRole newRole = new EmployeeRole();
        Location newLocation = new Location();
        LocalDate fireDate = LocalDate.now();

        EmployeeUpdateRequestDTO dto = new EmployeeUpdateRequestDTO();
        dto.setFireDate(fireDate);

        Employee updated = mapper.updateEmployee(
                employee,
                dto,
                newRole,
                newLocation,
                "admin",
                LocalDate.now()
        );

        assertEquals(fireDate, updated.getFireDate());
        assertEquals(newRole, updated.getRole());
        assertEquals(newLocation, updated.getLocation());
        assertEquals("admin", updated.getUpdatedBy());
        assertNotNull(updated.getUpdatedAt());
    }
}
