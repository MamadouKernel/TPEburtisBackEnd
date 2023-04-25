package com.eburtis.cigares.service;

import com.eburtis.cigares.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.eburtis.cigares.repository.UserRepository;

@Service
public class UserService {

    // Injection de dépendances du repository UserRepository
    @Autowired
    private UserRepository userRepository;

    // Définition de la méthode pour enregistrer un utilisateur dans la base de données
    public User saveUser(User user) {
        //save user to database
        return userRepository.save(user);
    }

    // Définition de la méthode pour récupérer un utilisateur en utilisant son adresse email
    public User fetchUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    // Définition de la méthode pour récupérer un utilisateur en utilisant son adresse email et son mot de passe
    public User fetchUserByEmailAndPassword(String tempEmailId, String tempPass) {
        return userRepository.findByEmailAndPassword(tempEmailId, tempPass);
    }

    // Définition de la méthode pour récupérer tous les utilisateurs
    public Iterable<User> getAllUsers() {
        return userRepository.findAll();
    }

    // Définition de la méthode pour récupérer un utilisateur en utilisant son identifiant unique
    public User getUserById(Long id) {
        return userRepository.findById(id).get();
    }

    // Définition de la méthode pour supprimer un utilisateur en utilisant son identifiant unique
    public void deleteUserById(Long id) {
        userRepository.deleteById(id);
    }

    // Définition de la méthode pour mettre à jour les informations d'un utilisateur dans la base de données
    public User updateUser(User user) {
        return userRepository.save(user);
    }
}
