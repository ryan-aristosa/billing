package com.academy.billing.service;

import com.academy.billing.exception.RecordNotFoundException;
import com.academy.billing.model.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserService {

    UserEntity saveUserEntity(UserEntity userEntity);

    Page<UserEntity> findAllUserEntity(Pageable pageable);

    UserEntity findUserEntityById(Long id) throws RecordNotFoundException;

    UserEntity updateUserEntity(Long id, UserEntity newUserEntity) throws RecordNotFoundException ;

    String deleteUserEntity(Long id) throws RecordNotFoundException;
}
