package com.tarefa.agenda.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BaseController {
    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/register")
    public String register() {
        return "register";
    }

    @GetMapping("/signin")
    public String login() {
        return "login";
    }
    @GetMapping("/user/task")
    public String task() {
        return "task-form";
    }

    @GetMapping("/user/task/alter")
    public  String alterTask(){return "altertask";}
}
