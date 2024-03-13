package com.vaganov.task.user;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.RestTemplate;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.security.Principal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {


    private final PasswordEncoder passwordEncoder;
    private final UserRepository repository;
    public void changePassword(ChangePasswordRequest request, Principal connectedUser) {

        var user = (User) ((UsernamePasswordAuthenticationToken) connectedUser).getPrincipal();

        // check if the current password is correct
        if (!passwordEncoder.matches(request.getCurrentPassword(), user.getPassword())) {
            throw new IllegalStateException("Wrong password");
        }
        // check if the two new passwords are the same
        if (!request.getNewPassword().equals(request.getConfirmationPassword())) {
            throw new IllegalStateException("Password are not the same");
        }

        // update the password
        user.setPassword(passwordEncoder.encode(request.getNewPassword()));

        // save the new password
        repository.save(user);
    }

    @Cacheable("users")
    public List<User> getUsers() throws IOException {
        String url = "https://jsonplaceholder.typicode.com/users";
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<List<User>> rateResponse =
            restTemplate.exchange(url,
                HttpMethod.GET, null, new ParameterizedTypeReference<List<User>>() {
                });
        List<User> users = rateResponse.getBody();

        for(User user: users){
            boolean exists = repository.existsById(user.getId());
            if(!exists) {
                repository.save(user);
            }
        }
        return users;
    }

    @Cacheable("users")
    public User getUser(Integer id) throws JsonProcessingException {
        String url = "https://jsonplaceholder.typicode.com/users/" +id;
        RestTemplate restTemplate = new RestTemplate();
        User user = restTemplate
            .getForObject(url, User.class);
        boolean exists = repository.existsById(user.getId());
        if(!exists) {
            repository.save(user);
        }
        return user;
    }
    @Cacheable("users")
    public User newUser (UserRequest newTask) throws JsonProcessingException {
        String url = "https://jsonplaceholder.typicode.com/users/";
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<UserRequest> request = new HttpEntity<>(newTask);
        User user= restTemplate.postForObject(url, request, User.class);
        return user;
    }
    @Cacheable("users")
    public void deleteUser(Integer id){
        RestTemplate restTemplate = new RestTemplate();
        String entityUrl = "https://jsonplaceholder.typicode.com/users/"+ id;
        restTemplate.delete(entityUrl);
    }



}
