package com.ionisstm.intervenants.controller;

import com.ionisstm.intervenants.model.Subject;
import com.ionisstm.intervenants.model.User;
import com.ionisstm.intervenants.repository.SubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;

@Controller
public class SubjectController {
    @Autowired
    private SubjectRepository subjectRepository;

    @RequestMapping(value="/admin/subjects", method = RequestMethod.GET)
    public ModelAndView getAllSubjects(){
        ModelAndView modelAndView = new ModelAndView();
        List<Subject> subjects = subjectRepository.findAll();
        modelAndView.addObject("subjects", subjects);
        modelAndView.setViewName("admin/subjects/home");
        return modelAndView;
    }

    @RequestMapping(value="/admin/subjects/add", method = RequestMethod.GET)
    public String newSubject(Subject subject){
        subject.setActive(true);
        return "admin/subjects/add";
    }

    @RequestMapping(value="/admin/subjects/add", method = RequestMethod.POST)
    public String addSubject(@ModelAttribute("subject") @Valid Subject subject, BindingResult bindingResult, @RequestParam(required=false) boolean isActive){
        if (bindingResult.hasErrors())
            return "/admin/subjects/add";
        if (isActive)
            subject.setActive(isActive);
        System.out.println(subject.isActive());
        subjectRepository.save(subject);
        return "redirect:/admin/subjects/";
    }

}
