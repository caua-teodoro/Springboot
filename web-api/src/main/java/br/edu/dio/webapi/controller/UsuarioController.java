package br.edu.dio.webapi.controller;

import br.edu.dio.webapi.model.Usuario;
import br.edu.dio.webapi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
// @RequestMapping("/users") fixar esse prefixo
public class UsuarioController {

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/users")
    public void postUser(Usuario usuario) {
        userRepository.save(usuario);
    }

    // uri
    @GetMapping("/users/{username}")
    public Usuario getUsuario(@PathVariable("username") String username) {
        return userRepository.findByUsername(username);
    }

    @DeleteMapping("/users/{id}")
    public void delete(@PathVariable("id") Integer id) {
        userRepository.delete(id);
    }
}
