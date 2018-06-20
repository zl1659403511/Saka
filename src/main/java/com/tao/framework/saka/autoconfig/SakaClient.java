package com.tao.framework.saka.autoconfig;

import com.tao.framework.saka.entity.MetaMethodArray;
import com.tao.framework.saka.entity.MetaMethod;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Saka客户端的实现
 *
 * @author tao
 */
@Slf4j
public class SakaClient implements ISakaClient {

  @Async
  @Override
  public void send() throws InvocationTargetException, IllegalAccessException {
    send(null);
  }

  @Async
  @Override
  public void send(Object message) throws InvocationTargetException, IllegalAccessException {
    int count = 0;
    for (MetaMethod metaMethod : MetaMethodArray.getMetaMethods()) {
      Method method = metaMethod.getMethod();
      Object instance = metaMethod.getInstance();
      // 传递过来的参数是null的话,要求只通知无参的方法
      Object invoke = null;
      if (message == null && metaMethod.getParamCount() == 0) {
        method.invoke(instance);
        if (metaMethod.printLog()) {
          log.info("Saka ------> Send Empty data to {} successfully", method.getName());
        }
        count++;
      } else if (message != null
          && metaMethod.getParamCount() == 1
          && metaMethod.getParamType()[0].equals(message.getClass())) {
        metaMethod.getMethod().invoke(metaMethod.getInstance(), message);
        if (metaMethod.printLog()) {
          log.info("Saka ------> Send data to {} successfully", method.getName());
          log.info("Data ------> ", message);
        }
        count++;
      }
    }
    log.info("Saka ------>  Saka has successfully sent {} times data.", count);
  }
}
