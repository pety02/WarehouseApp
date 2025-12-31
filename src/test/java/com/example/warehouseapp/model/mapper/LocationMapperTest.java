package com.example.warehouseapp.model.mapper;

import com.example.warehouseapp.model.dto.LocationResponseDTO;
import com.example.warehouseapp.model.entites.Address;
import com.example.warehouseapp.model.entites.Employee;
import com.example.warehouseapp.model.entites.EmployeeCredentials;
import com.example.warehouseapp.model.entites.Location;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class LocationMapperTest {

    @Test
    void mapToResponseDTO_shouldMapManagerAndLocationFields() throws JsonProcessingException {
        UUID locationId = UUID.randomUUID();
        UUID managerId = UUID.randomUUID();

        EmployeeCredentials credentials = new EmployeeCredentials();
        credentials.setEmail("manager@warehouse.com");
        credentials.setPhoneNumber("+359888111222");

        Employee manager = new Employee();
        manager.setId(managerId);
        manager.setName("Ivan");
        manager.setSurname("Petrov");
        manager.setCredentials(credentials);

        Address address = new Address();
        address.setCity("Sofia"); // <- set the city so JSON matches

        Location location = new Location();
        location.setId(locationId);
        location.setName("Sofia");
        location.setAddress(address);
        location.setManager(manager);

        LocationMapper mapper = new LocationMapper();
        LocationResponseDTO dto = mapper.mapToResponseDTO(location);

        ObjectMapper objectMapper = new ObjectMapper();

        assertEquals(locationId.toString(), dto.getId());
        assertEquals("Sofia", dto.getName());
        assertEquals("{\"country\":null,\"city\":\"Sofia\",\"zip\":null,\"street\":null,\"no\":null}", objectMapper.writeValueAsString(dto.getAddress()));
        assertEquals(managerId.toString(), dto.getManagerId());
        assertEquals("Ivan", dto.getManagerName());
        assertEquals("Petrov", dto.getManagerSurname());
        assertEquals("manager@warehouse.com", dto.getManagerEmail());
        assertEquals("+359888111222", dto.getManagerPhoneNumber());
    }
}
