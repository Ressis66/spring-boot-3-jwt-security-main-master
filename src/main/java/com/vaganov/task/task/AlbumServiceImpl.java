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
public class AlbumServiceImpl implements AlbumService {

  @Cacheable("albums")
  public List<Album> getAlbums() throws JsonProcessingException {
    String url = "https://jsonplaceholder.typicode.com/albums";
    RestTemplate restTemplate = new RestTemplate();
    ResponseEntity<List<Album>> rateResponse =
        restTemplate.exchange(url,
            HttpMethod.GET, null, new ParameterizedTypeReference<List<Album>>() {
            });
    List<Album> albums= rateResponse.getBody();

   return albums;
  }

  @Cacheable("albums")
  public Album getAlbum(Integer id) throws JsonProcessingException {
    String url = "https://jsonplaceholder.typicode.com/albums/" +id;
    RestTemplate restTemplate = new RestTemplate();
    Album album = restTemplate
        .getForObject(url, Album.class);
    return album;
  }
  @Cacheable("albums")
  public Album newAlbum (AlbumRequest newTask) throws JsonProcessingException {
    String url = "https://jsonplaceholder.typicode.com/albums";
    RestTemplate restTemplate = new RestTemplate();
    HttpEntity<AlbumRequest> request = new HttpEntity<>(newTask);
    Album album= restTemplate.postForObject(url, request, Album.class);
    return album;
  }

  @Cacheable("albums")
  public void updateAlbum(Integer id, AlbumRequest update) throws IOException {
    String url = "https://jsonplaceholder.typicode.com/albums/"+id;
    RestTemplate restTemplate = new RestTemplate();
    HttpEntity<AlbumRequest> requestUpdate = new HttpEntity<>(update);
    restTemplate.exchange(url, HttpMethod.PUT, requestUpdate, Void.class);
  }
  @Cacheable("albums")
  public void deleteAlbum(Integer id){
    RestTemplate restTemplate = new RestTemplate();
    String entityUrl = "https://jsonplaceholder.typicode.com/albums/"+ id;
    restTemplate.delete(entityUrl);
  }

}
