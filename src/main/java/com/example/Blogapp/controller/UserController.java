package com.example.Blogapp.controller;

import com.example.Blogapp.entity.User;
import com.example.Blogapp.payloads.ApiResponse;
import com.example.Blogapp.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/users/")
public class UserController {


    @Autowired
    private UserServiceImpl userService;
    //POST- Create User

    //ResponseEntity :- Errors code and error message can be sended and status codes
    @PostMapping("/addUser")
    public ResponseEntity<User> createUser(@Valid @RequestBody User user){
        User createUserDto= this.userService.createUser(user);
        // return new ResponseEntity<>(createUserDto, HttpStatus.CREATED);
        return new ResponseEntity(new ApiResponse("User Added Succesfuylly",true), HttpStatus.OK);
    }

    //    @PostMapping("/addUser")
//    public User addUser(@RequestBody User user ){
//
//        return userService.createUser(user);
//    }
    //PUT - Update User
//    @PutMapping("/updateUser/{id}")
//    public User updateUser(@PathVariable("id") int id){
//
//        User user = userService.updateUser(id);
//        return userService.createUser(user);
//    }
    @PutMapping("/updateUser/{id}")
    // public ResponseEntity<User> updateUser(@PathVariable("id") int id,User userdetails){
    public  User updateUser(@Valid @PathVariable("id") int id,User userdetails){
        // User user = userService.updateUser(id,userdetails);
        return userService.updateUser(id,userdetails);
        // return  userService.updateUser(id,userdetails);
        //    User upda=this. userService.createUser(user);
        //   userService.createUser(user);
        //return new ResponseEntity(new ApiResponse("User updated Succesfuylly",true), HttpStatus.OK);
        // return ResponseEntity.ok(upda);
    }

    //DELETE - delete User

    //    @DeleteMapping("/delete/{id}")
//    public String deleteProduct(@PathVariable("id") int  id){
//
//        userService.deleteUser(id);
//        return "User Deleted Succesfuylly";
//    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ApiResponse> deleteProduct(@PathVariable("id") int  id){

        userService.deleteUser(id);
        return new ResponseEntity(new ApiResponse("User Deleted Succesfuylly",true), HttpStatus.OK );
    }

    //GET - Fetch a single User
//    @GetMapping("/user/{id}")
//    public User findByUserId(@PathVariable int id){
//        return userService.getUserById(id);
//    }
    @GetMapping("/user/{id}")
    public User findByUserId(@PathVariable int id){
        //  return ResponseEntity.ok(this.userService.getUserById(id));
        return userService.getUserById(id);
        // return new ResponseEntity(new ApiResponse("User Fetched Succesfuylly",true), HttpStatus.OK);
    }
//    @GetMapping("/")
//    public List<User> findAllProducts(){
//        return userService.getAllUsers();
//    }

    @GetMapping("/")
    public List<User> getAllUsers(){
        return  userService.getAllUsers();
        // return new ResponseEntity(new ApiResponse("All Users dataFetched Succesfuylly",true), HttpStatus.OK);
        //return ResponseEntity.ok(this.userService.getAllUsers());
    }
}
