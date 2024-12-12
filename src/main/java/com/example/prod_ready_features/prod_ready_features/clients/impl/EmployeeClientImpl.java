package com.example.prod_ready_features.prod_ready_features.clients.impl;

import com.example.prod_ready_features.prod_ready_features.advices.APIResponse;
import com.example.prod_ready_features.prod_ready_features.clients.EmployeeClient;
import com.example.prod_ready_features.prod_ready_features.dto.EmployeeDTO;
import com.example.prod_ready_features.prod_ready_features.exceptions.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    Logger log= LoggerFactory.getLogger(EmployeeClientImpl.class);

    @Override
    public List<EmployeeDTO> getAllEmployees() {
        log.trace("Trying to retrieve all employees in getAllEmployees");
        try {
            log.info("Attempting to call restClient in getAllEmployees");
            APIResponse<List<EmployeeDTO>> employeeDTO = restClient.get().
                    uri("employees")
                    .retrieve()
                    .onStatus(HttpStatusCode::is4xxClientError,(req,res)->
                    {
                        log.debug("4xx client error due to:");
                        log.error(new String(res.getBody().readAllBytes()));
                        throw new ResourceNotFoundException("Employee is not created");
                    })
                    .body(new ParameterizedTypeReference<>() {
                    });
            log.debug("Successfully retrieved all employees in getAllEmployees");
            log.trace("Employee list in getAllEmployees:{}",employeeDTO.getData());
            return employeeDTO.getData();
        }
        catch(Exception e){
            log.error("Exception occured in getAllEmployees:",e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public EmployeeDTO createNewEmployee(EmployeeDTO employeeDTO) {
        log.trace("Trying to create new employee:{}",employeeDTO);
        try{
            log.info("Attempting to call rest client in createNewEmployee");
            ResponseEntity<APIResponse<EmployeeDTO>> employeeDTOResponse=restClient.post()
                    .uri("employees")
                    .body(employeeDTO)
                    .retrieve()
                    .onStatus(HttpStatusCode::is4xxClientError,(req,res)->{
                        log.error(new String(res.getBody().readAllBytes()));
                        throw new ResourceNotFoundException("Could not create Employee");
                    })
                    .toEntity(new ParameterizedTypeReference<>() {
                    });
            log.debug("Successfully created new employee");
            log.trace("Empployee Created:{}",employeeDTOResponse.getBody());
            return employeeDTOResponse.getBody().getData();
        }
        catch(Exception e){
            log.error("Exception occured in createNewEmployee",e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public EmployeeDTO getEmpployeeById(Long employeeId) {
        log.trace("Trying to retrieve employee in getEmployeeById with id:{}",employeeId);
        try{
            log.info("Attempting to call restclient in getEmployeeById");
            APIResponse<EmployeeDTO> employeeDTOAPIResponse=restClient.get()
                    .uri("employees/{employeeId}",employeeId)
                    .retrieve()
                    .onStatus(HttpStatusCode::is4xxClientError,(req,res)->{
                        log.error("Could not get employee with id");
                        throw new ResourceNotFoundException("Employee with this id does not exist");
                    })
                    .body(new ParameterizedTypeReference<>() {
                    });
            log.debug("Successfully retrieve employee in getEmployeeById");
            log.trace("Employee retrieved:{}",employeeDTOAPIResponse.getData());
            return employeeDTOAPIResponse.getData();
        }
        catch(Exception e){
            log.error("Exception occured in getEmployeeById:",e);
            throw new RuntimeException(e);
        }
    }
}
