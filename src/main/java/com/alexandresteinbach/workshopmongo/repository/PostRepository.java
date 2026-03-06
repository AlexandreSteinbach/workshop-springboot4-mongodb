package com.alexandresteinbach.workshopmongo.repository;

import com.alexandresteinbach.workshopmongo.domain.Post;
import com.alexandresteinbach.workshopmongo.domain.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends MongoRepository<Post, String> {

    //Query methods (step 1)
    List<Post> findByTitleContainingIgnoreCase(String text);
}
