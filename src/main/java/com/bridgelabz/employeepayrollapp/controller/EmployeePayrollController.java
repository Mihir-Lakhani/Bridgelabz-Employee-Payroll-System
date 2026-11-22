package com.bridgelabz.employeepayrollapp.controller;

import com.bridgelabz.employeepayrollapp.dto.EmployeePayrollDto;
import com.bridgelabz.employeepayrollapp.model.EmployeePayroll;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/employeepayrollservice")
public class EmployeePayrollController {
    @GetMapping({"", "/"})
    public List<EmployeePayroll> list() { return List.of(); }

    @GetMapping("/get/{id}")
    public EmployeePayroll get(@PathVariable long id) { return new EmployeePayroll(id, "Example", 1000); }

    @PostMapping("/create")
    public ResponseEntity<EmployeePayroll> create(@RequestBody EmployeePayrollDto request) {
        return ResponseEntity.status(201)
                .body(new EmployeePayroll(1, request.getName(), request.getSalary()));
    }

    @PutMapping("/update/{id}")
    public EmployeePayroll update(@PathVariable long id, @RequestBody EmployeePayrollDto request) {
        return new EmployeePayroll(id, request.getName(), request.getSalary());
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable long id) {
        return ResponseEntity.noContent().build();
    }
}
