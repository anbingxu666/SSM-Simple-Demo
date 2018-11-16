package com.an.curd.service;

import com.an.curd.bean.Employee;
import com.an.curd.bean.EmployeeExample;
import com.an.curd.dao.EmployeeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {
    @Autowired
    private EmployeeMapper employeeMapper;

    public int save(Employee e) {
        int i = employeeMapper.insertSelective(e);
        return i;
    }


    public List<Employee> getAllEmps(){
        return employeeMapper.selectByExampleWithDept(null);
    }

    public boolean checkEmpName(String empName) {
        EmployeeExample employeeExample = new EmployeeExample();
        EmployeeExample.Criteria criteria = employeeExample.createCriteria();
        criteria.andEmpNameEqualTo(empName);
        long count = employeeMapper.countByExample(employeeExample);
        return count==0;
    }
    public Employee getOne(int id){
        Employee employee = employeeMapper.selectByPrimaryKey(id);
        return employee;
    }
    public int updateOne(Employee employee){
        int insert = employeeMapper.updateByPrimaryKeySelective(employee);
        return insert;

    }
}
