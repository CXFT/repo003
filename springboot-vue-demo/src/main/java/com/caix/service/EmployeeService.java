package com.caix.service;

import com.caix.dao.EmployeeDao;
import com.caix.model.Department;
import com.caix.model.Employee;
import com.caix.model.Role;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import javax.annotation.Resource;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class EmployeeService {
    @Resource
    private EmployeeDao employeeDao;
    @Resource
    private DepartmentService departmentService;
    @Resource
    private RoleService roleService;

    public boolean addEmployee(Employee employee) {
       return  employeeDao.addEmployee(employee) == 1;
    }

    public boolean deleteEmployee(int id) {
        return employeeDao.deleteEmployee(id) == 1;
    }

    public boolean updateEmployee(Employee employee) {
        return employeeDao.updateEmployee(employee) == 1;
    }

    public Employee getEmployeeById(int id) {
        return employeeDao.getEmployeeById(id);
    }

    public List<Employee> getEmployees() {
        return employeeDao.getEmployees();
    }

    public int addEmployee(MultipartFile file) throws Exception{

        int result = 0;
//		存放excel表中所有employee用户
        List<Employee> employeeList = new ArrayList<>();
        /**
         *
         * 判断文件版本
         */
        String fileName = file.getOriginalFilename();
        String suffix = fileName.substring(fileName.lastIndexOf(".")+1);
        InputStream ins = file.getInputStream();
        Workbook wb = null;

        if(suffix.equals("xlsx")){
            wb = new XSSFWorkbook(ins);
        }else{
            wb = new HSSFWorkbook(ins);
        }
        /**
         * 获取excel表单
         */
        Sheet sheet = wb.getSheetAt(0);
        /**
         * line = 1 :从表的第二行开始获取记录
         *
         */
        if(null != sheet){
            for(int line = 1; line <= sheet.getLastRowNum();line++){
                Employee employee = new Employee();
                Row row = sheet.getRow(line);
                if(null == row){
                    continue;
                }
                for (Row employeeRow : sheet) {
                    int indexCell = 0;
                    for (Cell cell : employeeRow) {
                        //读取数据前设置单元格类型
                        cell.setCellType(CellType.STRING);
                        indexCell ++;
                    }
                }

                /**
                 * 判断单元格类型是否为文本类型
                 */
               /* if(1 != row.getCell(0).getCellType()){
                    throw new MyException("单元格类型不是文本类型！");
                }*/

                /**
                 * 获取第二个单元格的内容
                 */
                String name = row.getCell(1).getStringCellValue();
                employee.setName(name);

                /**
                 * 获取第三个单元格的内容
                 */
                String password = row.getCell(2).getStringCellValue();
                employee.setPassword(password);

                /**
                 * 获取第四个单元格的内容
                 */
                String gender = row.getCell(3).getStringCellValue();
                if(gender.equals("男")){
                    employee.setGender(1);
                }else{
                    employee.setGender(0);
                }
                /**
                 * 获取第五个单元格的内容
                 */
                String dob = row.getCell(4).getStringCellValue();
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
                Date birthday = simpleDateFormat.parse(dob);
                employee.setDob(birthday);
                /**
                 * 获取第六个单元格的内容
                 */
                String phone = row.getCell(5).getStringCellValue();
                employee.setPhone(phone);
                /**
                 * 获取第七个单元格的内容
                 */
                String email = row.getCell(6).getStringCellValue();
                employee.setEmail(email);
                /**
                 * 获取第八个单元格的内容
                 */
                String departmentName = row.getCell(7).getStringCellValue();
                Department department = departmentService.getDepartmentByName(departmentName);
                employee.setDepartment(department);

                /**
                 * 获取第九个单元格的内容
                 */
                String roleName = row.getCell(8).getStringCellValue();
                Role role = roleService.getRoleByName(roleName);
                employee.setRole(role);
                /**
                 * 将该用户装到集合中
                */
                employeeList.add(employee);
            }

            for(Employee employeeInfo:employeeList){
                /**
                 * 判断数据库表中是否存在用户记录，若存在，则更新，不存在，则保存记录
                 */
                String phone = employeeInfo.getPhone();
                Employee employee = employeeDao.getEmployeeByPhone(phone);
                if(employee == null){
                    employeeDao.addEmployee(employeeInfo);
                    result = 2;
                }else{
                    employeeDao.updateEmployee(employeeInfo);
                    result = 1;
                }
            }
        }
        return result;
    }


    public Employee getEmployeeByName(String name) {
        return employeeDao.getEmployeeByName(name);
    }
}
