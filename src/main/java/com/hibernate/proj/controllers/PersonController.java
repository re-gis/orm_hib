package com.hibernate.proj.controllers;

import com.hibernate.proj.models.User;
import com.hibernate.proj.serviceImpl.PersonServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:8080")
@RequestMapping("/api/v1")
public class PersonController {
    @Autowired
    private final PersonServiceImpl personService;
    PersonController(PersonServiceImpl personService){
        this.personService = personService;
    }

    @PostMapping("/create")
    public void addUser(@RequestBody User user){
        personService.AddUser(user);
    }

    @GetMapping("/users/{id}")
    public ResponseEntity getUserbyId(@PathVariable("id") int id){
        return ResponseEntity.ok(personService.getUserById(id));
    }

    @GetMapping("/people")
    public List<User> getAllUsers(){
        return personService.getAllUsers();
    }
}
