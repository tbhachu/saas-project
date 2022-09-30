package edu.greenriver.edu.saasproject.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class GreetingController {

    @RequestMapping("home")
    public String home()
    {
        return "index";
    }
}
