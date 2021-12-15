package com.hajofoglalo.controllers;

import com.hajofoglalo.model.User;
import com.hajofoglalo.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Optional;

@RequestMapping("/users")
@Controller
public class UsersController  {

    private final UserRepository userRepository;

    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

    @Autowired
    public UsersController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping
    public String getBoats(Model model) {
        List<User> boats = userRepository.findAll();
        model.addAttribute("users", boats);
        return "users/users";
    }

    @GetMapping("/{userId}")
    public ModelAndView getBoatById(@PathVariable Long userId) {
        ModelAndView mav = new ModelAndView("users/user");
        Optional<User> temp = userRepository.findById(userId);

        if ( temp.isEmpty() )
        {
            mav.addObject("boat", null);
        }else
        {
            User user = temp.get();
            mav.addObject("boat", user);
        }

        return mav;
    }

    @PostMapping ("/delete/{userId}")
    public ModelAndView deleteBoat(@PathVariable Long userId) {
        ModelAndView mav = new ModelAndView("users/users");
        mav.addObject(userRepository.findById(userId));
        return mav;
    }

}
