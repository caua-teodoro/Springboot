package br.edu.todo.springsecurity.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

// @Entity indica ao JPA/Hibernate que esta classe representa uma tabela no banco de dados.
@Entity
// @Table define o nome físico da tabela no PostgreSQL.
// Boa prática: evitamos o nome "user" puro porque USER é uma palavra reservada no PostgreSQL.
@Table(name = "tab_user_security")
public class User {
    // @Id marca o campo como chave primária (Primary Key) da tabela.
    @Id
    // GenerationType.IDENTITY delega ao PostgreSQL o auto-incremento do ID (SERIAL / IDENTITY).
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    // Define o nome da coluna da chave primária no banco como "id_user".
    @Column(name = "id_user")
    private Integer id;

    // Nome de exibição do usuário (VARCHAR(50) NOT NULL).
    @Column(length = 50, nullable = false)
    private String name;

    // Login usado na autenticação (VARCHAR(20) NOT NULL).
    @Column(length = 20, nullable = false)
    private String username;

    // Senha criptografada (VARCHAR(100) NOT NULL).
    // Importante: um hash BCrypt ocupa 60 caracteres (ex.: $2a$10$...), então length = 100 garante espaço suficiente.
    @Column(length = 100, nullable = false)
    private String password;

    // @ElementCollection mapeia uma lista de tipos simples (String) sem precisar criar uma entidade @Entity separada para Role.
    // FetchType.EAGER carrega os perfis (roles) imediatamente junto com o usuário ao consultar o banco.
    @ElementCollection(fetch = FetchType.EAGER)
    // @CollectionTable cria a tabela auxiliar "tab_user_roles" ligada a "tab_user_security" pela chave estrangeira "user_id".
    @CollectionTable(name = "tab_user_roles", joinColumns = @JoinColumn(name = "user_id"))
    // Nome da coluna que armazena o texto de cada role (ex.: "ADMIN", "USER") dentro de "tab_user_roles".
    @Column(name = "role_id")
    private List<String> roles = new ArrayList<>();

    // Construtor vazio obrigatório para o JPA/Hibernate instanciar a entidade ao ler registros do banco.
    public User() {

    }

    // Construtor auxiliar para instanciar rapidamente um usuário já definindo o username.
    public User(String username) {
        this.username = username;
    }

    // Getters e Setters usados pelo JPA e pela aplicação para ler/alterar os atributos da entidade.
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }
}
