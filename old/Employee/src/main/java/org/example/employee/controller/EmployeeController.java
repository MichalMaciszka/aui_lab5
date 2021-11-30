package org.example.employee.controller;


import org.example.company.service.CompanyService;
import org.example.employee.dto.CreateEmployeeRequest;
import org.example.employee.dto.GetEmployeeResponse;
import org.example.employee.dto.GetEmployeesResponse;
import org.example.employee.dto.UpdateEmployeeRequest;
import org.example.employee.entity.Employee;
import org.example.employee.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Optional;

@RestController
@RequestMapping("api/employees")
public class EmployeeController {
    private final EmployeeService employeeService;
    private final CompanyService companyService;

    @Autowired
    public EmployeeController(EmployeeService employeeService, CompanyService companyService) {
        this.employeeService = employeeService;
        this.companyService = companyService;
    }

    @GetMapping
    public ResponseEntity<GetEmployeesResponse> getEmployees() {
        return ResponseEntity.ok(GetEmployeesResponse.entityToDtoMapper().apply(employeeService.findAll()));
    }

    @GetMapping("{id}")
    public ResponseEntity<GetEmployeeResponse> getEmployee(@PathVariable("id") Integer id) {
        return employeeService.findById(id)
                .map(val -> ResponseEntity.ok(GetEmployeeResponse.entityToDtoMapper().apply(val)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Void> createEmployee(@RequestBody CreateEmployeeRequest request, UriComponentsBuilder builder) {
        Employee employee = CreateEmployeeRequest
                .dtoToEntityMapper(name -> companyService.find(name).orElseThrow(), () -> null)
                .apply(request);
        employee = employeeService.create(employee);
        return ResponseEntity.created(builder.pathSegment("api", "characters", "{id}")
                .buildAndExpand(employee.getId()).toUri()).build();
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable("id") Integer id) {
        Optional<Employee> employee = employeeService.findById(id);
        if (employee.isPresent()) {
            employeeService.delete(employee.get().getId());
            return ResponseEntity.accepted().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("{id}")
    public ResponseEntity<Void> updateEmployee(@RequestBody UpdateEmployeeRequest request, @PathVariable("id") Integer id) {
        Optional<Employee> employee = employeeService.findById(id);
        if (employee.isPresent()) {
            UpdateEmployeeRequest.dtoToEntityUpdater().apply(employee.get(), request);
            employeeService.update(employee.get());
            return ResponseEntity.accepted().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
