package dao;

import java.util.ArrayList;

import entidad.Cuenta;
import entidad.Usuario;

public interface CuentaDao {
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
