package com.lambdaschool.health.service;

import com.lambdaschool.health.model.User;

import java.util.List;

public interface UserService {

    List<User> findAll();

    User findUserName(String username);

    User findUserById(long id);

    void delete(long id);

    User save(User user);

    User update(User user, long id);


}