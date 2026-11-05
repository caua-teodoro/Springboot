package br.edu.todo.springsecurity.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

// @RestController combina @Controller + @ResponseBody:
// indica que os retornos dos métodos devem ser escritos diretamente no corpo da resposta HTTP (texto/JSON),
// em vez de procurar páginas HTML na pasta templates.
@RestController
public class WelcomeController {

    // Mapeia requisições HTTP GET na raiz "/" (ex.: http://localhost:8080/).
    // Rota pública: liberada com .requestMatchers("/").permitAll() no WebSecurityConfig.
    @GetMapping
    public String welcome() {
        return "Welcome to Spring Security";
    }

    // Mapeia requisições HTTP GET em "/users" (ex.: http://localhost:8080/users).
    // Rota protegida no WebSecurityConfig com .requestMatchers("/users").hasRole("USER").
    // Obs.: a anotação @PreAuthorize abaixo está comentada porque optamos por centralizar as regras de rota
    // lá no SecurityFilterChain (WebSecurityConfig), mas também funcionaria aqui graças ao @EnableMethodSecurity.
    @GetMapping("/users")
//    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public String users() {
        return "Authorized user";
    }

    // Mapeia requisições HTTP GET em "/admins" (ex.: http://localhost:8080/admins).
    // Rota restrita a administradores: exige "ROLE_ADMIN" configurada em .requestMatchers("/admins").hasRole("ADMIN").
    @GetMapping("/admins")
//    @PreAuthorize("hasAnyRole('ADMIN')")
    public String admins() {
        return "Authorized admin";
    }
}
