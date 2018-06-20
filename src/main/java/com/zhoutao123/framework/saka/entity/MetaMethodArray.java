package com.zhoutao123.framework.saka.entity;

import java.util.ArrayList;
import java.util.List;

/**
 * 元方法集合
 *
 * @author zhoutao123
 */
public class MetaMethodArray {

  private static List<MetaMethod> metaMethods = null;

  public static synchronized void add(MetaMethod metaMethod) {
    if (metaMethods == null) {
      metaMethods = new ArrayList<MetaMethod>();
    }
    metaMethods.add(metaMethod);
  }

  /**
   * 当前方法集合的size
   *
   * @return
   */
  public static int size() {
    return metaMethods.size();
  }

  /**
   * 当前的方法集合
   *
   * @return
   */
  public static boolean isEmpty() {
    return size() == 0;
  }

  public static List<MetaMethod> getMetaMethods() {
    return metaMethods;
  }
}
