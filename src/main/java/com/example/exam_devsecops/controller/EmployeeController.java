package com.example.exam_devsecops.controller;

import com.example.exam_devsecops.entity.Employee;
import com.example.exam_devsecops.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @PostMapping
    public ResponseEntity<String> addEmployee(@RequestBody Employee employee) {
        employeeService.addEmployee(employee, LocaleContextHolder.getLocale());
        return ResponseEntity.ok(
                employeeService.getMessageSource().getMessage("message.employee.added", null, LocaleContextHolder.getLocale())
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<Employee> getEmployee(@PathVariable Long id) {
        return ResponseEntity.ok(employeeService.getEmployee(id, LocaleContextHolder.getLocale()));
    }

    @GetMapping
    public ResponseEntity<List<Employee>> getAllEmployees() {
        return ResponseEntity.ok(employeeService.getAllEmployees());
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateEmployee(@PathVariable Long id, @RequestBody Employee employee) {
        employeeService.updateEmployee(id, employee, LocaleContextHolder.getLocale());
        return ResponseEntity.ok(
                employeeService.getMessageSource().getMessage("message.employee.updated", null, LocaleContextHolder.getLocale())
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteEmployee(@PathVariable Long id) {
        employeeService.deleteEmployee(id, LocaleContextHolder.getLocale());
        return ResponseEntity.ok(
                employeeService.getMessageSource().getMessage("message.employee.deleted", null, LocaleContextHolder.getLocale())
        );
    }
}
