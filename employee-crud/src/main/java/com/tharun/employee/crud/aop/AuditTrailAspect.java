package com.tharun.employee.crud.aop;

import com.tharun.employee.crud.model.AuditLog;
import com.tharun.employee.crud.repository.AuditLogRepository;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Aspect
@Component
public class AuditTrailAspect {

    private final AuditLogRepository auditLogRepository;

    public AuditTrailAspect(AuditLogRepository auditLogRepository) {
        this.auditLogRepository = auditLogRepository;
    }

    // 🔹 Log CREATE
    @AfterReturning("execution(* com.tharun.employee.crud.service.EmployeeService.createEmployee(..))")
    public void logCreate(JoinPoint joinPoint) {
        saveLog("CREATE", "Employee");
    }

    // 🔹 Log UPDATE
    @AfterReturning("execution(* com.tharun.employee.crud.service.EmployeeService.updateEmployee(..))")
    public void logUpdate(JoinPoint joinPoint) {
        saveLog("UPDATE", "Employee");
    }

    // 🔹 Log PATCH
    @AfterReturning("execution(* com.tharun.employee.crud.service.EmployeeService.patchEmployee(..))")
    public void logPatch(JoinPoint joinPoint) {
        saveLog("PATCH", "Employee");
    }

    // 🔹 Security + Log DELETE
    @Before("execution(* com.tharun.employee.crud.service.EmployeeService.deleteEmployee(..))")
    public void checkAndLogDelete(JoinPoint joinPoint) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        if (auth == null || auth.getAuthorities().stream()
                .noneMatch(a -> a.getAuthority().equals("ADMIN"))) {
            throw new AccessDeniedException("Only ADMIN can delete employees");
        }

        saveLog("DELETE", "Employee");
    }

    private void saveLog(String action, String entity) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();

        AuditLog log = new AuditLog();
        log.setUsername(username);
        log.setAction(action);
        log.setEntity(entity);
        log.setTimestamp(LocalDateTime.now());

        auditLogRepository.save(log);
    }
}
