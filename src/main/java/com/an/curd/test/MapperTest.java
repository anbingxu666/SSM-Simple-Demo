package com.an.curd.test;

import com.an.curd.bean.Department;
import com.an.curd.bean.Employee;
import com.an.curd.dao.DepartmentMapper;
import com.an.curd.dao.EmployeeMapper;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;
import java.util.UUID;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class MapperTest {
    @Autowired
    private EmployeeMapper employeeMapper;
    @Autowired
    private DepartmentMapper departmentMapper;
    @Autowired
    private SqlSession sqlSession;
    @Test
    public void testCurd(){
        Employee employee = employeeMapper.selectByPrimaryKeyWithDept(2);
        System.out.println(employee);
        departmentMapper.insert(new Department(null,"卫生部"));
        departmentMapper.insert(new Department(null,"产品部"));

        employeeMapper.insert(new Employee("niu",0,"23908423@qq.com",2));
        employeeMapper.insert(new Employee("huang",1,"777723@123.com",2));
        EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);
        for (int i = 0; i < 200; i++) {
            String s = UUID.randomUUID().toString().substring(0, 6);
            mapper.insert(new Employee(s,2,s+"@123.com",2));

        }
        System.out.println("success");


    }
}
