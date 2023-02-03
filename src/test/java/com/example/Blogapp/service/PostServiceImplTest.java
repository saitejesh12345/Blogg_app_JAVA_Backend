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
import com.example.Blogapp.dao.PostRepo;
import com.example.Blogapp.dao.UserRepo;
import com.example.Blogapp.entity.Category;
import com.example.Blogapp.entity.Post;
import com.example.Blogapp.entity.User;
import com.example.Blogapp.exceptions.ResourceNotFoundException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {PostServiceImpl.class})
@ExtendWith(SpringExtension.class)
class PostServiceImplTest {
    @MockBean
    private CategoryRepo categoryRepo;

    @MockBean
    private PostRepo postRepo;

    @Autowired
    private PostServiceImpl postServiceImpl;

    @MockBean
    private UserRepo userRepo;

    /**
     * Method under test: {@link PostServiceImpl#createPost(Post, Integer, Integer)}
     */
    @Test
    void testCreatePost() {
        Category category = new Category();
        category.setCatId(123);
        category.setCategoryDescription("Category Description");
        category.setCategoryTitle("Dr");
        category.setPosts(new ArrayList<>());
        Optional<Category> ofResult = Optional.of(category);
        when(categoryRepo.findById((Integer) any())).thenReturn(ofResult);

        Category category1 = new Category();
        category1.setCatId(123);
        category1.setCategoryDescription("Category Description");
        category1.setCategoryTitle("Dr");
        category1.setPosts(new ArrayList<>());

        User user = new User();
        user.setAbout("About");
        user.setEmail("jane.doe@example.org");
        user.setId(1);
        user.setName("Name");
        user.setPassword("iloveyou");
        user.setPosts(new ArrayList<>());

        Post post = new Post();
        LocalDateTime atStartOfDayResult = LocalDate.of(1970, 1, 1).atStartOfDay();
        post.setAddedDate(Date.from(atStartOfDayResult.atZone(ZoneId.of("UTC")).toInstant()));
        post.setCategory(category1);
        post.setComments(new HashSet<>());
        post.setContent("Not all who wander are lost");
        post.setImageName("Image Name");
        post.setPost_id(1);
        post.setTitle("Dr");
        post.setUser(user);
        when(postRepo.save((Post) any())).thenReturn(post);

        User user1 = new User();
        user1.setAbout("About");
        user1.setEmail("jane.doe@example.org");
        user1.setId(1);
        user1.setName("Name");
        user1.setPassword("iloveyou");
        user1.setPosts(new ArrayList<>());
        Optional<User> ofResult1 = Optional.of(user1);
        when(userRepo.findById((Integer) any())).thenReturn(ofResult1);

        Category category2 = new Category();
        category2.setCatId(123);
        category2.setCategoryDescription("Category Description");
        category2.setCategoryTitle("Dr");
        category2.setPosts(new ArrayList<>());

        User user2 = new User();
        user2.setAbout("About");
        user2.setEmail("jane.doe@example.org");
        user2.setId(1);
        user2.setName("Name");
        user2.setPassword("iloveyou");
        user2.setPosts(new ArrayList<>());

        Post post1 = new Post();
        LocalDateTime atStartOfDayResult1 = LocalDate.of(1970, 1, 1).atStartOfDay();
        post1.setAddedDate(Date.from(atStartOfDayResult1.atZone(ZoneId.of("UTC")).toInstant()));
        post1.setCategory(category2);
        post1.setComments(new HashSet<>());
        post1.setContent("Not all who wander are lost");
        post1.setImageName("Image Name");
        post1.setPost_id(1);
        post1.setTitle("Dr");
        post1.setUser(user2);
        assertSame(post, postServiceImpl.createPost(post1, 123, 123));
        verify(categoryRepo).findById((Integer) any());
        verify(postRepo).save((Post) any());
        verify(userRepo).findById((Integer) any());
        assertEquals(user2, post1.getUser());
        assertEquals(category2, post1.getCategory());
        assertEquals("default.png", post1.getImageName());
    }

    /**
     * Method under test: {@link PostServiceImpl#createPost(Post, Integer, Integer)}
     */
    @Test
    void testCreatePost2() {
        Category category = new Category();
        category.setCatId(123);
        category.setCategoryDescription("Category Description");
        category.setCategoryTitle("Dr");
        category.setPosts(new ArrayList<>());
        Optional<Category> ofResult = Optional.of(category);
        when(categoryRepo.findById((Integer) any())).thenReturn(ofResult);
        when(postRepo.save((Post) any())).thenThrow(new ResourceNotFoundException("default.png", "default.png", 42L));

        User user = new User();
        user.setAbout("About");
        user.setEmail("jane.doe@example.org");
        user.setId(1);
        user.setName("Name");
        user.setPassword("iloveyou");
        user.setPosts(new ArrayList<>());
        Optional<User> ofResult1 = Optional.of(user);
        when(userRepo.findById((Integer) any())).thenReturn(ofResult1);

        Category category1 = new Category();
        category1.setCatId(123);
        category1.setCategoryDescription("Category Description");
        category1.setCategoryTitle("Dr");
        category1.setPosts(new ArrayList<>());

        User user1 = new User();
        user1.setAbout("About");
        user1.setEmail("jane.doe@example.org");
        user1.setId(1);
        user1.setName("Name");
        user1.setPassword("iloveyou");
        user1.setPosts(new ArrayList<>());

        Post post = new Post();
        LocalDateTime atStartOfDayResult = LocalDate.of(1970, 1, 1).atStartOfDay();
        post.setAddedDate(Date.from(atStartOfDayResult.atZone(ZoneId.of("UTC")).toInstant()));
        post.setCategory(category1);
        post.setComments(new HashSet<>());
        post.setContent("Not all who wander are lost");
        post.setImageName("Image Name");
        post.setPost_id(1);
        post.setTitle("Dr");
        post.setUser(user1);
        assertThrows(ResourceNotFoundException.class, () -> postServiceImpl.createPost(post, 123, 123));
        verify(categoryRepo).findById((Integer) any());
        verify(postRepo).save((Post) any());
        verify(userRepo).findById((Integer) any());
    }

    /**
     * Method under test: {@link PostServiceImpl#createPost(Post, Integer, Integer)}
     */
    @Test
    void testCreatePost3() {
        when(categoryRepo.findById((Integer) any())).thenReturn(Optional.empty());

        Category category = new Category();
        category.setCatId(123);
        category.setCategoryDescription("Category Description");
        category.setCategoryTitle("Dr");
        category.setPosts(new ArrayList<>());

        User user = new User();
        user.setAbout("About");
        user.setEmail("jane.doe@example.org");
        user.setId(1);
        user.setName("Name");
        user.setPassword("iloveyou");
        user.setPosts(new ArrayList<>());

        Post post = new Post();
        LocalDateTime atStartOfDayResult = LocalDate.of(1970, 1, 1).atStartOfDay();
        post.setAddedDate(Date.from(atStartOfDayResult.atZone(ZoneId.of("UTC")).toInstant()));
        post.setCategory(category);
        post.setComments(new HashSet<>());
        post.setContent("Not all who wander are lost");
        post.setImageName("Image Name");
        post.setPost_id(1);
        post.setTitle("Dr");
        post.setUser(user);
        when(postRepo.save((Post) any())).thenReturn(post);

        User user1 = new User();
        user1.setAbout("About");
        user1.setEmail("jane.doe@example.org");
        user1.setId(1);
        user1.setName("Name");
        user1.setPassword("iloveyou");
        user1.setPosts(new ArrayList<>());
        Optional<User> ofResult = Optional.of(user1);
        when(userRepo.findById((Integer) any())).thenReturn(ofResult);

        Category category1 = new Category();
        category1.setCatId(123);
        category1.setCategoryDescription("Category Description");
        category1.setCategoryTitle("Dr");
        category1.setPosts(new ArrayList<>());

        User user2 = new User();
        user2.setAbout("About");
        user2.setEmail("jane.doe@example.org");
        user2.setId(1);
        user2.setName("Name");
        user2.setPassword("iloveyou");
        user2.setPosts(new ArrayList<>());

        Post post1 = new Post();
        LocalDateTime atStartOfDayResult1 = LocalDate.of(1970, 1, 1).atStartOfDay();
        post1.setAddedDate(Date.from(atStartOfDayResult1.atZone(ZoneId.of("UTC")).toInstant()));
        post1.setCategory(category1);
        post1.setComments(new HashSet<>());
        post1.setContent("Not all who wander are lost");
        post1.setImageName("Image Name");
        post1.setPost_id(1);
        post1.setTitle("Dr");
        post1.setUser(user2);
        assertThrows(ResourceNotFoundException.class, () -> postServiceImpl.createPost(post1, 123, 123));
        verify(categoryRepo).findById((Integer) any());
        verify(userRepo).findById((Integer) any());
    }

    /**
     * Method under test: {@link PostServiceImpl#createPost(Post, Integer, Integer)}
     */
    @Test
    void testCreatePost4() {
        Category category = new Category();
        category.setCatId(123);
        category.setCategoryDescription("Category Description");
        category.setCategoryTitle("Dr");
        category.setPosts(new ArrayList<>());
        Optional<Category> ofResult = Optional.of(category);
        when(categoryRepo.findById((Integer) any())).thenReturn(ofResult);

        Category category1 = new Category();
        category1.setCatId(123);
        category1.setCategoryDescription("Category Description");
        category1.setCategoryTitle("Dr");
        category1.setPosts(new ArrayList<>());

        User user = new User();
        user.setAbout("About");
        user.setEmail("jane.doe@example.org");
        user.setId(1);
        user.setName("Name");
        user.setPassword("iloveyou");
        user.setPosts(new ArrayList<>());

        Post post = new Post();
        LocalDateTime atStartOfDayResult = LocalDate.of(1970, 1, 1).atStartOfDay();
        post.setAddedDate(Date.from(atStartOfDayResult.atZone(ZoneId.of("UTC")).toInstant()));
        post.setCategory(category1);
        post.setComments(new HashSet<>());
        post.setContent("Not all who wander are lost");
        post.setImageName("Image Name");
        post.setPost_id(1);
        post.setTitle("Dr");
        post.setUser(user);
        when(postRepo.save((Post) any())).thenReturn(post);
        when(userRepo.findById((Integer) any())).thenReturn(Optional.empty());

        Category category2 = new Category();
        category2.setCatId(123);
        category2.setCategoryDescription("Category Description");
        category2.setCategoryTitle("Dr");
        category2.setPosts(new ArrayList<>());

        User user1 = new User();
        user1.setAbout("About");
        user1.setEmail("jane.doe@example.org");
        user1.setId(1);
        user1.setName("Name");
        user1.setPassword("iloveyou");
        user1.setPosts(new ArrayList<>());

        Post post1 = new Post();
        LocalDateTime atStartOfDayResult1 = LocalDate.of(1970, 1, 1).atStartOfDay();
        post1.setAddedDate(Date.from(atStartOfDayResult1.atZone(ZoneId.of("UTC")).toInstant()));
        post1.setCategory(category2);
        post1.setComments(new HashSet<>());
        post1.setContent("Not all who wander are lost");
        post1.setImageName("Image Name");
        post1.setPost_id(1);
        post1.setTitle("Dr");
        post1.setUser(user1);
        assertThrows(ResourceNotFoundException.class, () -> postServiceImpl.createPost(post1, 123, 123));
        verify(userRepo).findById((Integer) any());
    }

    /**
     * Method under test: {@link PostServiceImpl#updatePost(Integer, Post)}
     */
    @Test
    void testUpdatePost() {
        Category category = new Category();
        category.setCatId(123);
        category.setCategoryDescription("Category Description");
        category.setCategoryTitle("Dr");
        category.setPosts(new ArrayList<>());

        User user = new User();
        user.setAbout("About");
        user.setEmail("jane.doe@example.org");
        user.setId(1);
        user.setName("Name");
        user.setPassword("iloveyou");
        user.setPosts(new ArrayList<>());

        Post post = new Post();
        LocalDateTime atStartOfDayResult = LocalDate.of(1970, 1, 1).atStartOfDay();
        post.setAddedDate(Date.from(atStartOfDayResult.atZone(ZoneId.of("UTC")).toInstant()));
        post.setCategory(category);
        post.setComments(new HashSet<>());
        post.setContent("Not all who wander are lost");
        post.setImageName("Image Name");
        post.setPost_id(1);
        post.setTitle("Dr");
        post.setUser(user);
        Optional<Post> ofResult = Optional.of(post);

        Category category1 = new Category();
        category1.setCatId(123);
        category1.setCategoryDescription("Category Description");
        category1.setCategoryTitle("Dr");
        category1.setPosts(new ArrayList<>());

        User user1 = new User();
        user1.setAbout("About");
        user1.setEmail("jane.doe@example.org");
        user1.setId(1);
        user1.setName("Name");
        user1.setPassword("iloveyou");
        user1.setPosts(new ArrayList<>());

        Post post1 = new Post();
        LocalDateTime atStartOfDayResult1 = LocalDate.of(1970, 1, 1).atStartOfDay();
        post1.setAddedDate(Date.from(atStartOfDayResult1.atZone(ZoneId.of("UTC")).toInstant()));
        post1.setCategory(category1);
        post1.setComments(new HashSet<>());
        post1.setContent("Not all who wander are lost");
        post1.setImageName("Image Name");
        post1.setPost_id(1);
        post1.setTitle("Dr");
        post1.setUser(user1);
        when(postRepo.save((Post) any())).thenReturn(post1);
        when(postRepo.findById((Integer) any())).thenReturn(ofResult);

        Category category2 = new Category();
        category2.setCatId(123);
        category2.setCategoryDescription("Category Description");
        category2.setCategoryTitle("Dr");
        category2.setPosts(new ArrayList<>());

        User user2 = new User();
        user2.setAbout("About");
        user2.setEmail("jane.doe@example.org");
        user2.setId(1);
        user2.setName("Name");
        user2.setPassword("iloveyou");
        user2.setPosts(new ArrayList<>());

        Post post2 = new Post();
        LocalDateTime atStartOfDayResult2 = LocalDate.of(1970, 1, 1).atStartOfDay();
        post2.setAddedDate(Date.from(atStartOfDayResult2.atZone(ZoneId.of("UTC")).toInstant()));
        post2.setCategory(category2);
        post2.setComments(new HashSet<>());
        post2.setContent("Not all who wander are lost");
        post2.setImageName("Image Name");
        post2.setPost_id(1);
        post2.setTitle("Dr");
        post2.setUser(user2);
        assertSame(post1, postServiceImpl.updatePost(123, post2));
        verify(postRepo).save((Post) any());
        verify(postRepo).findById((Integer) any());
    }

    /**
     * Method under test: {@link PostServiceImpl#updatePost(Integer, Post)}
     */
    @Test
    void testUpdatePost2() {
        Category category = new Category();
        category.setCatId(123);
        category.setCategoryDescription("Category Description");
        category.setCategoryTitle("Dr");
        category.setPosts(new ArrayList<>());

        User user = new User();
        user.setAbout("About");
        user.setEmail("jane.doe@example.org");
        user.setId(1);
        user.setName("Name");
        user.setPassword("iloveyou");
        user.setPosts(new ArrayList<>());

        Post post = new Post();
        LocalDateTime atStartOfDayResult = LocalDate.of(1970, 1, 1).atStartOfDay();
        post.setAddedDate(Date.from(atStartOfDayResult.atZone(ZoneId.of("UTC")).toInstant()));
        post.setCategory(category);
        post.setComments(new HashSet<>());
        post.setContent("Not all who wander are lost");
        post.setImageName("Image Name");
        post.setPost_id(1);
        post.setTitle("Dr");
        post.setUser(user);
        Optional<Post> ofResult = Optional.of(post);
        when(postRepo.save((Post) any())).thenThrow(new ResourceNotFoundException("Resource Name", "Field Name", 42L));
        when(postRepo.findById((Integer) any())).thenReturn(ofResult);

        Category category1 = new Category();
        category1.setCatId(123);
        category1.setCategoryDescription("Category Description");
        category1.setCategoryTitle("Dr");
        category1.setPosts(new ArrayList<>());

        User user1 = new User();
        user1.setAbout("About");
        user1.setEmail("jane.doe@example.org");
        user1.setId(1);
        user1.setName("Name");
        user1.setPassword("iloveyou");
        user1.setPosts(new ArrayList<>());

        Post post1 = new Post();
        LocalDateTime atStartOfDayResult1 = LocalDate.of(1970, 1, 1).atStartOfDay();
        post1.setAddedDate(Date.from(atStartOfDayResult1.atZone(ZoneId.of("UTC")).toInstant()));
        post1.setCategory(category1);
        post1.setComments(new HashSet<>());
        post1.setContent("Not all who wander are lost");
        post1.setImageName("Image Name");
        post1.setPost_id(1);
        post1.setTitle("Dr");
        post1.setUser(user1);
        assertThrows(ResourceNotFoundException.class, () -> postServiceImpl.updatePost(123, post1));
        verify(postRepo).save((Post) any());
        verify(postRepo).findById((Integer) any());
    }

    /**
     * Method under test: {@link PostServiceImpl#updatePost(Integer, Post)}
     */
    @Test
    void testUpdatePost3() {
        Category category = new Category();
        category.setCatId(123);
        category.setCategoryDescription("Category Description");
        category.setCategoryTitle("Dr");
        category.setPosts(new ArrayList<>());

        User user = new User();
        user.setAbout("About");
        user.setEmail("jane.doe@example.org");
        user.setId(1);
        user.setName("Name");
        user.setPassword("iloveyou");
        user.setPosts(new ArrayList<>());

        Post post = new Post();
        LocalDateTime atStartOfDayResult = LocalDate.of(1970, 1, 1).atStartOfDay();
        post.setAddedDate(Date.from(atStartOfDayResult.atZone(ZoneId.of("UTC")).toInstant()));
        post.setCategory(category);
        post.setComments(new HashSet<>());
        post.setContent("Not all who wander are lost");
        post.setImageName("Image Name");
        post.setPost_id(1);
        post.setTitle("Dr");
        post.setUser(user);
        when(postRepo.save((Post) any())).thenReturn(post);
        when(postRepo.findById((Integer) any())).thenReturn(Optional.empty());

        Category category1 = new Category();
        category1.setCatId(123);
        category1.setCategoryDescription("Category Description");
        category1.setCategoryTitle("Dr");
        category1.setPosts(new ArrayList<>());

        User user1 = new User();
        user1.setAbout("About");
        user1.setEmail("jane.doe@example.org");
        user1.setId(1);
        user1.setName("Name");
        user1.setPassword("iloveyou");
        user1.setPosts(new ArrayList<>());

        Post post1 = new Post();
        LocalDateTime atStartOfDayResult1 = LocalDate.of(1970, 1, 1).atStartOfDay();
        post1.setAddedDate(Date.from(atStartOfDayResult1.atZone(ZoneId.of("UTC")).toInstant()));
        post1.setCategory(category1);
        post1.setComments(new HashSet<>());
        post1.setContent("Not all who wander are lost");
        post1.setImageName("Image Name");
        post1.setPost_id(1);
        post1.setTitle("Dr");
        post1.setUser(user1);
        assertThrows(ResourceNotFoundException.class, () -> postServiceImpl.updatePost(123, post1));
        verify(postRepo).findById((Integer) any());
    }

    /**
     * Method under test: {@link PostServiceImpl#deletePost(Integer)}
     */
    @Test
    void testDeletePost() {
        doNothing().when(postRepo).deleteById((Integer) any());
        when(postRepo.existsById((Integer) any())).thenReturn(true);
        postServiceImpl.deletePost(123);
        verify(postRepo).existsById((Integer) any());
        verify(postRepo).deleteById((Integer) any());
        assertTrue(postServiceImpl.getAllPosts().isEmpty());
    }

    /**
     * Method under test: {@link PostServiceImpl#deletePost(Integer)}
     */
    @Test
    void testDeletePost2() {
        doThrow(new ResourceNotFoundException("Resource Name", "Field Name", 42L)).when(postRepo)
                .deleteById((Integer) any());
        when(postRepo.existsById((Integer) any())).thenReturn(true);
        assertThrows(ResourceNotFoundException.class, () -> postServiceImpl.deletePost(123));
        verify(postRepo).existsById((Integer) any());
        verify(postRepo).deleteById((Integer) any());
    }

    /**
     * Method under test: {@link PostServiceImpl#deletePost(Integer)}
     */
    @Test
    void testDeletePost3() {
        doNothing().when(postRepo).deleteById((Integer) any());
        when(postRepo.existsById((Integer) any())).thenReturn(false);
        assertThrows(ResourceNotFoundException.class, () -> postServiceImpl.deletePost(123));
        verify(postRepo).existsById((Integer) any());
    }

    /**
     * Method under test: {@link PostServiceImpl#getAllPosts()}
     */
    @Test
    void testGetAllPosts() {
        ArrayList<Post> postList = new ArrayList<>();
        when(postRepo.findAll()).thenReturn(postList);
        List<Post> actualAllPosts = postServiceImpl.getAllPosts();
        assertSame(postList, actualAllPosts);
        assertTrue(actualAllPosts.isEmpty());
        verify(postRepo).findAll();
    }

    /**
     * Method under test: {@link PostServiceImpl#getAllPosts()}
     */
    @Test
    void testGetAllPosts2() {
        when(postRepo.findAll()).thenThrow(new ResourceNotFoundException("Resource Name", "Field Name", 42L));
        assertThrows(ResourceNotFoundException.class, () -> postServiceImpl.getAllPosts());
        verify(postRepo).findAll();
    }

    /**
     * Method under test: {@link PostServiceImpl#getPostById(Integer)}
     */
    @Test
    void testGetPostById() {
        Category category = new Category();
        category.setCatId(123);
        category.setCategoryDescription("Category Description");
        category.setCategoryTitle("Dr");
        category.setPosts(new ArrayList<>());

        User user = new User();
        user.setAbout("About");
        user.setEmail("jane.doe@example.org");
        user.setId(1);
        user.setName("Name");
        user.setPassword("iloveyou");
        user.setPosts(new ArrayList<>());

        Post post = new Post();
        LocalDateTime atStartOfDayResult = LocalDate.of(1970, 1, 1).atStartOfDay();
        post.setAddedDate(Date.from(atStartOfDayResult.atZone(ZoneId.of("UTC")).toInstant()));
        post.setCategory(category);
        post.setComments(new HashSet<>());
        post.setContent("Not all who wander are lost");
        post.setImageName("Image Name");
        post.setPost_id(1);
        post.setTitle("Dr");
        post.setUser(user);
        Optional<Post> ofResult = Optional.of(post);
        when(postRepo.findById((Integer) any())).thenReturn(ofResult);
        assertSame(post, postServiceImpl.getPostById(123));
        verify(postRepo).findById((Integer) any());
    }

    /**
     * Method under test: {@link PostServiceImpl#getPostById(Integer)}
     */
    @Test
    void testGetPostById2() {
        when(postRepo.findById((Integer) any())).thenReturn(Optional.empty());
        assertThrows(ResourceNotFoundException.class, () -> postServiceImpl.getPostById(123));
        verify(postRepo).findById((Integer) any());
    }

    /**
     * Method under test: {@link PostServiceImpl#getPostById(Integer)}
     */
    @Test
    void testGetPostById3() {
        when(postRepo.findById((Integer) any())).thenThrow(new ResourceNotFoundException("Post", "Post", 42L));
        assertThrows(ResourceNotFoundException.class, () -> postServiceImpl.getPostById(123));
        verify(postRepo).findById((Integer) any());
    }

    /**
     * Method under test: {@link PostServiceImpl#getPostsByUser(int)}
     */
    @Test
    void testGetPostsByUser() {
        ArrayList<Post> postList = new ArrayList<>();
        when(postRepo.findByUser((User) any())).thenReturn(postList);

        User user = new User();
        user.setAbout("About");
        user.setEmail("jane.doe@example.org");
        user.setId(1);
        user.setName("Name");
        user.setPassword("iloveyou");
        user.setPosts(new ArrayList<>());
        Optional<User> ofResult = Optional.of(user);
        when(userRepo.findById((Integer) any())).thenReturn(ofResult);
        List<Post> actualPostsByUser = postServiceImpl.getPostsByUser(123);
        assertSame(postList, actualPostsByUser);
        assertTrue(actualPostsByUser.isEmpty());
        verify(postRepo).findByUser((User) any());
        verify(userRepo).findById((Integer) any());
    }

    /**
     * Method under test: {@link PostServiceImpl#getPostsByUser(int)}
     */
    @Test
    void testGetPostsByUser2() {
        when(postRepo.findByUser((User) any()))
                .thenThrow(new ResourceNotFoundException("Resource Name", "Field Name", 42L));

        User user = new User();
        user.setAbout("About");
        user.setEmail("jane.doe@example.org");
        user.setId(1);
        user.setName("Name");
        user.setPassword("iloveyou");
        user.setPosts(new ArrayList<>());
        Optional<User> ofResult = Optional.of(user);
        when(userRepo.findById((Integer) any())).thenReturn(ofResult);
        assertThrows(ResourceNotFoundException.class, () -> postServiceImpl.getPostsByUser(123));
        verify(postRepo).findByUser((User) any());
        verify(userRepo).findById((Integer) any());
    }

    /**
     * Method under test: {@link PostServiceImpl#getPostsByUser(int)}
     */
    @Test
    void testGetPostsByUser3() {
        when(postRepo.findByUser((User) any())).thenReturn(new ArrayList<>());
        when(userRepo.findById((Integer) any())).thenReturn(Optional.empty());
        assertThrows(ResourceNotFoundException.class, () -> postServiceImpl.getPostsByUser(123));
        verify(userRepo).findById((Integer) any());
    }

    /**
     * Method under test: {@link PostServiceImpl#searchPosts(String)}
     */
    @Test
    void testSearchPosts() {
        ArrayList<Post> postList = new ArrayList<>();
        when(postRepo.findByTitleContainingIgnoreCaseOrContentContainingIgnoreCase((String) any(), (String) any()))
                .thenReturn(postList);
        List<Post> actualSearchPostsResult = postServiceImpl.searchPosts("Keyword");
        assertSame(postList, actualSearchPostsResult);
        assertTrue(actualSearchPostsResult.isEmpty());
        verify(postRepo).findByTitleContainingIgnoreCaseOrContentContainingIgnoreCase((String) any(), (String) any());
    }

    /**
     * Method under test: {@link PostServiceImpl#searchPosts(String)}
     */
    @Test
    void testSearchPosts2() {
        when(postRepo.findByTitleContainingIgnoreCaseOrContentContainingIgnoreCase((String) any(), (String) any()))
                .thenThrow(new ResourceNotFoundException("Resource Name", "Field Name", 42L));
        assertThrows(ResourceNotFoundException.class, () -> postServiceImpl.searchPosts("Keyword"));
        verify(postRepo).findByTitleContainingIgnoreCaseOrContentContainingIgnoreCase((String) any(), (String) any());
    }
}

