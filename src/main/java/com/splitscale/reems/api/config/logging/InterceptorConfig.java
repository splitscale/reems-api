package com.splitscale.reems.api.config.logging;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

@Configuration
public class InterceptorConfig extends WebMvcConfigurationSupport {

  private HandlerInterceptor interceptor;

  public InterceptorConfig(HandlerInterceptor interceptor) {
    this.interceptor = interceptor;
  }

  @Override
  public void addInterceptors(InterceptorRegistry registry) {
    InterceptorRegistration registration = registry.addInterceptor(interceptor);
    registration.addPathPatterns("/**");
  }

}
