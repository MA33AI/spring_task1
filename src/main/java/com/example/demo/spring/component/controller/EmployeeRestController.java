package com.example.demo.spring.component.controller;

import com.example.demo.spring.component.service.*;
import com.example.demo.spring.entity.*;
import lombok.*;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("api/employee")
@RequiredArgsConstructor
public class EmployeeRestController {

    private final EmployeeService employeeService;

    @PostMapping("/create")
    public Employee createEmployee(@RequestBody Employee employee) {

        return employeeService.createEmployee(employee);
    }

    @GetMapping("/getAllEmployees")
    public List<Employee> getAllEmployees() {

        return employeeService.getAllEmployees();
    }

    @GetMapping("/getById")
    public Employee getById(@RequestParam("id") UUID id) throws Exception {

        return employeeService.getByIdOrThrown(id);
    }

    @GetMapping("/getByName")
    public List<Employee> getByName(@RequestParam("name") String name) throws Exception {

        return employeeService.getByNameOrThrown(name);
    }

    @PatchMapping("/changeToInactive")
    public Employee changeToInactive(@RequestParam("employee_id") UUID employeeid) {

        return employeeService.changeToInactiveOrThrow(employeeid);
    }

    @PatchMapping("/changeToActive")
    public Employee changeToActive(@RequestParam("employee_id") UUID employeeid) {

        return employeeService.changeToActiveOrThrow(employeeid);
    }


    @PatchMapping("/changeName")
    public Employee changeName(@RequestParam("employee_id") UUID employeeid, @RequestParam("name") String name) {

        return employeeService.changeNameOrThrow(employeeid, name);
    }

    @PatchMapping("/changeAddress")
    public Employee changeAddress(@RequestParam("employee_id") UUID employeeid, @RequestParam("address") String address) {

        return employeeService.changeAddressOrThrow(employeeid, address);
    }

    @PatchMapping("/changeTelephone")
    public Employee changeTelephone(@RequestParam("employee_id") UUID employeeid, @RequestParam("telephone") Integer telephone) {

        return employeeService.changeTelephoneOrThrow(employeeid, telephone);
    }

    @DeleteMapping("/deleteTelephone")
    public Employee deleteTelephone(@RequestParam("employee_id") UUID employeeid) {

        return employeeService.deleteTelephoneOrThrow(employeeid);
    }
}