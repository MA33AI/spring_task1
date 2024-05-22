package com.example.demo.spring.components;

import com.example.demo.spring.entitys.Employee;
import com.example.demo.spring.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.parser.Entity;
import java.util.*;

//@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequiredArgsConstructor
public class PracticeRestController {

    private Map<Integer, Employee> employeeMap = new HashMap<>();

    @Autowired
    EmployeeRepository employeeRepository;

    // Получение всех сотрудников
    @GetMapping("/getEmployees")
    public ResponseEntity<List<Employee>> getAllEmployees(@RequestParam("name") String name) {

        try {
            List<Employee> employees = new ArrayList<Employee>();

            if (name == null)
                employeeRepository.findAll().forEach(employees::add);
            else
                employeeRepository.findByName(name).forEach(employees::add);

            if (employees.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(employees, HttpStatus.OK);


        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/getEmployeesId")
    public ResponseEntity<Employee> getEmployeeById(@RequestParam("id") Integer id) {

        try {
            Optional<Employee> employeeData = employeeRepository.findById(id);
            if (employeeData.isPresent()) {
                return new ResponseEntity<>(employeeData.get(), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    // Создание записи работника
    @PostMapping("/createEmployee")
    public ResponseEntity<Employee> createEmployee(@RequestBody Employee employee) {

        try {
            Employee _employee =
                    employeeRepository
                            .save(new Employee(employee.getId(), employee.getName(), employee.getAddress(), employee.getTelephone(), employee.getStatus()));

            employeeMap.put(employee.getId(), _employee);
            System.out.println("\nСотрудник " + employeeMap.get(employee.getId()) + " создан");
            System.out.println("Размер массива списка сотрудников: " + employeeMap.size());

            return new ResponseEntity<>(_employee, HttpStatus.CREATED);

        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    // Перенос сотрудника в архив
    @PatchMapping("/changeToInactive")
    public ResponseEntity<Employee> changeToInactiveEmployee(@RequestParam("employee_id") Integer employeeid) {
        try {
            System.out.println("\nСотрудник " + employeeMap.get(employeeid) + " больше не активен");
            employeeMap.get(employeeid).setStatus(StatusEmployee.INACTIVE);
            System.out.println("Новые данные сотрудника: " + employeeMap.get(employeeid));

            Optional<Employee> employee = employeeRepository.findById(employeeid);
            if(employee.isPresent()){
                employee.get().setStatus(StatusEmployee.INACTIVE);
                employeeRepository.save(employee.get());
                return new ResponseEntity<>(employee.get(), HttpStatus.OK);
            }
            else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }

        } catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Удаление сотрудника из архива
    @PatchMapping("/changeToActive")
    public ResponseEntity<Employee> changeToActiveEmployee(@RequestParam("employee_id") Integer employeeid) {

        try{
            System.out.println("\nСотрудник " + employeeMap.get(employeeid) + " удален из архива и снова активен");
            employeeMap.get(employeeid).setStatus(StatusEmployee.ACTIVE);
            System.out.println("Новые данные сотрудника: " + employeeMap.get(employeeid));
            Optional<Employee> employee = employeeRepository.findById(employeeid);

            if(employee.isPresent()){
                employee.get().setStatus(StatusEmployee.ACTIVE);
                employeeRepository.save(employee.get());
                return new ResponseEntity<>(employee.get(), HttpStatus.OK);
            }
            else{
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Переименование работника
    @PatchMapping("/updateName")
    public ResponseEntity<Employee> updateName(@RequestParam("employee_id") Integer employeeid, @RequestParam("name") String name) {

        try{
            System.out.println("\nСотрудник " + employeeMap.get(employeeid) + " переименован");
            employeeMap.get(employeeid).setName(name);
            System.out.println("Новые данные сотрудника: " + employeeMap.get(employeeid));

            Optional<Employee> employee = employeeRepository.findById(employeeid);
            if(employee.isPresent()){
                employee.get().setName(name);
                employeeRepository.save(employee.get());
                return new ResponseEntity<>(employee.get(), HttpStatus.OK);
            }
            else{
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Смена адреса работника
    @PatchMapping("/changeAddress")
    public ResponseEntity<Employee> changeAddress(@RequestParam("employee_id") Integer employeeid, @RequestParam("address") String address) {

        try{
            System.out.println("\nАдрес сотрудника " + employeeMap.get(employeeid) + " изменен");
            employeeMap.get(employeeid).setAddress(address);
            System.out.println("Новые данные сотрудника: " + employeeMap.get(employeeid));

            Optional<Employee> employee = employeeRepository.findById(employeeid);
            if(employee.isPresent()){
                employee.get().setAddress(address);
                employeeRepository.save(employee.get());
                return new ResponseEntity<>(employee.get(), HttpStatus.OK);
            }
            else{
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Добавление телефона работника
    @PatchMapping("/changeTelephone")
    public ResponseEntity<Employee> changeTelephone(@RequestParam("employee_id") Integer employeeid, @RequestParam("telephone") Integer telephone) {

        try{
            System.out.println("\nТелефон сотрудника " + employeeMap.get(employeeid) + " изменен");
            employeeMap.get(employeeid).setTelephone(telephone);
            System.out.println("Новые данные сотрудника: " + employeeMap.get(employeeid));

            Optional<Employee> employee = employeeRepository.findById(employeeid);
            if(employee.isPresent()){
                employee.get().setTelephone(telephone);
                employeeRepository.save(employee.get());
                return new ResponseEntity<>(employee.get(), HttpStatus.OK);
            }
            else{
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    // Удаление телефона работника
    @DeleteMapping("/deleteTelephone")
    public ResponseEntity<Employee> deleteTelephone(@RequestParam("employee_id") Integer employeeid) {

        try{
            System.out.println("\nТелефон сотрудника " + employeeMap.get(employeeid) + " удален");
            employeeMap.get(employeeid).setTelephone(null);
            System.out.println("Новые данные сотрудника: " + employeeMap.get(employeeid));

            Optional<Employee> employee = employeeRepository.findById(employeeid);
            if(employee.isPresent()){
                employee.get().setTelephone(null);
                employeeRepository.save(employee.get());
                return new ResponseEntity<>(employee.get(), HttpStatus.OK);
            }
            else{
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}