package com.kaya.authorizationapi.mapper;

import com.kaya.authorizationapi.dto.response.CustomerCreateResponseDTO;
import com.kaya.authorizationapi.entity.Customer;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CustomerMapper {
  CustomerCreateResponseDTO map(Customer customer);
}
