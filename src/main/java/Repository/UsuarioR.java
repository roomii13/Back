package Repository;

import org.springframework.stereotype.Repository;
import java.util.List;
import Model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface UsuarioR extends JpaRepository<Usuario, Long> {
    // Métodos para realizar operaciones CRUD sobre la entidad Usuario

    // Método para buscar un usuario por su ID
    Usuario findById(long id);

    // Método para buscar un usuario por su correo electrónico
    Usuario findByEmail(String email);

    // Método para obtener todos los usuarios
    @Override
    List<Usuario> findAll();

    // Método para crear o actualizar un usuario
    @Override
    <S extends Usuario> S save(S entity);

    // Método para actualizar los datos de un usuario
    Usuario update(Usuario usuario);

    // Método para eliminar un usuario por su ID
    void deleteById(long id);
}
