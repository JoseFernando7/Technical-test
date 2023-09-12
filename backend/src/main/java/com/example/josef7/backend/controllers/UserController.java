package com.example.josef7.backend.controllers;

import com.example.josef7.backend.model.User;
import com.example.josef7.backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/")
@CrossOrigin(origins = "http://localhost:4200/")
public class UserController
{
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/users")
    public List<User> getUsers()
    {
        return userRepository.findAll();
    }

    @PostMapping("/add-user")
    public ResponseEntity<User> addUser(@RequestBody User newUser)
    {
        User savedUser = userRepository.save(newUser);

        return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
    }

    @PutMapping("edit/{user_id}")
    public ResponseEntity<User> editUser(@PathVariable Long user_id, @RequestBody User newUser)
    {
        Optional<User> userRegistered = userRepository.findById(user_id);

        if (userRegistered.isEmpty()) return ResponseEntity.notFound().build();

        User user = userRegistered.get();
        user.setName(newUser.getName());
        user.setLastName(newUser.getLastName());
        user.setAge(newUser.getAge());

        User updatedUser = userRepository.save(user);

        return ResponseEntity.ok(updatedUser);
    }

    @DeleteMapping("delete/{user_id}")
    public ResponseEntity<User> deleteUser(@PathVariable Long user_id)
    {
        Optional<User> userFound = userRepository.findById(user_id);

        if (userFound.isEmpty()) return ResponseEntity.notFound().build();

        userRepository.deleteById(user_id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
