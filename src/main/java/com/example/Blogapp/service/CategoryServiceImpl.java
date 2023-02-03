package com.example.Blogapp.service;

import com.example.Blogapp.dao.CategoryRepo;
import com.example.Blogapp.entity.Category;
import com.example.Blogapp.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl {
    @Autowired
    private CategoryRepo categoryRepo;

    //create
    public Category createCategory(Category category) {

        return categoryRepo.save(category);
    }

    //update
    public Category updateCategory( int  Id,Category category) {
        Category category1 = this.categoryRepo.findById(Id)
                .orElseThrow(()-> new ResourceNotFoundException("Category","catId",Id));

        //after getting id updating the user Details
        //   category1.setCategoryId(category.getCategoryId());
        category1.setCategoryTitle(category.getCategoryTitle());
        category1.setCategoryDescription(category.getCategoryDescription());

        categoryRepo.save(category1);
        return category1;
    }

    //delete
    public void deleteCategory(int Id){
        categoryRepo.deleteById(Id);
    }
    //get
    public Category getCategory(int Id){
        return categoryRepo.findById(Id).
                orElseThrow(()->new ResourceNotFoundException("Category","catId",Id));
    }
    //get ALl
    public List<Category> getAllCategories(){
        return  categoryRepo.findAll();
    }
}

