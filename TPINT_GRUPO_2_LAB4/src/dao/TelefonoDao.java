package dao;

import java.util.ArrayList;

import entidad.Persona;
import entidad.Telefono;

public interface TelefonoDao {
	public boolean insert(Telefono tel);
	public boolean delete(Telefono tel);
	public boolean update(Telefono tel);
	public ArrayList<Telefono> filter(Persona persona);
	public ArrayList<Telefono> readAll();
}
