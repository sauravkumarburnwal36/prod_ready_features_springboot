package com.example.prod_ready_features.prod_ready_features.dto;


import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class EmployeeDTO {
    private Long id;

    private String name;

    private String email;

    private Integer age;

    private LocalDate date;

    private Boolean isActive;

    private String role;

    private Double salary;
}
