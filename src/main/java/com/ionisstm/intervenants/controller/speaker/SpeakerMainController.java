package com.ionisstm.intervenants.controller.speaker;

import com.ionisstm.intervenants.model.Address;
import com.ionisstm.intervenants.model.Speaker;
import com.ionisstm.intervenants.model.User;
import com.ionisstm.intervenants.repository.AddressRepository;
import com.ionisstm.intervenants.repository.SpeakerRepository;
import com.ionisstm.intervenants.repository.UserRepository;
import com.ionisstm.intervenants.service.SpeakerService;
import com.ionisstm.intervenants.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.Optional;

@Controller
public class SpeakerMainController {

    @Autowired
    private UserService userService;
    @Autowired
    private SpeakerRepository speakerRepository;
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AddressRepository addressRepository;
    @Autowired
    private SpeakerService speakerService;


    @RequestMapping(value="/speaker/home", method = RequestMethod.GET)
    public ModelAndView home(){
        ModelAndView modelAndView = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByUserName(auth.getName());
        modelAndView.addObject("user", user);
        modelAndView.setViewName("speaker/home");
        return modelAndView;
    }

    @RequestMapping(value={"/speaker/address"}, method = RequestMethod.GET)
        public ModelAndView address(){
            ModelAndView modelAndView = new ModelAndView();
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            User user = userService.findUserByUserName(auth.getName());
            modelAndView.addObject("user", user);
            modelAndView.setViewName("speaker/address");
        return modelAndView;

    }


    @RequestMapping(value="/speaker/save", method = RequestMethod.POST)
    public String peristSpeaker(@ModelAttribute("speaker") @Valid Speaker speaker, BindingResult bindingResult){
        if (bindingResult.hasErrors())
            return "/speaker/profile";
        //speaker.setId(id);
        speaker = speakerRepository.save(speaker);
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByUserName(auth.getName());
        user.setSpeaker(speaker);
        userRepository.save(user);

        return "redirect:/speaker/home";
    }

    @RequestMapping(value="/speaker/profil", method = RequestMethod.GET)
    public ModelAndView editSpeaker(){
        ModelAndView modelAndView = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByUserName(auth.getName());
        modelAndView.setViewName("speaker/profile");

        if (user.getSpeaker() == null){
            Speaker s = new Speaker();
            user.setSpeaker(s);

            //modelAndView = new ModelAndView("redirect:/speaker/home/");
        }

        modelAndView.addObject("speaker", user.getSpeaker());

        return modelAndView;
    }






}
