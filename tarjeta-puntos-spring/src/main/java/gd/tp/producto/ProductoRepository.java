package gd.tp.producto;

import org.apache.logging.log4j.util.ProcessIdUtil;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductoRepository extends JpaRepository<Producto, Long> {
}
