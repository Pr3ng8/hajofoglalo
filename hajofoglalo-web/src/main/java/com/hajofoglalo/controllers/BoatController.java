package com.hajofoglalo.controllers;

import com.hajofoglalo.model.Boat;
import com.hajofoglalo.repositories.BoatRepository;
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
import java.util.Optional;

@RequestMapping("/boats")
@Controller
public class BoatController {

    private final BoatRepository boatRepository;

    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

    @Autowired
    public BoatController(BoatRepository boatRepository) {
        this.boatRepository = boatRepository;
    }

    @GetMapping
    public String getBoats(Model model) {
        List<Boat> boats = boatRepository.findAll();
        model.addAttribute("boats", boats);
        return "boats/boats";
    }

    @GetMapping("/{boatId}")
    public ModelAndView getBoatById(@PathVariable Long boatId) {
        ModelAndView mav = new ModelAndView("boats/boat");
        Optional<Boat> temp = boatRepository.findById(boatId);

        if ( temp.isEmpty() )
        {
            mav.addObject("boat", null);
        }else
        {
            Boat boat = temp.get();
            mav.addObject("boat", boat);
        }

        return mav;
    }

    @GetMapping("/create")
    public String createBoat() {
        return "boats/form";
    }

    @PostMapping("/save")
    public ModelAndView saveBoat(@Validated Boat post) {
        ModelAndView mav = new ModelAndView("boats/form");
        Boat save = post;
        authentication.getName();
        mav.addObject(boatRepository.save(post));
        return mav;
    }

    @PostMapping ("/delete/{boatId}")
    public ModelAndView deleteBoat(@PathVariable Long boatId) {
        ModelAndView mav = new ModelAndView("boats/boats");
        mav.addObject(boatRepository.findById(boatId));
        return mav;
    }

    @GetMapping("/edit/{boatId}")
    public ModelAndView editBoat(@PathVariable Long boatId) {
        ModelAndView mav = new ModelAndView("boats/form");
        mav.addObject(boatRepository.findById(boatId));
        return mav;
    }

    @PostMapping("/update/{boatId}")
    public ModelAndView updateBoat(@Validated Boat post, BindingResult result, @PathVariable Long boatId) {
        ModelAndView mav = new ModelAndView("boats/form");
        mav.addObject(boatRepository.findById(boatId));
        return mav;
    }

}
