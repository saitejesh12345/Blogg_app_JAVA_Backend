package com.example.Blogapp.controller;


import com.example.Blogapp.entity.Comment;
import com.example.Blogapp.payloads.ApiResponse;
import com.example.Blogapp.service.CommentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/comments")
public class CommentController {

    @Autowired
    private CommentServiceImpl commentService;


    //Add comment Rest api


    // This is a REST API endpoint for creating a new comment.
    // The @PostMapping annotation tells the framework that this method should handle
    // HTTP POST requests to the "/comments" endpoint. It takes two parameters,
    // the comment object, which is passed in the request body, and the postId of the post
    // to which the comment is being added, which is passed as a request parameter.


    //The @RequestBody annotation tells the framework to bind the request body
    // to the "comment" object. This means that when a client makes a POST request
    // to this endpoint with a JSON payload in the request body, the framework will convert
    // that JSON into a Comment object and pass it as the first argument to this method.


    //The @RequestParam annotation is used to bind the postId from request parameter
    // to the postId variable in the controller method. This means that when a client makes
    // a POST request to this endpoint and includes a query parameter called "postId", the
    // value of that parameter will be passed as the second argument to this method.


    //The method uses the createComment method from the service layer to create the
    // new comment, then it creates a new ResponseEntity with the newComment as the
    // body and a HttpStatus of CREATED(201) as the status code, which informs the client
    // that the request was successful and a new resource has been created.


    //In summary, this REST API endpoint allows clients to create new comments by
    // making a HTTP POST request to the "/comments" endpoint with a JSON payload
    // representing the comment in the request body and a query parameter representing
    // the postId to which the comment is being added.
    @PostMapping("/comments")
    public ResponseEntity<Comment> createComment
            (@RequestBody Comment comment, @RequestParam("postId") Integer postId) {
        Comment newComment = commentService.createComment(comment, postId);
        return new ResponseEntity<Comment>(newComment, HttpStatus.CREATED);

    }

    //delete Comment

    //This method uses the @DeleteMapping annotation to handle
    // HTTP DELETE requests to the "/comments/{commentId}" endpoint.
    // It takes a single parameter, the commentId, which is passed in the
    // URI path via the @PathVariable annotation. The method uses the deleteComment
    // method from the service layer to delete the comment,
    // then it returns a ResponseEntity with a status code of 200 (OK) to
    // indicate that the request was successful.

    //The @PathVariable annotation is used to bind the commentId
    // from the URI path to the commentId variable in the controller method.

    //The ResponseEntity.ok().build() is used to send a response back to the client with a 200 OK status code,
    // indicating that the request was successful.


    //In summary, this REST API endpoint allows clients to delete a comment by
    // making a HTTP DELETE request to the "/comments/{commentId}" endpoint with the
    // commentId in the URI path. The endpoint uses the service method to delete
    // the comment and returns a 200 OK status code to confirm that the operation
    // was successful.
    @DeleteMapping("/comments/{commentId}")
    public ResponseEntity<?> deleteComment(@PathVariable(value ="commentId" ) Integer commentId) {
        commentService.deleteComment(commentId);
      //  return ResponseEntity.ok().build();
        return new ResponseEntity(new ApiResponse("Comment deleted Succesfuylly",true), HttpStatus.CREATED);

    }

}