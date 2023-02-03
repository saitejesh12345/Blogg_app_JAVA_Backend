package com.example.Blogapp.dao;

import com.example.Blogapp.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

//EXPLANATION OF THIS CODE WHY DAO PACKAGE HAS REPO LAYER

//This code defines a Java interface called "UserRepo" which extends
// the "JpaRepository" interface. The JpaRepository interface is a Spring framework interface
// for data access and management in Java applications. By extending this interface,
// the UserRepo interface is able to inherit all of the methods provided by JpaRepository,
// which can be used to perform various operations on a database,such as saving and retrieving data.


//The JpaRepository interface takes two generic types as arguments.
// The first, "User", refers to the type of the entity that the repository will be working
// with. The second, "Integer", refers to the type of the primary key of the entity.
// In this case, the UserRepo interface is designed to work with User entities and their
// primary keys are of type Integer.


public interface UserRepo extends JpaRepository<User,Integer> {

//    for security
   // Optional<User> findByEmail(String email);

    //Optional<Object> findByUser(Integer userId);
}
