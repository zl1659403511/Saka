package com.zhoutao123.framework.saka.annotation;

import org.springframework.stereotype.Service;

import java.lang.annotation.*;

/**
 * 订阅时间处理注解
 *
 * @author zhoutao123
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Service
public @interface SakaService {
}
