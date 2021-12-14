package com.hajofoglalo.bootstrap;

import com.hajofoglalo.model.User;
import com.hajofoglalo.repositories.UserRepository;
import com.hajofoglalo.services.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {
    private final UserRepository userRepository;

    public DataLoader(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        User user = new User();
        user.setFirstName("Bence");
        user.setLastName("Varga");
        User savedUSer = userRepository.save(user);
    }
}
