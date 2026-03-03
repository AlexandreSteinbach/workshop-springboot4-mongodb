package com.alexandresteinbach.workshopmongo.services;

import com.alexandresteinbach.workshopmongo.domain.User;
import com.alexandresteinbach.workshopmongo.dto.UserDTO;
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

    public User insert(User obj) {
        return repo.insert(obj);
    }

    public void delete(String id) {
        findById(id);
        repo.deleteById(id);
    }

    public User update(User obj) {
        User newObj = repo.findById(obj.getId()).orElseThrow(() -> new ObjectNotFoundException("Object not found."));
        updateData(newObj, obj);
        return repo.save(newObj);
    }

    private void updateData(User newObj, User obj) {
        newObj.setName(obj.getName());
        newObj.setEmail(obj.getEmail());
    }

    // UserService handles DTO conversion to allow future database access/logic
    public User fromDTO(UserDTO objDto) {
        return new User(objDto.getId(), objDto.getName(), objDto.getEmail());
    }

}
