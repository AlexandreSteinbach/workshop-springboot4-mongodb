package com.alexandresteinbach.workshopmongo.services;

import com.alexandresteinbach.workshopmongo.domain.User;
import com.alexandresteinbach.workshopmongo.repository.UserRepository;
import com.alexandresteinbach.workshopmongo.services.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository repo;

    public List<User> findAll() {
        return repo.findAll();
    }

    public User findById(String id) {
        User user = repo.findById(id).orElseThrow(() -> new ObjectNotFoundException("Object not found."));
        return user;
    }
}
