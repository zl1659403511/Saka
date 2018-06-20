package com.tao.framework.saka.autoconfig;


import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("saka")
@Getter
@Setter
public class SakaProperties {

  //开启Saka
  private boolean enable = true;

  //扫描路径
  private String[] scanpath;
}
