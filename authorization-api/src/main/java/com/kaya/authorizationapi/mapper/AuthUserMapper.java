package com.kaya.authorizationapi.mapper;

import com.kaya.authorizationapi.dto.response.UserCreateResponseDTO;
import com.kaya.authorizationapi.entity.AuthUser;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AuthUserMapper {
  UserCreateResponseDTO map(AuthUser authUser);
}
