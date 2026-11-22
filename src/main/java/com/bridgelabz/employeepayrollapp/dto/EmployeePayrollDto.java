package com.bridgelabz.employeepayrollapp.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class EmployeePayrollDto {
    @NotBlank(message = "Name is required")
    @Pattern(regexp = "^[A-Z][a-zA-Z]{2,}(?: [A-Za-z]+)*$", message = "Name must start with a capital letter and have at least 3 letters")
    private String name;
    @Min(value = 500, message = "Salary must be at least 500")
    private int salary;
}
