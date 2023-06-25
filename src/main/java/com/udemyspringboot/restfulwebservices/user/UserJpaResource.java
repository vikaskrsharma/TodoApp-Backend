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
@RequestMapping(path = "/jpa")
public class UserJpaResource {
    
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PostRepository postRepository;

    @GetMapping(path = "/users")
    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    @GetMapping(path = "/users/{id}")
    public EntityModel<User> getUserById(@PathVariable Integer id){
         User user = userRepository.findById(id).get();
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
        User savedUser =userRepository.save(user);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(savedUser.getId()).toUri();
        return ResponseEntity.created(location).build();
    }

    @DeleteMapping(path = "/users/{id}")
    public void deleteUserById(@PathVariable Integer id){
        userRepository.deleteById(id);
    }

    @GetMapping(path = "/users/{id}/posts")
    public List<Post> getPostsForUser(@PathVariable Integer id){
        User user = userRepository.findById(id).get();
        if(user == null){
            throw new UserNotFoundException("id: " + id);
        }
        return user.getPosts();
    }

    @PostMapping("/users/{id}/posts")
    public ResponseEntity<Post> CreateUser(@PathVariable Integer id, @Valid @RequestBody Post post){
        User user = userRepository.findById(id).get();
        if(user == null){
            throw new UserNotFoundException("id: " + id);
        }
        post.setUser(user);
        Post savedPost = postRepository.save(post);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(savedPost.getId()).toUri();
        return ResponseEntity.created(location).build();
    }
}
