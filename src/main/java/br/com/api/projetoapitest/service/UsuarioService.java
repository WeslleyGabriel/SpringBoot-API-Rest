package br.com.api.projetoapitest.service;

import br.com.api.projetoapitest.modelTest.Usuario;
import br.com.api.projetoapitest.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;
    public Usuario salvar(Usuario usuario){
        return usuarioRepository.save(usuario);
    }

    public String delete(Long idUser){
        usuarioRepository.deleteById(idUser);
        return "Usuário deletado com sucesso! ";
    }
    public Usuario buscaruserid(Long idUser){
        Usuario usuarios = usuarioRepository.findById(idUser).get();
        return usuarios;
    }
    public List<Usuario> getAllUsuarios(){
        List<Usuario> usuarios = usuarioRepository.findAll();
        return usuarios;
    }
    public String atualizar(Usuario usuario) throws ClassNotFoundException {
        if(usuario.getId() == null){
            return "";
        }
        Usuario usuarios = usuarioRepository.save(usuario);
        return String.valueOf(usuarios);
    }

    public List<Usuario> buscarpornome(String nome){
        List<Usuario> usuario = usuarioRepository.findByName(nome.trim().toUpperCase());
        return usuario;
    }
}