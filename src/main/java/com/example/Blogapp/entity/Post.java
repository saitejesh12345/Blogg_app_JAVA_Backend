package com.example.Blogapp.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;


@Entity
@Table(name = "post")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int post_id;

    @Column(name = "post_title",nullable = false)
    private String title;

   // @Column(length = 300)
    private  String content;
    private  String imageName;
    private Date addedDate;
    @ManyToOne
    private User user;
  @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;


//The "@OneToMany" annotation is used in a Java class to indicate a one-to-many relationship
// between two entities. It is part of the Java Persistence API (JPA) and is used to
// define the mapping of a collection of child entities to a parent entity.


    //In this example, the "comments" field is a collection of "Comment" entities,
    // and it's annotated with "@OneToMany" to indicate that each "Post" entity can
    // have multiple "Comment" entities associated with it.

    //The "mappedBy" attribute of the annotation is used to specify
    // the field in the "Comment" entity that represents the relationship
    // with the "Post" entity. In this case, the field is "post".

    //The "cascade" attribute of the annotation is used to specify
    // the actions that should be cascaded from the parent entity to the child entities.
    // The "CascadeType.ALL" value means that all operations such as persist, merge,
    // remove, refresh and detach will be cascaded from the parent entity to the child entities.

    //Therefore, this annotation is specifying that the "comments" field represents a set of comments
    // that are associated with the post and JPA should cascade the operations that are performed on the post to the comments
@OneToMany(mappedBy = "post",cascade = CascadeType.ALL)
 private Set<Comment> comments = new HashSet<>();
}
