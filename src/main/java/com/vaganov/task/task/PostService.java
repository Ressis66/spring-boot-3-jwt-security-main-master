package com.vaganov.task.task;

import com.fasterxml.jackson.core.JsonProcessingException;

import java.io.IOException;
import java.util.List;

public interface PostService {
  List<Post> getPosts() throws JsonProcessingException;
  Post getPost(Integer id) throws JsonProcessingException;
  Post newPost (PostRequest newTask) throws JsonProcessingException;
  void updatePost(Integer id, PostRequest updated) throws IOException;
  void deletePost(Integer id);
}
