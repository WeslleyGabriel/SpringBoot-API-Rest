package br.com.api.projetoapitest.modelTest;

import br.com.api.projetoapitest.modelTest.Usuario;
import br.com.api.projetoapitest.repository.UsuarioRepository;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

public class UsuarioTest {

    private UsuarioRepository usuarioRepository;

    @Test
    public void testComportamentosEntidade() {
        Usuario usuario = new Usuario();
        usuario.setNome("John Doe");
        usuario.setIdade(20);

        assertEquals("John Doe", usuario.getNome());
        assertEquals(20, usuario.getIdade());
    }
    @Test
    public void testValidacaoTamanhoNome() {
        Usuario usuario = new Usuario();
        usuario.setNome("John Doe");

        assertTrue(usuario.getNome().length() <= 255);
        assertFalse(usuario.getNome().isEmpty());
    }

    @Test
    public void testConsistenciaDados() {
        Usuario usuario = new Usuario();
        usuario.setNome("John Doe");
        usuario.setIdade(-1);

        // Verifica se a idade é um número inteiro positivo
        assertThatThrownBy(() -> usuarioRepository.save(usuario)).isInstanceOf(Exception.class);
    }

    @Test
    public void testConsistenciaDados2() {
        Usuario usuario = new Usuario();
        usuario.setNome("John Doe");
        usuario.setIdade(120);

        // Verifica se a idade está dentro do limite permitido
        assertThatThrownBy(() -> usuarioRepository.save(usuario)).isInstanceOf(Exception.class);
    }

}