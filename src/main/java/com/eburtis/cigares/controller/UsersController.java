package com.eburtis.cigares.controller;

import com.eburtis.cigares.service.UserService;
import com.eburtis.cigares.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

// Autorise les demandes de toutes les origines
@CrossOrigin(origins = "*")
// Définit le point d'accès de base pour toutes les requêtes dans
@RestController
@RequestMapping("/users")
public class UsersController {
    // Injection de dépendances du service UserService
    @Autowired
    private UserService userService;

    // Création d'un nouvel utilisateur
    @PostMapping("/create")
    public User registerUser(@RequestBody User user) {
        // Récupère l'adresse email de l'utilisateur
        String tempEmailId = user.getEmail();
        if (tempEmailId != null && !"".equals(tempEmailId)) {
            // Vérifie si l'utilisateur existe déjà dans la base de données
            User userObj = userService.fetchUserByEmail(tempEmailId);
            if (userObj != null) {
                throw new RuntimeException( "User with "+tempEmailId+" is already exists" );
            }
        }
        // Vérifie si l'utilisateur existe déjà dans la base de données
        User userObj= null;
        userObj = userService.saveUser(user);
        return userObj;
    }

    // Connexion d'un utilisateur
    @PostMapping("/login")
    public User loginUser(@RequestBody User user) {
        // Récupère l'adresse email de l'utilisateur
        String tempEmailId = user.getEmail();
        // Récupère le mot de passe de l'utilisateur
        String tempPass = user.getPassword();
        User userObj = null;
        if (tempEmailId != null && tempPass != null) {
            // Vérifie si l'utilisateur existe déjà dans la base de données
            userObj = userService.fetchUserByEmailAndPassword(tempEmailId, tempPass);
        }
        if (userObj == null) {
            throw new RuntimeException( "Bad credentials" );
        }
        return userObj;
    }

    //Mise à jour d'un utilisateur
    @PutMapping("/{id}")
    public User updateUser(@PathVariable Long id, @RequestBody User user) {
        // Définit l'identifiant de l'utilisateur en fonction de la variable d'URL
        user.setId(id);
        // Met à jour l'utilisateur dans la base de données
        return userService.updateUser(user);
    }

    // Suppression d'un utilisateur
    @DeleteMapping("/{id}")
    public void deleteUserById(@PathVariable Long id) {
        // Supprime l'utilisateur de la base de données en fonction de l'identifiant
        userService.deleteUserById(id);
    }

    // Obtention de la liste de tous les utilisateurs
    @GetMapping("/all")
    public Iterable<User> getAllUsers() {
        // Récupère la liste de tous les utilisateurs dans la base de données
        return userService.getAllUsers();
    }

    // Obtention d'un utilisateur en fonction de l'identifiant
    @GetMapping("/{id}")
    public User getUserById(@PathVariable Long id) {
        // Récupère l'utilisateur dans la base de données en fonction de l'identifiant
        return userService.getUserById(id);
    }

}
