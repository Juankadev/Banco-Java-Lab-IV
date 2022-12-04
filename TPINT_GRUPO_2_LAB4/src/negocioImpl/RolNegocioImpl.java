package negocioImpl;

import java.util.ArrayList;

import dao.RolDao;
import daoImpl.RolDaoImpl;
import entidad.Rol;
import negocio.RolNegocio;

public class RolNegocioImpl implements RolNegocio {

	@Override
	public boolean insert(Rol r) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(Rol r) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(Rol r) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public ArrayList<Rol> filter(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Rol> readAll() {
		RolDao rd = new RolDaoImpl();
		return rd.readAll();
	}

}
