package br.com.api.projetoapitest.controlles;

import br.com.api.projetoapitest.model.Usuario;
import br.com.api.projetoapitest.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping()
public class UsuarioController {

    @Autowired
    private  UsuarioRepository usuarioRepository;


    @GetMapping("/br/{name}/{idade}")
    @ResponseBody
    public String home(@PathVariable (name = "name") String name,
                       @PathVariable (value = "idade") int idade) {

        Usuario usuario = new Usuario();
        usuario.setNome(name);
        usuario.setIdade(idade);
        usuarioRepository.save(usuario);


        return "Olá Weslley!!! " + name + idade;
    }


    @GetMapping(value = "listaTodos")
    @ResponseBody
    public ResponseEntity<List<Usuario>> listaUsuarios(){

        List<Usuario> usuarios = usuarioRepository.findAll();

        return new ResponseEntity<List<Usuario>>(usuarios, HttpStatus.OK);
    }

    @PostMapping(value = "salvar")
    @ResponseBody
    public ResponseEntity<Usuario> salvar(@RequestBody Usuario usuario){

        Usuario user = usuarioRepository.save(usuario);

        return new ResponseEntity<Usuario>(user, HttpStatus.CREATED);
    }
}


