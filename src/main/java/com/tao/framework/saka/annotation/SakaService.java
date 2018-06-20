package com.tao.framework.saka.annotation;

import org.springframework.stereotype.Service;

import java.lang.annotation.*;

/**
 * 订阅时间处理注解
 *
 * @author tao
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Service
public @interface SakaService {
}
