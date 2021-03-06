package com.kaya.orderapi.mapper;

import com.kaya.orderapi.dto.response.OrderCreateResponseDTO;
import com.kaya.orderapi.dto.response.OrderResponseDTO;
import com.kaya.orderapi.entity.Order;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(
    componentModel = "spring",
    uses = {OrderedBookMapper.class})
public interface OrderMapper {

  OrderCreateResponseDTO mapToCreateResponse(Order order);

  OrderResponseDTO mapToResponse(Order order);
}
