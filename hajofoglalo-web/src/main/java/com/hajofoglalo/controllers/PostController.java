package com.hajofoglalo.controllers;

import com.hajofoglalo.model.Post;
import com.hajofoglalo.repositories.PostRepositroy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RequestMapping("/posts")
@Controller
public class PostController {

    private final PostRepositroy postRepository;

    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

    @Autowired
    public PostController(PostRepositroy postRepository) {
        this.postRepository = postRepository;
    }

    @GetMapping
    public String getPosts(Model model) {
        List<Post> posts = postRepository.findAll();
        model.addAttribute("posts", posts);
        return "posts/posts";
    }

    @GetMapping("/{postId}")
    public ModelAndView getPostById(@PathVariable Integer postId) {
        ModelAndView mav = new ModelAndView("posts/posts");
        mav.addObject(postRepository.findById(postId));
        return mav;
    }

    @GetMapping("/create")
    public String createPost() {
        return "posts/create";
    }

    @PostMapping ("/save")
    public ModelAndView savePost(@Validated Post post, BindingResult result) {
        ModelAndView mav = new ModelAndView("posts/posts");
        mav.addObject(postRepository.save(post));
        return mav;
    }

    @PostMapping ("/delete/{postId}")
    public ModelAndView deletePost(@PathVariable Integer postId) {
        ModelAndView mav = new ModelAndView("posts/posts");
        mav.addObject(postRepository.findById(postId));
        return mav;
    }

    @GetMapping("/edit/{postId}")
    public ModelAndView editPost(@PathVariable Integer postId) {
        ModelAndView mav = new ModelAndView("posts/posts");
        mav.addObject(postRepository.findById(postId));
        return mav;
    }

    @PostMapping("/update/{postId}")
    public ModelAndView updatePost(@Validated Post post, BindingResult result, @PathVariable Integer postId) {
        ModelAndView mav = new ModelAndView("posts/posts");
        mav.addObject(postRepository.findById(postId));
        return mav;
    }

}
