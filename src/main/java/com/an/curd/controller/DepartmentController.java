package com.an.curd.controller;

import com.an.curd.bean.Department;
import com.an.curd.bean.Result;
import com.an.curd.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;


@Controller
public class DepartmentController {
    @Autowired
    private DepartmentService departmentService;
    @ResponseBody
    @RequestMapping("/depts")
    public Result getDepts(){

        List<Department> list = departmentService.getList();
        return Result.success().add("deptList",list);
    }

}
