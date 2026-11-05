package br.edu.todo.springsecurityjwt.service;

import br.edu.todo.springsecurityjwt.model.User;
import br.edu.todo.springsecurityjwt.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public void createUser(User user) {
        String pass = user.getPassword();
        //criptografando antes de salvar no banco
        user.setPassword(passwordEncoder.encode(pass));
        userRepository.save(user);
    }



}
