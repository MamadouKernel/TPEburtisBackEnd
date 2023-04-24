package com.eburtis.cigares.repository;

import com.eburtis.cigares.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    public User findByEmail(String email);

    User findByEmailAndPassword(String tempEmailId, String tempPass);

}

