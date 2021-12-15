package com.hajofoglalo.controllers;

import com.hajofoglalo.model.Comment;
import com.hajofoglalo.repositories.CommentRepository;
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
import java.util.Optional;

@RequestMapping("/comments")
@Controller
public class CommentsController {

    private final CommentRepository commentRepository;

    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

    @Autowired
    public CommentsController(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    @GetMapping
    public String getComment(Model model) {
        List<Comment> comments = commentRepository.findAll();
        model.addAttribute("posts", comments);
        return "posts/posts";
    }

    @GetMapping("/{commentId}")
    public ModelAndView getCommentById(@PathVariable Long commentId) {
        ModelAndView mav = new ModelAndView("posts/post");
        Optional<Comment> temp = commentRepository.findById(commentId);

        if ( temp.isEmpty() )
        {
            mav.addObject("comment", null);
        }else
        {
            Comment comment = temp.get();
            mav.addObject("comment", comment);
        }

        return mav;
    }

    @GetMapping("/create")
    public String createComment() {
        return "posts/form";
    }

    @PostMapping("/save")
    public ModelAndView saveComment(@Validated Comment comment) {
        ModelAndView mav = new ModelAndView("posts/form");
        Comment save = comment;
        authentication.getName();
        mav.addObject(commentRepository.save(comment));
        return mav;
    }

    @PostMapping ("/delete/{commentId}")
    public ModelAndView deleteComment(@PathVariable Long commentId) {
        ModelAndView mav = new ModelAndView("posts/post");
        mav.addObject(commentRepository.findById(commentId));
        return mav;
    }

    @GetMapping("/edit/{commentId}")
    public ModelAndView editComment(@PathVariable Long commentId) {
        ModelAndView mav = new ModelAndView("posts/form");
        mav.addObject(commentRepository.findById(commentId));
        return mav;
    }

    @PostMapping("/update/{commentId}")
    public ModelAndView updateComment(@Validated Comment post, BindingResult result, @PathVariable Long commentId) {
        ModelAndView mav = new ModelAndView("posts/form");
        mav.addObject(commentRepository.findById(commentId));
        return mav;
    }
}
