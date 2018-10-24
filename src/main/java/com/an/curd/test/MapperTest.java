package com.an.curd.test;

import com.an.curd.bean.Employee;
import com.an.curd.dao.EmployeeMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class MapperTest {
    @Autowired
    private EmployeeMapper employeeMapper;
    @Test
    public void testCurd(){
        Employee employee = employeeMapper.selectByPrimaryKeyWithDept(2);
        System.out.println(employee);
    }
}
