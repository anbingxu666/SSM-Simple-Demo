package com.an.curd.controller;

import com.an.curd.bean.Employee;
import com.an.curd.bean.Result;
import com.an.curd.service.EmployeeService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;


    //    使用json传递数据 实现平台无关性
    @RequestMapping("/emps")
    @ResponseBody
    public Result getLists(@RequestParam(value = "pn", defaultValue = "1") Integer pn) {
        PageHelper.startPage(pn, 8);
        List<Employee> allEmps = employeeService.getAllEmps();
        PageInfo pageInfo = new PageInfo(allEmps, 5);


        return Result.success().add("pageInfo",pageInfo);
    }
//    @RequestMapping("/emps")
//    public String getLists(@RequestParam(value ="pn",defaultValue = "1")Integer pn
//            , Model model){
//        PageHelper.startPage(pn,8);
//        List<Employee> allEmps = employeeService.getAllEmps();
//        PageInfo pageInfo = new PageInfo(allEmps,5);
//
//        model.addAttribute("pageInfo",pageInfo);
//        return "list";
//    }
}
