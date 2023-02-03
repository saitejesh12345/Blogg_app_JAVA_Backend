package com.example.Blogapp.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.example.Blogapp.dao.CommentRepo;
import com.example.Blogapp.dao.PostRepo;
import com.example.Blogapp.entity.Category;
import com.example.Blogapp.entity.Comment;
import com.example.Blogapp.entity.Post;
import com.example.Blogapp.entity.User;
import com.example.Blogapp.exceptions.ResourceNotFoundException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Optional;

import org.junit.jupiter.api.Disabled;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {CommentServiceImpl.class})
@ExtendWith(SpringExtension.class)
class CommentServiceImplTest {
    @MockBean
    private CommentRepo commentRepo;

    @Autowired
    private CommentServiceImpl commentServiceImpl;

    @MockBean
    private PostRepo postRepo;

    /**
     * Method under test: {@link CommentServiceImpl#createComment(Comment, Integer)}
     */
    @Test
    void testCreateComment() {
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
        when(commentRepo.save((Comment) any())).thenReturn(comment);

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
        Optional<Post> ofResult = Optional.of(post1);
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

        Comment comment1 = new Comment();
        comment1.setContent("Not all who wander are lost");
        comment1.setId(1);
        comment1.setPost(post2);
        assertSame(comment, commentServiceImpl.createComment(comment1, 123));
        verify(commentRepo).save((Comment) any());
        verify(postRepo).findById((Integer) any());
        assertEquals(post2, comment1.getPost());
    }

    /**
     * Method under test: {@link CommentServiceImpl#createComment(Comment, Integer)}
     */
    @Test
    void testCreateComment2() {
        when(commentRepo.save((Comment) any()))
                .thenThrow(new ResourceNotFoundException("Resource Name", "Field Name", 42L));

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

        Comment comment = new Comment();
        comment.setContent("Not all who wander are lost");
        comment.setId(1);
        comment.setPost(post1);
        assertThrows(ResourceNotFoundException.class, () -> commentServiceImpl.createComment(comment, 123));
        verify(commentRepo).save((Comment) any());
        verify(postRepo).findById((Integer) any());
    }

    /**
     * Method under test: {@link CommentServiceImpl#createComment(Comment, Integer)}
     */
    @Test
    void testCreateComment3() {
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
        when(commentRepo.save((Comment) any())).thenReturn(comment);
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

        Comment comment1 = new Comment();
        comment1.setContent("Not all who wander are lost");
        comment1.setId(1);
        comment1.setPost(post1);
        assertThrows(ResourceNotFoundException.class, () -> commentServiceImpl.createComment(comment1, 123));
        verify(postRepo).findById((Integer) any());
    }

    /**
     * Method under test: {@link CommentServiceImpl#deleteComment(Integer)}
     */
    @Test
    void testDeleteComment() {
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
        Optional<Comment> ofResult = Optional.of(comment);
        doNothing().when(commentRepo).delete((Comment) any());
        when(commentRepo.findById((Integer) any())).thenReturn(ofResult);
        commentServiceImpl.deleteComment(123);
        verify(commentRepo).findById((Integer) any());
        verify(commentRepo).delete((Comment) any());
    }

    /**
     * Method under test: {@link CommentServiceImpl#deleteComment(Integer)}
     */
    @Test
    void testDeleteComment2() {
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
        Optional<Comment> ofResult = Optional.of(comment);
        doThrow(new ResourceNotFoundException("Resource Name", "Field Name", 42L)).when(commentRepo)
                .delete((Comment) any());
        when(commentRepo.findById((Integer) any())).thenReturn(ofResult);
        assertThrows(ResourceNotFoundException.class, () -> commentServiceImpl.deleteComment(123));
        verify(commentRepo).findById((Integer) any());
        verify(commentRepo).delete((Comment) any());
    }

    /**
     * Method under test: {@link CommentServiceImpl#deleteComment(Integer)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testDeleteComment3() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException
        //       at com.example.Blogapp.service.CommentServiceImpl.deleteComment(CommentServiceImpl.java:58)
        //   See https://diff.blue/R013 to resolve this issue.

        doNothing().when(commentRepo).delete((Comment) any());
        when(commentRepo.findById((Integer) any())).thenReturn(null);
        commentServiceImpl.deleteComment(123);
    }
}

