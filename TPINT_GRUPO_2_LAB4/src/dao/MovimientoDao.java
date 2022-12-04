package dao;

import java.util.ArrayList;

import entidad.Movimiento;

public interface MovimientoDao {
	public boolean insert(Movimiento mov);
	public boolean delete(Movimiento mov);
	public boolean update(Movimiento mov);
	public Movimiento getMovimientoByID(int id);
	public ArrayList<Movimiento> getMovimientosByDNI(int dni);
	public ArrayList<Movimiento> readAll();
	public int cantMovimientos();
}
