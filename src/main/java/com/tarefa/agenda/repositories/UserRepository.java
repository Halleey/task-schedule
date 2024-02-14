package com.tarefa.agenda.repositories;

import com.tarefa.agenda.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByEmail(String emaill);
}
