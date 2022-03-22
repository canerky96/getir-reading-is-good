package com.kaya.orderapi.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import java.util.Date;

@MappedSuperclass
@Getter
@Setter
public class BaseEntity {

  @Id @GeneratedValue private Long id;

  @Column(name = "create_date", nullable = false)
  private Date createDate;

  @Column(name = "update_date")
  private Date updateDate;

  @PrePersist
  void onCreate() {
    this.createDate = new Date();
  }

  @PreUpdate
  void onUpdate() {
    this.updateDate = new Date();
  }
}
