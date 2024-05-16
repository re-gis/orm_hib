package com.hibernate.proj.serviceImpl;

import com.hibernate.proj.models.User;
import com.hibernate.proj.repositories.UserRepository;
import com.hibernate.proj.services.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonServiceImpl implements PersonService {
    @Autowired
    private final UserRepository userRepository;

    PersonServiceImpl(UserRepository userRepository){
        this.userRepository = userRepository;
    }


    @Override
    public void AddUser(User user) {
        userRepository.save(user);
        System.out.println("User added");
    }

    @Override
    public User getUserById(int id) {
        System.out.println(String.format("Get user %d", id));
        return userRepository.findById(id).isPresent() ? null : userRepository.findById(id).get();
    }

    @Override
    public List<User> getAllUsers(){
        List<User> users = userRepository.findAll();
        for(User user : users){
            user.setCart(null);
            user.setMy_products(null);
            user.setOrders(null);
        }
        return users;
    }
}
