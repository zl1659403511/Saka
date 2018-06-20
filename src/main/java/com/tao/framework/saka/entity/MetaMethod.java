package com.tao.framework.saka.entity;

import com.tao.framework.saka.annotation.SakaSubscribe;
import lombok.Data;

import java.lang.reflect.Method;

/**
 * 元数据
 *
 *
 * @author tao
 */
@Data
public class MetaMethod {

  private Object instance;

  private Method method;

  private int paramCount;

  private Class[] paramType;

  public MetaMethod(Object instance, Method method) {
    this.instance = instance;
    this.method = method;
    this.paramCount = method.getParameterCount();
    this.paramType = method.getParameterTypes();
  }

  /**
   * 方法是否允许访问
   *
   * @return
   */
  public boolean allowAccess() {
    return method.isAccessible();
  }

  /**
   * 是否打印日志
   *
   * @return
   */
  public boolean printLog() {
    SakaSubscribe subscribe = method.getAnnotation(SakaSubscribe.class);
    return subscribe == null ? false : subscribe.debug();
  }
}
