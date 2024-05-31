package com.example.demo.spring.component.service;

import com.example.demo.spring.entity.*;
import com.example.demo.spring.entity.enumeration.*;
import com.example.demo.spring.exception.*;
import com.example.demo.spring.repository.*;
import lombok.*;
import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Service
@RequiredArgsConstructor
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    public Employee createEmployee(Employee employee) {

        return employeeRepository
                .save(new Employee(UUID.randomUUID(), employee.getName(), employee.getAddress(), employee.getTelephone(), employee.getStatus()));
    }

    public List<Employee> getAllEmployees() {

        List<Employee> employees = new ArrayList<Employee>();
        employeeRepository.findAll().forEach(employees::add);
        return employees;
    }

    public Employee getByIdOrThrown(UUID id) {

        Optional<Employee> employeeData = employeeRepository.findById(id);
        if (employeeData.isPresent()) {
            return employeeData.get();
        } else {
            throw new NotFoundException("Пользователь не найден");
        }
    }

    public List<Employee> getByNameOrThrown(String name) {

        List<Employee> employeeData = employeeRepository.findByName(name);
        if (!employeeData.isEmpty())
            return employeeData;
        else
            throw new NotFoundException("Пользователь не найден");
    }

    public Employee changeToInactiveOrThrow(@RequestParam("employee_id") UUID employeeid) {

        Optional<Employee> employee = employeeRepository.findById(employeeid);
        if (employee.isPresent()) {
            employee.get().setStatus(StatusEmployee.INACTIVE);
            employeeRepository.save(employee.get());
            return employee.get();
        } else {
            throw new NotFoundException("Пользователь не найден");
        }
    }

    public Employee changeToActiveOrThrow(UUID employeeid) {

        Optional<Employee> employee = employeeRepository.findById(employeeid);

        if (employee.isPresent()) {
            employee.get().setStatus(StatusEmployee.ACTIVE);
            employeeRepository.save(employee.get());
            return employee.get();
        } else {
            throw new NotFoundException("Пользователь не найден");
        }
    }

    public Employee changeNameOrThrow(UUID employeeid, String name) {

        Optional<Employee> employee = employeeRepository.findById(employeeid);
        if (employee.isPresent()) {
            employee.get().setName(name);
            employeeRepository.save(employee.get());
            return employee.get();
        } else {
            throw new NotFoundException("Пользователь не найден");
        }
    }

    public Employee changeAddressOrThrow(UUID employeeid, String address) {

        Optional<Employee> employee = employeeRepository.findById(employeeid);
        if (employee.isPresent()) {
            employee.get().setAddress(address);
            employeeRepository.save(employee.get());
            return employee.get();
        } else {
            throw new NotFoundException("Пользователь не найден");
        }
    }

    public Employee changeTelephoneOrThrow(UUID employeeid, Integer telephone) {

        Optional<Employee> employee = employeeRepository.findById(employeeid);
        if (employee.isPresent()) {
            employee.get().setTelephone(telephone);
            employeeRepository.save(employee.get());
            return employee.get();
        } else {
            throw new NotFoundException("Пользователь не найден");
        }
    }

    public Employee deleteTelephoneOrThrow(@RequestParam("employee_id") UUID employeeid) {

        Optional<Employee> employee = employeeRepository.findById(employeeid);
        if (employee.isPresent()) {
            employee.get().setTelephone(null);
            employeeRepository.save(employee.get());
            return employee.get();
        } else {
            throw new NotFoundException("Пользователь не найден");
        }
    }
}
