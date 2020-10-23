package com.codemark.demoapp.services;

import com.codemark.demoapp.model.User;

import java.util.List;

public interface UserService {
    User register(User user, String roleName);

    List<User> getAll();

    User findByEmail(String email);

    User findById(int id);

    void delete(int id);

    boolean checkEmail(String email);

    boolean checkPassword(String password);
}
