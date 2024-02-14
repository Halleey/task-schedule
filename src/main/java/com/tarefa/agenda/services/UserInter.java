package com.tarefa.agenda.services;

import com.tarefa.agenda.entities.User;

public interface UserInter {
    public User saveUser(User user);

    public void removeSessionMessage();
}
