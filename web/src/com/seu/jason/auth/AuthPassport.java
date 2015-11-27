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
    boolean validate() default true;
}