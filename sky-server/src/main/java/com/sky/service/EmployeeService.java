package com.sky.service;

import com.sky.dto.EmployeeDTO;
import com.sky.dto.EmployeeLoginDTO;
import com.sky.dto.EmployeePageQueryDTO;
import com.sky.entity.Employee;
import com.sky.result.PageResult;

public interface EmployeeService {

    /**
     * 员工登录
     * @param employeeLoginDTO
     * @return
     */
    Employee login(EmployeeLoginDTO employeeLoginDTO);

    /**
     * @description:新增员工
     * @author: Yibing Yang 
     * @date:
     * @param: employeeDTO
     * @return: 
     **/
    void save(EmployeeDTO employeeDTO);

    /**
     * @description:分页查询
     * @author: Yibing Yang
     * @date: ${DATE} ${TIME}
     * @param: [employeePageQueryDTO]
     * @return: com.sky.result.PageResult
     **/
    PageResult pageQuery(EmployeePageQueryDTO employeePageQueryDTO);
    /**
     * @description:启用禁用员工账号
     * @author: Yibing Yang
     * @date: ${DATE} ${TIME}
     * @param: [status, id]
     * @return: void
     **/
    void start0rstop(Integer status, Long id);
    /**
     * @description:根据id查询员工
     * @author: Yibing Yang
     * @date: ${DATE} ${TIME}
     * @param: [id]
     * @return: com.sky.result.Result<com.sky.entity.Employee>
     **/
    Employee getById(Long id);
    /**
     * @description:编辑员工信息
     * @author: Yibing Yang
     * @date: ${DATE} ${TIME}
     * @param: [employeeDTO]
     * @return: com.sky.result.Result
     **/
    void update(EmployeeDTO employeeDTO);

}
