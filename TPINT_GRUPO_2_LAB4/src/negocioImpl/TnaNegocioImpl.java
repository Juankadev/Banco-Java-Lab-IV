package negocioImpl;

import java.util.ArrayList;

import dao.TnaDao;
import daoImpl.TnaDaoImpl;
import negocio.TnaNegocio;

public class TnaNegocioImpl implements TnaNegocio{

	@Override
	public boolean insert(double t) {
		TnaDao dao = new TnaDaoImpl();
		if(dao.insert(t)) return true;
		return false;
	}

	@Override
	public boolean delete(double t) {
		TnaDao dao = new TnaDaoImpl();
		if(dao.delete(t)) return true;
		return false;
	}

	@Override
	public boolean update(double t) {
		TnaDao dao = new TnaDaoImpl();
		if(dao.update(t)) return true;
		return false;
	}

	@Override
	public ArrayList<Double> getTna() {
		TnaDao dao = new TnaDaoImpl();
		return dao.getTna();
	}
	
}