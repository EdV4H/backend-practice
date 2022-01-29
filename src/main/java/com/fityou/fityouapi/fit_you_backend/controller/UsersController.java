package com.fityou.fityouapi.fit_you_backend.controller;

import java.util.List;

import com.fityou.fityouapi.fit_you_backend.mapper.UsersMapper;
import com.fityou.fityouapi.fit_you_backend.service.UserService;

import org.openapi.fityou.api.UsersApi;
import org.openapi.fityou.model.NewUser;
import org.openapi.fityou.model.UpdateUser;
import org.openapi.fityou.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UsersController implements UsersApi {

  @Autowired
  UsersMapper userMapper;

  @Autowired
  UserService userService;

  @Override
  public ResponseEntity<Void> deleteUserUserId(Integer userId) {
    boolean result = userService.deleteOneById(userId);
    if (!result) return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
    else return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }

  @Override
  public ResponseEntity<User> getUserUserId(Integer userId) {
    User result = userService.findOneById(userId);
    if (result == null) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    else return new ResponseEntity<User>(result, HttpStatus.OK);
  }

  @Override
  public ResponseEntity<User> patchUserUserId(Integer userId, UpdateUser updateUser) {
    User result = userService.updateOne(userId, updateUser);
    if (result == null) return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
    else return new ResponseEntity<User>(result, HttpStatus.OK);
  }
  
  @Override
  public ResponseEntity<List<User>> getUsers() {
    List<User> result = userService.findMany();
    if (result == null) return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
    else return new ResponseEntity<List<User>>(result, HttpStatus.OK);
  }

  @Override
  public ResponseEntity<User> postUsers(NewUser newUser) {
    User result = userService.create(newUser);
    if (result == null) return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
    else return new ResponseEntity<User>(result, HttpStatus.OK);
  }
}
