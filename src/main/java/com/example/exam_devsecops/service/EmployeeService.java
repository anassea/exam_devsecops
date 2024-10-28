package com.example.exam_devsecops.service;

import com.example.exam_devsecops.entity.Employee;
import com.example.exam_devsecops.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Locale;
@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository repository;

    public Employee addEmployee(Employee employee) {
        if (repository.findByEmail(employee.getEmail()).isPresent()) {
            throw new IllegalArgumentException("Email is already in use.");
        }
        return repository.save(employee);
    }

    public Employee getEmployee(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Employee not found."));
    }

    public List<Employee> getAllEmployees() {
        return repository.findAll();
    }

    public Employee updateEmployee(Long id, Employee newEmployeeData) {
        Employee employee = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Employee not found."));
        employee.setName(newEmployeeData.getName());
        employee.setEmail(newEmployeeData.getEmail());
        return repository.save(employee);
    }

    public void deleteEmployee(Long id) {
        if (!repository.existsById(id)) {
            throw new IllegalArgumentException("Employee not found.");
        }
        repository.deleteById(id);
    }
}