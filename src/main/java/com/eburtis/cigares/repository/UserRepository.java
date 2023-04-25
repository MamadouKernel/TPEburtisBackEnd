package com.eburtis.cigares.repository;

import com.eburtis.cigares.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

// Définition de l'interface UserRepository étendant JpaRepository pour la classe User avec un identifiant de type Long
public interface UserRepository extends JpaRepository<User, Long> {

    // Définition de la méthode de recherche d'un utilisateur par son adresse email
    public User findByEmail(String email);

    // Définition de la méthode de recherche d'un utilisateur par son adresse email et son mot de passe
    User findByEmailAndPassword(String tempEmailId, String tempPass);

}

