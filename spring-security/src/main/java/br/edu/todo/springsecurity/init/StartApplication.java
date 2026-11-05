package br.edu.todo.springsecurity.init;

import br.edu.todo.springsecurity.model.User;
import br.edu.todo.springsecurity.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

// @Component registra esta classe no container do Spring.
// Ao implementar CommandLineRunner, o método run(...) é executado automaticamente uma única vez
// logo após a aplicação terminar de subir — ideal para carga inicial de dados (seed) em ambiente de estudo/dev.
@Component
public class StartApplication implements CommandLineRunner {
    // Injeta o Bean BCryptPasswordEncoder definido em WebSecurityConfig para criptografar senhas antes de salvar.
    @Autowired
    private PasswordEncoder  passwordEncoder;

    // Injeta o repositório JPA para consultar e persistir usuários no PostgreSQL.
    @Autowired
    private UserRepository repository;

    // @Transactional garante que todas as operações de leitura e gravação dentro do método ocorram na mesma transação do banco
    // (salvando de forma atômica tanto em "tab_user_security" quanto na tabela auxiliar "tab_user_roles").
    @Transactional
    @Override
    public void run(String... args) throws Exception {
        // --- 1. Carga / verificação do usuário ADMIN ---
        User user = repository.findByUsername("admin");
        if (user == null) {
            // Se o usuário "admin" ainda não existe no banco, cria o registro inicial:
            user = new User();
            user.setName("ADMIN");
            user.setUsername("admin");
            // NUNCA salvamos a senha em texto puro: geramos o hash BCrypt de "master123".
            user.setPassword(passwordEncoder.encode("master123"));
            // Adicionamos apenas "ADMIN" (sem "ROLE_"), pois o SecurityDatabaseService concatena "ROLE_" ao carregar.
            user.getRoles().add("ADMIN");
            repository.save(user);
        } else if (!user.getPassword().startsWith("$2a$")) {
            // [Ajuste auxiliar de desenvolvimento]
            // Caso o registro já existisse no banco com senha em texto puro de testes anteriores (não começa com o prefixo BCrypt "$2a$"),
            // regrava a senha já criptografada para evitar erro 401 Unauthorized.
            // Obs.: em produção, não usamos esse else-if (limpamos o banco ou usamos migrations como Flyway).
            user.setPassword(passwordEncoder.encode("master123"));
            repository.save(user);
        }

        // --- 2. Carga / verificação do usuário comum USER ---
        user = repository.findByUsername("user");
        if (user == null) {
            // Se o usuário "user" ainda não existe no banco, cria o registro inicial:
            user = new User();
            user.setName("USER");
            user.setUsername("user");
            // Gera o hash BCrypt da senha "user123" antes de persistir no PostgreSQL.
            user.setPassword(passwordEncoder.encode("user123"));
            // Define o perfil "USER" (que virará "ROLE_USER" no SecurityDatabaseService).
            user.getRoles().add("USER");
            repository.save(user);
        } else if (!user.getPassword().startsWith("$2a$")) {
            // Atualiza para hash BCrypt caso estivesse salvo em texto puro anteriormente no banco local.
            user.setPassword(passwordEncoder.encode("user123"));
            repository.save(user);
        }
    }
}
