package com.example.Blogapp.controller;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import com.example.Blogapp.entity.Category;
import com.example.Blogapp.entity.Comment;
import com.example.Blogapp.entity.Post;
import com.example.Blogapp.entity.User;
import com.example.Blogapp.service.CommentServiceImpl;
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

@ContextConfiguration(classes = {CommentController.class})
@ExtendWith(SpringExtension.class)
class CommentControllerTest {
    @Autowired
    private CommentController commentController;

    @MockBean
    private CommentServiceImpl commentServiceImpl;

    /**
     * Method under test: {@link CommentController#createComment(Comment, Integer)}
     */
    @Test
    void testCreateComment() throws Exception {
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

        Comment comment = new Comment();
        comment.setContent("Not all who wander are lost");
        comment.setId(1);
        comment.setPost(post);
        when(commentServiceImpl.createComment((Comment) any(), (Integer) any())).thenReturn(comment);

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

        Comment comment1 = new Comment();
        comment1.setContent("Not all who wander are lost");
        comment1.setId(1);
        comment1.setPost(post1);
        String content = (new ObjectMapper()).writeValueAsString(comment1);
        MockHttpServletRequestBuilder postResult = MockMvcRequestBuilders.post("/api/comments/comments");
        MockHttpServletRequestBuilder requestBuilder = postResult.param("postId", String.valueOf(1))
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(commentController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"id\":1,\"content\":\"Not all who wander are lost\",\"post\":{\"post_id\":1,\"title\":\"Dr\",\"content\":\"Not all"
                                        + " who wander are lost\",\"imageName\":\"Image Name\",\"addedDate\":0,\"user\":{\"id\":1,\"name\":\"Name\",\"email\":"
                                        + "\"jane.doe@example.org\",\"password\":\"iloveyou\",\"about\":\"About\",\"posts\":[]},\"category\":{\"catId\":123,"
                                        + "\"categoryTitle\":\"Dr\",\"categoryDescription\":\"Category Description\",\"posts\":[]},\"comments\":[]}}"));
    }

    /**
     * Method under test: {@link CommentController#deleteComment(Integer)}
     */
    @Test
    void testDeleteComment() throws Exception {
        doNothing().when(commentServiceImpl).deleteComment((Integer) any());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/api/comments/comments/{commentId}",
                123);
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(commentController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string("{\"message\":\"Comment deleted Succesfuylly\",\"success\":true}"));
    }

    /**
     * Method under test: {@link CommentController#deleteComment(Integer)}
     */
    @Test
    void testDeleteComment2() throws Exception {
        doNothing().when(commentServiceImpl).deleteComment((Integer) any());
        MockHttpServletRequestBuilder deleteResult = MockMvcRequestBuilders.delete("/api/comments/comments/{commentId}",
                123);
        deleteResult.characterEncoding("Encoding");
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(commentController)
                .build()
                .perform(deleteResult);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string("{\"message\":\"Comment deleted Succesfuylly\",\"success\":true}"));
    }
}

