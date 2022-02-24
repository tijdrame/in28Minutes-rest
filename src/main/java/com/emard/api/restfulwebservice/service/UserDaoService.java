package com.emard.api.restfulwebservice.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import com.emard.api.restfulwebservice.response.User;

import org.springframework.stereotype.Service;

@Service
public class UserDaoService {
    
    private static List<User> users = new ArrayList<>();

    private static int usersCount = 3;
    static {
        users.add(new User(1, "Adam", LocalDate.now(), null));
        users.add(new User(2, "Eve", LocalDate.now(), null));
        users.add(new User(3, "Jack", LocalDate.now(), null));
    }

    public List<User> findAll(){
        return users;
    }

    public User save(User user){
        if(user.getId()==null){
            user.setId(++usersCount);
        }
        users.add(user);
        return user;
    }

    public Optional<User> findOne(Integer id){
        return users.stream().filter(u -> u.getId() == id).findFirst();
    }

    public User deleteById(Integer id) {
        Iterator<User> iterator = users.iterator();
        while(iterator.hasNext()){
            User us = iterator.next();
            if(us.getId() == id) {
                iterator.remove();
                return us;
            }
        }
        return null;
    }

}
