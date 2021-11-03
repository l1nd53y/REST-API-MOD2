package com.l1nd53y.mod2.repository;


import com.l1nd53y.mod2.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

}