package negocioImpl;

import java.util.ArrayList;

import dao.MovimientoDao;
import dao.PersonaDao;
import daoImpl.MovimientoDaoImpl;
import daoImpl.PersonaDaoImpl;
import entidad.Movimiento;
import negocio.MovimientoNegocio;

public class MovimientoNegocioImpl implements MovimientoNegocio {

	@Override
	public boolean insert(Movimiento movimiento) {
		MovimientoDao mdao = new MovimientoDaoImpl();
		if(mdao.insert(movimiento)) return true;
		return false;
	}

	@Override
	public boolean delete(Movimiento movimiento) {
		MovimientoDao mdao = new MovimientoDaoImpl();
		if(mdao.delete(movimiento)) return true;
		return false;
	}

	@Override
	public boolean update(Movimiento movimiento) {
		MovimientoDao mdao = new MovimientoDaoImpl();
		if(mdao.update(movimiento)) return true;
		return false;
	}

	@Override
	public Movimiento getMovimientoByID(int id) {
		MovimientoDao mdao = new MovimientoDaoImpl();
		return mdao.getMovimientoByID(id);
	}

	@Override
	public ArrayList<Movimiento> readAll() {
		MovimientoDao mdao = new MovimientoDaoImpl();
		return mdao.readAll();
	}

	@Override
	public ArrayList<Movimiento> getMovimientosByDNI(int dni) {
		MovimientoDao mdao = new MovimientoDaoImpl();
		return mdao.getMovimientosByDNI(dni);
	}

	@Override
	public int cantMovimientos() {
		MovimientoDao mdao = new MovimientoDaoImpl();
		return mdao.cantMovimientos();
	}

}
