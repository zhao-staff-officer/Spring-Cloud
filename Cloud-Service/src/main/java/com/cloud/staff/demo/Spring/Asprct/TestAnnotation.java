package com.cloud.staff.demo.Spring.Asprct;

import java.lang.annotation.*;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface TestAnnotation {

    String name() default "";

    String descrption() default  "";
}
