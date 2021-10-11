package com.pe.schoolreactive.util;

import com.pe.schoolreactive.domain.User;
import com.pe.schoolreactive.service.dto.UserDto;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.util.ObjectUtils;

import java.util.function.BiFunction;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UtilConverter {

    public static final BiFunction<UserDto, User, User> convertDomain = (userDto, user) ->
        User.builder()
                .iduser(ObjectUtils.isEmpty(user.getIduser()) ? userDto.getId() : user.getIduser())
                .name(ObjectUtils.isEmpty(user.getName()) ? userDto.getName() : user.getName())
                .email(ObjectUtils.isEmpty(user.getEmail()) ? userDto.getEmail() : user.getEmail())
                .phone(ObjectUtils.isEmpty(user.getPhone()) ? userDto.getPhone() : user.getPhone())
                .active(Constants.ACTIVE)
                .build();
}
