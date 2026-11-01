package br.edu.dio.springdatajpa.repository;

import br.edu.dio.springdatajpa.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Integer> {
    // Query Method
    List<User> findByName(String name);

    // Query Method
    User findByUsername(String username);

    // Query Override
    @Query("SELECT u FROM User u Where u.name LIKE %:name%")
    List<User> findByNameLike(String name);
}
