package com.tarefa.agenda.controllers;
import com.tarefa.agenda.entities.Task;
import com.tarefa.agenda.entities.User;
import com.tarefa.agenda.repositories.UserRepository;
import com.tarefa.agenda.services.TaskService;
import com.tarefa.agenda.services.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;
import java.util.List;


@Controller

public class EntitiesController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private TaskService taskService;

    @ModelAttribute
    public void commonUser(Principal p, Model m) {
        if (p != null) {
            String email = p.getName();
            User user = userRepo.findByEmail(email);

            m.addAttribute("user", user);
        }

    }
    @PostMapping("/saveUser")
    public String saveUser(@ModelAttribute User user, HttpSession session, Model m) {
        
        User u = userService.saveUser(user);
        if (u != null) {

            session.setAttribute("msg", "Cadastro concluído com sucesso!");

        } else {

            session.setAttribute("msg", "Something wrong server");
        }
        return "redirect:/index";
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
    @GetMapping("/user/profile")
    public String profile(Principal p, Model m) {
        String email = p.getName();
        User user = userRepo.findByEmail(email);
        m.addAttribute("user", user);
        return "profile";
    }
    @GetMapping("/task/tasklist")
    public String listTask(Principal principal, Model model) {
        String email = principal.getName();
        User user = userRepo.findByEmail(email);
        List<Task> tasks = taskService.getTasksByUser(user);
        model.addAttribute("tasks", tasks);
        return "tasklist";
    }
    @PostMapping("/task/deleteTask")
    public String deleteTask(@RequestParam("taskId") Long taskId, HttpSession session) {
        taskService.deleteTaskById(taskId);
        session.setAttribute("msg", "Tarefa excluída com sucesso!");
        return "redirect:/task/tasklist";
    }
}