package jpdr.apps.devsu.apidemo.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import jpdr.apps.devsu.apidemo.entities.Cuenta;

public interface CuentasRepository extends CrudRepository<Cuenta, Integer>{
	
	public boolean existsByNumeroCuentaAndCuentaIdNot(int numeroCuenta, int cuentaId);
	
	public Optional<Cuenta> findByNumeroCuenta(int numeroCuenta);

}
