package negocio;

import java.util.ArrayList;

import entidad.Movimiento;

public interface MovimientoNegocio {
	public boolean insert(Movimiento mov);
	public boolean delete(Movimiento mov);
	public boolean update(Movimiento mov);
	public Movimiento getMovimientoByID(int id);
	public ArrayList<Movimiento> readAll();
}
