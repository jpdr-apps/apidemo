package jpdr.apps.devsu.apidemo.mappers;

import jpdr.apps.devsu.apidemo.dtos.CuentaDTO;
import jpdr.apps.devsu.apidemo.entities.Cuenta;

public class CuentasMapper implements BasicMapper<Cuenta, CuentaDTO>{
	
	private static volatile CuentasMapper instance;
	
	private CuentasMapper() {}
	
	public static CuentasMapper getInstance() {		
		
		if (instance==null) { 
			synchronized (ClientesMapper.class) {
				if (instance==null) 
					instance = new CuentasMapper();					
			}
		}
		return instance;				
	}

	@Override
	public Cuenta fromDTO(CuentaDTO cuentaDTO) {		
		
		Cuenta cuenta = new Cuenta();
		cuenta.setCuentaId(cuentaDTO.getCuentaId());
		cuenta.setNumeroCuenta(cuentaDTO.getNumeroCuenta());
		cuenta.setTipoCuenta(cuentaDTO.getTipoCuenta());
		cuenta.setEstado(Boolean.parseBoolean(cuentaDTO.getEstado()));
		cuenta.setSaldoInicial(cuentaDTO.getSaldoInicial());
		
		return cuenta;
	}

	@Override
	public CuentaDTO fromEntity(Cuenta cuenta) {
		
		CuentaDTO cuentaDTO = new CuentaDTO();
		cuentaDTO.setCuentaId(cuenta.getCuentaId());
		cuentaDTO.setNumeroCuenta(cuenta.getNumeroCuenta());
		cuentaDTO.setTipoCuenta(cuenta.getTipoCuenta());			
		cuentaDTO.setEstado(Boolean.toString(cuenta.getEstado()));
		cuentaDTO.setSaldoInicial(cuenta.getSaldoInicial());		
		
		return cuentaDTO;
	}

}
