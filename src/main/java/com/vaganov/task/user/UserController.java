package com.vaganov.task.user;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.fasterxml.jackson.core.JsonProcessingException;

import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;
import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService service;

    @PatchMapping
    public ResponseEntity<?> changePassword(
          @RequestBody ChangePasswordRequest request,
          Principal connectedUser
    ) {
        service.changePassword(request, connectedUser);
        return ResponseEntity.ok().build();
    }
    @PreAuthorize("hasAuthority('user:create') or hasAuthority('admin:create')")
    @PostMapping("/newUser")
    public User newUser (@RequestBody UserRequest newTask) throws JsonProcessingException {
        return service.newUser(newTask);
    }
    @PreAuthorize("hasAuthority('user:read') or hasAuthority('admin:read')")
    @GetMapping("/users")
    public List<User> getAllUsers() throws IOException{
        return service.getUsers();
    }
    @PreAuthorize("hasAuthority('user:read') or hasAuthority('admin:read')")
    @GetMapping("/user/{id}")
    public User getUser(@PathVariable Integer id) throws JsonProcessingException {
        return service.getUser(id);
    }

    @PreAuthorize("hasAuthority('user:delete') or hasAuthority('admin:delete')")
    @DeleteMapping("/user/{id}")
    public void deleteUser(@PathVariable Integer id){
        service.deleteUser(id);
    }
}
