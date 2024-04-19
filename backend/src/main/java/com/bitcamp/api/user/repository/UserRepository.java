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


    @Modifying
    @Query("update users set :id")
    public void  modifyTokenById(@Param("id") Long id);
    
    
} 