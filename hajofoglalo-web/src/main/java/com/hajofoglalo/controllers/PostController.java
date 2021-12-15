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
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Optional;

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
        if (authentication.getCredentials().toString() == "a")
        {

        }
        List<Post> posts = postRepository.findAll();
        model.addAttribute("posts", posts);
        return "posts/posts";
    }

    @GetMapping("/{postId}")
    public ModelAndView getPostById(@PathVariable Long postId) {
        ModelAndView mav = new ModelAndView("posts/post");
        Optional<Post> temp = postRepository.findById(postId);

        if ( temp.isEmpty() )
        {
            mav.addObject("post", null);
        }else
        {
            Post post = temp.get();
            mav.addObject("post", post);
        }

        return mav;
    }

    @GetMapping("/create")
    public String createPost() {
        return "posts/form";
    }

    @PostMapping("/save")
    public ModelAndView savePost(@Validated Post post) {
        ModelAndView mav = new ModelAndView("posts/form");
        Post save = post;
        authentication.getName();
        mav.addObject(postRepository.save(post));
        return mav;
    }

    @PostMapping ("/delete/{postId}")
    public ModelAndView deletePost(@PathVariable Long postId) {
        ModelAndView mav = new ModelAndView("posts/post");
        mav.addObject(postRepository.findById(postId));
        return mav;
    }

    @GetMapping("/edit/{postId}")
    public ModelAndView editPost(@PathVariable Long postId) {
        ModelAndView mav = new ModelAndView("posts/form");
        mav.addObject(postRepository.findById(postId));
        return mav;
    }

    @PostMapping("/update/{postId}")
    public ModelAndView updatePost(@Validated Post post, BindingResult result, @PathVariable Long postId) {
        ModelAndView mav = new ModelAndView("posts/form");
        mav.addObject(postRepository.findById(postId));
        return mav;
    }

}
