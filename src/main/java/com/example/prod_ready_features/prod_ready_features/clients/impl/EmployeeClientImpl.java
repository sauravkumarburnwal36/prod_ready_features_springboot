package com.example.prod_ready_features.prod_ready_features.clients.impl;

import com.example.prod_ready_features.prod_ready_features.advices.APIResponse;
import com.example.prod_ready_features.prod_ready_features.clients.EmployeeClient;
import com.example.prod_ready_features.prod_ready_features.dto.EmployeeDTO;
import com.example.prod_ready_features.prod_ready_features.exceptions.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeClientImpl implements EmployeeClient {

    private final RestClient restClient;
    @Override
    public List<EmployeeDTO> getAllEmployees() {
        try {
            APIResponse<List<EmployeeDTO>> employeeDTO = restClient.get().
                    uri("employees")
                    .retrieve()
                    .body(new ParameterizedTypeReference<>() {
                    });
            return employeeDTO.getData();
        }
        catch(Exception e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public EmployeeDTO createNewEmployee(EmployeeDTO employeeDTO) {
        try{
            ResponseEntity<APIResponse<EmployeeDTO>> employeeDTOResponse=restClient.post()
                    .uri("employees")
                    .body(employeeDTO)
                    .retrieve()
                    .onStatus(HttpStatusCode::is4xxClientError,(req,res)->{
                        System.out.println(new String(res.getBody().readAllBytes()));
                        throw new ResourceNotFoundException("Could not create Employee");
                    })
                    .toEntity(new ParameterizedTypeReference<>() {
                    });
            return employeeDTOResponse.getBody().getData();
        }
        catch(Exception e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public EmployeeDTO getEmpployeeById(Long employeeId) {
        try{
            APIResponse<EmployeeDTO> employeeDTOAPIResponse=restClient.get()
                    .uri("employees/{employeeId}",employeeId)
                    .retrieve()
                    .body(new ParameterizedTypeReference<>() {
                    });
            return employeeDTOAPIResponse.getData();
        }
        catch(Exception e){
            throw new RuntimeException(e);
        }
    }
}
