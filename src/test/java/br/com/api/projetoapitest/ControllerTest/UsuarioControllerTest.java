package br.com.api.projetoapitest.ControllerTest;

import br.com.api.projetoapitest.controlles.UsuarioController;
import br.com.api.projetoapitest.modelTest.Usuario;
import br.com.api.projetoapitest.service.UsuarioService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class UsuarioControllerTest {

    private UsuarioController usuarioController;
    private UsuarioService usuarioService;

    @BeforeEach
    public void setUp() {
        usuarioService = mock(UsuarioService.class);
        usuarioController = new UsuarioController(usuarioService);
    }

    @Test
    public void testGetAllUsuarios() {
        List<Usuario> usuarios = new ArrayList<>();
        usuarios.add(new Usuario(1L, "John Doe", 20));
        usuarios.add(new Usuario(2L, "Jane Doe", 24));

        when(usuarioService.getAllUsuarios()).thenReturn(usuarios);

        ResponseEntity<List<Usuario>> response = usuarioController.getAllUsuarios();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(2, response.getBody().size());
    }

    @Test
    public void testSalvarUsuario() {
        Usuario usuario = new Usuario(null, "John Doe", 20);

        when(usuarioService.salvar(usuario)).thenReturn(usuario);

        ResponseEntity<Usuario> response = usuarioController.salvar(usuario);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(usuario, response.getBody());
    }

    @Test
    public void testDeleteUsuario() {
        Long idUser = 1L;

        when(usuarioService.delete(idUser)).thenReturn(String.valueOf(true));

        ResponseEntity<String> response = usuarioController.delete(idUser);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Usuario foi deletado do sistema!", response.getBody());
    }

    @Test
    public void testBuscarUsuarioPorId() {
        Long idUser = 1L;
        Usuario usuario = new Usuario(idUser, "John Doe", 20);

        when(usuarioService.buscaruserid(idUser)).thenReturn(usuario);

        ResponseEntity<Usuario> response = usuarioController.buscaruserid(idUser);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(usuario, response.getBody());
    }

    @Test
    public void testAtualizarUsuario() throws ClassNotFoundException {
        Usuario usuario = new Usuario(1L, "John Doe", 20);

        when(usuarioService.atualizar(usuario)).thenReturn(String.valueOf(true));

        ResponseEntity<?> response = usuarioController.atualizar(usuario);

        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void testBuscarUsuarioPorNome() {
        String nome = "John Doe";
        List<Usuario> usuarios = new ArrayList<>();
        usuarios.add(new Usuario(1L, nome, 20));

        when(usuarioService.buscarpornome(nome.trim().toLowerCase())).thenReturn(usuarios);

        ResponseEntity<List<Usuario>> response = usuarioController.buscarPorNome(nome);

        assertEquals(HttpStatus.OK, response.getStatusCode());
    }
}