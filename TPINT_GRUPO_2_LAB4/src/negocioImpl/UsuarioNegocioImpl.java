package negocioImpl;

import java.util.ArrayList;

import dao.PersonaDao;
import dao.UsuarioDao;
import daoImpl.PersonaDaoImpl;
import daoImpl.UsuarioDaoImpl;
import entidad.Persona;
import entidad.Usuario;
import negocio.UsuarioNegocio;

public class UsuarioNegocioImpl implements UsuarioNegocio {

	@Override
	public boolean insert(Usuario usuario) {
		UsuarioDao pdao = new UsuarioDaoImpl();
		if(pdao.insert(usuario)) return true;
		return false;
	}

	@Override
	public boolean delete(Usuario usuario) {
		UsuarioDao pdao = new UsuarioDaoImpl();
		if(pdao.delete(usuario)) return true;
		return false;
	}

	@Override
	public boolean update(Usuario usuario) {
		UsuarioDao pdao = new UsuarioDaoImpl();
		if(pdao.update(usuario)) return true;
		return false;
	}

	@Override
	public Usuario getUserByID(int dni) {
		UsuarioDao pdao = new UsuarioDaoImpl();
		return pdao.getUserByID(dni);
	}

	@Override
	public ArrayList<Usuario> readAll() {
		UsuarioDao pdao = new UsuarioDaoImpl();
		return pdao.readAll();
	}

	@Override
	public ArrayList<Usuario> ComboClientes() {
		UsuarioDao pdao = new UsuarioDaoImpl();
		return pdao.ComboClientes();
	}

	@Override
	public int reporteCantidadUsuarios(String desde, String hasta) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Usuario getUserByUser(String user) {
		UsuarioDao udao = new UsuarioDaoImpl();
		return udao.getUserByUser(user);
	}

}
