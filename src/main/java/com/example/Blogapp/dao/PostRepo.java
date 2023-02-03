package com.example.Blogapp.dao;

import com.example.Blogapp.entity.Category;
import com.example.Blogapp.entity.Comment;
import com.example.Blogapp.entity.Post;
import com.example.Blogapp.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;


//EXPLANATION OF THIS CODE WHY DAO PACKAGE HAS REPO LAYER

//This code defines a Java interface called "PostRepo" which extends
// the "JpaRepository" interface. The JpaRepository interface is a Spring framework interface
// for data access and management in Java applications. By extending this interface,
// the PostRepo interface is able to inherit all of the methods provided by JpaRepository,
// which can be used to perform various operations on a database,such as saving and retrieving data.

//The JpaRepository interface takes two generic types as arguments.
// The first, "Post", refers to the type of the entity that the repository will be working
// with. The second, "Integer", refers to the type of the primary key of the entity.
// In this case, the PostRepo interface is designed to work with Post entities and their
// primary keys are of type Integer.
public interface PostRepo extends JpaRepository<Post,Integer> {


//    List<Post> findByUser(User user) {
//    }

   //    static List<Post> findByUser(int user){

  //         return null;
     //  }
    // find all posts by user

   // List<Post> findByCategory(Category cat);
   //List<Post> findByCategory(Category cat);
    List<Post> findByUser(User user);

    List<Post> findByTitleContainingIgnoreCaseOrContentContainingIgnoreCase(String keyword, String keyword1);



    //update image in db related query
   //  Post findById( Post post_id);

//    List<Post> findByCategory(Category category); //find all posts by category


}
