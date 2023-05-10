
package Service;

import Model.Usuario;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class UsuarioService {
   private boolean usuarioAutenticado = false;
    private final Usuario usuario = new Usuario();
    private final String urlServidor = "http://localhost:3006";

    public boolean autenticarUsuario(String email, String contraseña) {
        if (this.usuario.getEmail().equals(email) && this.usuario.getContraseña().equals(contraseña)) {
            usuarioAutenticado = true;
            return true;
        } else {
            usuarioAutenticado = false;
            return false;
        }
    }

    public boolean estaAutenticado() {
        return usuarioAutenticado;
    }

    public Usuario obtenerUsuario() {
        return usuario;
    }

    public class Credenciales {
        private String email;
        private String contraseña;

        public Credenciales() {
        }

        public Credenciales(String email, String contraseña) {
            this.email = email;
            this.contraseña = contraseña;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getContraseña() {
            return contraseña;
        }

        public void setContraseña(String contraseña) {
            this.contraseña = contraseña;
        }
    }

    public class RespuestaServidor {
        private boolean guardadoExitoso;
        private String mensaje;

        public RespuestaServidor() {
        }

        public RespuestaServidor(boolean guardadoExitoso, String mensaje) {
            this.guardadoExitoso = guardadoExitoso;
            this.mensaje = mensaje;
        }

        public boolean isGuardadoExitoso() {
            return guardadoExitoso;
        }

        public void setGuardadoExitoso(boolean guardadoExitoso) {
            this.guardadoExitoso = guardadoExitoso;
        }

        public String getMensaje() {
            return mensaje;
        }

        public void setMensaje(String mensaje) {
            this.mensaje = mensaje;
        }
    }

    public boolean guardarDatosEditados(Usuario datosEditados) {
        if (usuarioAutenticado) {
            usuario.setNombre(datosEditados.getNombre() != null ? datosEditados.getNombre() : usuario.getNombre());
            usuario.setApellido(
                    datosEditados.getApellido() != null ? datosEditados.getApellido() : usuario.getApellido());
            usuario.setTitulo(datosEditados.getTitulo() != null ? datosEditados.getTitulo() : usuario.getTitulo());
            usuario.setFotoPerfil(
                    datosEditados.getFotoPerfil() != null ? datosEditados.getFotoPerfil() : usuario.getFotoPerfil());
            usuario.setDescripcion(
                    datosEditados.getDescripcion() != null ? datosEditados.getDescripcion() : usuario.getDescripcion());
            usuario.setLinkedin(
                    datosEditados.getLinkedin() != null ? datosEditados.getLinkedin() : usuario.getLinkedin());
            usuario.setTwiter(datosEditados.getTwiter() != null ? datosEditados.getTwiter() : usuario.getTwiter());
            usuario.setUrlcv(datosEditados.getUrlcv() != null ? datosEditados.getUrlcv() : usuario.getUrlcv());

            RestTemplate restTemplate = new RestTemplate();

            HttpEntity<Usuario> requestEntity = new HttpEntity<>(usuario);
            ResponseEntity<RespuestaServidor> responseEntity;
            responseEntity = restTemplate.exchange(
                    urlServidor + "/guardar-datos-editados",
                    HttpMethod.POST,
                    requestEntity,
                    RespuestaServidor.class);

            RespuestaServidor respuesta = responseEntity.getBody();

            if (respuesta != null) {
                return respuesta.isGuardadoExitoso();
            } else {
                // Manejo de error cuando la respuesta es nula
                return false;
            }
        }
        return false;
    } 
}
