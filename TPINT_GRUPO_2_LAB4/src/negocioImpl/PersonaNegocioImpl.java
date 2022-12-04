package negocioImpl;
import java.util.ArrayList;

import dao.CuentaDao;
import dao.PersonaDao;
import daoImpl.CuentaDaoImpl;
import daoImpl.PersonaDaoImpl;
import entidad.Persona;
import negocio.PersonaNegocio;

public class PersonaNegocioImpl implements PersonaNegocio{
	
	private PersonaDao pDao = new PersonaDaoImpl();

	@Override
	public boolean insert(Persona persona) {
		
		PersonaDao pdao = new PersonaDaoImpl();
		if(pdao.insert(persona)) return true;
		return false;
	}

	@Override
	public boolean update(Persona persona) {
		PersonaDao pdao = new PersonaDaoImpl();
		if(pdao.update(persona)) return true;
		return false;
	}

	@Override
	public boolean delete(Persona persona) {
		PersonaDao pdao = new PersonaDaoImpl();
		if(pdao.delete(persona)) return true;
		return false;
	}

	@Override
	public ArrayList<Persona> readAll() {
		PersonaDao pdao = new PersonaDaoImpl();
		return pdao.readAll();
	}

	@Override
	public Persona getPersonaByID(int DNI) {
		PersonaDao pdao = new PersonaDaoImpl();
		return pdao.getPersonaByID(DNI);
	}

	
}
