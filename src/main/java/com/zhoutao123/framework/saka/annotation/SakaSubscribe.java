package com.zhoutao123.framework.saka.annotation;

import java.lang.annotation.*;

/**
 * 订阅时间处理注解
 *
 * @author zhoutao123
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface SakaSubscribe {
  //调试模式
  boolean debug() default false;
}
