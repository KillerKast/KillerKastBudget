package com.killerkast.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AngularController {

    @RequestMapping("/bill/{type}")
    public String index(){
        return "forward:/index.html";
    }

}
