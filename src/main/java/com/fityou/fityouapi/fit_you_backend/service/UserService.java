package com.fityou.fityouapi.fit_you_backend.service;

import java.util.List;

import com.fityou.fityouapi.fit_you_backend.mapper.UsersMapper;

import org.openapi.fityou.model.NewUser;
import org.openapi.fityou.model.UpdateUser;
import org.openapi.fityou.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

  @Autowired
  UsersMapper userMapper;
  
  public boolean deleteOneById(int userId) {
    return userMapper.delete(userId);
  }

  public User findOneById(int userId) {
    List<User> result = userMapper.findById(userId);
    if (result.size() == 0) return null;
    else return result.get(0);
  }

  public User updateOne(Integer userId, UpdateUser updateUser) {
    boolean result = userMapper.update(updateUserRequest(userId, updateUser));
    if (!result) return null;
    else return findOneById(userId);
  }

  public List<User> findMany() {
    return userMapper.read();
  }

  public User create(NewUser newUser) {
    boolean result = userMapper.create(newUser);
    if (!result) return null;
    else return findOneById(userMapper.lastInsertId());
  }

  private User updateUserRequest(Integer userId, UpdateUser updateUser) {
    User user = new User();
    user.setId(userId);
    user.setName(updateUser.getName());
    return user;
  }
}
