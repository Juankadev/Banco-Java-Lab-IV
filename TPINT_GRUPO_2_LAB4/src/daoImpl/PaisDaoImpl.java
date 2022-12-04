package daoImpl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import dao.PaisDao;
import entidad.Pais;

public class PaisDaoImpl implements PaisDao{
	
	private Pais ps;
	
	
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
		
		ArrayList<Pais> lPais = new ArrayList<Pais>();
		Connection cn = Conexion.getConexion().getSQLConexion();
		
		try {
			
			Statement st = cn.createStatement();
			ResultSet rs = st.executeQuery("Select idpais, descripcion from paises");
			
			while(rs.next()) {
				initialize();
				ps.setIdPais(rs.getInt("idpais"));
				ps.setDescripcion(rs.getString("descripcion"));
				
				lPais.add(ps);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return lPais;
	}
	
	
	public void initialize() {
		ps = new Pais();
	}
	
}
