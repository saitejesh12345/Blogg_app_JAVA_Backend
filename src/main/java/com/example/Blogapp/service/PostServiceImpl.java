package com.example.Blogapp.service;


import com.example.Blogapp.dao.CategoryRepo;
import com.example.Blogapp.dao.PostRepo;
import com.example.Blogapp.dao.UserRepo;
import com.example.Blogapp.entity.Category;
import com.example.Blogapp.entity.Post;
import com.example.Blogapp.entity.User;
import com.example.Blogapp.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
//import java.util.stream.Collectors;

//import static jdk.nashorn.internal.objects.NativeDebug.map;

//import static jdk.nashorn.internal.objects.NativeDebug.map;

@Service
public class PostServiceImpl {

    @Autowired
    private PostRepo postRepo;
@Autowired
private UserRepo userRepo;

@Autowired
private CategoryRepo categoryRepo;


    //create
    public Post createPost(Post post,Integer userId,Integer categoryId) {

        User user =userRepo.findById(userId)
                .orElseThrow(()->new ResourceNotFoundException("User","id",userId));

        Category category=categoryRepo.findById(categoryId)
                .orElseThrow(()-> new ResourceNotFoundException("Category","catId",categoryId));

        post.setImageName("default.png");
        post.setAddedDate(new Date());
        post.setUser(user);
        post.setCategory(category);
 return postRepo.save(post);
       // post
       // return postRepo.save(post);

    }

    //update the post
//    Post updatePost( Integer postId,Post post) {
//        Post post1 = this.postRepo.findById(postId)
//                .orElseThrow(()-> new ResourceNotFoundException("Post","post_id",postId));

        //after getting id updating the user Details
        //   category1.setCategoryId(category.getCategoryId());
      //  post1.setCategoryTitle(category.getCategoryTitle());
        //post1.setCategoryDescription(category.getCategoryDescription());
      //  ....................
//        postRepo.save(post1);
//        return post1;
//        //return null;
//    }

    //update the post
    public Post updatePost(Integer postId, Post post) {
        Post existingPost = postRepo.findById(postId)
                .orElseThrow(() -> new ResourceNotFoundException("Post", "postId", postId));

        existingPost.setTitle(post.getTitle());
        existingPost.setContent(post.getContent());

        return postRepo.save(existingPost);
    }

    //delete
    public void deletePost(Integer postId) {
        if(postRepo.existsById(postId)){
            postRepo.deleteById(postId);
        }else{
            throw new ResourceNotFoundException("Post", "postId", postId);
        }
    }

    //get All posts
    public List<Post> getAllPosts() {
        return postRepo.findAll();
    }

    //get single post by Id
    public Post getPostById(Integer postId) {
        return postRepo.findById(postId)
                .orElseThrow(() -> new ResourceNotFoundException("Post", "postId", postId));
    }
    //get All posts by Category
//   public List<Post> getPostsByCategory(int  categoryId) {
//        Category cat=
//                categoryRepo.findById(categoryId).orElseThrow(()->
//                new ResourceNotFoundException("Category","catId",categoryId));
//       List<Post> posts=categoryRepo.findByCategory(cat);
//     List<Post> postdto=  posts.stream().map((post)
//             -> this.map(posts,Post.class)).collect(Collectors.toList());
//return postdto;
//    }

    //get All posts by Category
//    public List<Post> getPostsByCategory(int categoryId ) {
//        Category cat = categoryRepo.findById(categoryId)
//                .orElseThrow(() -> new ResourceNotFoundException("Category", "catId", categoryId));
//        List<Post> posts = categoryRepo.findByCategory(cat);
//        return posts;
//    }


    //get ALl posts by userId
    public List<Post> getPostsByUser(int userId) {
        User user = userRepo.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User", "userId", userId));
        List<Post> posts = postRepo.findByUser(user);
        return posts;
    }
    //search post by keyword
    public List<Post> searchPosts(String keyword) {
        return postRepo.findByTitleContainingIgnoreCaseOrContentContainingIgnoreCase(keyword, keyword);
    }
}
