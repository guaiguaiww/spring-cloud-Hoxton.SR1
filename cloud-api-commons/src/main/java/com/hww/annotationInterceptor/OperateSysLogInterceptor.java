package com.hww.annotationInterceptor;

import com.hww.annotation.OperateSysLog;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * @author heweiwei@hztianque.com
 * @date 2020/3/19-14:21
 * @Description:
 */
@Component
@Aspect
@Slf4j
public class OperateSysLogInterceptor {


    @Pointcut("execution(public * com.hww.controller.*.*(..))")
    public void serviceLogAspect() {
    }

    @Around(value = "serviceLogAspect() && @annotation(annotation)")
    public Object interceptOperateSysLog(ProceedingJoinPoint joinPoint, OperateSysLog annotation) throws Throwable {
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        String className = joinPoint.getTarget().getClass().getSimpleName();
        log.info("访问的类为:" + className);
        log.info("访问的方法的名称为：" + methodSignature.getName());
        log.info("访问的方法的返回类型为：" + methodSignature.getReturnType());
        String[] params = methodSignature.getParameterNames();// 获取参数名称
        Object[] args = joinPoint.getArgs();// 获取参数值
        if (null != params && null != args) {
            for (int i = 0; i < params.length; i++) {
                log.info("访问的参数为 " + params[i] + "参数值为：" + args[i]);
            }
        }

        Method targetMethod = methodSignature.getMethod();
        if (targetMethod.isAnnotationPresent(OperateSysLog.class)) {
            OperateSysLog operateSysLog = targetMethod.getAnnotation(OperateSysLog.class);
            log.info("moduleName:" + operateSysLog.moduleName());
            log.info("content:" + operateSysLog.content());
        }
        /*使用方法的参数获取目标方法上的注解的形式*/
       /* log.info("moduleName:" + annotation.moduleName());
        log.info("option:" + annotation.content());*/
        return joinPoint.proceed();
    }
}
