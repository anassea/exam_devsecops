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

    @Autowired
    private MessageSource messageSource;

    public Employee addEmployee(Employee employee, Locale locale) {
        if (repository.findByEmail(employee.getEmail()).isPresent()) {
            throw new IllegalArgumentException(
                    messageSource.getMessage("error.email.in.use", null, locale)
            );
        }
        return repository.save(employee);
    }

    public Employee getEmployee(Long id, Locale locale) {
        return repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException(
                        messageSource.getMessage("error.employee.not.found", null, locale)
                ));
    }

    public List<Employee> getAllEmployees() {
        return repository.findAll();
    }

    public Employee updateEmployee(Long id, Employee newEmployeeData, Locale locale) {
        Employee employee = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException(
                        messageSource.getMessage("error.employee.not.found", null, locale)
                ));
        employee.setName(newEmployeeData.getName());
        employee.setEmail(newEmployeeData.getEmail());
        return repository.save(employee);
    }

    public void deleteEmployee(Long id, Locale locale) {
        if (!repository.existsById(id)) {
            throw new IllegalArgumentException(
                    messageSource.getMessage("error.employee.not.found", null, locale)
            );
        }
        repository.deleteById(id);
    }

    public MessageSource getMessageSource() {
        return messageSource;
    }
}
