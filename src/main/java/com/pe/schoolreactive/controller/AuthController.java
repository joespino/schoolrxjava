package com.pe.schoolreactive.controller;

import com.pe.schoolreactive.service.dto.JwtBody;
import com.pe.schoolreactive.service.dto.UserDto;
import io.reactivex.rxjava3.core.Maybe;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(consumes = MediaType.APPLICATION_JSON_VALUE, value = "/auth")
@RequiredArgsConstructor
public class AuthController {

    @PostMapping("/signin")
    public Maybe<ResponseEntity<JwtBody>> signin(@RequestBody UserDto userDto) {
        return null;
    }
}
