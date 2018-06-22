package com.zhoutao123.framework.saka.annotation;

import com.zhoutao123.framework.saka.constance.OrderConstance;

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

  /**
   * <p>execute method order: 'zero' express frist execute and 'ten' express last execute,default is 5</p>
   *
   * @return
   */
  int order() default OrderConstance.ORDER_NORMALE;

}
