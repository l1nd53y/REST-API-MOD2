package com.l1nd53y.mod2.controller;

import com.l1nd53y.mod2.exception.ResourceNotFoundException;
import com.l1nd53y.mod2.model.User;
import com.l1nd53y.mod2.repository.UserRepository;
import com.l1nd53y.mod2.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import java.util.List;

import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

@CrossOrigin("*") //Cross-origin resource sharing (CORS) "allows you to specify in a flexible way what kind of cross domain requests are authorized"
@RestController // "Every request handling method of the controller class automatically serializes return objects into HttpResponse"
@RequestMapping("/api/users") // Used to map web requests to Spring Controller methods
public class UserController {

    @Autowired // allows Spring to resolve and inject collaborating beans into our bean
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }

    @GetMapping // Annotation for mapping HTTP GET requests onto specific handler methods
    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    // build create user REST API
    @PostMapping // Annotation for mapping HTTP POST requests onto specific handler methods
    public User createUser(@RequestBody User user) {
        return this.userService.save(user);
    }

    // build get user by id REST API
    @GetMapping("{id}") // Annotation for mapping HTTP GET requests onto specific handler methods
    public ResponseEntity<User> getUserById(@PathVariable  long id){
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User does not exist with id:" + id));
        return ResponseEntity.ok(user);
    }

    // build update user REST API
    @PutMapping("{id}") // Annotation for mapping HTTP PUT requests onto specific handler methods
    public ResponseEntity<User> updateUser(@PathVariable long id,@RequestBody User userDetails) { //@PathVariable annotation to extract the templated part of the URI (id), @RequestBody maps the HttpRequest body to a transfer or domain object, enabling automatic deserialization of the inbound HttpRequest body onto a Java object
        User updateUser = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User does not exist with id: " + id));

        updateUser.setFirstName(userDetails.getFirstName());
        updateUser.setLastName(userDetails.getLastName());
        updateUser.setEmailId(userDetails.getEmailId());
        updateUser.setPassword(userDetails.getPassword());

        userRepository.save(updateUser);

        return ResponseEntity.ok(updateUser);
    }

    // build delete user REST API
    @DeleteMapping("{id}") // Annotation for mapping HTTP DELETE requests onto specific handler methods.
    public ResponseEntity<HttpStatus> deleteUser(@PathVariable long id){

        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User does not exist with id: " + id));

        userRepository.delete(user);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }

}
