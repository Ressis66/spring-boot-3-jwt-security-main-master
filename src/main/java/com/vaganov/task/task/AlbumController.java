package com.vaganov.task.task;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/v1/albums")
public class AlbumController {

  private AlbumService albumService;

  public AlbumController(AlbumService albumService){
    this.albumService=albumService;
  }

  @PreAuthorize("hasAuthority('albums:create') or hasAuthority('admin:create')")
  @PostMapping("/newAlbum")
  public Album newAlbum (@RequestBody AlbumRequest newTask) throws JsonProcessingException {
    return albumService.newAlbum(newTask);
  }
  @PreAuthorize("hasAuthority('albums:read') or hasAuthority('admin:read')")
  @GetMapping("/albums")
  public List<Album> getAllTasks() throws JsonProcessingException {
    return albumService.getAlbums();
  }
  @PreAuthorize("hasAuthority('albums:read') or hasAuthority('admin:read')")
  @GetMapping("/album/{id}")
  public Album getAlbum(@PathVariable Integer id) throws JsonProcessingException {
    return albumService.getAlbum(id);
  }

  @PreAuthorize("hasAuthority('albums:update') or hasAuthority('admin:update')")
  @PutMapping("/albumToAlbum/{id}")
  public void updateAlbumExecutor(@RequestBody AlbumRequest updated, @PathVariable Integer id) throws IOException {
    albumService.updateAlbum(id, updated);
  }
  @PreAuthorize("hasAuthority('albums:delete') or hasAuthority('admin:delete')")
  @DeleteMapping("/album/{id}")
  public void deleteAlbum(@PathVariable Integer id){
    albumService.deleteAlbum(id);
  }


}
