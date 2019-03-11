package com.qihe.lib;


import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


/**
 * 只在源代码中存在，编译后没有在字节码中，所以最最终的运行时是没有影响的
 *
 */
@Target(ElementType.TYPE) //作用于类上
@Retention(RetentionPolicy.SOURCE)//在源码可见
public @interface MyAnnotation {
}
