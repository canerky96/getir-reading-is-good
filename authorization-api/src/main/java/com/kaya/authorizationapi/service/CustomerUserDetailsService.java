package com.kaya.authorizationapi.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AccountStatusUserDetailsChecker;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class CustomerUserDetailsService implements UserDetailsService {

  private final CustomerService customerService;
  private final AccountStatusUserDetailsChecker accountStatusUserDetailsChecker;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    var user = customerService.findByUsername(username);
    this.accountStatusUserDetailsChecker.check(user);
    log.info("User found with username: {}, and user: {}", username, user.toString());
    return user;
  }
}
