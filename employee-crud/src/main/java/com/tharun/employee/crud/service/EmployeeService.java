package com.tharun.employee.crud.service;

import com.tharun.employee.crud.model.Employee;
import com.tharun.employee.crud.repository.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {

    private final EmployeeRepository repository;

    public EmployeeService(EmployeeRepository repository) {
        this.repository = repository;
    }

    public List<Employee> getAllEmployees() {
        return repository.findAll();
    }

    public Optional<Employee> getEmployeeById(Long id) {
        return repository.findById(id);
    }

    public Employee createEmployee(Employee employee) {
        return repository.save(employee);
    }

    public Employee updateEmployee(Long id, Employee employee) {
        return repository.findById(id).map(existing -> {
            existing.setFirstName(employee.getFirstName());
            existing.setLastName(employee.getLastName());
            existing.setEmail(employee.getEmail());
            existing.setDepartment(employee.getDepartment());
            return repository.save(existing);
        }).orElseThrow(() -> new RuntimeException("Employee not found with id " + id));
    }

    public Employee patchEmployee(Long id, Employee employee) {
        return repository.findById(id).map(existing -> {
            if (employee.getFirstName() != null) existing.setFirstName(employee.getFirstName());
            if (employee.getLastName() != null) existing.setLastName(employee.getLastName());
            if (employee.getEmail() != null) existing.setEmail(employee.getEmail());
            if (employee.getDepartment() != null) existing.setDepartment(employee.getDepartment());
            return repository.save(existing);
        }).orElseThrow(() -> new RuntimeException("Employee not found with id " + id));
    }

    public void deleteEmployee(Long id) {
        repository.deleteById(id);
    }
}
