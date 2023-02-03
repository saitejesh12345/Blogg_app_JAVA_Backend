package com.example.Blogapp.service;

import com.example.Blogapp.dao.CommentRepo;
import com.example.Blogapp.dao.PostRepo;
import com.example.Blogapp.entity.Comment;
import com.example.Blogapp.entity.Post;
import com.example.Blogapp.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CommentServiceImpl {

    @Autowired
    private CommentRepo commentRepo;
    @Autowired
    private PostRepo postRepo;


    //add comment to post

    //Explanation for this SERVICE LOGIC
    //This service class is responsible for creating a comment
    // and associating it with a post. The createComment method takes in a Comment object
    // and a postId as arguments. It then retrieves the Post object using the postId and the
    // findById method of the PostRepository. If the post is not found, it throws a
    // ResourceNotFoundException. If the post is found, the service sets
    // the post object to the comment object and then saves the comment object to the database.
    public Comment createComment(Comment comment, Integer postId) {
        Post post = postRepo.findById(postId)
                .orElseThrow(() -> new ResourceNotFoundException("Post", "post id", postId));
        comment.setPost(post);
        return commentRepo.save(comment);
    }



    //delete comment

    //Explanation for this SERVICE LOGIC

    // This is a method called "deleteComment" in Java
    // which takes a single parameter "commentId" of type Integer.
    // The method appears to be used for deleting a comment from a database.
    //The first line is using the findById method of commentRepo which is returning
    // an Optional<Comment> object which represents a container object
    // which may or may not contain a non-null value.
    //Then it is checking if the comment returned is null or not.
    // If it is null, it throws a ResourceNotFoundException with message "Comment", "id", commentId.
    //Otherwise, it is using the delete method of commentRepo to delete the comment from the database.
    //It is important to notice that the delete method is being passed the comment object,
    // not the commentId, this is because the findById method is returning an Optional<Comment> object, not a commentId.

    public void deleteComment(Integer commentId){
        // Retrieve the comment from the database
       Comment comment = commentRepo.findById(commentId).get();
        // Check if the comment exists
        if (comment == null) {
            throw new ResourceNotFoundException("Comment","id", commentId);
        }
        // Delete the comment
        commentRepo.delete(comment);
    }

}
