package com.academy.billing.service;

import com.academy.billing.exception.UserNotFoundException;
import com.academy.billing.model.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserService {

    UserEntity saveUserEntity(UserEntity userEntity);

    Page<UserEntity> findAllUserEntity(Pageable pageable);

    UserEntity findUserEntityById(Long id) throws UserNotFoundException;

    UserEntity updateUserEntity(Long id, UserEntity newUserEntity) throws UserNotFoundException;

    String deleteUserEntity(Long id) throws UserNotFoundException;

}
