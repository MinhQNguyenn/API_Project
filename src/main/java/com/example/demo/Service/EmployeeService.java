package com.example.demo.Service;

import com.example.demo.Model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService implements IGeneralService{
    @Autowired
    private IGeneralService employeeService;
    @Override
    public Iterable<Employee> findAll() {
        return employeeService.findAll();
    }

    @Override
    public Optional<Employee> findById(Long id) {
        return employeeService.findById(id);
    }

    @Override
    public Object save(Object o) {
        return null;
    }

    @Override
    public void remove(Long id) {
        employeeService.remove(id);
    }

    @Override
    public List<Employee> getAllEmployees() {
//        List<Employee> employees = new ArrayList<>();
//        employees.add(new Employee(Long.valueOf(123), "Nguyen", "Minh", "hn"));
//        return employees;
        return employeeService.getAllEmployees();
    }
}
