package com.eburtis.cigares.service;

import com.eburtis.cigares.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.eburtis.cigares.repository.UserRepository;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    public User saveUser(User user) {
        //save user to database
        return userRepository.save(user);
    }

    public User fetchUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public User fetchUserByEmailAndPassword(String tempEmailId, String tempPass) {
        return userRepository.findByEmailAndPassword(tempEmailId, tempPass);
    }

    public Iterable<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserById(Long id) {
        return userRepository.findById(id).get();
    }

    public void deleteUserById(Long id) {
        userRepository.deleteById(id);
    }

    public User updateUser(User user) {
        return userRepository.save(user);
    }
}
