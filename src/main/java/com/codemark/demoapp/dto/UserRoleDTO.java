package com.codemark.demoapp.dto;

import com.codemark.demoapp.model.User;

import java.util.List;

public class UserRoleDTO {
    private User user;
    private List<String> roles;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }
}
