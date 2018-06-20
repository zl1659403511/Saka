package com.tao.framework.saka.autoconfig;

import com.tao.framework.saka.annotation.SakaService;
import com.tao.framework.saka.entity.MetaMethodArray;
import com.tao.framework.saka.annotation.SakaSubscribe;
import com.tao.framework.saka.entity.MetaMethod;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

import java.lang.reflect.Method;

/**
 * Saka客户端信息
 *
 * @author tao
 */
@Data
@Slf4j
@NoArgsConstructor
@ConditionalOnBean(annotation = SakaService.class)
@EnableConfigurationProperties(SakaProperties.class)
public class SakaInit {

  @Autowired ApplicationContext applicationContext;

  @Autowired SakaProperties sakaProperties;

  @Bean
  @ConditionalOnMissingBean(SakaClient.class)
  public SakaClient serverBuilderConfigurer() {
    // 获取包含Bean
    String[] beanDefinitionNames =
        applicationContext.getBeanNamesForAnnotation(SakaService.class);
    for (String name : beanDefinitionNames) {
      Object bean = applicationContext.getBean(name);
      Method[] methods1 = bean.getClass().getMethods();
      for (Method method : methods1) {
        // 检查注解信息
        SakaSubscribe methodAnnotation = method.getAnnotation(SakaSubscribe.class);
        if (methodAnnotation == null) {
          continue;
        }
        method.setAccessible(true);
        MetaMethod metaMethod = new MetaMethod(bean, method);
        MetaMethodArray.add(metaMethod);
      }
    }
    return new SakaClient();
  }
}
