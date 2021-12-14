package com.hajofoglalo.controllers;

import com.hajofoglalo.model.Post;
import com.hajofoglalo.repositories.PostRepositroy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class PostController {

    private final PostRepositroy postRepository;

    @Autowired
    public PostController(PostRepositroy postRepository) {
        this.postRepository = postRepository;
    }

    @GetMapping("/posts")
    public String getPosts(Model model) {
        List<Post> posts = postRepository.findAll();
        model.addAttribute("posts", posts);
        return "posts";
    }
}
