package com.tharun.employee.crud.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.tharun.employee.crud.model.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}
