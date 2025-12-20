package com.s2;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

//告诉 JVM：这个注解要在运行时保留（否则反射读不到）
@Retention(RetentionPolicy.RUNTIME)
//告诉编译器：只能用在方法上（不能标在类或字段上）
@Target(ElementType.METHOD)
// 告诉 IDE：这是一个「标记型注解」，没有参数
public @interface LogTime {
}
