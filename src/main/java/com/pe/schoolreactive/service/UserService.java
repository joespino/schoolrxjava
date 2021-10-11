package com.pe.schoolreactive.service;

import com.pe.schoolreactive.domain.User;
import com.pe.schoolreactive.service.dto.MessageBody;
import com.pe.schoolreactive.service.dto.UserDto;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Maybe;
import io.reactivex.rxjava3.core.Single;

public interface UserService {

    Flowable<User> getAllUser();
    Single<User> saveUser(UserDto user);
    Single<User> updateUser(UserDto user);
    Maybe<MessageBody> deleteUser(Integer id);
}
