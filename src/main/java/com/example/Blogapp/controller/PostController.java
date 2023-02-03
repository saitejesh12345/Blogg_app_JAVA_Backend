package com.example.Blogapp.controller;


import com.example.Blogapp.entity.Category;

import com.example.Blogapp.entity.Post;
import com.example.Blogapp.exceptions.ResourceNotFoundException;
import com.example.Blogapp.payloads.ApiResponse;
import com.example.Blogapp.service.PostServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
//import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

@RestController
//@RequestMapping("/api/posts")
public class PostController {

    @Autowired
    private PostServiceImpl postService;



    @Value("${project.image}")
    private String path;
    //create

   // ResponseEntity :- Errors code and error message can be sended and status codes
 //   @PostMapping("/user/{userId}/category/{categoryId}/posts")
  //  public ResponseEntity<Post> createPost(@Valid
//                                           @RequestBody Post post, @PathVariable Integer userId,
//                                           @PathVariable Integer categoryId) {
//
//      Post createPost = this.postService.createPost(post, userId, categoryId);
//        return new ResponseEntity(new ApiResponse("Post Added Succesfuylly",true), HttpStatus.OK);
    //    return new ResponseEntity<Post>(createPost, HttpStatus.CREATED);
    //.....................................................................
        // return new ResponseEntity<>(createUserDto, HttpStatus.CREATED);
        //return new ResponseEntity(new ApiResponse("Category Added Succesfuylly",true), HttpStatus.OK);
   // }


    //create
    @PostMapping("/newpost")
    public ResponseEntity<Post> createPost(@Valid @RequestBody Post post,@RequestParam Integer userId, @RequestParam Integer categoryId) {
        Post newPost = postService.createPost(post,userId,categoryId);
        return new ResponseEntity<Post>(newPost, HttpStatus.CREATED);
       // return ResponseEntity.ok().body(newPost);
    }

    //get by user
    //@GetMapping("/user/{userId}")
//    public ResponseEntity<List<Post>> getPostsByUser(@PathVariable int userId) {
//        List<Post> posts = postService.getPostsByUser(userId);
//        return new ResponseEntity<List<Post>>(posts, HttpStatus.OK);
//    }
    //get by user
     @GetMapping("/user/{userId}")
    public ResponseEntity<List<Post>> getPostsByUser(@PathVariable(value = "userId") int userId) {
        List<Post> posts = postService.getPostsByUser(userId);
        return ResponseEntity.ok().body(posts);
    }

    //get by category
//    @GetMapping("/category/{categoryId}")
//    public ResponseEntity<List<Post>> getPostsByCategory(@PathVariable(value = "categoryId")
//                                                             int categoryId) {
//        List<Post> posts = postService.getPostsByCategory(categoryId);
//        return;
//    }

//get single post by id
    @GetMapping("/{postId}")
    public ResponseEntity<Post> getPostById(@PathVariable(value = "postId") Integer postId) {
        Post post = postService.getPostById(postId);
        return ResponseEntity.ok().body(post);
    }

    //search
    @GetMapping("/search")
    public ResponseEntity<List<Post>> searchPosts(@RequestParam String keyword) {
        List<Post> posts = postService.searchPosts(keyword);
        return ResponseEntity.ok().body(posts);
    }

    //get all posts
    @GetMapping
    public ResponseEntity<List<Post>> getAllPosts() {
        List<Post> posts = postService.getAllPosts();
        return ResponseEntity.ok().body(posts);
    }
    //delete post
    @DeleteMapping("/{postId}")
    public ResponseEntity<?> deletePost(@PathVariable(value = "postId") Integer postId) {
        postService.deletePost(postId);
        return ResponseEntity.ok().build();
    }
    //update post
    @PutMapping("/{postId}")
    public ResponseEntity<Post> updatePost(@PathVariable(value = "postId") Integer postId,
                                           @RequestBody Post post) {
        Post updatedPost = postService.updatePost(postId, post);
        return ResponseEntity.ok().body(updatedPost);
    }


    //upload image
//    @PostMapping("/post/image/upload/{postId}")
//    public ResponseEntity<ImageResponse> uploadPostImage(
//            @RequestParam("image") MultipartFile image,@PathVariable Integer postId) throws IOException {
//       String fileName = fileService.uploadImage(path,image);
//      //  return null;
//    Post postsample =postService.getPostById(postId);
//   postsample.setImageName(fileName);
//   Post.updatePost =postService.updatePost(postsample,postId);
//return  new ResponseEntity<Post>(updatePost,HttpStatus.OK);
//    }
//@PostMapping("/post/image/upload/{postId}")
//public ResponseEntity<ImageResponse> uploadPostImage(
//        @RequestParam("image") MultipartFile image,@PathVariable Integer postId) throws IOException {
//    if(postService.getPostById(postId) == null)
//        throw new ResourceNotFoundException("Post","postId", postId);
//    String fileName = fileService.uploadImage("path/to/upload",image);
//    Post updatePost =postService.updatePost(postId,postsample);
//    ImageResponse response = new ImageResponse();
//    response.setImageName(fileName);
//    return  new ResponseEntity<ImageResponse>(response,HttpStatus.OK);
//}
    @RequestMapping(value = "/upload/{postId}",
            method = RequestMethod.POST,
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @PostMapping
    public String fileUpload(@RequestParam("file") MultipartFile file,@PathVariable Integer postId) throws IOException
    {

        File convertFile = new File(" C:/Users/2144103/Documents" + file.getOriginalFilename());
        convertFile.createNewFile();

        try (FileOutputStream fout = new FileOutputStream(convertFile))
        {
            fout.write(file.getBytes());
        }
        catch (Exception exe)
        {
            exe.printStackTrace();
        }
        //get the image name
        String imageName = file.getOriginalFilename();
        //update the related entity in the database
        Post entity = postService.getPostById(postId);
     //   Post entity = postService.findById(post_id);
        entity.setImageName(imageName);
        Post updatePost =postService.updatePost(postId,entity);
//    postRepo.save(entity);
        return "File has uploaded successfully";
    }

    //download uploaded image rest api

}
