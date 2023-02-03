package com.example.Blogapp.entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.*;
import java.util.stream.Collectors;

//The "@Entity" annotation is used in a Java class to indicate that the class
// should be mapped to a database table. It is part of the Java Persistence API (JPA) and
// is used to define an object-relational mapping between a Java class and a database table.

//When a class is annotated with "@Entity", JPA will automatically create a table
// in the database with the same name as the class, and map the fields of the
// class to columns of the table. The annotation also allows for
// customizing the table and column names, as well as other attributes of the table and columns.

//Additionally, this annotation is used to tell the JPA provider that this
// class is an Entity and it should be managed by JPA, JPA provider will create
// an instance of this class and use it to interact with the database.
// And also it's essential to have the "@Entity" annotation in order
// to use features such as JPA repositories, JPQL, Criteria API, etc.

@Entity
@Table(name = "users")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User{


    //When a field is annotated with "@Id", JPA will automatically map the field to
    // the primary key column of the corresponding table in the database.
    //Therefore, the "@Id" annotation is used to specify the primary key of an entity and it's mandatory
    // to have at least one field annotated with this annotation in each entity.


    //When JPA wants to persist a new object, it checks the fields which are
    // annotated with "@GeneratedValue" and if the strategy is set to "IDENTITY"
    // JPA will not provide a value for this field, it will let the
    // database generate the value and return it after the insertion.

    //Therefore, this annotation is usually used on primary key fields of an entity,
    // to auto-generate the primary key value for each new record that is inserted into
    // the database, which in turn it can be used as a foreign key in another table.

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    //spring  data validation is most important concept when ever we dont fill any attribute it will
    //send error messages data validations are @NotNull and @Email,@Size, @Max Etc.....
    //To enable spring boot data validation we need to give @Valid annotation at Controller @Post method.

    //@Column(name = "user_name")
    @NotEmpty
    @Size(min=4,message = "Username must be min of 4 charchters")
    private String name;

    //  @Column(name ="email_id" )

    @NotEmpty
    @Email(message = "Email address is not valid!!")
    private String email;

//    Given a password, the task is to validate the password with the help of Regular Expression.
//    A password is considered valid if all the following constraints are satisfied:
//
//    It contains at least 8 characters and at most 20 characters.
//    It contains at least one digit.
//    It contains at least one upper case alphabet.
//    It contains at least one lower case alphabet.
//    It contains at least one special character which includes !@#$%&*()-+=^.
//    It doesn’t contain any white space.
//    Input: Str = “Geeks@portal20”
//    Output: True.
//            Explanation: This password satisfies all constraints mentioned above.
//
//            Input: Str = “Geeksforgeeks”
//    Output: False.
//            Explanation: It contains upper case and lower case alphabet
//    but doesn’t contains any digits, and special characters.

    // @Column(name ="password" )

    @NotEmpty //blank or not null
    // @Size(min = 8, max =20 ,  )
    @Pattern(regexp ="^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&-+=()])(?=\\S+$).{8,20}$",message = "Password must be minimum of 4 chars and max of 10 chars!!" +
            "Input: Str = “Geeks@portal20” Output: True Explanation: This password satisfies all constraints mentioned above. " +
            " Input: Str = “Geeksforgeeks”Output: False.Explanation: It contains upper case and lower case alphabet, It contains at least one digit., and special characters.")
    private String password;

    // @Column(name="about" )
    @NotEmpty
    private String about;

    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private List<Post> posts=new ArrayList<>();



    //security based
//    @ManyToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
//    @JoinTable(name = "user_roles",
//    joinColumns =@JoinColumn(name = "user",referencedColumnName = "id"),
//    inverseJoinColumns =  @JoinColumn(name = "role",referencedColumnName = "id")
//    )
//    private Set<Role> roles= new HashSet<>();


//
//    @Override
//    public Collection<? extends GrantedAuthority> getAuthorities() {
//       List<SimpleGrantedAuthority> authorities = roles.stream().map((role)-> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
//        return authorities;
//    }
//
//    @Override
//    public String getUsername() {
//        return email ;
//    }
//
//    @Override
//    public boolean isAccountNonExpired() {
//        return true;
//    }
//
//    @Override
//    public boolean isAccountNonLocked() {
//        return true;
//    }
//
//    @Override
//    public boolean isCredentialsNonExpired() {
//        return true;
//    }
//
//    @Override
//    public boolean isEnabled() {
//        return true;
//    }
}
