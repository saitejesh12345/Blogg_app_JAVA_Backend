package com.example.Blogapp.service;
import com.example.Blogapp.dao.UserRepo;
import com.example.Blogapp.entity.User;
import com.example.Blogapp.exceptions.ResourceNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class UserServiceImpl {
    @Autowired
    private UserRepo userRepo;


    public User createUser(User user) {

        return userRepo.save(user);
    }


    public User updateUser( int userId,User userdetails) {
        //getting user id if not throwing exception

        User user = this.userRepo.findById(userId)
                .orElseThrow(()-> new ResourceNotFoundException("User","id",userId));

        //after getting id updating the user Details
        user.setName(userdetails.getName());
        user.setEmail(userdetails.getEmail());
        user.setAbout(userdetails.getAbout());
        user.setPassword(userdetails.getPassword());

        // updated the details in User object
        // User updatedUser =this.userRepo.save(user);
        userRepo.save(user);
        return user;
        // Converted User object to Data transfer Object UserDto class
        //UserDto userDto1 =this.userToDto(updatedUser);

        //returned the UserDto object
        //return userDto1;
    }


    public User getUserById(int userId) {

        return userRepo.findById(userId).
                orElseThrow(()->new ResourceNotFoundException("User","Id",userId));

    }


    public List<User> getAllUsers() {
        //    List<User> users = this.userRepo.findAll();
        // List<UserDto> userDtos =  users.stream().map(user -> this.userToDto(user)).collect(Collectors.toList());
        return  userRepo.findAll();
    }


    public void deleteUser(int userId) {
        //return userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User","Id",userId));
//this.userRepo.delete(deluser); 919701716860
        userRepo.deleteById(userId);
        //   orElseThrow(()-> new ResourceNotFoundException("user","Id",userId));
    }








    //this below code is mainly for creating UserDto class in payload ,if we take directy entity
    // in Userservice we dont need to create this methods for converting UserDto to User and
    // vice Versa
    //here UserService methods which are going to implement in UserServiceImpl ,
    // we need to take parameters
    //according to UserDto class of payaload to write the logic for UserServiceimpl,
    // because we have created Duplicate version of Entity
    //for security purpose not to expose Entity class directly.

    //*******we can use "Model mapper dependency also to convert User to UserDto and vice cersa*******
    //Using Model mapper we can map User Object to UserDto object and vice versa in order to protect
    //Entity layer or Confidential layers we Use Object mapper by creating dummy Enittiy attributs layer.
    // or through below code Also


    //method for converting  UserDto to User
//    private User dtoToUser(UserDto userDto){
//        User user = new User();
//        user.setId(userDto.getId());
//        user.setName(userDto.getName());
//        user.setAbout(userDto.getAbout());
//        user.setEmail(userDto.getEmail());
//        user.setPassword(userDto.getPassword());
//        return user;
//    }


    //method for converting  User to UserDto
//    private UserDto userToDto(User user){
//        UserDto userdto = new UserDto();
//        userdto .setId(user.getId());
//        userdto .setName(user.getName());
//        userdto .setAbout(user.getAbout());
//        userdto .setEmail(user.getEmail());
//        userdto .setPassword(user.getPassword());
//        return userdto;
//    }
}
