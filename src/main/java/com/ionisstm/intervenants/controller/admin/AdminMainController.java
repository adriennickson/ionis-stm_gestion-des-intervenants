package com.ionisstm.intervenants.controller.admin;

import com.ionisstm.intervenants.model.Speaker;
import com.ionisstm.intervenants.model.User;
import com.ionisstm.intervenants.repository.SpeakerRepository;
import com.ionisstm.intervenants.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class AdminMainController {

    @Autowired
    private UserService userService;

    @Autowired
    private SpeakerRepository speakerRepository;

    @RequestMapping(value="/admin/home", method = RequestMethod.GET)
    public ModelAndView home(){
        ModelAndView modelAndView = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByUserName(auth.getName());
        modelAndView.addObject("user", user);
        modelAndView.setViewName("admin/home");
        return modelAndView;
    }

    @RequestMapping(value="/admin/search", method = RequestMethod.GET)
    public ModelAndView search(@RequestParam(required = false) String q){
        System.out.println(q);
        ModelAndView modelAndView = new ModelAndView();
        List<Speaker> speakers = speakerRepository.FindAllWithJoinSubjectByName( "%"+q+"%");
        modelAndView.addObject("speakers", speakers);
        modelAndView.setViewName("admin/search");
        return modelAndView;
    }

}
