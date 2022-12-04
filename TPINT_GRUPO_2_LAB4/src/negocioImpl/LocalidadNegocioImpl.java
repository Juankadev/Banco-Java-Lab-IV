package negocioImpl;

import java.util.ArrayList;

import dao.LocalidadDao;
import daoImpl.LocalidadDaoImpl;
import entidad.Localidad;
import entidad.Pais;
import negocio.LocalidadNegocio;

public class LocalidadNegocioImpl implements LocalidadNegocio {

	@Override
	public boolean insert(Localidad localidad) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(Localidad localidad) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(Localidad localidad) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public ArrayList<Localidad> filter(String CP, Pais pais) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Localidad> get_localidad_by_id(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Localidad> readAll() {
		LocalidadDao ld = new LocalidadDaoImpl();
		return ld.readAll();
	}

}
