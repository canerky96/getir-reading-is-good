package com.kaya.orderapi.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "g_order_book")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class OrderedBook extends BaseEntity {

  @Column(name = "name")
  private String name;

  @Column(name = "writer")
  private String writer;

  @Column(name = "price", nullable = false)
  private Long price;

  @ManyToOne
  @JoinColumn(name = "order_id", nullable = false)
  private Order order;
}
