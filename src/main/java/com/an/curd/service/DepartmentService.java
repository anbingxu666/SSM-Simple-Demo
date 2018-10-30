package com.an.curd.service;

import com.an.curd.bean.Department;
import com.an.curd.bean.Result;
import com.an.curd.dao.DepartmentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentService {
    @Autowired
    private DepartmentMapper departmentMapper;
    public List<Department> getList(){
        List<Department> departments = departmentMapper.selectByExample(null);
        return  departments;
    }
}
