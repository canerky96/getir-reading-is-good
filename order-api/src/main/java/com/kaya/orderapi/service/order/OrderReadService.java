package com.kaya.orderapi.service.order;

import com.kaya.orderapi.entity.Order;
import com.kaya.orderapi.entity.QOrder;
import com.kaya.orderapi.entity.QOrderedBook;
import com.kaya.orderapi.exception.ErrorCode;
import com.kaya.orderapi.exception.OrderApiException;
import com.kaya.orderapi.repository.OrderRepository;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderReadService {

  private final OrderRepository orderRepository;
  private final JPAQueryFactory query;

  @Transactional(readOnly = true)
  public Order findById(Long id) {
    return orderRepository
        .findById(id)
        .orElseThrow(() -> new OrderApiException(ErrorCode.ORDER_NOT_FOUND));
  }

  @Transactional(readOnly = true)
  public Page<Order> findPageableByUsername(String username, Pageable pageable) {
    var orders = orderRepository.findAllByUsername(username, pageable);
    if (CollectionUtils.isEmpty(orders.getContent())) {
      throw new OrderApiException(ErrorCode.ORDERS_NOT_FOUND_WITH_USERNAME);
    }
    return orders;
  }

  @Transactional(readOnly = true)
  public List<Order> findByBetweenDates(Date startDate, Date endDate) {
    QOrder qOrder = QOrder.order;
    QOrderedBook qOrderedBook = QOrderedBook.orderedBook;
    var afterExpression = qOrder.createDate.after(startDate);
    var beforeExpression = qOrder.createDate.before(endDate);
    var whereCondition = afterExpression.and(beforeExpression);

    return query
        .selectFrom(qOrder)
        .innerJoin(qOrderedBook)
        .on(qOrder.eq(qOrderedBook.order))
        .where(whereCondition)
        .fetch();
  }
}
