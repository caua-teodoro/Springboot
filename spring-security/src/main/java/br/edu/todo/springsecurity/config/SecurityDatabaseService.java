package br.edu.todo.springsecurity.config;

import br.edu.todo.springsecurity.model.User;
import br.edu.todo.springsecurity.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

// @Component registra esta classe como um Bean gerenciado pelo Spring.
// Como ela implementa a interface UserDetailsService, o Spring Security 6 a detecta automaticamente
// e passa a usá-la como ponte entre o mecanismo de login e o banco de dados PostgreSQL.
@Component
public class SecurityDatabaseService implements UserDetailsService {
    // Injeta o repositório JPA para consultar os usuários cadastrados na tabela "tab_user_security".
    @Autowired
    private UserRepository userRepository;

    // Método obrigatório do contrato UserDetailsService: o Spring Security chama este método automaticamente
    // em toda tentativa de autenticação (ex.: Basic Auth no Postman), passando o username recebido.
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // 1. Busca a entidade User no banco de dados pelo username informado.
        User userEntity = userRepository.findByUsername(username);

        // 2. Se não encontrar o usuário no banco, lança UsernameNotFoundException;
        //    o Spring Security intercepta essa exceção e retorna HTTP 401 Unauthorized.
        if (userEntity == null) {
            throw new UsernameNotFoundException(username);
        }

        // 3. Converte cada role salva no banco (Strings como "ADMIN", "USER") em objetos GrantedAuthority,
        //    que é o formato padrão que o Spring Security utiliza para validar autorizações.
        Set<GrantedAuthority> authorities = new HashSet<GrantedAuthority>();
        userEntity.getRoles().forEach(role -> {
            // IMPORTANTE: adicionamos o prefixo "ROLE_" porque no banco salvamos apenas "ADMIN" ou "USER",
            // mas o método .hasRole("ADMIN") em WebSecurityConfig procura internamente por "ROLE_ADMIN".
            authorities.add(new SimpleGrantedAuthority("ROLE_" + role));
        });

        // 4. Instancia e retorna o User nativo do Spring Security (implementação pronta de UserDetails).
        //    Usamos o caminho completo do pacote (org.springframework.security.core.userdetails.User)
        //    para não conflitar com a nossa entidade br.edu.todo.springsecurity.model.User importada no topo.
        //    Obs.: a conferência da senha NÃO é feita manualmente aqui — entregamos o hash do banco (userEntity.getPassword())
        //    e o próprio Spring Security (DaoAuthenticationProvider) compara com a senha enviada usando o BCryptPasswordEncoder.
        UserDetails user = new org.springframework.security.core.userdetails.User(username, userEntity.getPassword(), authorities);
        return user;
    }
}
