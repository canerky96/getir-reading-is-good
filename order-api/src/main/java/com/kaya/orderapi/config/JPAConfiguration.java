package com.kaya.orderapi.config;

import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import javax.persistence.EntityManager;

@Configuration
@EnableJpaAuditing(auditorAwareRef = "auditorProvider")
public class JPAConfiguration {

  @Bean
  public JPAQueryFactory jpaQueryFactory(EntityManager entityManager) {
    return new JPAQueryFactory(entityManager);
  }

  @Bean
  public AuditorAware<String> auditorProvider() {
    return new AuditorAwareImpl();
  }
}
