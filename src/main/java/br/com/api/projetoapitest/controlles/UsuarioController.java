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


    @GetMapping(value = "/listaTodos")
    @ResponseBody
    public ResponseEntity<List<Usuario>> getAllUsuarios(){

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
    public ResponseEntity<String> delete(@RequestParam Long idUser){

     usuarioRepository.deleteById(idUser);

        return new ResponseEntity<String>("Usuario foi deletado do sistema!", HttpStatus.OK);
    }


    @GetMapping(value = "buscaruserid")
    @ResponseBody
    public ResponseEntity<Usuario> buscaruserid (@RequestParam(name = "idUser") Long idUser){

        Usuario usuarios = usuarioRepository.findById(idUser).get();

        return new ResponseEntity<Usuario>(usuarios, HttpStatus.OK);
    }


    @PutMapping(value = "atualizar")
    @ResponseBody
    public ResponseEntity<?> atualizar(@RequestBody Usuario usuario) throws ClassNotFoundException{

        if(usuario.getId() == null){
            return new ResponseEntity<String>("Id não encontrado! Por favor, informe o ID do usuário.", HttpStatus.OK);
        }

        Usuario usuarios = usuarioRepository.saveAndFlush(usuario);

        return new ResponseEntity<Usuario>(usuarios, HttpStatus.OK);
    }

    @GetMapping(value = "buscarPorNome")
    @ResponseBody
    public ResponseEntity<List<Usuario>> buscarPorNome (@RequestParam(name = "name") String nome){

        List<Usuario> usuario = usuarioRepository.findByName(nome.trim().toUpperCase());

        return new ResponseEntity<List<Usuario>>(usuario, HttpStatus.OK);
    }
}


