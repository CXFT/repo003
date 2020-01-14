package com.caix.collection;

import com.caix.model.Employee;
import com.caix.service.DepartmentService;
import com.caix.service.EmployeeService;
import com.caix.service.RoleService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import javax.annotation.Resource;

@Controller
@RequestMapping("employee")
public class EmployeeController {

    @Resource
    private EmployeeService employeeService;
    @Resource
    private DepartmentService departmentService;
    @Resource
    private RoleService roleService;

    @RequestMapping("/addEmployee")
    public ModelAndView add(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("roles",roleService.getRoles());
        modelAndView.addObject("departments",departmentService.getDepartments());
        modelAndView.addObject("act","1");
        modelAndView.setViewName("employee/empInput");
        return  modelAndView;
    }

    @RequestMapping("/addSaveEmployee")
    public ModelAndView addSave(@RequestParam("departmentId") Integer departmentId,
                                @RequestParam("roleId") Integer roleId, Employee employee){
        ModelAndView modelAndView = new ModelAndView();
        employee.setDepartment(departmentService.getDepartmentById(departmentId));
        employee.setRole(roleService.getRoleById(roleId));
        if(employeeService.addEmployee(employee)){
            return show();
        }else{
            modelAndView.addObject("employee",employee);
            modelAndView.setViewName("employee/empInput");
        }
        return  modelAndView;

    }

    @RequestMapping("/deleteEmployee")
    public ModelAndView delete(Integer[] empIds){
       ModelAndView modelAndView = new ModelAndView();
        int count = 0;
        try {
            for(int i = 0;i<empIds.length;i++){
                if(employeeService.deleteEmployee(empIds[i])){
                    count++;
                }
            }
            modelAndView.addObject("msg",
                    "共有"+empIds.length+"条数据，删除了"+count+"条数据");
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
       return  show();
    }

    @RequestMapping("/updateEmployee")
    public ModelAndView update(){
        ModelAndView modelAndView = new ModelAndView();
        return  modelAndView;

    }
    @RequestMapping("/updateSaveEmployee")
    public ModelAndView updateSave(){
        ModelAndView modelAndView = new ModelAndView();
        return  modelAndView;

    }
    @RequestMapping("/showEmployee")
    public ModelAndView show(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("employees",employeeService.getEmployees());
        modelAndView.setViewName("employee/empList");
        return modelAndView;
    }

    @ResponseBody
    @RequestMapping("/checkName")
    public String check(@RequestParam("name")String name){
        String msg = "";
        if(name == null || name == ""){
            msg+="用户名不得为空";
        }else if(employeeService.getEmployeeByName(name) != null){
            msg+="用户名已存在";
        }else{
            msg+="Ok";
        }
        return  msg;
    }

    @ResponseBody
    @RequestMapping("/checkPas")
    public String checkPassword(@RequestParam("password")String password){
        String msg = "";
        if(password == null || password == ""){
            msg+="密码不得为空";
        }else{
            msg+="ok";
        }
        return  msg;
    }

}
