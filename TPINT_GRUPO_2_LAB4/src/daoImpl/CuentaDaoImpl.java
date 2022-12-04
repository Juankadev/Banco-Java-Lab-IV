 package daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import dao.CuentaDao;
import entidad.Cuenta;
import entidad.Persona;
import entidad.Usuario;


public class CuentaDaoImpl implements CuentaDao {
	
	@Override
	public boolean insert(Cuenta cuenta) {
		PreparedStatement statement;
		Connection con = Conexion.getConexion().getSQLConexion();
		
		try {
			statement = con.prepareStatement("INSERT into cuentas values(?,?,?,?,?,?,?)");
			statement.setInt(1, cuenta.getNumeroCuenta());
			statement.setInt(2, cuenta.getUsuario().getPersona().getDNI());
			statement.setString(3, cuenta.getCBU());
			statement.setInt(4, cuenta.getTipoCuenta().getIDTipoCuenta());
			statement.setString(5, cuenta.getFechaCreacion().toString());
			statement.setDouble(6, 10000.00);
			statement.setBoolean(7, cuenta.isEstado());
			
			if(statement.executeUpdate() > 0) {
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
		finally {
			Conexion.instancia.cerrarConexion();
		}
		
		return false;
	}

	@Override
	public boolean delete(Cuenta cuenta) {
		PreparedStatement statement;
		Connection con = Conexion.getConexion().getSQLConexion();
		
		try {
			statement = con.prepareStatement("Update Cuentas Set Estado=? where NumeroCuenta=?");
			statement.setBoolean(1, false);
			statement.setInt(2, cuenta.getNumeroCuenta());
			
			if(statement.executeUpdate() > 0) {
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
		finally {
			Conexion.instancia.cerrarConexion();
		}
		
		return false;
	}

	@Override
	public boolean update(Cuenta cuenta) {
		PreparedStatement statement;
		Connection con = Conexion.getConexion().getSQLConexion();
		
		try {
			statement = con.prepareStatement("Update Cuentas SET DNI=?, TipoCuenta=?, Saldo=?, Estado=? Where NumeroCuenta=?");
			statement.setInt(1, cuenta.getUsuario().getPersona().getDNI());
			statement.setInt(2, cuenta.getTipoCuenta().getIDTipoCuenta());
			statement.setDouble(3, cuenta.getSaldo());
			statement.setBoolean(4, cuenta.isEstado());
			statement.setInt(5, cuenta.getNumeroCuenta());
			
			if(statement.executeUpdate() > 0) {
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
		finally {
			Conexion.instancia.cerrarConexion();
		}
		
		return false;
	}

	@Override
	public Cuenta getCuentaByID(int numeroCuenta) {
		Connection cn = Conexion.getConexion().getSQLConexion();
		Cuenta cuenta = new Cuenta();
		try {
			Statement st = cn.createStatement();
			ResultSet rs = st.executeQuery("SELECT c.* FROM cuentas c"
					+ " INNER JOIN usuarios u ON c.DNI = u.DNI"
					+ " INNER JOIN Personas pe ON u.DNI = pe.DNI"
					+ " WHERE NumeroCuenta = " + numeroCuenta);
			if(rs.next()) {
				cuenta = setCuenta(rs, cuenta);
			}
		} catch (Exception e) {
			e.printStackTrace();
			
		}
		return cuenta;
	}
	

	@Override
	public ArrayList<Cuenta> readAll() {
		ArrayList<Cuenta> cuentas = new ArrayList<Cuenta>();
		Cuenta cuenta = new Cuenta();
		Connection cn = Conexion.getConexion().getSQLConexion();
		try {
			Statement st = cn.createStatement();
			ResultSet rs = st.executeQuery("SELECT * FROM cuentas;");
			while(rs.next()) {
				cuenta = setCuenta(rs, cuenta);
				cuentas.add(cuenta);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cuentas;
	}

	@Override
	public ArrayList<Cuenta> getCuentaByUser(int dni) {
		ArrayList<Cuenta> cuentas = new ArrayList<Cuenta>();
		Connection cn = Conexion.getConexion().getSQLConexion();
		Cuenta cuenta = new Cuenta();
		try {
			Statement st = cn.createStatement();
			ResultSet rs = st.executeQuery("SELECT * FROM cuentas\r\n"
					+ "					INNER JOIN personas ON cuentas.DNI = personas.DNI\r\n"
					+ "					WHERE personas.DNI = " + dni);
			while(rs.next()) {
				cuenta = setCuenta(rs, cuenta);
				cuentas.add(cuenta);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cuentas;
	}
	
	private Cuenta setCuenta(ResultSet rs, Cuenta cuenta) throws SQLException {
		cuenta = new Cuenta();
		cuenta.setCBU(rs.getString("CBU"));
		cuenta.setTipoCuenta(new TipoCuentaDaoImpl().getById(rs.getInt("TipoCuenta")));
		cuenta.setEstado(rs.getBoolean("Estado"));
		cuenta.setFechaCreacion(LocalDateTime.parse(rs.getString("FechaCreacion"), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
		cuenta.setNumeroCuenta(rs.getInt("NumeroCuenta"));
		cuenta.setSaldo(rs.getDouble("Saldo"));
		cuenta.setUsuario(new UsuarioDaoImpl().getUserByID(rs.getInt("DNI")));
		return cuenta;
	}

	@Override
	public int total_cuentas(int dni) {
		int cant  = 0;
		Connection cn = Conexion.getConexion().getSQLConexion();
		Cuenta cuenta = new Cuenta();
		try {
			Statement st = cn.createStatement();
			ResultSet rs = st.executeQuery("SELECT count(*) as cant FROM cuentas\r\n"
					+ "					INNER JOIN personas ON cuentas.DNI = personas.DNI\r\n"
					+ "					WHERE personas.DNI = " + dni);
			while(rs.next()) {
				cant = rs.getInt("cant");
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cant;
	}

	@Override
	public Cuenta getCuentaByCbu(String cbu) {
		Connection cn = Conexion.getConexion().getSQLConexion();
		Cuenta cuenta = new Cuenta();
		try {
			Statement st = cn.createStatement();
			ResultSet rs = st.executeQuery("SELECT c.* FROM cuentas c"
					+ " INNER JOIN usuarios u ON c.DNI = u.DNI"
					+ " INNER JOIN Personas pe ON u.DNI = pe.DNI"
					+ " WHERE CBU = " + cbu);
			if(rs.next()) {
				cuenta = setCuenta(rs, cuenta);
			}
		} catch (Exception e) {
			e.printStackTrace();
			
		}
		return cuenta;
	}

	@Override
	public Cuenta get_datoscuenta_by_cbu(String cbu) {
		Connection cn = Conexion.getConexion().getSQLConexion();
		Cuenta cuenta = new Cuenta();
		Persona p = new Persona();
		Usuario us = new Usuario();
		try {
			Statement st = cn.createStatement();
			ResultSet rs = st.executeQuery("select c.NumeroCuenta, c.CBU, c.Saldo, p.Nombre , p.Apellido , p.DNI  from cuentas c \r\n" + 
					"inner join personas p on p.DNI = c.DNI "
					+ " WHERE c.CBU = " + cbu);
			if(rs.next()) {
				cuenta.setNumeroCuenta(rs.getInt("NumeroCuenta"));
				cuenta.setCBU(rs.getString("CBU"));
				cuenta.setSaldo(rs.getDouble("Saldo"));
				p.setDNI(rs.getInt("DNI"));
				p.setNombre(rs.getString("Nombre"));
				p.setApellido(rs.getString("Apellido"));
				us.setPersona(p);
				cuenta.setUsuario(us);
			
			}
		} catch (Exception e) {
			e.printStackTrace();
			
		}
		return cuenta;
	}
	
}
