package com.zhoutao123.framework.saka.autoconfig;

import java.lang.reflect.InvocationTargetException;

/**
 * Saka客户端接口
 *
 * @author zhoutao123
 */
public interface ISakaClient<T> {

  public void send() throws InvocationTargetException, IllegalAccessException;

  public void send(T message) throws InvocationTargetException, IllegalAccessException;
}
