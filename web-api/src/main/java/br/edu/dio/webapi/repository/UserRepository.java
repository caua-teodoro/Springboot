package br.edu.dio.webapi.repository;

import br.edu.dio.webapi.model.Usuario;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestBody;

@Repository
public class UserRepository {

    public void save(@RequestBody Usuario usuario) {

        if (usuario.getId() == null) {
            System.out.println("SAVE - Recebendo o usuário na camada repositório");
        } else {
            System.out.println("UPDATE - Recebendo o usuário na camada repositório");
        }

        System.out.println(usuario);
    }

    public void delete(Integer id) {
        System.out.println(String.format("DELETE/id - Recebendo o id: %d para excluir do Banco de dados"));
    }

    public Usuario find(Integer id) {
        System.out.println(String.format("FIND - Recebendo o id: %d", id));
        return new Usuario("Caua", "123");
    }

    public Usuario findByUsername(String username) {
        System.out.println(String.format("FIND - Recebendo o username: %s", username));
        return new Usuario("Caua", "123");
    }
}
