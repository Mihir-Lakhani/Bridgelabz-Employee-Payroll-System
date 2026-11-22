package com.bridgelabz.employeepayrollapp.service;

import com.bridgelabz.employeepayrollapp.dto.EmployeePayrollDto;
import com.bridgelabz.employeepayrollapp.model.EmployeePayroll;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class EmployeePayrollService {
    private final List<EmployeePayroll> employees = new ArrayList<>();
    private long nextId = 1;

    public synchronized List<EmployeePayroll> list() { return List.copyOf(employees); }

    public synchronized EmployeePayroll get(long id) {
        return employees.stream().filter(employee -> employee.getId() == id).findFirst()
                .orElseThrow(() -> new NoSuchElementException("Employee not found: " + id));
    }

    public synchronized EmployeePayroll create(EmployeePayrollDto request) {
        EmployeePayroll employee = new EmployeePayroll(nextId++, request.getName(), request.getSalary());
        employees.add(employee);
        log.info("Created employee id={}", employee.getId());
        return employee;
    }

    public synchronized EmployeePayroll update(long id, EmployeePayrollDto request) {
        EmployeePayroll employee = get(id);
        employee.setName(request.getName());
        employee.setSalary(request.getSalary());
        log.info("Updated employee id={}", id);
        return employee;
    }

    public synchronized void delete(long id) {
        EmployeePayroll employee = get(id);
        employees.remove(employee);
        log.info("Deleted employee id={}", id);
    }
}
