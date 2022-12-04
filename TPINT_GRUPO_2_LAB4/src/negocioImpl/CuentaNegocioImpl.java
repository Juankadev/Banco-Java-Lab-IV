package negocioImpl;

import java.util.ArrayList;

import dao.CuentaDao;
import daoImpl.CuentaDaoImpl;
import entidad.Cuenta;
import negocio.CuentaNegocio;

public class CuentaNegocioImpl implements CuentaNegocio {

	@Override
	public boolean insert(Cuenta cuenta) {
		CuentaDao cdao = new CuentaDaoImpl();
		if(cdao.insert(cuenta)) return true;
		return false;
	}

	@Override
	public boolean delete(Cuenta cuenta) {
		CuentaDao cdao = new CuentaDaoImpl();
		if(cdao.delete(cuenta)) return true;
		return false;
	}

	@Override
	public boolean update(Cuenta cuenta) {
		CuentaDao cdao = new CuentaDaoImpl();
		if(cdao.update(cuenta)) return true;
		return false;
	}

	@Override
	public Cuenta getCuentaByID(int numeroCuenta) {
		CuentaDao cdao = new CuentaDaoImpl();
		return cdao.getCuentaByID(numeroCuenta);
	}

	@Override
	public ArrayList<Cuenta> readAll() {
		CuentaDao cdao = new CuentaDaoImpl();
		return cdao.readAll();
	}

	@Override
	public ArrayList<Cuenta> getCuentaByUser(int dni) {
		ArrayList<Cuenta> cuentas = new CuentaDaoImpl().getCuentaByUser(dni);
		if(cuentas.size() > 0) return cuentas;
		return null;
	}

	@Override
	public int total_cuentas(int dni) {
		CuentaDao cd = new CuentaDaoImpl();
		int cant = cd.total_cuentas(dni);
		return cant;
	}

	@Override
	public Cuenta getCuentaByCbu(String cbu) {
		return new CuentaDaoImpl().getCuentaByCbu(cbu);
	}

	@Override
	public Cuenta get_datoscuenta_by_cbu(String cbu) {
		CuentaDao cd = new CuentaDaoImpl();
		return cd.get_datoscuenta_by_cbu(cbu);
	}

}
