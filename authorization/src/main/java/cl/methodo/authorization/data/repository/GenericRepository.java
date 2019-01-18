package cl.methodo.authorization.data.repository;

import cl.methodo_commons.enums.Estatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@NoRepositoryBean
public interface GenericRepository<T, ID extends Serializable> extends JpaRepository<T, ID> {

    T findByNombre(String nombre);

    List<T> findByEstatus(Estatus estatus);

    List<T> findByFechaHoraCreacion(Date fechaHoraCreacion);
}
