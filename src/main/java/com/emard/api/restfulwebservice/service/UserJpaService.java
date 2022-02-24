package com.emard.api.restfulwebservice.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import com.emard.api.restfulwebservice.response.User;
import com.emard.api.restfulwebservice.response.UserRepository;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserJpaService {
    
private final UserRepository repository;
    

    public List<User> findAll(){
        return repository.findAll();
    }

    public User save(User user){
        return repository.save(user);
    }

    public Optional<User> findOne(Integer id){
        //users.stream().filter(u -> u.getId() == id).findFirst()
        return repository.findById(id);
    }

    public void deleteById(Integer id) {
        
        repository.deleteById(id);
    }

}
