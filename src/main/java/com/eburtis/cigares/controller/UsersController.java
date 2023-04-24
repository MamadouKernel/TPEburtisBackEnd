package com.eburtis.cigares.controller;

import com.eburtis.cigares.service.UserService;
import com.eburtis.cigares.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/users")
public class UsersController {
    @Autowired
    private UserService userService;

    @PostMapping("/create")
    public User registerUser(@RequestBody User user) {
        String tempEmailId = user.getEmail();
        if (tempEmailId != null && !"".equals(tempEmailId)) {
            User userObj = userService.fetchUserByEmail(tempEmailId);
            if (userObj != null) {
                throw new RuntimeException( "User with "+tempEmailId+" is already exists" );
            }
        }
        User userObj= null;
        userObj = userService.saveUser(user);
        return userObj;
    }

    @PostMapping("/login")
    public User loginUser(@RequestBody User user) {
        String tempEmailId = user.getEmail();
        String tempPass = user.getPassword();
        User userObj = null;
        if (tempEmailId != null && tempPass != null) {
            userObj = userService.fetchUserByEmailAndPassword(tempEmailId, tempPass);
        }
        if (userObj == null) {
            throw new RuntimeException( "Bad credentials" );
        }
        return userObj;
    }

    @PutMapping("/{id}")
    public User updateUser(@PathVariable Long id, @RequestBody User user) {
        user.setId(id); // Set the id from the path variable
        return userService.updateUser(user);
    }

    @DeleteMapping("/{id}")
    public void deleteUserById(@PathVariable Long id) {
        userService.deleteUserById(id);
    }

    @GetMapping("/all")
    public Iterable<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    public User getUserById(@PathVariable Long id) {
        return userService.getUserById(id);
    }

}
