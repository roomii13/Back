
package Controller;

import Model.Usuario;
import Service.UsuarioService;
import Service.UsuarioService.Credenciales;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;




@RestController
@RequestMapping("/usuario")
public class UsuarioControlador {

    @Autowired
    private UsuarioService service;

    
    
    
    
    
    @PostMapping("/autenticar")
    public ResponseEntity<Boolean> autenticarUsuario(@RequestBody Credenciales credenciales) {
        boolean usuarioAutenticado = service.autenticarUsuario(credenciales.getEmail(), credenciales.getContraseña());
        return ResponseEntity.ok(usuarioAutenticado);
    }

    @GetMapping("/autenticado")
    public ResponseEntity<Boolean> estaAutenticado() {
        boolean usuarioAutenticado = service.estaAutenticado();
        return ResponseEntity.ok(usuarioAutenticado);
    }

    @GetMapping
    public ResponseEntity<Usuario> obtenerUsuario() {
        Usuario usuario = service.obtenerUsuario();
        return ResponseEntity.ok(usuario);
    }

    @PostMapping("/editar")
    public ResponseEntity<Boolean> guardarDatosEditados(@RequestBody Usuario datosEditados) {
        boolean guardadoExitoso = service.guardarDatosEditados(datosEditados);
        return ResponseEntity.ok(guardadoExitoso);
    }
}

