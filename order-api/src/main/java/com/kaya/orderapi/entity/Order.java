package com.kaya.orderapi.entity;

import com.kaya.orderapi.entity.enums.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

@Entity
@Table(name = "g_order", indexes = @Index(columnList = "username"))
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString
public class Order extends BaseEntity {

  @Column(name = "username", nullable = false)
  private String username;

  @Enumerated(EnumType.STRING)
  private OrderStatus status;

  @OneToMany(mappedBy="order")
  private List<OrderedBook> books;
}
