package com.metaverse.workflow.employee.repository;

import com.metaverse.workflow.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository  extends JpaRepository<Employee,Long> {
}
