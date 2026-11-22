package com.bridgelabz.employeepayrollapp.service;

import com.bridgelabz.employeepayrollapp.dto.EmployeePayrollDto;
import com.bridgelabz.employeepayrollapp.model.EmployeePayroll;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class EmployeePayrollService {
    public List<EmployeePayroll> list() { return List.of(); }
    public EmployeePayroll get(long id) { return new EmployeePayroll(id, "Example", 1000); }
    public EmployeePayroll create(EmployeePayrollDto request) {
        return new EmployeePayroll(1, request.getName(), request.getSalary());
    }
    public EmployeePayroll update(long id, EmployeePayrollDto request) {
        return new EmployeePayroll(id, request.getName(), request.getSalary());
    }
    public void delete(long id) { }
}
