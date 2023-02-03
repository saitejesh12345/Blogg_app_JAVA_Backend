package com.example.Blogapp.dao;

import com.example.Blogapp.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


//EXPLANATION OF THIS CODE WHY DAO PACKAGE HAS REPO LAYER

//This code defines a Java interface called "CommentRepo" which extends
// the "JpaRepository" interface. The JpaRepository interface is a Spring framework interface
// for data access and management in Java applications. By extending this interface,
// the CommentRepo interface is able to inherit all of the methods provided by JpaRepository,
// which can be used to perform various operations on a database,such as saving and retrieving data.



//The JpaRepository interface takes two generic types as arguments.
// The first, "Comment", refers to the type of the entity that the repository will be working
// with. The second, "Integer", refers to the type of the primary key of the entity.
// In this case, the CommentRepo interface is designed to work with Comment entities and their
// primary keys are of type Integer.





public interface CommentRepo extends JpaRepository<Comment,Integer> {

    void delete (Comment comment);

  //  void delete(Optional<Comment> comment);
}
