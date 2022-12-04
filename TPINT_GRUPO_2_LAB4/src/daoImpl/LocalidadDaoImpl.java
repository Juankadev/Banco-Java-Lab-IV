package daoImpl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import dao.LocalidadDao;
import entidad.Localidad;
import entidad.Pais;

public class LocalidadDaoImpl implements LocalidadDao{
	
	private Localidad loca;

	@Override
	public boolean insert(Localidad localidad) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(Localidad localidad) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(Localidad localidad) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public ArrayList<Localidad> filter(String CP, Pais pais) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Localidad> readAll() {
		
		ArrayList<Localidad> lLoca = new ArrayList<Localidad>();
		Connection cn = Conexion.getConexion().getSQLConexion();
		
		try {
			
			Statement st = cn.createStatement();
			ResultSet rs = st.executeQuery("Select CP, descripcion from Localidades");
			
			
			while(rs.next()) {
				initialize();
				loca.setCP(rs.getString("CP"));
				loca.setDescripcion(rs.getString("descripcion"));
				
				lLoca.add(loca);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return lLoca;
	}
	
	
	public void initialize() {
		loca = new Localidad();
	}

	@Override
	public ArrayList<Localidad> get_localidad_by_id(int id) {
		return null;
	}

}
