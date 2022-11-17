package com.academy.billing.controller;


import com.academy.billing.exception.UserNotFoundException;
import com.academy.billing.model.BaseAuditClass;
import com.academy.billing.model.UserEntity;
import com.academy.billing.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController extends BaseAuditClass {

    @Autowired
    private UserService userService;

    @PostMapping()
    public ResponseEntity<UserEntity> saveUserEntity(@RequestBody UserEntity user) {
        UserEntity saveUserEntity = userService.saveUserEntity(user);
        return new ResponseEntity<>(saveUserEntity, HttpStatus.OK);
    }

    // Getting all users
    @GetMapping()
    public ResponseEntity<Page<UserEntity>> getUserEntity(Pageable pageable) {
        Page<UserEntity> users = userService.findAllUserEntity(pageable);
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    // Getting user by id
    @GetMapping("/{id}")
    public ResponseEntity<UserEntity> getUserEntityById(@PathVariable Long id) throws UserNotFoundException {
        UserEntity userById = userService.findUserEntityById(id);
        return new ResponseEntity<>(userById, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserEntity> updateUserEntity(
            @PathVariable Long id,
            @RequestBody UserEntity newUserEntity
    ) throws UserNotFoundException {
        UserEntity update = userService.updateUserEntity(id, newUserEntity);
        return new ResponseEntity<>(update, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUserEntity(@PathVariable Long id) throws UserNotFoundException {
        String username = userService.deleteUserEntity(id);
        String message = "User " + username + " has been deleted";
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

}
