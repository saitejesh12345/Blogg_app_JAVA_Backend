//package com.example.Blogapp.controller;
//
////import com.example.Blogapp.entity.FileService;
////import org.springframework.beans.factory.annotation.Autowired;
////import org.springframework.beans.factory.annotation.Value;
////import org.springframework.stereotype.Controller;
////import org.springframework.ui.Model;
//import com.example.Blogapp.dao.PostRepo;
//import com.example.Blogapp.entity.Post;
////import com.example.Blogapp.service.PostServiceImpl;
////import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.MediaType;
//import org.springframework.web.bind.annotation.*;
//import org.springframework.web.multipart.MultipartFile;
//
//import java.io.File;
//import java.io.FileOutputStream;
//import java.io.IOException;
//
//@RestController
//public class FileController {
//
//
//    private  PostRepo postRepo;
//
//    public FileController(PostRepo postRepo) {
//        this.postRepo = postRepo;
//
//    }
//    //
////    @Value("${image.upload.path}")
////    private String imageUploadPath;
////    @Autowired
////    private FileService fileService;
////
////    @PostMapping("/upload")
////    public String uploadImage(@RequestParam("file") MultipartFile file) {
////        try {
////            return fileService.uploadImage("path/to/save/image", file);
////          //  return fileService.uploadImage("C:\Users\2144103\OneDrive - Cognizant\Pictures\Camera Roll", file);
////        } catch (IOException e) {
////            e.printStackTrace();
////            return "uploadFailure";
////        }
////    }
////@RequestMapping(value = "/upload",
////        method = RequestMethod.POST,
////        consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
////@PostMapping
////public String fileUpload(@RequestParam("file") MultipartFile file,@PathVariable Integer post_Id) throws IOException
////{
////    File convertFile = new File("C:/Users/2144103/Documents" + file.getOriginalFilename());
////    convertFile.createNewFile();
////
////    try (FileOutputStream fout = new FileOutputStream(convertFile))
////    {
////        fout.write(file.getBytes());
////    }
////    catch (Exception exe)
////    {
////        exe.printStackTrace();
////    }
////    //get the image name
////    String imageName = file.getOriginalFilename();
////   //update the related entity in the database
////  Post entity = postRepo.findById(post_id);
////    entity.setImageName(imageName);
////   Post updatePost =postService.updatePost(post_id,entity);
//////    postRepo.save(entity);
////    return "File has uploaded successfully";
////}
//}
