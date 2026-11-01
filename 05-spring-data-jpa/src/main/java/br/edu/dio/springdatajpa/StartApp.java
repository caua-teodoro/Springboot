package br.edu.dio.springdatajpa;

import br.edu.dio.springdatajpa.model.User;
import br.edu.dio.springdatajpa.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.swing.*;

@Component
public class StartApp implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    @Override
    public void run(String... args) throws Exception {
        User user = new User();
        String name = JOptionPane.showInputDialog(null, "Digite o nome do usuario");
        user.setName(name);
        String username = JOptionPane.showInputDialog(null, "Digite o username do usuario");
        user.setUsername(username);
        String password = JOptionPane.showInputDialog(null, "Digite a senha do usuario");
        user.setPassword(password);

        userRepository.save(user);

        for (User u : userRepository.findAll()) {
            System.out.println(u);
        }
    }
}
