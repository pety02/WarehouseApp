package com.example.warehouseapp.service;

import com.example.warehouseapp.exception.NotFoundEntityException;
import com.example.warehouseapp.model.dto.EmployeeCredentialsCreateRequestDTO;
import com.example.warehouseapp.model.dto.EmployeeCredentialsResponseDTO;
import com.example.warehouseapp.model.entites.Employee;
import com.example.warehouseapp.model.entites.EmployeeCredentials;
import com.example.warehouseapp.model.mapper.EmployeeCredentialsMapper;
import com.example.warehouseapp.repository.EmployeeCredentialsRepository;
import com.example.warehouseapp.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EmployeeCredentialsService {
    private final EmployeeCredentialsRepository employeeCredentialsRepository;
    private final EmployeeRepository employeeRepository;
    private final EmployeeCredentialsMapper employeeCredentialsMapper;

    List<EmployeeCredentialsResponseDTO> getAllEmployeeCredentials() {
        List<EmployeeCredentials> employeeCredentialsList = this.employeeCredentialsRepository.findAll();
        return employeeCredentialsList
                .stream()
                .map(ec -> {
                    Optional<Employee> e = this.employeeRepository.findEmployeeByCredentialsId(ec.getId());
                    if(e.isEmpty()) {
                        throw new NotFoundEntityException("Employee not found");
                    }
                    return this.employeeCredentialsMapper.mapToResponseDTO(ec, e.get());
                }).toList();
    }

    EmployeeCredentials createCredentials(EmployeeCredentialsCreateRequestDTO employeeCredentialsRequest,
                                          String user, LocalDate today) {
        return this.employeeCredentialsRepository.save(
                this.employeeCredentialsMapper.mapToEntity(employeeCredentialsRequest, user, today)
        );
    }
}
