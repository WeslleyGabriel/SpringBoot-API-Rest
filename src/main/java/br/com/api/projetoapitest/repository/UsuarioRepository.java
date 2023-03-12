package br.com.api.projetoapitest.repository;


import br.com.api.projetoapitest.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    @Query(value = "SELECT u FROM Usuario u WHERE u.nome LIKE %?1%")
    List<Usuario> buscarPorNome(String name);

}
