package com.example.Herschel.controller;

import com.example.Herschel.exception.ResourceNotFoundException;
import com.example.Herschel.model.User;
import com.example.Herschel.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
public class UserController
{
    @Autowired
    UserRepository userRepository;

    @GetMapping("/users")
    public List<User> getAllUsers()
    {
        return userRepository.findAll();
    }

    @PostMapping("/users")
    public User createUser(@RequestBody User user) // @Valid
    {
        return userRepository.save(user);
    }

    @PostMapping("/users/{id}")
    public User updateUser(@PathVariable(value = "id") Long userId,
                           @Valid @RequestBody User userDetails)
    {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));
        user.setEmail(userDetails.getEmail());
        user.setUsername(userDetails.getUsername());
        user.setPassword(userDetails.getPassword());
        return userRepository.save(user);
    }

    @GetMapping("/users/findById/{id}")
    public User getUserById(@PathVariable(value = "id") Long userId)
    {
        return userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));
    }

    @GetMapping("/users/findByUsername/{username}")
    public User getUserByUsername(@PathVariable(value = "username") String username)
    {
        return userRepository.findUserByUsername(username).orElseThrow(() -> new ResourceNotFoundException("User", "username", username));
    }

    @PostMapping("/users/login")
    public User login(@RequestBody String username, @RequestBody String password)
    {
        User user = userRepository.findUserByUsername(username).orElseThrow(() -> new ResourceNotFoundException("User", "username", username));
        if(password.equals(user.getPassword()))
            user.setLoggedIn(true);
        userRepository.save(user);
        return user;
    }

    @GetMapping("/users/loginStatus")
    public boolean isLoggedIn(@RequestBody Long id)
    {
        User user = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User", "id", id));
        return user.isLoggedIn();
    }

    @PostMapping("/users/logout")
    public boolean logout(@RequestBody Long id)
    {
        User user = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User", "id", id));
        if(user.isLoggedIn())
        {
            user.setLoggedIn(false);
            userRepository.save(user);
            return true;
        }
        return false;
    }
}
