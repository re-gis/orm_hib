package com.hibernate.proj.services;

import com.hibernate.proj.models.User;

import java.util.List;

public interface PersonService {
    public void AddUser(User user);
    public User getUserById(int id);
    public List<User> getAllUsers();
}
