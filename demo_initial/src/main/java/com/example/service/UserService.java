package com.example.service;


import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class UserService {
    public String getUser() {
        return "User";
    }
}
