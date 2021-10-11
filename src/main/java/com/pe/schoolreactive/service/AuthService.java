package com.pe.schoolreactive.service;

import com.pe.schoolreactive.service.dto.JwtBody;
import com.pe.schoolreactive.service.dto.UserDto;

public interface AuthService {
    JwtBody signin(UserDto userDto);
}
