package com.ionisstm.intervenants.controller.admin;

import com.ionisstm.intervenants.model.Address;
import com.ionisstm.intervenants.model.Session;
import com.ionisstm.intervenants.model.Speaker;
import com.ionisstm.intervenants.model.Subject;
import com.ionisstm.intervenants.repository.AddressRepository;
import com.ionisstm.intervenants.repository.SessionRepository;
import com.ionisstm.intervenants.repository.SpeakerRepository;
import com.ionisstm.intervenants.repository.SubjectRepository;
import com.ionisstm.intervenants.service.UploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.ServletContext;
import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

@Controller
public class AdminSpeakerController {
    @Autowired
    private SpeakerRepository speakerRepository;

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private SubjectRepository subjectRepository;

    @Autowired
    private SessionRepository sessionRepository;

    @Autowired
    UploadService uploadService;

    @RequestMapping(value="/admin/speakers", method = RequestMethod.GET)
    public ModelAndView getAllSpeakers(){
        ModelAndView modelAndView = new ModelAndView();
        List<Speaker> speakers = speakerRepository.findAll();
        modelAndView.addObject("speakers", speakers);
        modelAndView.setViewName("admin/speakers/home");
        return modelAndView;
    }

    @RequestMapping(value="/admin/speakers/add", method = RequestMethod.GET)
    public String newPeaker(Speaker speaker){
        return "admin/speakers/form";
    }

    @RequestMapping(value="/admin/speakers/{id}/edit", method = RequestMethod.GET)
    public ModelAndView editSpeakers(@PathVariable("id") int id){
        ModelAndView modelAndView = new ModelAndView();
        Optional<Speaker> s = speakerRepository.findById(id);
        modelAndView.setViewName("admin/speakers/form");
        if (s.isEmpty())
            modelAndView = new ModelAndView("redirect:/admin/speakers/");
        else
            modelAndView.addObject("speaker", s.get());

        return modelAndView;
    }

    @RequestMapping(value="/admin/speakers/{id}/detail", method = RequestMethod.GET)
    public ModelAndView detailSpeakers(@PathVariable("id") int id){
        ModelAndView modelAndView = new ModelAndView();
        Optional<Speaker> s = speakerRepository.findById(id);
        modelAndView.setViewName("admin/speakers/detail");
        if (s.isEmpty())
            modelAndView = new ModelAndView("redirect:/admin/speakers/");
        else{
            modelAndView.addObject("speaker", s.get());
        }
        return modelAndView;
    }

    @RequestMapping(value="/admin/speakers/save", method = RequestMethod.POST)
    public String peristSpeaker(@ModelAttribute("speaker") @Valid Speaker speaker, BindingResult bindingResult, @RequestParam(required=false) int id){
        if (bindingResult.hasErrors())
            return "/admin/speakers/form";
        speaker.setId(id);
        speakerRepository.save(speaker);
        return "redirect:/admin/speakers/";
    }

    @RequestMapping(value="/admin/speakers/{id}/delete", method = RequestMethod.POST)
    public String deleteSpeaker(@PathVariable("id") int id){
        // Todo: Vérifier avant la suppretion si l'enregistrement est utilisé comme clef étrangère dans une autre table
        speakerRepository.deleteById(id);
        return "redirect:/admin/speakers/";
    }

    //###############################
    // Address
    //###############################
    @RequestMapping(value="/admin/speakers/{id}/address", method = RequestMethod.GET)
    public String newPeakerAddress(Address address, @PathVariable("id") int id){
        return "admin/speakers/address/form";
    }

    @RequestMapping(value="/admin/speakers/{id}/address", method = RequestMethod.POST)
    public String peristSpeakerAddress(@ModelAttribute("address") @Valid Address address, BindingResult bindingResult, @PathVariable("id") int id){
        if (bindingResult.hasErrors())
            return "/admin/speakers/address/form";
        Optional<Speaker> s = speakerRepository.findById(id);
        if (s.isPresent()){
            address.setSpeaker(s.get());
            addressRepository.save(address);
        }
        return "redirect:/admin/speakers/";
    }

    //###############################
    // subjects
    //###############################
    @RequestMapping(value="/admin/speakers/{id}/subjects", method = RequestMethod.GET)
    public ModelAndView setPeakerSubjects(@PathVariable("id") int id){
        ModelAndView modelAndView = new ModelAndView();

        Optional<Speaker> s = speakerRepository.findById(id);
        if (s.isEmpty())
            modelAndView = new ModelAndView("redirect:/admin/speakers/");
        else{
            Speaker speaker = s.get();
            modelAndView.addObject("speaker", speaker);
            List<Subject> subjects = subjectRepository.findAll();
            List<String[]> subjectArray = subjects.stream().map( subject -> new String[]{
                    subject.getId()+"",
                    subject.getName(),
                    speaker.getSubjects().stream().filter(sub -> sub.getId() == subject.getId()).collect(Collectors.toList()).size() > 0 ? "true" : "false" }  ).collect(Collectors.toList());
            modelAndView.addObject("subjects", subjectArray);
            modelAndView.setViewName("admin/speakers/subjects/form");
        }
        return modelAndView;
    }

    @RequestMapping(value="/admin/speakers/{id}/subjects", method = RequestMethod.POST)
    public ModelAndView savePeakerSubjects(@PathVariable("id") int id, @RequestParam(value = "subjects") String[] paramValues){
        ModelAndView modelAndView = new ModelAndView();
        Set<Subject> subjects = new HashSet<>(subjectRepository.findAllById(Arrays.stream(paramValues).map(Integer::parseInt).collect(Collectors.toList())));
        Optional<Speaker> s = speakerRepository.findById(id);
        s.ifPresent(speaker -> {
            speaker.setSubjects(subjects);
            speakerRepository.save(speaker);
        });
        modelAndView = new ModelAndView("redirect:/admin/speakers/");
        return modelAndView;
    }

    //########################
    // Session
    //########################
    @RequestMapping(value="/admin/speakers/{id}/session", method = RequestMethod.GET)
    public ModelAndView setPeakerSession(@PathVariable("id") int id, Session session){
        ModelAndView modelAndView = new ModelAndView();
        Optional<Speaker> s = speakerRepository.findById(id);
        if (s.isEmpty())
            modelAndView = new ModelAndView("redirect:/admin/speakers/");
        else{
            Speaker speaker = s.get();
            List<Subject> subjects = subjectRepository.findAll();
            List<Integer> years = new ArrayList<>();
            for (int i = 2015; i < 2030; i++) { years.add(i); }
            modelAndView.addObject("subjects", subjects);
            modelAndView.addObject("years", years);
            modelAndView.setViewName("admin/speakers/session/form");
        }
        return modelAndView;
    }

    @RequestMapping(value="/admin/speakers/{id}/session", method = RequestMethod.POST)
    public ModelAndView savePeakerSession(@ModelAttribute("session") @Valid Session session, BindingResult bindingResult, @PathVariable("id") int id){
        Optional<Speaker> s = speakerRepository.findById(id);
        System.out.println(session.getYear());
        if (bindingResult.hasErrors()) {
            List<Subject> subjects = subjectRepository.findAll();
            List<Integer> years = new ArrayList<>();
            for (int i = 2015; i < 2030; i++) { years.add(i); }
            ModelAndView modelAndView = new ModelAndView("/admin/speakers/session/form");
            modelAndView.addObject("subjects", subjects);
            modelAndView.addObject("years", years);
            modelAndView.setViewName("admin/speakers/session/form");
            return modelAndView;
        }
        s.ifPresent(speaker -> {
            session.setSpeaker(speaker);
            if (sessionRepository.findBySpeakerAndYear(speaker, session.getYear()).size() == 0)
                sessionRepository.save(session);
        });
        return new ModelAndView("redirect:/admin/speakers/");
    }

    @RequestMapping(value="/admin/speakers/{id}/session/{session_id}/evaluate", method = RequestMethod.GET)
    public ModelAndView evaluatePeakerSession(@PathVariable("id") int id, @PathVariable("session_id") int sessionId){
        Optional<Session> s = sessionRepository.findById(sessionId);
        ModelAndView modelAndView = new ModelAndView();
        if (s.isPresent()){
            System.out.println("---------> : " + s.get().getId());
            modelAndView.addObject("id", id);
            modelAndView.addObject("speakerSession", s.get());
        }
        else
            return new ModelAndView("redirect:/admin/speakers/");

        modelAndView.setViewName("admin/speakers/session/eval");
        return modelAndView;
    }

    @RequestMapping(value="/admin/speakers/{id}/session/{session_id}/evaluate", method = RequestMethod.POST)
    public ModelAndView savePeakerSessionEvaluation(@ModelAttribute("speakerSession") @Valid Session speakerSession, BindingResult bindingResult, @PathVariable("id") int id){
        Optional<Session> s = sessionRepository.findById(speakerSession.getId());
        if (s.isPresent()){
            Session toSave = s.get();
            System.out.println("|--->"+toSave.getSpeaker().getId());
            toSave.setEvaluation(speakerSession.getEvaluation());
            sessionRepository.save(toSave);
        }
        return new ModelAndView("redirect:/admin/speakers/");
    }

    @RequestMapping(value="/admin/speakers/{id}/session/{session_id}/delete", method = RequestMethod.POST)
    public ModelAndView deletePeakerSession(@PathVariable("id") int id, @PathVariable("session_id") int sessionId){
        ModelAndView modelAndView = new ModelAndView();
        Optional<Speaker> s = speakerRepository.findById(id);
        s.ifPresent(speaker -> {
            speakerRepository.deleteById(sessionId);
        });
        modelAndView = new ModelAndView("redirect:/admin/speakers/");
        return modelAndView;
    }

    //########################
    //Resume upload
    //########################
    @RequestMapping(value="/admin/speakers/{id}/upload", method = RequestMethod.GET)
    public ModelAndView uploadSpeakerResume(@PathVariable("id") int id){
        ModelAndView modelAndView = new ModelAndView();
        Optional<Speaker> s = speakerRepository.findById(id);
        modelAndView.setViewName("admin/speakers/upload");
        if (s.isEmpty())
            modelAndView = new ModelAndView("redirect:/admin/speakers/");
        else{
            modelAndView.addObject("speaker", s.get());
        }
        return modelAndView;
    }

    @RequestMapping(value="/admin/speakers/{id}/upload", method = RequestMethod.POST)
    public ModelAndView handleFileUpload(@PathVariable("id") int id, @RequestParam("file") MultipartFile file, RedirectAttributes redirectAttributes){
        Optional<Speaker> s = speakerRepository.findById(id);
        if (s.isPresent() && !file.isEmpty()){
            Speaker speaker = s.get();
            String path = uploadService.uploadResume(file, speaker);
            if (path != null && path.length() > 0){
                speaker.setResumePath(path);
                speakerRepository.save(speaker);
            }
        }
        redirectAttributes.addFlashAttribute("message", "You successfully uploaded " + file.getOriginalFilename() + "!");
        return new ModelAndView("redirect:/admin/speakers");
    }

}
