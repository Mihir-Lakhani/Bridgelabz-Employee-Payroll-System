package com.bridgelabz.employeepayrollapp.controller;

import com.bridgelabz.employeepayrollapp.dto.EmployeePayrollDto;
import com.bridgelabz.employeepayrollapp.model.EmployeePayroll;
import com.bridgelabz.employeepayrollapp.service.EmployeePayrollService;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/employeepayrollservice")
public class EmployeePayrollController {
    private final EmployeePayrollService service;

    public EmployeePayrollController(EmployeePayrollService service) { this.service = service; }

    @GetMapping({"", "/"})
    public List<EmployeePayroll> list() { return service.list(); }

    @GetMapping("/get/{id}")
    public EmployeePayroll get(@PathVariable long id) { return service.get(id); }

    @PostMapping("/create")
    public ResponseEntity<EmployeePayroll> create(@Valid @RequestBody EmployeePayrollDto request) {
        return ResponseEntity.status(201).body(service.create(request));
    }

    @PutMapping("/update/{id}")
    public EmployeePayroll update(@PathVariable long id, @Valid @RequestBody EmployeePayrollDto request) {
        return service.update(id, request);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
