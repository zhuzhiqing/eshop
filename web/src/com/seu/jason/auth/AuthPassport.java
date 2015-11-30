package com.seu.jason.auth;

import java.lang.annotation.*;

/**
 * Created by ToZhu on 2015/10/29.
 */
@Documented
@Inherited
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface AuthPassport {

    SecrityType secrityType() default SecrityType.PRIVATE;

    boolean validate() default true;

    static  public enum SecrityType{            //资源安全等级，个人私有，公共可见的
        PRIVATE,
        PUBLIC
    }
}

