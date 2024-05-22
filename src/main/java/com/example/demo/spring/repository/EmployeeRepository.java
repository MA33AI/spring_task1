package com.example.demo.spring.repository;

import com.example.demo.spring.components.StatusEmployee;
import com.example.demo.spring.entitys.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

    //List<Employee> findByActive(StatusEmployee statusEmployee);
    List<Employee> findByName(String name);
}
