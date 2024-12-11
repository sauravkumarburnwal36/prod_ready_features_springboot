package com.example.prod_ready_features.prod_ready_features.clients;

import com.example.prod_ready_features.prod_ready_features.dto.EmployeeDTO;

import java.util.List;

public interface EmployeeClient {
    List<EmployeeDTO> getAllEmployees();

    EmployeeDTO createNewEmployee(EmployeeDTO employeeDTO);

    EmployeeDTO getEmpployeeById(Long employeeId);
}
