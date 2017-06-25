package com.jd.annotation;

import java.lang.annotation.*;

/**
 * 注解也叫元数据
 *
 * @Documented表明这个注解应该被javadoc工具记录，默认情况下,javadoc是不包括注解的
 * @Retention表明这个注解的生命周期
 * SOURCE:注解只保留在源文件中
 * CLASS:注解保留在class文件中，在加载到JVM虚拟机时丢弃
 * RUNTIME:注解保留在程序运行期间,此时可以通过反射获得定义在某类上的所有注解
 * @Target表明该注解可以被声明在哪些元素之前
 * TYPE:声明在类前
 * FIELD:声明在类的字段前
 * METHOD:声明在类的方法前
 * PARAMETER:声明在方法的参数前
 * CONSTRUCTOR:声明在构造方法前
 * LOCAL_VARIABLE:局部变量声明
 * ANNOTATON_TYPE:注释类型声明
 * PACKAGE:包声明
 *
 * Created by chenminghe on 2017/6/25.
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
public @interface ScanIgnore {
}
