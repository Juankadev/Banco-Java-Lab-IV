package negocioImpl;

import java.util.ArrayList;

import dao.DireccionDao;
import daoImpl.DireccionDaoImpl;
import entidad.Direccion;
import negocio.DireccionNegocio;

public class DireccionNegocioImpl implements DireccionNegocio {

	@Override
	public boolean insert(Direccion dir) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(Direccion dir) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(Direccion dir) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public ArrayList<Direccion> getDireccionByID(int id) {
		DireccionDao dd = new DireccionDaoImpl();
		return dd.getDireccionByID(id);
	}

	@Override
	public ArrayList<Direccion> readAll() {
		DireccionDao dd = new DireccionDaoImpl();
		return dd.readAll();
	}

}
