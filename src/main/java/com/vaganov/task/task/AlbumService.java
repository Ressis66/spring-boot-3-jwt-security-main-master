package com.vaganov.task.task;

import com.fasterxml.jackson.core.JsonProcessingException;

import java.io.IOException;
import java.util.List;

public interface AlbumService {
  List<Album> getAlbums() throws JsonProcessingException;
  Album getAlbum(Integer id) throws JsonProcessingException;
  Album newAlbum (AlbumRequest newTask) throws JsonProcessingException;
  void updateAlbum(Integer id, AlbumRequest albumRequest) throws IOException;
  void deleteAlbum(Integer id);
}
