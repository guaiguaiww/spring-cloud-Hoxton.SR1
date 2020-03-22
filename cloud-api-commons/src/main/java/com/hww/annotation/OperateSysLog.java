package com.hww.annotation;

import java.lang.annotation.*;

/**
 * @author heweiwei@hztianque.com
 * @date 2020/3/19-14:17
 * @Description:
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Documented
public @interface OperateSysLog {

    String moduleName() default "";

    String content() default "";

}
