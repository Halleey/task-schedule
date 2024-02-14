package com.tarefa.agenda.services;

import com.tarefa.agenda.entities.User;
import com.tarefa.agenda.repositories.UserRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Service
public class UserService implements UserInter {
    @Autowired
    private UserRepository userRepo;

    private BCryptPasswordEncoder passwordEncoder;


    public User saveUser(User user) {

        String password=passwordEncoder.encode(user.getPassword());
        user.setRole("ROLE_USER");
        user.setPassword(password);

        return userRepo.save(user);
    }
    public void removeSessionMessage() {

        HttpSession session = ((ServletRequestAttributes) (RequestContextHolder.getRequestAttributes())).getRequest()
                .getSession();

        session.removeAttribute("msg");
    }
}
