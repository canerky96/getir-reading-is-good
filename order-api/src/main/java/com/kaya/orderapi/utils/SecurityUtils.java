package com.kaya.orderapi.utils;

import org.springframework.security.core.context.SecurityContextHolder;

public final class SecurityUtils {

  public static String getUsername() {
    return SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
  }
}
