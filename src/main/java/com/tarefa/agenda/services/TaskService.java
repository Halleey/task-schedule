package com.tarefa.agenda.services;

import com.tarefa.agenda.entities.Task;
import com.tarefa.agenda.entities.User;
import com.tarefa.agenda.repositories.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {

    @Autowired
    private TaskRepository repository;
    public Task saveTask(Task task) {
        return repository.save(task);
    }
    public List<Task> getAllTasks() {
        return repository.findAll();
    }

    public List<Task> getTasksByUser(User user) {
        return repository.findByUser(user);
    }
}
