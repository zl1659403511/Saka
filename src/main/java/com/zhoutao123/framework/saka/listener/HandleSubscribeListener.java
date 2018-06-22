package com.zhoutao123.framework.saka.listener;

import com.zhoutao123.framework.saka.entity.MetaMethod;

/**
 * when handle subscribe complete,saka will execute hanleSubscribeLister
 *
 * @author tao
 */
public interface HandleSubscribeListener {

  /**
   * execute subscribe is success,callback this methods.
   *
   * @param metaMethod execute subscribe's message
   * @param resultObject execute Subscribe's result
   */
  void onSuccess(MetaMethod metaMethod, Object resultObject);

  /**
   * exeute subcribe happen exception @{e},callback onError
   *
   * @param t
   * @return
   */
  boolean onError(Throwable t);
}
