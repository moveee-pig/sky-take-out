package com.sky.mapper;

import com.github.pagehelper.Page;
import com.sky.annotation.AutoFill;
import com.sky.dto.EmployeePageQueryDTO;
import com.sky.entity.Employee;
import com.sky.enumeration.OperationType;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface EmployeeMapper {

    /**
     * 根据用户名查询员工
     * @param username
     * @return
     */
    @Select("select * from employee where username = #{username}")
    Employee getByUsername(String username);
    
    /**
     * @description:  插入员工数据
     * @author: Yibing Yang 
     * @date: ${DATE} ${TIME}
     * @param: [employee]
     * @return: void
     **/

    @Insert("insert into sky_take_out.employee (name, username, password, phone, sex, id_number," +
            " create_time, update_time, create_user, update_user,status) values (#{name},#{username},#{password},#{phone},#{sex}," +
            "#{idNumber},#{createTime},#{updateTime},#{createUser},#{updateUser},#{status})")
    @AutoFill(value = OperationType.INSERT)
    void insert(Employee employee);
    /**
     * @description:分页查询，sql进行动态查询
     * @author: Yibing Yang
     * @date: ${DATE} ${TIME}
     * @param: [employeePageQueryDTO]
     * @return: Page<Employee>
     **/
    Page<Employee> pageQuery(EmployeePageQueryDTO employeePageQueryDTO);

    @AutoFill(value = OperationType.UPDATE)
    void update(Employee employee);

    /**
     * @description:根据id查询员工
     * @author: Yibing Yang
     * @date: ${DATE} ${TIME}
     * @param: [id]
     * @return: com.sky.result.Result<com.sky.entity.Employee>
     **/
    @Select("select * from employee where id =#{id}")
    Employee getById(Long id);
}
