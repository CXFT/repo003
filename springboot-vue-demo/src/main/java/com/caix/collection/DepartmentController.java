package com.caix.collection;

import com.caix.model.Department;
import com.caix.service.DepartmentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import javax.annotation.Resource;

@Controller
@RequestMapping("department")
public class DepartmentController {
    @Resource
    private DepartmentService departmentService;

    @RequestMapping("/addDepartment")
    public ModelAndView add(){
        ModelAndView modelAndView = new ModelAndView("department/departmentInput");
        modelAndView.addObject("act" ,"1");
        return  modelAndView;
    }

    @RequestMapping("/addSaveDepartment")
    public ModelAndView addSave(Department department){
        ModelAndView modelAndView = new ModelAndView();
        if(departmentService.addDepartment(department)){
            modelAndView.addObject("msg","新增成功");
            return show();
        }else{
            modelAndView.addObject("msg","新增失败");
            modelAndView.addObject("department",department);
        }
        return  modelAndView;
    }

    @RequestMapping("/deleteDepartment")
    public ModelAndView delete(Integer[] ids){
        ModelAndView modelAndView = new ModelAndView();
        int count = 0;
        try {
            for(int i = 0;i<ids.length;i++){
                if(departmentService.deleteDepartment(ids[i])){
                    count++;
                }
            }
            modelAndView.addObject("msg",
                    "共有"+ids.length+"条数据，删除了"+count+"条数据");
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        return  show();
    }

    @RequestMapping("/updateDepartment")
    public ModelAndView update(String id){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("act","2");
        modelAndView.addObject("department",departmentService.getDepartmentById(Integer.parseInt(id)));
        modelAndView.setViewName("department/departmentInput");
        return  modelAndView;
    }
    @RequestMapping("/updateSaveDepartment")
    public ModelAndView updateSave(Department department){
        ModelAndView modelAndView = new ModelAndView();
        if(departmentService.updateDepartment(department)){
            modelAndView.addObject("msg","修改成功");
            return show();
        }else{
            modelAndView.addObject("department",department);
            modelAndView.setViewName("department/departmentInput");
        }
        return  modelAndView;
    }

    @RequestMapping("/showDepartment")
    public ModelAndView show(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("departments",departmentService.getDepartments());
        modelAndView.setViewName("department/departmentList");
        return  modelAndView;
    }

}
