package com.example.Blogapp.entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "categories")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int  catId;

    @NotBlank
    // @NotEmpty
    @Size(min=4, message = "Min size of category Title is 4")
    @Column(name = "title",nullable = false)
    private String categoryTitle;

    @NotBlank
    @Size(min = 10, message = "Min size of Category description is 10")
    // @NotEmpty
    @Column(name = "description")
    private  String categoryDescription;

//The "@OneToMany" annotation is used in a Java class to indicate a
// one-to-many relationship between two entities. It is part of the
// Java Persistence API (JPA) and is used to define the mapping of a collection of child entities to a parent entity

    //In this example, the "posts" field is a list of "Post" entities,
    // and it's annotated with "@OneToMany" to indicate that each "Category" entity can have
    // multiple "Post" entities associated with it

    //The "mappedBy" attribute of the annotation is used to specify the field
    // in the "Post" entity that represents the relationship with the "Category" entity.
    // In this case, the field is "category".

    //The "cascade" attribute of the annotation is used to specify the actions
    // that should be cascaded from the parent entity to the child entities.
    // The "CascadeType.ALL" value means that all operations such as persist,
    // merge, remove, refresh and detach will be cascaded from the parent entity
    // to the child entities.


    //The "fetch" attribute of the annotation is used to specify the
    // fetching strategy for the relationship. The "FetchType.LAZY" value means
    // that the related entities will not be loaded until they are accessed.
    // It is useful to prevent loading unnecessary data and
    // improve the performance of the application.

    //Therefore, this annotation is specifying that the "posts" field represents
    // a list of posts that are associated with the category, JPA should cascade
    // the operations that are performed on the category to the posts and the fetching strategy is set to lazy loading.
    @OneToMany(mappedBy = "category",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private List<Post> posts=new ArrayList<>();

}
