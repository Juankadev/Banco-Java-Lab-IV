package negocioImpl;

import java.util.ArrayList;

import dao.TipoCuentaDao;
import daoImpl.TipoCuentaDaoImpl;
import entidad.TipoCuenta;
import negocio.TipoCuentaNegocio;

public class TipoCuentaNegocioImpl implements TipoCuentaNegocio{

	@Override
	public boolean insert(TipoCuenta tp) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(TipoCuenta tp) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(TipoCuenta tp) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public ArrayList<TipoCuenta> filter(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<TipoCuenta> readAll() {
		TipoCuentaDao tcd = new TipoCuentaDaoImpl();
		
		return tcd.readAll();
	}

	@Override
	public TipoCuenta getById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

}
