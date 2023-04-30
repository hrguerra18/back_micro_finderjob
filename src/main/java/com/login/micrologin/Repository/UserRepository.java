package com.login.micrologin.Repository;

import com.login.micrologin.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    @Query(value = "SELECT * FROM users WHERE use_email= :email AND use_password = :password", nativeQuery = true)
    User findUserByEmailAndPassword(String email, String password);
    @Query(value = "SELECT * FROM users WHERE use_email= :email", nativeQuery = true)
    User findUserByEmail(String email);
}
