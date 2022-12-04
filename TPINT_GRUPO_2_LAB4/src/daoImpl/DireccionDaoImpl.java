package daoImpl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import dao.DireccionDao;
import entidad.Direccion;
import entidad.Localidad;
import entidad.Pais;

public class DireccionDaoImpl implements DireccionDao {
	
	private Direccion dir;

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
		ArrayList<Direccion> lDir = new ArrayList<Direccion>();
		Connection cn = Conexion.getConexion().getSQLConexion();
		
		try {
			
			Statement st = cn.createStatement();
			ResultSet rs = st.executeQuery("Select IDDIRECCION, CP, DESCRIPCION FROM DIRECCIONES where cp="+id);
				
			Localidad l = new Localidad();
			
			while(rs.next()) {
				initialize();
				l.setCP(rs.getString("CP"));
				dir.setLocalidad(l);
				dir.setIdDireccion(rs.getInt("IDDIRECCION"));
				dir.setDescripicion(rs.getString("DESCRIPCION"));
				
				lDir.add(dir);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return lDir;
	}

	@Override
	public ArrayList<Direccion> readAll() {
		ArrayList<Direccion> lDir = new ArrayList<Direccion>();
		Connection cn = Conexion.getConexion().getSQLConexion();
		
		try {
			
			Statement st = cn.createStatement();
			ResultSet rs = st.executeQuery("Select IDDIRECCION, CP, DESCRIPCION FROM DIRECCIONES");
				
			Localidad l = new Localidad();
			
			while(rs.next()) {
				initialize();
				l.setCP(rs.getString("CP"));
				dir.setLocalidad(l);
				dir.setIdDireccion(rs.getInt("IDDIRECCION"));
				dir.setDescripicion(rs.getString("DESCRIPCION"));
				
				lDir.add(dir);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return lDir;
	}
	
	
	public void initialize() {
		dir = new Direccion();
	}

}
