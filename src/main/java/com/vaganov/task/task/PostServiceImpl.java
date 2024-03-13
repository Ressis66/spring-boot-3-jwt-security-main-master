package com.vaganov.task.task;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.List;

@Service
public class PostServiceImpl implements PostService {

  @Cacheable("posts")
  public List<Post> getPosts() throws JsonProcessingException {
    String url = "https://jsonplaceholder.typicode.com/posts";
    RestTemplate restTemplate = new RestTemplate();
    ResponseEntity<List<Post>> rateResponse =
        restTemplate.exchange(url,
            HttpMethod.GET, null, new ParameterizedTypeReference<List<Post>>() {
            });
    List<Post> posts= rateResponse.getBody();

    return posts;
  }

  @Cacheable("posts")
  public Post getPost(Integer id) throws JsonProcessingException {
    String url = "https://jsonplaceholder.typicode.com/posts/" +id;
    RestTemplate restTemplate = new RestTemplate();
    Post post = restTemplate
        .getForObject(url, Post.class);
    return post;
  }
  @Cacheable("posts")
  public Post newPost (PostRequest newTask) throws JsonProcessingException {
    String url = "https://jsonplaceholder.typicode.com/posts";
    RestTemplate restTemplate = new RestTemplate();
    HttpEntity<PostRequest> request = new HttpEntity<>(newTask);
    Post post= restTemplate.postForObject(url, request, Post.class);
    return post;
  }
  @Cacheable("posts")
  public void updatePost(Integer id, PostRequest updated) throws IOException {
    String url = "https://jsonplaceholder.typicode.com/posts/"+ id;
    RestTemplate restTemplate = new RestTemplate();
    HttpEntity<PostRequest> requestUpdate = new HttpEntity<>(updated);
    restTemplate.exchange(url, HttpMethod.PUT, requestUpdate, Void.class);
  }
  @Cacheable("posts")
  public void deletePost(Integer id){
    RestTemplate restTemplate = new RestTemplate();
    String entityUrl = "https://jsonplaceholder.typicode.com/posts/"+ id;
    restTemplate.delete(entityUrl);
  }

}
