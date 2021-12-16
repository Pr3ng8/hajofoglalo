package com.hajofoglalo.controllers;

import com.hajofoglalo.model.Post;
import com.hajofoglalo.model.User;
import com.hajofoglalo.repositories.PostRepositroy;
import com.hajofoglalo.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Objects;
import java.util.Optional;
import java.util.Set;

@RequestMapping("/posts")
@Controller
public class PostController {

    private final PostRepositroy postRepository;
    private final UserRepository userRepository;

    @Autowired
    public PostController(PostRepositroy postRepository, UserRepository userRepository) {
        this.postRepository = postRepository;
        this.userRepository = userRepository;
    }

    @GetMapping("/posts")
    public ModelAndView getPosts(Authentication authentication) {
        Optional<User> temp = userRepository.findUserByEmail( authentication.getName());
        Set<Post> posts = null;
        ModelAndView mav = new ModelAndView("posts/posts");

        if (temp.isPresent())
        {
            User user = temp.get();
            posts = user.getPosts();
        }

        mav.addObject("posts", posts);
        return mav;
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
    public ModelAndView savePost(@Validated Post post, Authentication authentication) {

        Optional<User> temp = userRepository.findUserByEmail( authentication.getName());
        ModelAndView mav = new ModelAndView("posts/form");

        if (temp.isPresent())
        {
            User user = temp.get();
            Post save = post;
            post.setUser(user);
            mav.addObject(postRepository.save(post));
        }

        return mav;

    }

    @PostMapping ("/delete/{postId}")
    public ModelAndView deletePost(@PathVariable Long postId, Authentication authentication) {

        Optional<User> temp = userRepository.findUserByEmail( authentication.getName());
        Post post = postRepository.getById(postId);

        ModelAndView mav = new ModelAndView("redirect:/posts/posts");

        if (temp.isPresent())
        {
            User user = temp.get();

            if ( user.getPosts().contains(post) || user.getRole().contains("author"))
            {
                postRepository.delete(post);
                mav.addObject("msg", "Sikeres törlés");
            }else
            {
                mav.addObject("msg", "Sikertelen törlés!");
            }
        }

        return mav;
    }

    @GetMapping("/edit/{postId}")
    public ModelAndView editPost(@PathVariable Long postId, Authentication authentication) {
        ModelAndView mav = new ModelAndView();
        Optional<User> tmpuser = userRepository.findUserByEmail( authentication.getName());
        Optional<Post> tmppost = postRepository.findById(postId);

        if (tmpuser.isPresent() && tmppost.isPresent() )
        {
            User user = tmpuser.get();
            Post post = tmppost.get();

            if (Objects.equals(post.getUser().getEmail(), user.getEmail()))
            {
                mav.setViewName("posts/form");
                mav.addObject("post", post);
            }
            else{
                mav.setViewName("/posts/posts");
                mav.addObject("msg", "Ehhez nincs hozzáférése!");
            }
        }
        return mav;
    }

    @PostMapping("/update/{postId}")
    public ModelAndView updatePost(@Validated Post post, BindingResult result, @PathVariable Long postId, Authentication authentication) {
        ModelAndView mav = new ModelAndView("posts/form");

        Optional<User> temp = userRepository.findUserByEmail( authentication.getName());

        if (temp.isPresent())
        {
            User user = temp.get();
            Post save = post;
            post.setUser(user);
            mav.addObject(postRepository.save(save));
            mav.addObject("msg", "Sikeres frissítés");
        } else
        {
            mav.addObject("msg", "Sikertelen frissítés!");
        }

        return mav;
    }

}
