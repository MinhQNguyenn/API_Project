package com.example.demo.Dao;


import com.example.demo.Model.Employee;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository {
    @Query(value = "CALL `PersonalFinance`.get_all_employee();", nativeQuery = true)
    List<Employee> getAllEmployees();
}
