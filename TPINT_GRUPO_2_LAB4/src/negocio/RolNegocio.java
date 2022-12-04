package negocio;

import java.util.ArrayList;

import entidad.Rol;

public interface RolNegocio {
	public boolean insert(Rol r);
	public boolean delete(Rol r);
	public boolean update(Rol r);
	public ArrayList<Rol> filter(int id);
	public ArrayList<Rol> readAll();
}
