package com.tarefa.agenda.Config;
import com.tarefa.agenda.entities.User;
import com.tarefa.agenda.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = userRepo.findByEmail(username);
        System.out.println(user);
        if (user == null) {
            throw new UsernameNotFoundException("user not found");
        } else {
            return new CustomUser(user);
        }

    }

}