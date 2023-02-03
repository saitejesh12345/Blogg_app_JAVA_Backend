package com.example.Blogapp.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.example.Blogapp.dao.CategoryRepo;
import com.example.Blogapp.entity.Category;
import com.example.Blogapp.exceptions.ResourceNotFoundException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {CategoryServiceImpl.class})
@ExtendWith(SpringExtension.class)
class CategoryServiceImplTest {
    @MockBean
    private CategoryRepo categoryRepo;

    @Autowired
    private CategoryServiceImpl categoryServiceImpl;

    /**
     * Method under test: {@link CategoryServiceImpl#createCategory(Category)}
     */
    @Test
    void testCreateCategory() {
        Category category = new Category();
        category.setCatId(123);
        category.setCategoryDescription("Category Description");
        category.setCategoryTitle("Dr");
        category.setPosts(new ArrayList<>());
        when(categoryRepo.save((Category) any())).thenReturn(category);

        Category category1 = new Category();
        category1.setCatId(123);
        category1.setCategoryDescription("Category Description");
        category1.setCategoryTitle("Dr");
        category1.setPosts(new ArrayList<>());
        assertSame(category, categoryServiceImpl.createCategory(category1));
        verify(categoryRepo).save((Category) any());
    }

    /**
     * Method under test: {@link CategoryServiceImpl#createCategory(Category)}
     */
    @Test
    void testCreateCategory2() {
        when(categoryRepo.save((Category) any()))
                .thenThrow(new ResourceNotFoundException("Resource Name", "Field Name", 42L));

        Category category = new Category();
        category.setCatId(123);
        category.setCategoryDescription("Category Description");
        category.setCategoryTitle("Dr");
        category.setPosts(new ArrayList<>());
        assertThrows(ResourceNotFoundException.class, () -> categoryServiceImpl.createCategory(category));
        verify(categoryRepo).save((Category) any());
    }

    /**
     * Method under test: {@link CategoryServiceImpl#updateCategory(int, Category)}
     */
    @Test
    void testUpdateCategory() {
        Category category = new Category();
        category.setCatId(123);
        category.setCategoryDescription("Category Description");
        category.setCategoryTitle("Dr");
        category.setPosts(new ArrayList<>());
        Optional<Category> ofResult = Optional.of(category);

        Category category1 = new Category();
        category1.setCatId(123);
        category1.setCategoryDescription("Category Description");
        category1.setCategoryTitle("Dr");
        category1.setPosts(new ArrayList<>());
        when(categoryRepo.save((Category) any())).thenReturn(category1);
        when(categoryRepo.findById((Integer) any())).thenReturn(ofResult);

        Category category2 = new Category();
        category2.setCatId(123);
        category2.setCategoryDescription("Category Description");
        category2.setCategoryTitle("Dr");
        category2.setPosts(new ArrayList<>());
        Category actualUpdateCategoryResult = categoryServiceImpl.updateCategory(123, category2);
        assertSame(category, actualUpdateCategoryResult);
        assertEquals("Dr", actualUpdateCategoryResult.getCategoryTitle());
        assertEquals("Category Description", actualUpdateCategoryResult.getCategoryDescription());
        verify(categoryRepo).save((Category) any());
        verify(categoryRepo).findById((Integer) any());
    }

    /**
     * Method under test: {@link CategoryServiceImpl#updateCategory(int, Category)}
     */
    @Test
    void testUpdateCategory2() {
        Category category = new Category();
        category.setCatId(123);
        category.setCategoryDescription("Category Description");
        category.setCategoryTitle("Dr");
        category.setPosts(new ArrayList<>());
        Optional<Category> ofResult = Optional.of(category);
        when(categoryRepo.save((Category) any()))
                .thenThrow(new ResourceNotFoundException("Resource Name", "Field Name", 42L));
        when(categoryRepo.findById((Integer) any())).thenReturn(ofResult);

        Category category1 = new Category();
        category1.setCatId(123);
        category1.setCategoryDescription("Category Description");
        category1.setCategoryTitle("Dr");
        category1.setPosts(new ArrayList<>());
        assertThrows(ResourceNotFoundException.class, () -> categoryServiceImpl.updateCategory(123, category1));
        verify(categoryRepo).save((Category) any());
        verify(categoryRepo).findById((Integer) any());
    }

    /**
     * Method under test: {@link CategoryServiceImpl#updateCategory(int, Category)}
     */
    @Test
    void testUpdateCategory3() {
        Category category = new Category();
        category.setCatId(123);
        category.setCategoryDescription("Category Description");
        category.setCategoryTitle("Dr");
        category.setPosts(new ArrayList<>());
        when(categoryRepo.save((Category) any())).thenReturn(category);
        when(categoryRepo.findById((Integer) any())).thenReturn(Optional.empty());

        Category category1 = new Category();
        category1.setCatId(123);
        category1.setCategoryDescription("Category Description");
        category1.setCategoryTitle("Dr");
        category1.setPosts(new ArrayList<>());
        assertThrows(ResourceNotFoundException.class, () -> categoryServiceImpl.updateCategory(123, category1));
        verify(categoryRepo).findById((Integer) any());
    }

    /**
     * Method under test: {@link CategoryServiceImpl#deleteCategory(int)}
     */
    @Test
    void testDeleteCategory() {
        doNothing().when(categoryRepo).deleteById((Integer) any());
        categoryServiceImpl.deleteCategory(123);
        verify(categoryRepo).deleteById((Integer) any());
        assertTrue(categoryServiceImpl.getAllCategories().isEmpty());
    }

    /**
     * Method under test: {@link CategoryServiceImpl#deleteCategory(int)}
     */
    @Test
    void testDeleteCategory2() {
        doThrow(new ResourceNotFoundException("Resource Name", "Field Name", 42L)).when(categoryRepo)
                .deleteById((Integer) any());
        assertThrows(ResourceNotFoundException.class, () -> categoryServiceImpl.deleteCategory(123));
        verify(categoryRepo).deleteById((Integer) any());
    }

    /**
     * Method under test: {@link CategoryServiceImpl#getCategory(int)}
     */
    @Test
    void testGetCategory() {
        Category category = new Category();
        category.setCatId(123);
        category.setCategoryDescription("Category Description");
        category.setCategoryTitle("Dr");
        category.setPosts(new ArrayList<>());
        Optional<Category> ofResult = Optional.of(category);
        when(categoryRepo.findById((Integer) any())).thenReturn(ofResult);
        assertSame(category, categoryServiceImpl.getCategory(123));
        verify(categoryRepo).findById((Integer) any());
    }

    /**
     * Method under test: {@link CategoryServiceImpl#getCategory(int)}
     */
    @Test
    void testGetCategory2() {
        when(categoryRepo.findById((Integer) any())).thenReturn(Optional.empty());
        assertThrows(ResourceNotFoundException.class, () -> categoryServiceImpl.getCategory(123));
        verify(categoryRepo).findById((Integer) any());
    }

    /**
     * Method under test: {@link CategoryServiceImpl#getCategory(int)}
     */
    @Test
    void testGetCategory3() {
        when(categoryRepo.findById((Integer) any()))
                .thenThrow(new ResourceNotFoundException("Category", "Category", 42L));
        assertThrows(ResourceNotFoundException.class, () -> categoryServiceImpl.getCategory(123));
        verify(categoryRepo).findById((Integer) any());
    }

    /**
     * Method under test: {@link CategoryServiceImpl#getAllCategories()}
     */
    @Test
    void testGetAllCategories() {
        ArrayList<Category> categoryList = new ArrayList<>();
        when(categoryRepo.findAll()).thenReturn(categoryList);
        List<Category> actualAllCategories = categoryServiceImpl.getAllCategories();
        assertSame(categoryList, actualAllCategories);
        assertTrue(actualAllCategories.isEmpty());
        verify(categoryRepo).findAll();
    }

    /**
     * Method under test: {@link CategoryServiceImpl#getAllCategories()}
     */
    @Test
    void testGetAllCategories2() {
        when(categoryRepo.findAll()).thenThrow(new ResourceNotFoundException("Resource Name", "Field Name", 42L));
        assertThrows(ResourceNotFoundException.class, () -> categoryServiceImpl.getAllCategories());
        verify(categoryRepo).findAll();
    }
}

