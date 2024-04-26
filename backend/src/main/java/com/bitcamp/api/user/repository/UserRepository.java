package com.bitcamp.api.user.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import com.bitcamp.api.user.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{

    Optional<User> findByUsername(String username);
    List<User> findUsersByName(String name);
    List<User> findUsersByJob(String job);
    List<User> findAllByOrderById();


    @Modifying
    @Query("update users set token = :token where id = :id")
    public void  modifyTokenById(@Param("id") Long id, @Param("token") String token);

    @Query("select count(id) as count from users where username = :username")
    Integer existsUsername(@Param("username") String username);
    
    
} 