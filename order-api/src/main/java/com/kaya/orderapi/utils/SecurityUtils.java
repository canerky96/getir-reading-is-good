package com.kaya.orderapi.utils;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class SecurityUtils {

  public String getUsername() {
    return SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
  }
}
