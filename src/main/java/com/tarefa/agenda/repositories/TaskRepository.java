package com.tarefa.agenda.repositories;

import com.tarefa.agenda.entities.Task;
import com.tarefa.agenda.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> findByUser(User user);
}
