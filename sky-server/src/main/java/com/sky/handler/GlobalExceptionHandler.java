package com.sky.handler;

import com.alibaba.druid.sql.ast.expr.SQLIntegerExpr;
import com.sky.constant.MessageConstant;
import com.sky.exception.BaseException;
import com.sky.result.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.SQLIntegrityConstraintViolationException;

/**
 * 全局异常处理器，处理项目中抛出的业务异常
 * @ExceptionHandler 是Spring框架中的一个注解，用于处理控制器中抛出的异常
 *
 * @RestControllerAdvice
 * 是Spring框架中的一个组合注解，它结合了 @ControllerAdvice 和 @ResponseBody 的功能。
 * @ControllerAdvice 用于定义全局的控制器相关的注解，如 @ExceptionHandler、@InitBinder 和
 * @ModelAttribute，
 *
 * 而 @ResponseBody 用于指示方法的返回值应该直接写入HTTP响应体，而不是解析为视图名称。
 */
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    /**
     * 捕获业务异常
     * @param ex
     * @return
     */
    @ExceptionHandler
    public Result exceptionHandler(BaseException ex){
        log.error("异常信息：{}", ex.getMessage());
        return Result.error(ex.getMessage());
    }


    @ExceptionHandler
    public Result exceptionHandler(SQLIntegrityConstraintViolationException ex){
        // Duplicate entry "zhangsan "for key 'employee.idx_username'
        String message = ex.getMessage();
        if (message.contains("Duplicate entry")){
            String[] split = message.split(" ");
            String username = split[2];
            String msg = username + MessageConstant.ALREADY_EXISTS;
            return Result.error(msg);
        }else {
            return Result.error(MessageConstant.UNKNOWN_ERROR);
        }
    }

    

}
