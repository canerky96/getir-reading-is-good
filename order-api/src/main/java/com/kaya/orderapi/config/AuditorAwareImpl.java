package com.kaya.orderapi.config;

import com.kaya.orderapi.utils.SecurityUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.AuditorAware;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.Optional;

@Component("auditorProvider")
@RequiredArgsConstructor
public class AuditorAwareImpl implements AuditorAware<String> {

  private final SecurityUtils securityUtils;

  @Override
  public Optional<String> getCurrentAuditor() {
    var username = securityUtils.getUsername();
    if (Objects.isNull(username)) {
      return Optional.of("SYSTEM");
    }
    return Optional.of(username);
  }
}
