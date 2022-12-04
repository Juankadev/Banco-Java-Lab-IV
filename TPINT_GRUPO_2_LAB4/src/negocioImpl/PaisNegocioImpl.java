package negocioImpl;

import java.util.ArrayList;

import dao.PaisDao;
import daoImpl.PaisDaoImpl;
import entidad.Pais;
import negocio.PaisNegocio;

public class PaisNegocioImpl implements PaisNegocio {

	@Override
	public boolean insert(Pais p) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(Pais p) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(Pais p) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public ArrayList<Pais> filter(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Pais> readAll() {
		PaisDao pd = new PaisDaoImpl();
		return pd.readAll();
	}

}
