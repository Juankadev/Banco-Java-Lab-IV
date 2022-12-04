package negocio;

import java.util.ArrayList;

import entidad.Cuenta;

public interface CuentaNegocio {
	public boolean insert(Cuenta cuenta);
	public boolean delete(Cuenta cuenta);
	public boolean update(Cuenta cuenta);
	public Cuenta getCuentaByID(int numeroCuenta);
	public Cuenta getCuentaByCbu(String cbu);
	public ArrayList<Cuenta> getCuentaByUser(int dni);
	public ArrayList<Cuenta> readAll();
	public int total_cuentas(int dni);
	public Cuenta get_datoscuenta_by_cbu(String cbu);
}
