package com.ionisstm.intervenants.controller.custom;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class CustomMainController {
    @RequestMapping(value={"/custom/about"}, method = RequestMethod.GET)
    public String about(){
        return "custom/about";
    }
}
