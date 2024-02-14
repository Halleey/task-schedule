package com.tarefa.agenda.controllers;

import com.tarefa.agenda.entities.Task;
import com.tarefa.agenda.entities.User;
import com.tarefa.agenda.repositories.UserRepository;
import com.tarefa.agenda.services.TaskService;
import com.tarefa.agenda.services.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;

@Controller
public class EntitiesController {


    private UserService userService;


    private UserRepository userRepo;


    private TaskService taskService;

    private UserRepository repository;

    @PostMapping("/saveUser")
    public String saveUser(@ModelAttribute User user, HttpSession session, Model m) {

        // System.out.println(user);

        User u = userService.saveUser(user);

        if (u != null) {
            // System.out.println("save sucess");
            session.setAttribute("msg", "Cadastro concluído com sucesso!");

        } else {
            // System.out.println("error in server");
            session.setAttribute("msg", "Something wrong server");
        }
        return "redirect:/register";
    }

    @PostMapping("/saveTask")
    public String saveTask(@ModelAttribute Task task, Principal principal, HttpSession session) {
        String email = principal.getName();
        User user = userRepo.findByEmail(email);
        task.setUser(user);
        taskService.saveTask(task);
        session.setAttribute("msg", "Tarefa cadastrada com sucesso!");
        return "redirect:/task/tasklist";
    }

}
