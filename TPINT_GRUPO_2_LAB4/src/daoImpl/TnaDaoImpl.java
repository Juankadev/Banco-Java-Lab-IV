package daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import dao.TnaDao;

public class TnaDaoImpl implements TnaDao{
	
	private String table = "tasanominalanual";

	@Override
	public boolean insert(double tna) {
		PreparedStatement pst;
		Connection con = Conexion.getConexion().getSQLConexion();
		try {
			// Si ya existe el valor, se debe informar al admin
			// para que haga un update o delete
			if (rowsPointer() > 0) {
				return false;
			}
			pst = con.prepareStatement("INSERT into "+ table +" values(?)");
			
			pst.setDouble(1, tna);
			
			if(pst.executeUpdate() > 0) {
				con.commit();
				return true;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			try {
				con.rollback();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
			
		}
		return false;
	}

	@Override
	public boolean delete(double tna) {
		Connection con = Conexion.getConexion().getSQLConexion();
		
		try {
			if(con.prepareStatement("DELETE FROM "+ table +" WHERE (`tna` = '"+ tna +"')").executeUpdate() > 0) {
				con.commit();
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			try {
				con.rollback();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		
		return true;
	}

	@Override
	public boolean update(double tna) {
		Connection con = Conexion.getConexion().getSQLConexion();
		try {
			if (con.prepareStatement("UPDATE " + table + " SET `tna` = '" + tna + "'"
					+ "WHERE (`tna` = '" + getTna().get(0) +"')").executeUpdate() > 0) {
				con.commit();
			}

		} catch (Exception e) {
			e.printStackTrace();
			try {
				con.rollback();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}

		return true;
	}

	@Override
	public ArrayList<Double> getTna() {
		Connection cn = Conexion.getConexion().getSQLConexion();
		ArrayList<Double> tna = new ArrayList<>();
		try {
			Statement st = cn.createStatement();
			ResultSet rs = st.executeQuery("SELECT * FROM " + table);
			while (rs.next()) {
				tna.add(rs.getDouble("tna"));
			}

		} catch (Exception e2) {
			e2.printStackTrace();
		}
		// Deberia devolverse un arrayList de un solo elemento
		// Si devuelve un arrayList con varios elementos, entonces hay error en insert
		return tna;
	}

	private int rowsPointer() {
		Connection con = Conexion.getConexion().getSQLConexion();
		int count = 0;
		try {
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("SELECT count(*) AS total FROM " + table);
			while (rs.next()) {
			    count = rs.getInt("total");
			}
		} catch (Exception e) {
			e.printStackTrace();
			try {
				con.rollback();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return count;
	}
}