package com.emard.api.restfulwebservice.web;

import java.net.URI;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import com.emard.api.restfulwebservice.exception.UserNotFoudException;
import com.emard.api.restfulwebservice.response.Post;
import com.emard.api.restfulwebservice.response.User;
import com.emard.api.restfulwebservice.service.UserDaoService;
import com.emard.api.restfulwebservice.service.UserJpaService;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/jpa")
@AllArgsConstructor
public class UserJpaResource {
    private final UserJpaService userDaoService;

    @GetMapping("/users")
    public CollectionModel<User> allUsers() {
        List<User> list = userDaoService.findAll();
        CollectionModel<User> model = CollectionModel.of(list);
        for (User it : list) {
            WebMvcLinkBuilder linkToUsers = linkTo(methodOn(this.getClass()).findOne(it.getId()));
            model.add(linkToUsers.withRel("/api/users/"+it.getId()));
        }
        return model;
    }

    @GetMapping("/users/{id}")
    public EntityModel<User> findOne(@PathVariable Integer id) {
        Optional<User> opt = userDaoService.findOne(id);
        if (!opt.isPresent())
            throw new UserNotFoudException("id-"+id);
        EntityModel<User> entityModel = EntityModel.of(opt.get());
        WebMvcLinkBuilder linkToUsers = linkTo(methodOn(this.getClass()).allUsers());
        entityModel.add(linkToUsers.withRel("all-users"));
        return entityModel;
    }

    @PostMapping("/users")
    public ResponseEntity<User> createUser(@Valid @RequestBody User user) {
        user = userDaoService.save(user);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(user.getId()).toUri();
        return ResponseEntity.created(uri).body(user);
    }

    @DeleteMapping("/users/{id}")
    public void deleteUser(@PathVariable Integer id) {
        userDaoService.deleteById(id);
        
    }

    @GetMapping("/users/{id}/posts")
    public List<Post> postByUser(@PathVariable Integer id) {
        Optional<User> uOptional= userDaoService.findOne(id);
        if(!uOptional.isPresent()) throw new UserNotFoudException("id-"+id);
        return uOptional.get().getPosts();
    }
}
