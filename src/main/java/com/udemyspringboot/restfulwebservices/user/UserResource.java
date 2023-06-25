package com.udemyspringboot.restfulwebservices.user;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
public class UserResource {

    @Autowired
    private UserDaoService userDaoService;

    @GetMapping(path = "/users")
    public List<User> getAllUsers(){
        return userDaoService.findAllUsers();
    }

    @GetMapping(path = "/users/{id}")
    public EntityModel<User> getUserById(@PathVariable Integer id){
         User user = userDaoService.findUserById(id);
         if(user==null){
             throw new UserNotFoundException("id: " + id);
         }

         EntityModel<User> entityModel = EntityModel.of(user);
        WebMvcLinkBuilder link = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass())
                .getAllUsers());
        entityModel.add(link.withRel("all-users"));
         return entityModel;
    }

    @PostMapping("/users")
    public ResponseEntity<User> CreateUser(@Valid @RequestBody User user){
        User savedUser = userDaoService.createUser(user);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(savedUser.getId()).toUri();
        return ResponseEntity.created(location).build();
    }

    @DeleteMapping(path = "/users/{id}")
    public void deleteUserById(@PathVariable Integer id){
        userDaoService.deleteUserById(id);
    }
}
