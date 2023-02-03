package com.example.Blogapp.controller;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.anyInt;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import com.example.Blogapp.entity.Category;
import com.example.Blogapp.service.CategoryServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ContextConfiguration(classes = {CategoryController.class})
@ExtendWith(SpringExtension.class)
class CategoryControllerTest {
    @Autowired
    private CategoryController categoryController;

    @MockBean
    private CategoryServiceImpl categoryServiceImpl;

    /**
     * Method under test: {@link CategoryController#createCategory(Category)}
     */
    @Test
    void testCreateCategory() throws Exception {
        Category category = new Category();
        category.setCatId(123);
        category.setCategoryDescription("Category Description");
        category.setCategoryTitle("Dr");
        category.setPosts(new ArrayList<>());
        String content = (new ObjectMapper()).writeValueAsString(category);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/category/addCategory")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(categoryController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(400));
    }

    /**
     * Method under test: {@link CategoryController#createCategory(Category)}
     */
    @Test
    void testCreateCategory2() throws Exception {
        Category category = new Category();
        category.setCatId(123);
        category.setCategoryDescription("Category Description");
        category.setCategoryTitle("Dr");
        category.setPosts(new ArrayList<>());
        when(categoryServiceImpl.createCategory((Category) any())).thenReturn(category);

        Category category1 = new Category();
        category1.setCatId(123);
        category1.setCategoryDescription("Category Description");
        category1.setCategoryTitle("Category Title");
        category1.setPosts(new ArrayList<>());
        String content = (new ObjectMapper()).writeValueAsString(category1);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/category/addCategory")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(categoryController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(
                        MockMvcResultMatchers.content().string("{\"message\":\"Category Added Succesfuylly\",\"success\":true}"));
    }

    /**
     * Method under test: {@link CategoryController#updateCategory(int, Category)}
     */
    @Test
    void testUpdateCategory() throws Exception {
        Category category = new Category();
        category.setCatId(123);
        category.setCategoryDescription("Category Description");
        category.setCategoryTitle("Dr");
        category.setPosts(new ArrayList<>());
        when(categoryServiceImpl.updateCategory(anyInt(), (Category) any())).thenReturn(category);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.put("/api/category/updatecategory/{catId}",
                123);
        MockMvcBuilders.standaloneSetup(categoryController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"catId\":123,\"categoryTitle\":\"Dr\",\"categoryDescription\":\"Category Description\",\"posts\":[]}"));
    }

    /**
     * Method under test: {@link CategoryController#deleteCategory(int)}
     */
    @Test
    void testDeleteCategory() throws Exception {
        doNothing().when(categoryServiceImpl).deleteCategory(anyInt());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/api/category/delete/{catId}", 123);
        MockMvcBuilders.standaloneSetup(categoryController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string("{\"message\":\"Category Deleted Succesfuylly\",\"success\":true}"));
    }

    /**
     * Method under test: {@link CategoryController#deleteCategory(int)}
     */
    @Test
    void testDeleteCategory2() throws Exception {
        doNothing().when(categoryServiceImpl).deleteCategory(anyInt());
        MockHttpServletRequestBuilder deleteResult = MockMvcRequestBuilders.delete("/api/category/delete/{catId}", 123);
        deleteResult.characterEncoding("Encoding");
        MockMvcBuilders.standaloneSetup(categoryController)
                .build()
                .perform(deleteResult)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string("{\"message\":\"Category Deleted Succesfuylly\",\"success\":true}"));
    }

    /**
     * Method under test: {@link CategoryController#findCategoryById(int)}
     */
    @Test
    void testFindCategoryById() throws Exception {
        Category category = new Category();
        category.setCatId(123);
        category.setCategoryDescription("Category Description");
        category.setCategoryTitle("Dr");
        category.setPosts(new ArrayList<>());
        when(categoryServiceImpl.getCategory(anyInt())).thenReturn(category);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/category/GetCategory/{catId}",
                123);
        MockMvcBuilders.standaloneSetup(categoryController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"catId\":123,\"categoryTitle\":\"Dr\",\"categoryDescription\":\"Category Description\",\"posts\":[]}"));
    }

    /**
     * Method under test: {@link CategoryController#getAllUsers()}
     */
    @Test
    void testGetAllUsers() throws Exception {
        when(categoryServiceImpl.getAllCategories()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/category/");
        MockMvcBuilders.standaloneSetup(categoryController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Method under test: {@link CategoryController#getAllUsers()}
     */
    @Test
    void testGetAllUsers2() throws Exception {
        when(categoryServiceImpl.getAllCategories()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/api/category/");
        getResult.characterEncoding("Encoding");
        MockMvcBuilders.standaloneSetup(categoryController)
                .build()
                .perform(getResult)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }
}

