package com.tharun.employee.crud.service;

import com.tharun.employee.crud.exception.ResourceNotFoundException;
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

    public Employee getEmployeeById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found with id " + id));
    }

    public Employee createEmployee(Employee employee) {
        return repository.save(employee);
    }


    public Employee patchEmployee(Long id, Employee employee) {
        return repository.findById(id).map(existing -> {
            if (employee.getFirstName() != null) existing.setFirstName(employee.getFirstName());
            if (employee.getLastName() != null) existing.setLastName(employee.getLastName());
            if (employee.getEmail() != null) existing.setEmail(employee.getEmail());
            if (employee.getDepartment() != null) existing.setDepartment(employee.getDepartment());
            return repository.save(existing);
        }).orElseThrow(() -> new ResourceNotFoundException("Employee not found with id " + id));
    }



    public Employee updateEmployee(Long id, Employee updatedEmployee) {
        return repository.findById(id)
                .map(employee -> {
                    employee.setFirstName(updatedEmployee.getFirstName());
                    employee.setLastName(updatedEmployee.getLastName());
                    employee.setEmail(updatedEmployee.getEmail());
                    employee.setDepartment(updatedEmployee.getDepartment());
                    return repository.save(employee);
                })
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found with id " + id));
    }

    public void deleteEmployee(Long id) {
        Employee employee = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found with id " + id));
        repository.delete(employee);
    }

}
