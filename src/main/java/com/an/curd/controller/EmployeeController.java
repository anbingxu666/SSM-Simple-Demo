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
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    public Result add(@Valid Employee e, BindingResult result){
        if(result.hasErrors()){
            Map<String, Object> map = new HashMap<>();
            List<FieldError> errors = result.getFieldErrors();
            for (FieldError fieldError : errors) {
                System.out.println("错误的字段名："+fieldError.getField());
                System.out.println("错误信息："+fieldError.getDefaultMessage());
                map.put(fieldError.getField(), fieldError.getDefaultMessage());
            }
            return Result.fail().add("errorFields", map);

        }else{
            employeeService.save(e);
            return Result.success().add("status","添加员工成功");
        }
    }

////    删除员工
//    @ResponseBody
//    @RequestMapping(value = "/emp",method = RequestMethod.DELETE)
//    public Result delete(Employee e){
//
//    }
//
//    //更新员工
    @ResponseBody
    @RequestMapping(value = "/emp/{empId}",method = RequestMethod.PUT)
    public Result updateEmployee(Employee employee){
        System.out.println(employee);
        employeeService.updateOne(employee);
        return Result.success();
    }
//    //查询员工
    @ResponseBody
    @RequestMapping(value = "/emp/{id}",method = RequestMethod.GET)
    public Result selectEmployee(@PathVariable("id") Integer id){
        Employee one = employeeService.getOne(id);
        return Result.success().add("employee",one);
    }
//

    @ResponseBody
    @RequestMapping(value = "/check",method = RequestMethod.POST)
    public Result check(@RequestParam("empName") String empName){
        //先使用正则的方式校验用户名
        String regex = "(^[a-zA-Z0-9_-]{6,16}$)|(^[\\u2E80-\\u9FFF]{2,5})";
        boolean matches = empName.matches(regex);
        if(!matches){
            return Result.fail().add("valid","用户名可以是2-5位中文或者6-16位英文和数字的组合");
        }
        boolean isValid = employeeService.checkEmpName(empName);
        if(isValid){
            return Result.success().add("valid",true);

        }else{
            return Result.fail().add("valid",false);
        }
    }
}
