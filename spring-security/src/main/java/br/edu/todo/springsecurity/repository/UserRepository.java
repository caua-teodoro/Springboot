package br.edu.todo.springsecurity.repository;

import br.edu.todo.springsecurity.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

// @Repository indica que esta interface pertence à camada de acesso a dados (DAO) e traduz exceções de banco para o Spring.
// Estender JpaRepository<User, Integer> fornece automaticamente métodos prontos de CRUD (save, findById, findAll, delete, etc.)
// para a entidade User cuja chave primária é do tipo Integer.
@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    // Query JPQL customizada para buscar o usuário pelo username já trazendo suas roles na mesma consulta SQL (JOIN FETCH).
    // O JOIN FETCH evita consultas extras (problema N+1) e garante que "e.roles" venha carregada para o Spring Security.
    // @Param("username") vincula o parâmetro do método Java à variável (:username) definida na query.
    @Query("SELECT e FROM User e JOIN FETCH e.roles WHERE e.username= (:username)")
    public User findByUsername(@Param("username") String username);
}
