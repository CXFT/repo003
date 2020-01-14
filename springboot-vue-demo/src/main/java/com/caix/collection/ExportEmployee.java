package com.caix.collection;

import com.caix.model.Employee;
import com.caix.service.EmployeeService;
import org.apache.poi.hssf.usermodel.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;

@Controller
@RequestMapping("Employee")
public class ExportEmployee {
    @Resource
    private EmployeeService employeeService;

    @RequestMapping("/export")
    public void exportEmployee(HttpServletResponse response) throws IOException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        //创建Excel工作薄对象
        HSSFWorkbook workbook = new HSSFWorkbook();
        //创建Excel工作表对象
        HSSFSheet sheet = workbook.createSheet("信息表");

        List<Employee> classmateList = employeeService.getEmployees();

        //设置要导出的文件的名字
        String fileName = "员工信息表"  + ".xls";
        //新增数据行，并且设置单元格数据

        int rowNum = 1;

        String[] headers = { "编号", "姓名","性别", "生日","电话号码","邮箱","所在部门","担任职务"};
        //headers表示excel表中第一行的表头

        HSSFRow row = sheet.createRow(0);
        //在excel表中添加表头

        for(int i=0;i<headers.length;i++){
            HSSFCell cell = row.createCell(i);
           // HSSFRichTextString text = new HSSFRichTextString(headers[i]);
            cell.setCellValue(headers[i]);
        }

        //在表中存放查询到的数据放入对应的列
        for (Employee employee : classmateList) {
            HSSFRow row1 = sheet.createRow(rowNum);
            row1.createCell(0).setCellValue(employee.getId());
            row1.createCell(1).setCellValue(employee.getName());
            if(employee.getGender()==1){
                row1.createCell(2).setCellValue("男");
            }else{
                row1.createCell(2).setCellValue("女");
            }
            row1.createCell(3).setCellValue(simpleDateFormat.format(employee.getDob()));
            row1.createCell(4).setCellValue(employee.getPhone());
            row1.createCell(5).setCellValue(employee.getEmail());
            row1.createCell(6).setCellValue(employee.getDepartment().getName());
            row1.createCell(7).setCellValue(employee.getRole().getName());
            rowNum++;
        }

        //.*（ 二进制流，不知道下载文件类型）
        response.setContentType("application/octet-stream");
        //解决中文乱码
        response.setHeader("Content-disposition", "attachment;filename=" + fileName);
        //response有个buffer，flushBuffer()会强行把Buffer的 内容写到客户端浏览器。
        response.flushBuffer();
        workbook.write(response.getOutputStream());
    }

    @RequestMapping("/import")
    @ResponseBody
    public String excelImport(@RequestParam(value="filename")MultipartFile file,HttpSession session){
        int result = 0;
        try {
            result = employeeService.addEmployee(file);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if(result > 0){
            return "excel文件数据导入成功！";
        }else{
            return "excel数据导入失败！";
        }
    }

}