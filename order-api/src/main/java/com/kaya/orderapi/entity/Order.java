package com.kaya.orderapi.entity;

import com.kaya.orderapi.entity.enums.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.Table;
import java.util.List;

@Entity
@Table(name = "t_order", indexes = @Index(columnList = "username"))
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Order extends BaseEntity {

  @Column(name = "username", nullable = false)
  private String username;

  @Enumerated(EnumType.STRING)
  private OrderStatus status;

  @ElementCollection(fetch = FetchType.EAGER)
  @CollectionTable(name = "order_book", joinColumns = @JoinColumn(name = "order_id"))
  @Column(name = "book_id")
  private List<Long> books;
}
