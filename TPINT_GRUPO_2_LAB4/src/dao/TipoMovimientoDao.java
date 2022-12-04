package dao;

import java.util.ArrayList;

import entidad.TipoMovimiento;

public interface TipoMovimientoDao {
	public boolean insert(TipoMovimiento tm);
	public boolean delete(TipoMovimiento tm);
	public boolean update(TipoMovimiento tm);
	public ArrayList<TipoMovimiento> filter(int id);
	public ArrayList<TipoMovimiento> readAll();
}
