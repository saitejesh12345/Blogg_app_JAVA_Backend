package com.example.Blogapp.controller;


import com.example.Blogapp.entity.Category;
import com.example.Blogapp.payloads.ApiResponse;
import com.example.Blogapp.service.CategoryServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/category/")
public class CategoryController {

    @Autowired
    private CategoryServiceImpl categoryService;

    //create
    //ResponseEntity :- Errors code and error message can be sended and status codes
    @PostMapping("/addCategory")
    public ResponseEntity<Category> createCategory(@Valid @RequestBody Category category){
        Category createUserDto= this.categoryService.createCategory( category);
        // return new ResponseEntity<>(createUserDto, HttpStatus.CREATED);
        return new ResponseEntity(new ApiResponse("Category Added Succesfuylly",true), HttpStatus.OK);
    }
    //update
    @PutMapping("/updatecategory/{catId}")
    public  Category updateCategory(@Valid @PathVariable("catId") int  catId, Category category) {
        return categoryService.updateCategory(catId, category);
    }
    //delete
    @DeleteMapping("/delete/{catId}")
    public ResponseEntity<ApiResponse> deleteCategory(@PathVariable("catId") int catId){

        categoryService.deleteCategory(catId);
        return new ResponseEntity(new ApiResponse("Category Deleted Succesfuylly",true), HttpStatus.OK );
    }
    //get
    @GetMapping("/GetCategory/{catId}")
    public  Category findCategoryById(@PathVariable int  catId){
        //  return ResponseEntity.ok(this.userService.getUserById(id));
        return categoryService.getCategory(catId);
        // return new ResponseEntity(new ApiResponse("User Fetched Succesfuylly",true), HttpStatus.OK);
    }

    //get All
    @GetMapping("/")
    public List<Category> getAllUsers() {
        return categoryService.getAllCategories();
    }
}

