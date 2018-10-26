package com.an.curd.service;

import com.an.curd.bean.Employee;
import com.an.curd.dao.EmployeeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {
    @Autowired
    private EmployeeMapper employeeMapper;
    public List<Employee> getAllEmps(){
        return employeeMapper.selectByExampleWithDept(null);
    }
}
