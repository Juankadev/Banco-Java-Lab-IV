package dao;

import java.util.ArrayList;

import entidad.Direccion;

public interface DireccionDao {
	public boolean insert(Direccion dir);
	public boolean delete(Direccion dir);
	public boolean update(Direccion dir);
	public ArrayList<Direccion> getDireccionByID(int id);
	public ArrayList<Direccion> readAll();
}
