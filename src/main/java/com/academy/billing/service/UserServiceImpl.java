package com.academy.billing.service;

import com.academy.billing.enums.Role;
import com.academy.billing.exception.RecordNotFoundException;
import com.academy.billing.model.UserEntity;
import com.academy.billing.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserDetailsService, UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("Invalid user");
        }
        return new User(user.getUsername(), user.getPassword(), getAuthority(user));
    }

    private List<SimpleGrantedAuthority> getAuthority(UserEntity user) {
        if (user.getType() == Role.ADMIN) {
            return List.of(new SimpleGrantedAuthority("ROLE_ADMIN"));
        }
        return List.of(new SimpleGrantedAuthority("ROLE_USER"));
    }


    @Override
    public UserEntity saveUserEntity(UserEntity userEntity) {
        return userRepository.save(userEntity);
    }

    @Override
    public Page<UserEntity> findAllUserEntity(Pageable pageable) {
        return userRepository.findAll(pageable);
    }

    @Override
    public UserEntity findUserEntityById(Long id) throws RecordNotFoundException {
        return userRepository.findById(id).orElseThrow(RecordNotFoundException::new);
    }

    @Override
    public UserEntity updateUserEntity(Long id, UserEntity newUserEntity) throws RecordNotFoundException {
        return userRepository.findById(id).map(billing -> {
            billing.setUsername(newUserEntity.getUsername());
            billing.setPassword(newUserEntity.getPassword());
            return userRepository.save(billing);
        }).orElseThrow(RecordNotFoundException::new);
    }

    @Override
    public Long deleteUserEntity(Long id) throws RecordNotFoundException {
        return userRepository.findById(id).map(userEntity -> {
            userRepository.delete(userEntity);
            return userEntity.getId();
        }).orElseThrow(RecordNotFoundException::new);
    }
}
