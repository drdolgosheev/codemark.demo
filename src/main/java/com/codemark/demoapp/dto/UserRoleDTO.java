package com.codemark.demoapp.dto;

import com.codemark.demoapp.model.User;

public class UserRoleDTO {
    private User user;
    private String role;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
