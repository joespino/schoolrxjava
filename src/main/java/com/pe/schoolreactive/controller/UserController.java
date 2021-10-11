package com.pe.schoolreactive.controller;

import com.pe.schoolreactive.domain.User;
import com.pe.schoolreactive.service.UserService;
import com.pe.schoolreactive.service.dto.MessageBody;
import com.pe.schoolreactive.service.dto.UserDto;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Maybe;
import io.reactivex.rxjava3.core.Single;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE, value = "/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService service;

    @GetMapping
    public Maybe<ResponseEntity<Flowable<User>>> getUser() {
        return Maybe.just(ResponseEntity.ok(service.getAllUser()));
    }

    @PostMapping
    public Single<ResponseEntity<Single<User>>> createUser(@RequestBody UserDto userDto) {
        return Single.just(ResponseEntity.ok(service.saveUser(userDto)));
    }

    @PutMapping
    public Single<ResponseEntity<Single<User>>> updateUser(@RequestBody UserDto userDto) {
        return Single.just(ResponseEntity.ok(service.updateUser(userDto)));
    }

    @DeleteMapping("/{id}")
    public Single<ResponseEntity<Maybe<MessageBody>>> deleteUser(@PathVariable Integer id) {
        return Single.just(ResponseEntity.ok(service.deleteUser(id)));
    }
}
