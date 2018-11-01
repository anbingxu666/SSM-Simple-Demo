package com.an.curd.controller;

import com.an.curd.bean.Employee;
import com.an.curd.bean.Result;
import com.an.curd.service.EmployeeService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.objenesis.instantiator.sun.MagicInstantiator;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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


//    增加员工
    @ResponseBody
    @RequestMapping(value = "/emp",method = RequestMethod.POST)
    public Result add(Employee e){
        employeeService.save(e);
        return Result.success().add("status","添加员工成功");
    }

////    删除员工
//    @ResponseBody
//    @RequestMapping(value = "/emp",method = RequestMethod.DELETE)
//    public Result delete(Employee e){
//
//    }
//
//    //更新员工
//    @ResponseBody
//    @RequestMapping(value = "/emp/{id}",method = RequestMethod.PUT)
//    public Result update(@PathVariable("id") Integer id){
//
//    }
//    //查询员工
//    @ResponseBody
//    @RequestMapping(value = "/emp/{id}",method = RequestMethod.GET)
//    public Result update(@PathVariable("id") Integer id){
//        employeeService.
//        return Result.success().add("employee",)
//    }
//

    @ResponseBody
    @RequestMapping(value = "/check",method = RequestMethod.GET)
    public Result check(@RequestParam("empName") String empName){
        boolean isValid = employeeService.checkEmpName(empName);
        if(isValid){
            return Result.success().add("valid",true);

        }else{
            return Result.fail().add("valid",false);
        }
    }
}
