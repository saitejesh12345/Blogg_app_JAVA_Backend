package com.example.Blogapp.controller;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.anyInt;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import com.example.Blogapp.entity.Category;
import com.example.Blogapp.entity.Post;
import com.example.Blogapp.entity.User;
import com.example.Blogapp.service.PostServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;

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
import org.springframework.web.multipart.MultipartFile;

@ContextConfiguration(classes = {PostController.class})
@ExtendWith(SpringExtension.class)
class PostControllerTest {
    @Autowired
    private PostController postController;

    @MockBean
    private PostServiceImpl postServiceImpl;

    /**
     * Method under test: {@link PostController#createPost(Post, Integer, Integer)}
     */
    @Test
    void testCreatePost() throws Exception {
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
        when(postServiceImpl.createPost((Post) any(), (Integer) any(), (Integer) any())).thenReturn(post);

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
        String content = (new ObjectMapper()).writeValueAsString(post1);
        MockHttpServletRequestBuilder postResult = MockMvcRequestBuilders.post("/newpost");
        MockHttpServletRequestBuilder paramResult = postResult.param("categoryId", String.valueOf(1));
        MockHttpServletRequestBuilder requestBuilder = paramResult.param("userId", String.valueOf(1))
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(postController).build().perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"post_id\":1,\"title\":\"Dr\",\"content\":\"Not all who wander are lost\",\"imageName\":\"Image Name\",\"addedDate"
                                        + "\":0,\"user\":{\"id\":1,\"name\":\"Name\",\"email\":\"jane.doe@example.org\",\"password\":\"iloveyou\",\"about\":\"About"
                                        + "\",\"posts\":[]},\"category\":{\"catId\":123,\"categoryTitle\":\"Dr\",\"categoryDescription\":\"Category Description"
                                        + "\",\"posts\":[]},\"comments\":[]}"));
    }

    /**
     * Method under test: {@link PostController#getPostsByUser(int)}
     */
    @Test
    void testGetPostsByUser() throws Exception {
        when(postServiceImpl.getPostsByUser(anyInt())).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/user/{userId}", 123);
        MockMvcBuilders.standaloneSetup(postController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Method under test: {@link PostController#getPostsByUser(int)}
     */
    @Test
    void testGetPostsByUser2() throws Exception {
        when(postServiceImpl.getPostsByUser(anyInt())).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/user/{userId}", 123);
        getResult.characterEncoding("Encoding");
        MockMvcBuilders.standaloneSetup(postController)
                .build()
                .perform(getResult)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Method under test: {@link PostController#getPostById(Integer)}
     */
    @Test
    void testGetPostById() throws Exception {
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
        when(postServiceImpl.getPostById((Integer) any())).thenReturn(post);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/{postId}", 123);
        MockMvcBuilders.standaloneSetup(postController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"post_id\":1,\"title\":\"Dr\",\"content\":\"Not all who wander are lost\",\"imageName\":\"Image Name\",\"addedDate"
                                        + "\":0,\"user\":{\"id\":1,\"name\":\"Name\",\"email\":\"jane.doe@example.org\",\"password\":\"iloveyou\",\"about\":\"About"
                                        + "\",\"posts\":[]},\"category\":{\"catId\":123,\"categoryTitle\":\"Dr\",\"categoryDescription\":\"Category Description"
                                        + "\",\"posts\":[]},\"comments\":[]}"));
    }

    /**
     * Method under test: {@link PostController#searchPosts(String)}
     */
    @Test
    void testSearchPosts() throws Exception {
        when(postServiceImpl.searchPosts((String) any())).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/search").param("keyword", "foo");
        MockMvcBuilders.standaloneSetup(postController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Method under test: {@link PostController#getAllPosts()}
     */
    @Test
    void testGetAllPosts() throws Exception {
        when(postServiceImpl.getAllPosts()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/");
        MockMvcBuilders.standaloneSetup(postController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Method under test: {@link PostController#getAllPosts()}
     */
    @Test
    void testGetAllPosts2() throws Exception {
        when(postServiceImpl.getAllPosts()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/");
        getResult.characterEncoding("Encoding");
        MockMvcBuilders.standaloneSetup(postController)
                .build()
                .perform(getResult)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Method under test: {@link PostController#deletePost(Integer)}
     */
    @Test
    void testDeletePost() throws Exception {
        doNothing().when(postServiceImpl).deletePost((Integer) any());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/{postId}", 123);
        MockMvcBuilders.standaloneSetup(postController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    /**
     * Method under test: {@link PostController#deletePost(Integer)}
     */
    @Test
    void testDeletePost2() throws Exception {
        doNothing().when(postServiceImpl).deletePost((Integer) any());
        MockHttpServletRequestBuilder deleteResult = MockMvcRequestBuilders.delete("/{postId}", 123);
        deleteResult.characterEncoding("Encoding");
        MockMvcBuilders.standaloneSetup(postController)
                .build()
                .perform(deleteResult)
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    /**
     * Method under test: {@link PostController#updatePost(Integer, Post)}
     */
    @Test
    void testUpdatePost() throws Exception {
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
        when(postServiceImpl.updatePost((Integer) any(), (Post) any())).thenReturn(post);

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
        String content = (new ObjectMapper()).writeValueAsString(post1);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.put("/{postId}", 123)
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(postController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"post_id\":1,\"title\":\"Dr\",\"content\":\"Not all who wander are lost\",\"imageName\":\"Image Name\",\"addedDate"
                                        + "\":0,\"user\":{\"id\":1,\"name\":\"Name\",\"email\":\"jane.doe@example.org\",\"password\":\"iloveyou\",\"about\":\"About"
                                        + "\",\"posts\":[]},\"category\":{\"catId\":123,\"categoryTitle\":\"Dr\",\"categoryDescription\":\"Category Description"
                                        + "\",\"posts\":[]},\"comments\":[]}"));
    }

    /**
     * Method under test: {@link PostController#fileUpload(MultipartFile, Integer)}
     */
    @Test
    void testFileUpload() throws Exception {
        MockHttpServletRequestBuilder postResult = MockMvcRequestBuilders.post("/upload/{postId}", 123);
        MockHttpServletRequestBuilder requestBuilder = postResult.param("file", String.valueOf((Object) null));
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(postController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(415));
    }
}

