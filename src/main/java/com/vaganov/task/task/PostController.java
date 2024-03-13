package com.vaganov.task.task;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/v1/posts")
public class PostController {

  private PostService postService;

  public PostController(PostService postService){
    this.postService=postService;
  }

  @PreAuthorize("hasAuthority('posts:create') or hasAuthority('admin:create')")
  @PostMapping("/newPost")
  public Post newPost (@RequestBody PostRequest newPost) throws JsonProcessingException {
    return postService.newPost(newPost);
  }
  @PreAuthorize("hasAuthority('posts:read') or hasAuthority('admin:read')")
  @GetMapping("/posts")
  public List<Post> getAllPosts() throws JsonProcessingException {
    return postService.getPosts();
  }
  @PreAuthorize("hasAuthority('posts:read') or hasAuthority('admin:read')")
  @GetMapping("/post")
  public Post getPost(@PathVariable Integer id) throws JsonProcessingException {
    return postService.getPost(id);
  }

  @PreAuthorize("hasAuthority('posts:update') or hasAuthority('admin:update')")
  @PutMapping("/postToUser/{id}")
  public void updatePostExecutor(@RequestBody PostRequest updated, @PathVariable Integer id) throws IOException {
    postService.updatePost(id, updated);
  }
  @PreAuthorize("hasAuthority('posts:delete') or hasAuthority('admin:delete')")
  @DeleteMapping("/post/{id}")
  public void deletePost(@PathVariable Integer id){
    postService.deletePost(id);
  }



}
