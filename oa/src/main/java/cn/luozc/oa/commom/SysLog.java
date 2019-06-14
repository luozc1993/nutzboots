package cn.luozc.oa.commom;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME) // 必须带这个,不然读取不到的
@Target({ElementType.METHOD}) // aop,一般指方法
@Documented // 记录到javadoc
public @interface SysLog {

    /**
     * 方法说明
     */
    String value() default "";
}
