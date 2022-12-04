package negocio;

import java.util.ArrayList;

import entidad.Localidad;
import entidad.Pais;

public interface LocalidadNegocio {
	public boolean insert(Localidad localidad);
	public boolean delete(Localidad localidad);
	public boolean update(Localidad localidad);
	public ArrayList<Localidad> filter(String CP, Pais pais);
	public ArrayList<Localidad> get_localidad_by_id(int id);
	public ArrayList<Localidad> readAll();
}
