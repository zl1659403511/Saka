package com.zhoutao123.framework.saka.autoconfig;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("saka")
@Getter
@Setter
public class SakaProperties {

  /** make saka open default value is open */
  private boolean enable = true;

  /** scan class path */
  private String[] scanPath;

  /** Open Subscribe's Order execute,default value is close */
  private boolean sequenceExecute = false;
}
