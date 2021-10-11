package com.pe.schoolreactive.service.impl;

import com.pe.schoolreactive.domain.User;
import com.pe.schoolreactive.repository.UserRepository;
import com.pe.schoolreactive.service.UserService;
import com.pe.schoolreactive.service.dto.MessageBody;
import com.pe.schoolreactive.service.dto.UserDto;
import com.pe.schoolreactive.util.UtilConverter;
import com.pe.schoolreactive.util.UtilMethods;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Maybe;
import io.reactivex.rxjava3.core.Single;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Log4j2
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository repository;

    @Override
    public Flowable<User> getAllUser() {
        return Flowable.fromIterable(repository.findAll());
    }

    @Override
    public Single<User> saveUser(UserDto user) {
        return Single.just(repository.count())
                .map(x -> user.toBuilder()
                        .id((int) UtilMethods.getIncrementIdentifier.applyAsLong(x))
                        .build())
                .map(x -> repository.save(UtilConverter.convertDomain.apply(x, User.builder().build())));
    }

    @Override
    public Single<User> updateUser(UserDto user) {

        return Maybe.just(repository.findById(user.getId()))
                .filter(Optional::isPresent)
                .switchIfEmpty(Single.error(new Exception("El usuario no existe")))
                .map(Optional::get)
                .map(x -> repository.save(UtilConverter.convertDomain.apply(user, x)));
    }

    @Override
    public Maybe<MessageBody> deleteUser(Integer id) {
        return Maybe.just(repository.findById(id))
                .filter(Optional::isPresent)
                .switchIfEmpty(Single.error(new Exception("El usuario no existe")))
                .map(Optional::get)
                .map(x -> repository.updateStatus(id, false))
                .filter(Boolean.TRUE::equals)
                .map(x -> MessageBody
                        .builder()
                        .id(id)
                        .message("Se ha eliminado el usuario correctamente")
                        .build());
    }
}
