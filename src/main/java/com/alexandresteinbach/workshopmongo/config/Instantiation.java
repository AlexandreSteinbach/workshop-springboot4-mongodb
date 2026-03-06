package com.alexandresteinbach.workshopmongo.config;

import com.alexandresteinbach.workshopmongo.domain.Post;
import com.alexandresteinbach.workshopmongo.domain.User;
import com.alexandresteinbach.workshopmongo.dto.AuthorDTO;
import com.alexandresteinbach.workshopmongo.dto.CommentDTO;
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

        userRepository.saveAll(Arrays.asList(maria,alex,bob));

        Post post1 = new Post(null, sdf.parse("21/02/2026"), "Partiu viagem", "Vou viajar para São Paulo, abraços", new AuthorDTO(maria));
        Post post2 = new Post(null, sdf.parse("28/02/2026"), "Bom dia!", "Acordei feliz hoje!", new AuthorDTO(maria));

        CommentDTO c1 = new CommentDTO("Boa viagem, mano!", sdf.parse("24/02/2026"), new AuthorDTO(alex));
        CommentDTO c2 = new CommentDTO("Aproveite!", sdf.parse("25/02/2026"), new AuthorDTO(bob));
        CommentDTO c3 = new CommentDTO("Tenha um ótimo dia!", sdf.parse("28/02/2026"), new AuthorDTO(alex));

        post1.getComments().addAll(Arrays.asList(c1,c2));
        post2.getComments().add(c3);

        postRepository.saveAll(Arrays.asList(post1,post2));

        maria.getPosts().addAll(Arrays.asList(post1,post2));
        userRepository.save(maria);
    }
}
