package com.sky.aspect;

import com.sky.annotation.AutoFill;
import com.sky.constant.AutoFillConstant;
import com.sky.context.BaseContext;
import com.sky.enumeration.OperationType;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.time.LocalDateTime;


//自定义切面，实现公共段自动填充处理逻辑
@Aspect
@Component
@Slf4j
public class AutoFillAspect{

    /**
     * 切入点
     */
    @Pointcut("execution(* com.sky.mapper.*.*(..)) && @annotation(com.sky.annotation.AutoFill)")
    public void autoFillPointCut(){}

    /**
     * 前置通知，在通知中进行公共字段的赋值
     **/
    @Before("autoFillPointCut()")
    public void autoFill(JoinPoint joinPoint) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        log.info("开始进行公共字段的填充.......");
        //获取到当前被拦截的方法上的数据库操作类型


        MethodSignature signature = (MethodSignature) joinPoint.getSignature();//方法签名对象，向下转型Signature——>MethodSignature
        AutoFill autoFill = signature.getMethod().getAnnotation(AutoFill.class);//获得方法上的注解对象
        OperationType operationType = autoFill.value();//获得数据库操作类型
        //获取到当前被拦截的方法的参数--实体对象
        Object[] args = joinPoint.getArgs();
        if(args==null ||args.length==0 ){
            return;
        }
        Object entity=args[0];
        //准备赋值的数据
        LocalDateTime now = LocalDateTime.now();
        Long currentId = BaseContext.getCurrentId();



        if(operationType==OperationType.INSERT){
            Method setCreateTime = entity.getClass().getDeclaredMethod(AutoFillConstant.SET_CREATE_TIME, LocalDateTime.class);
            Method setCreateUser = entity.getClass().getDeclaredMethod(AutoFillConstant.SET_CREATE_USER, Long.class);
            Method setUpdateTime = entity.getClass().getDeclaredMethod(AutoFillConstant.SET_UPDATE_TIME, LocalDateTime.class);
            Method setUpdateUser = entity.getClass().getDeclaredMethod(AutoFillConstant.SET_UPDATE_USER, Long.class);
            //根据当前的操作类型，为对应的属性通过反射来赋值
            setCreateTime.invoke(entity,now);
            setCreateUser.invoke(entity,currentId);
            setUpdateUser.invoke(entity,currentId);
            setUpdateTime.invoke(entity,now);

        } else if (operationType==OperationType.UPDATE) {
            Method setUpdateTime = entity.getClass().getDeclaredMethod(AutoFillConstant.SET_UPDATE_TIME, LocalDateTime.class);
            Method setUpdateUser = entity.getClass().getDeclaredMethod(AutoFillConstant.SET_UPDATE_USER, Long.class);
            setUpdateUser.invoke(entity,currentId);
            setUpdateTime.invoke(entity,now);
        }
    }

}