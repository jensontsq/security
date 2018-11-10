


package com.jenson.security.validater;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;
//表示实在那个地方起作用
@Target({ElementType.METHOD,ElementType.FIELD})
//运行时的注解
@Retention(RetentionPolicy.RUNTIME)
//表示用于注解,validatedBy  注解逻辑在哪个类
@Constraint(validatedBy=TestConstarint.class)
public @interface TestValidater {
	
   String message();
 
   Class<?>[] group() default{};
 
   Class<? extends Payload>[] payload() default {};
}
