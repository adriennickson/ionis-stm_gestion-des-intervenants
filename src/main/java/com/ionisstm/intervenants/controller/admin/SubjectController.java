package com.ionisstm.intervenants.controller.admin;

import com.ionisstm.intervenants.model.Subject;
import com.ionisstm.intervenants.model.User;
import com.ionisstm.intervenants.repository.SubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

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
        return "admin/subjects/form";
    }

    @RequestMapping(value="/admin/subjects/{id}/edit", method = RequestMethod.GET)
    public ModelAndView editSubject(@PathVariable("id") int id){
        ModelAndView modelAndView = new ModelAndView();
        Optional<Subject> s = subjectRepository.findById(id);
        modelAndView.setViewName("admin/subjects/form");
        if (s.isEmpty())
            modelAndView = new ModelAndView("redirect:/admin/subjects/");
        else
            modelAndView.addObject("subject", s.get());

        return modelAndView;
    }

    @RequestMapping(value="/admin/subjects/save", method = RequestMethod.POST)
    public String peristSubject(@ModelAttribute("subject") @Valid Subject subject, BindingResult bindingResult, @RequestParam(required=false) boolean isActive, @RequestParam(required=false) int id){
        if (bindingResult.hasErrors())
            return "/admin/subjects/form";
        if (isActive)
            subject.setActive(isActive);
        subject.setId(id);
        subjectRepository.save(subject);
        return "redirect:/admin/subjects/";
    }

    @RequestMapping(value="/admin/subjects/{id}/delete", method = RequestMethod.POST)
    public String peristSubject(@PathVariable("id") int id){
        // Todo: Vérifier avant la suppretion si l'enregistrement est utilisé comme clef étrangère dans une autre table
        subjectRepository.deleteById(id);
        return "redirect:/admin/subjects/";
    }

}
