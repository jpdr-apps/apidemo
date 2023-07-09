package jpdr.apps.devsu.apidemo.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import jpdr.apps.devsu.apidemo.entities.Movimiento;

public interface MovimientosRepository extends CrudRepository<Movimiento, Integer>{
		
	public List<Movimiento> findByFechaBetweenAndCuenta_CuentaIdOrderByFechaAscMovimientoIdAsc(String startDate, String endDate, int cuentaId);

	@Query(value=""
			+ "SELECT IFNULL("
			+ " ( SELECT SUM(valor) "
			+ "FROM Movimiento "
			+ "WHERE fecha = ?1 "
			+ "AND cuenta.cuentaId = ?2 "
			+ "AND tipoMovimiento = 'Retiro' "
			+ "GROUP BY cuenta.cuentaId ) , 0 )")
	public double sumRetirosByFecha(String fecha, int cuentaId);
	
	public Optional<Movimiento> findByFecha(String fecha);
	

}
