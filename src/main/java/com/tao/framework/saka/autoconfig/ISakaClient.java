package com.tao.framework.saka.autoconfig;

import java.lang.reflect.InvocationTargetException;

/**
 * Saka客户端接口
 *
 * @author tao
 */
public interface ISakaClient<T> {

  public void send() throws InvocationTargetException, IllegalAccessException;

  public void send(T message) throws InvocationTargetException, IllegalAccessException;
}
