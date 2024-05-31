package com.example.demo.spring.repository;

import com.example.demo.spring.entity.*;
import org.springframework.data.jpa.repository.*;

import java.util.*;

public interface EmployeeRepository extends JpaRepository<Employee, UUID> {

    List<Employee> findByName(String name);

    Optional<Employee> findById(UUID id);
}
