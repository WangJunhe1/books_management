package com.bookManage.aspect;

/**
 * @author :Wjh
 * @since :2023/11/29 17:38
 */

import com.bookManage.annotation.AutoFill;
import com.bookManage.constant.AutoFillConstant;
import com.bookManage.enumeration.OperationType;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.time.LocalDateTime;

/**
 * 实现自动填充切面类
 */
@Aspect
@Component
@Slf4j
public class AutoFillAspect {
    /**
     * 切入点
     */
    @Pointcut("execution(* com.bookManage.mapper..*.*(..)) && @annotation(com.bookManage.annotation.AutoFill)")
    public void autoFillPointCut(){
    }

    /**
     * 前置通知(实体对象作为第一个参数）
     * @param jp
     */
    @Before("autoFillPointCut()")
    public void autoFillBefore(JoinPoint jp){
        log.info("自动填充前置通知");

        MethodSignature signature = (MethodSignature) jp.getSignature();
        AutoFill autoFill = signature.getMethod().getAnnotation(AutoFill.class);
        OperationType operationType = autoFill.value();

        Object[] args = jp.getArgs();

        if(args == null || args.length == 0){
            return ;
        }

        Object object = args[0];
        LocalDateTime now = LocalDateTime.now();

        if(operationType == OperationType.INSERT){
            try {
                Method setCreateTime = object.getClass().getMethod(AutoFillConstant.SET_CREATE_TIME, LocalDateTime.class);
                Method setUpdateTime = object.getClass().getMethod(AutoFillConstant.SET_UPDATE_TIME, LocalDateTime.class);
                //为对象属性赋值
                setCreateTime.invoke(object, now);
                setUpdateTime.invoke(object, now);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }else if(operationType == OperationType.UPDATE){
            //更新
            Method setUpdateTime = null;
            try {
                setUpdateTime = object.getClass().getMethod(AutoFillConstant.SET_UPDATE_TIME, LocalDateTime.class);
                //为对象属性赋值
                setUpdateTime.invoke(object, now);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }
}
