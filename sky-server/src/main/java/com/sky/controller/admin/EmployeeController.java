package com.sky.controller.admin;

import com.sky.constant.JwtClaimsConstant;
import com.sky.dto.EmployeeDTO;
import com.sky.dto.EmployeeLoginDTO;
import com.sky.dto.EmployeePageQueryDTO;
import com.sky.entity.Employee;
import com.sky.properties.JwtProperties;
import com.sky.result.PageResult;
import com.sky.result.Result;
import com.sky.service.EmployeeService;
import com.sky.utils.JwtUtil;
import com.sky.vo.EmployeeLoginVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * 员工管理
 */

/*
    @RestController:
    这是一个Spring MVC的注解，用于标记一个类为RESTful Web服务的控制器。
    与@Controller类似，但@RestController会默认所有的方法返回的结果都绑定到响应体上，
    即返回的数据会自动转换为JSON或XML等格式，而不需要在每个方法上添加@ResponseBody注解。

    @RequestMapping("/admin/employee"):
    这是一个定义URL映射的注解。
    它会将该类下的所有请求路径都映射到/admin/employee这个前缀下。
    例如，如果该类中有一个方法被@GetMapping("/list")注解，
    那么该方法的完整请求路径就是/admin/employee/list。

    @Slf4j:
    这是Lombok库提供的一个注解，用于自动在类中生成一个SLF4J的日志对象。
    使用这个注解后，你可以直接在类中使用log.info(), log.debug(), log.error()等方法进行日志记录，
    而无需手动创建和配置日志对象。

    @Api(tags = "员工相关接口（对类的说明）"):
    这是Swagger框架的一个注解，用于为RESTful API生成文档。
    tags属性为这个控制器类提供一个标签，这个标签通常用于描述这个控制器的主要功能或所属的模块。
    在生成的Swagger文档中，这个标签会作为这个控制器类下所有API的一个分类或描述。
 **/
@RestController
@RequestMapping("/admin/employee")
@Slf4j
@Api(tags = "员工相关接口（对类的说明）")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private JwtProperties jwtProperties;

    /**
     * 登录
     *
     * @param employeeLoginDTO
     * @return
     */
    @PostMapping("/login")
    @ApiOperation(value = "员工登录")
    public Result<EmployeeLoginVO> login(@RequestBody EmployeeLoginDTO employeeLoginDTO) {
        log.info("员工登录：{}", employeeLoginDTO);

        Employee employee = employeeService.login(employeeLoginDTO);

        //登录成功后，生成jwt令牌
        Map<String, Object> claims = new HashMap<>();
        claims.put(JwtClaimsConstant.EMP_ID, employee.getId());
        String token = JwtUtil.createJWT(
                jwtProperties.getAdminSecretKey(),
                jwtProperties.getAdminTtl(),
                claims);

        EmployeeLoginVO employeeLoginVO = EmployeeLoginVO.builder()
                .id(employee.getId())
                .userName(employee.getUsername())
                .name(employee.getName())
                .token(token)
                .build();

        return Result.success(employeeLoginVO);
    }

    @PostMapping
    @ApiOperation("新增员工")
    public Result save(@RequestBody EmployeeDTO employeeDTO) {
        log.info("新增员工");
        employeeService.save(employeeDTO);
        System.out.println("当前线程ID："+ Thread.currentThread().getId());
        return Result.success();
    }
    @GetMapping("/page")
    @ApiOperation("员工分月查询")
    public Result<PageResult> page(EmployeePageQueryDTO employeePageQueryDTO){
        log.info("员工分页查询,参数为{}",employeePageQueryDTO);
        PageResult pageResult = employeeService.pageQuery(employeePageQueryDTO);
        return Result.success(pageResult);
    }


    @PostMapping("/status/{status}")
    @ApiOperation("启用禁用员工账号")
    public Result startOrstop(@PathVariable Integer status, Long id) {
        log.info("启用禁用员工账号");
        employeeService.start0rstop(status,id);
        return Result.success();
    }

    /**
     * @description:根据id查询员工
     * @author: Yibing Yang
     * @date: ${DATE} ${TIME}
     * @param: [id]
     * @return: com.sky.result.Result<com.sky.entity.Employee>
     **/
    @GetMapping("/{id}")
    @ApiOperation("根据ID查询")
    public Result<Employee> getById(@PathVariable Long id){
        Employee employee = employeeService.getById(id);
        return Result.success(employee);
    }

    /**
     * @description:编辑员工信息
     * @author: Yibing Yang
     * @date: ${DATE} ${TIME}
     * @param: [employeeDTO]
     * @return: com.sky.result.Result
     **/
    @PutMapping
    @ApiOperation("编辑员工信息")
    public Result update(@RequestBody EmployeeDTO employeeDTO){
        log.info("编辑员工信息:{}",employeeDTO);
        employeeService.update(employeeDTO);
        return Result.success();
    }


    /**
     * 退出
     *
     * @return
     */
    @PostMapping("/logout")
    @ApiOperation("员工退出")
    public Result<String> logout() {
        return Result.success();
    }

}
