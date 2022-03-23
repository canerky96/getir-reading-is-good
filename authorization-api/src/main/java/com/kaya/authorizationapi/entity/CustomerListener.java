package com.kaya.authorizationapi.entity;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.AfterSaveEvent;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class CustomerListener extends AbstractMongoEventListener<Customer> {

  @Override
  public void onAfterSave(AfterSaveEvent<Customer> event) {
    log.info("Customer created, username:{} customer:{}", event.getSource().getUsername(), event.getSource());
  }
}
