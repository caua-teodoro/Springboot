package br.edu.todo.springsecurity.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

// @Configuration informa ao Spring que esta classe declara configurações e métodos @Bean.
@Configuration
// @EnableMethodSecurity habilita anotações de segurança direto nos métodos (como @PreAuthorize nos Controllers/Services).
// No Spring Security 6, ela substitui a antiga @EnableGlobalMethodSecurity.
@EnableMethodSecurity
public class WebSecurityConfig {

    // [LEGADO - Spring Security 5] Configuração manual via AuthenticationManagerBuilder.
    // Mantido comentado porque no Spring Boot 3 / Spring Security 6 não é mais necessário:
    // basta existir um Bean UserDetailsService (SecurityDatabaseService) e um Bean PasswordEncoder
    // que o próprio Spring Security cria e conecta o DaoAuthenticationProvider automaticamente.
    // Além disso, usar auth.userDetailsService(...) sem .passwordEncoder(...) faria o Spring ignorar o BCryptPasswordEncoder abaixo.
//    @Autowired
//    public void globalUserDetails(AuthenticationManagerBuilder auth) throws Exception {
//        auth.userDetailsService(securityDatabaseService);
//        //.passwordEncoder(new BCryptPasswordEncoder()
//    }

    // Registra o BCryptPasswordEncoder como o codificador oficial de senhas da aplicação.
    // Ele cumpre dois papéis:
    // 1. Permite injetar PasswordEncoder (ex.: em StartApplication) para gerar o hash antes de salvar no banco.
    // 2. É usado automaticamente pelo Spring Security no login para comparar a senha enviada com o hash salvo no banco.
    @Bean
    public PasswordEncoder passwordEncoder()
    {
        return new BCryptPasswordEncoder();
    }

    // [ESTUDO ANTERIOR - Usuários em Memória]
    // Mantido comentado porque criava usuários fixos na memória RAM (InMemoryUserDetailsManager).
    // Se ficasse ativo junto com o SecurityDatabaseService, haveria conflito entre dois UserDetailsService.
//    @Bean
//    public UserDetailsService
//    userDetailsService(PasswordEncoder encoder)
//    {
//
//        UserDetails admin
//                = User.withUsername("admin")
//                .password(encoder.encode("admin123"))
//                .roles("ADMIN", "USER")
//                .build();
//
//        UserDetails user
//                = User.withUsername("user")
//                .password(encoder.encode("user123"))
//                .roles("USER")
//                .build();
//
//        return new InMemoryUserDetailsManager(admin, user);
//    }

    // Define a corrente de filtros de segurança HTTP (substitui o antigo WebSecurityConfigurerAdapter do Spring Security 5).
    // Toda requisição HTTP que chega na API passa por estas regras antes de alcançar o Controller.
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception
    {
        http
                // Configura as regras de autorização por rota (quem pode acessar o quê):
                .authorizeHttpRequests(auth -> auth
                        // Libera a rota raiz "/" para qualquer pessoa, mesmo sem estar autenticada.
                        .requestMatchers("/").permitAll()
                        // Libera a rota "/login" publicamente.
                        .requestMatchers("/login").permitAll()
                        // Exige a role "ADMIN" (internamente "ROLE_ADMIN") para acessar "/admins".
                        // Sem credencial -> 401 Unauthorized | Autenticado sem a role ADMIN -> 403 Forbidden.
                        .requestMatchers("/admins").hasRole("ADMIN")
                        // Exige a role "USER" (internamente "ROLE_USER") para acessar "/users".
                        // Obs.: se quiser que ADMIN também acesse "/users", troque por .hasAnyRole("USER", "ADMIN").
                        .requestMatchers("/users").hasRole("USER")
                        // Qualquer outra rota não listada acima exige pelo menos estar autenticado.
                        .anyRequest().authenticated()
                )
                // O formLogin entra aqui, encadeado no http (usado quando queremos tela de login HTML via navegador e sessão):
//                .formLogin(form -> form
//                        .permitAll()
//                );
                // Configuração atualizada do HTTP Basic:
                // Lê o cabeçalho "Authorization: Basic base64(usuario:senha)" enviado em cada requisição (ex.: aba Authorization -> Basic Auth no Postman).
                // Em uma evolução futura para API REST Stateless com JWT, esta linha sai para dar lugar à validação de Bearer Token.
                .httpBasic(Customizer.withDefaults());

        // Constrói e ativa o filtro de segurança configurado acima.
        return http.build();
    }

}
