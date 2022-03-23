package com.kaya.orderapi.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Version;

@Entity
@Table(name = "g_book")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Book extends BaseEntity {

  @Column(name = "name")
  private String name;

  @Column(name = "writer")
  private String writer;

  @Column(name = "price", nullable = false)
  private Long price;

  @Column(name = "stock", nullable = false)
  private Integer stock;

  @Version private Integer version;

  @Override
  public String toString() {
    return "Book{" +
            "createdBy='" + createdBy + '\'' +
            ", createDate=" + createDate +
            ", lastModifiedBy='" + lastModifiedBy + '\'' +
            ", updateDate=" + updateDate +
            ", name='" + name + '\'' +
            ", writer='" + writer + '\'' +
            ", price=" + price +
            ", stock=" + stock +
            ", version=" + version +
            '}';
  }
}
