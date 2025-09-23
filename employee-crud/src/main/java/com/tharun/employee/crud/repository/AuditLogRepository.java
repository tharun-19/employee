package com.tharun.employee.crud.repository;

import com.tharun.employee.crud.model.AuditLog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuditLogRepository extends JpaRepository<AuditLog, Long> {
}
