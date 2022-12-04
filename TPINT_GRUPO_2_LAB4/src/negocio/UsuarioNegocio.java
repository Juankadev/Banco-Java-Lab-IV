package negocio;

import java.util.ArrayList;

import entidad.Persona;
import entidad.Usuario;

public interface UsuarioNegocio {
	public boolean insert(Usuario user);
	public boolean delete(Usuario user);
	public boolean update(Usuario user);
	public Usuario getUserByID(int dni);
	public Usuario getUserByUser(String user);
	public ArrayList<Usuario> readAll();
	public ArrayList<Usuario> ComboClientes();
	public int reporteCantidadUsuarios(String desde, String hasta);
}
