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

    @DeleteMapping(value = "delete")
    @ResponseBody
    public ResponseEntity<String> delete(@RequestParam Long iduser){

        usuarioRepository.deleteById(iduser);

        return new ResponseEntity<String>("Usuario foi deletado do sistema!", HttpStatus.OK);
    }


    @GetMapping(value = "buscaruserid")
    @ResponseBody
    public ResponseEntity<Usuario> buscaruserid (@RequestParam(name = "iduser") Long iduser){

        Usuario usuario = usuarioRepository.findById(iduser).get();

        return new ResponseEntity<Usuario>(usuario, HttpStatus.OK);
    }


    @PutMapping(value = "atualizar")
    @ResponseBody
    public ResponseEntity<?> atualizar(@RequestBody Usuario usuario){

        if(usuario.getId() == null){
            return new ResponseEntity<String>("Id não encontrado! Por favor, informe o ID do usuário.", HttpStatus.OK);
        }

        Usuario user = usuarioRepository.saveAndFlush(usuario);

        return new ResponseEntity<Usuario>(user, HttpStatus.OK);
    }
}


