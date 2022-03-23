package com.kaya.orderapi.config;

import com.kaya.orderapi.utils.SecurityUtils;
import org.springframework.data.domain.AuditorAware;

import java.util.Objects;
import java.util.Optional;

public class AuditorAwareImpl implements AuditorAware<String> {

  @Override
  public Optional<String> getCurrentAuditor() {
    var username = SecurityUtils.getUsername();
    if (Objects.isNull(username)) {
      return Optional.of("SYSTEM");
    }
    return Optional.of(username);
  }
}
