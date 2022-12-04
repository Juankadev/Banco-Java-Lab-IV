package daoImpl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;

import dao.RolDao;
import entidad.Localidad;
import entidad.Rol;
import entidad.Usuario;

public class RolDaoImpl implements RolDao{
	private Rol rolex;
	
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
		
		ArrayList<Rol> lRolex = new ArrayList<Rol>();
		Connection cn = Conexion.getConexion().getSQLConexion();
		try {
			Statement st = cn.createStatement();
			ResultSet rs = st.executeQuery("Select IDRol, Descripcion from roles ");
			while(rs.next()) {
				inicializar();
				
				rolex.setIDRol(rs.getInt("IDROL"));
				rolex.setDescripcion(rs.getString("Descripcion"));
				
				lRolex.add(rolex);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lRolex;
	}

	public void inicializar() {
		rolex = new Rol();
	}
}
