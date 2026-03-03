package com.alexandresteinbach.workshopmongo.config;

import com.alexandresteinbach.workshopmongo.domain.Post;
import com.alexandresteinbach.workshopmongo.domain.User;
import com.alexandresteinbach.workshopmongo.repository.PostRepository;
import com.alexandresteinbach.workshopmongo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

import java.text.SimpleDateFormat;
import java.util.TimeZone;

@Configuration
public class Instantiation implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PostRepository postRepository;

    @Override
    public void run(String... args) throws Exception {

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        sdf.setTimeZone(TimeZone.getTimeZone("GMT"));

        userRepository.deleteAll();
        postRepository.deleteAll();

        User maria = new User(null, "Maria Brown", "maria@gmail.com");
        User alex = new User(null, "Alex Green", "alex@gmail.com");
        User bob = new User(null, "Bob Grey", "bob@gmail.com");

        Post post1 = new Post(null, sdf.parse("21/02/2026"), "Partiu viagem", "Vou viajar para São Paulo, abraços", maria);
        Post post2 = new Post(null, sdf.parse("28/02/2026"), "Bom dia!", "Acordei feliz hoje!", maria);

        userRepository.saveAll(Arrays.asList(maria,alex,bob));
        postRepository.saveAll(Arrays.asList(post1,post2));
    }
}
