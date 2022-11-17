package com.academy.billing.service;

import com.academy.billing.enums.Role;
import com.academy.billing.exception.UserNotFoundException;
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
        return new User(user.getUsername(), user.getPassword(), getAuthority(user.getType()));
    }

    private List<SimpleGrantedAuthority> getAuthority(Role role) {
        return role == Role.ADMIN
                ? List.of(new SimpleGrantedAuthority("ROLE_ADMIN"))
                : List.of(new SimpleGrantedAuthority("ROLE_USER"));
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
    public UserEntity findUserEntityById(Long id) throws UserNotFoundException {
        return userRepository.findById(id).orElseThrow(UserNotFoundException::new);
    }

    @Override
    public UserEntity updateUserEntity(Long id, UserEntity newUserEntity) throws UserNotFoundException {
        return userRepository.findById(id).map(billing -> {
            billing.setUsername(newUserEntity.getUsername());
            billing.setPassword(newUserEntity.getPassword());
            return userRepository.save(billing);
        }).orElseThrow(UserNotFoundException::new);
    }

    @Override
    public String deleteUserEntity(Long id) throws UserNotFoundException {
        return userRepository.findById(id).map(userEntity -> {
            userRepository.delete(userEntity);
            return userEntity.getUsername();
        }).orElseThrow(UserNotFoundException::new);
    }

}
