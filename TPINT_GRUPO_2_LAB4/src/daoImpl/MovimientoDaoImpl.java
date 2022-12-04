package daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Types;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import dao.CuentaDao;
import dao.MovimientoDao;
import entidad.Cuenta;
import entidad.Direccion;
import entidad.Localidad;
import entidad.Movimiento;
import entidad.Pais;
import entidad.Persona;
import entidad.Rol;
import entidad.TipoCuenta;
import entidad.TipoMovimiento;
import entidad.Usuario;

public class MovimientoDaoImpl implements MovimientoDao{

	private Cuenta cuenta;
	private Usuario usuario;
	private Persona persona;
	private Pais pais;
	private Localidad localidad;
	private Direccion direccion;
	private Rol rol;
	private TipoCuenta tipoCuenta;
	private TipoMovimiento tmovimiento;
	private Movimiento movimiento;
	@Override
	public boolean insert(Movimiento movimiento) {
		//insert into movimientos values (1, 1, 1, curdate(), 135900.50);
		PreparedStatement statement;
		Connection con = Conexion.getConexion().getSQLConexion();
		
		try {
			statement = con.prepareStatement("INSERT into Movimientos values(?,?,?,?,?)");
			
			if (movimiento.getIdMovimiento() == null) {
				statement.setNull(1, Types.INTEGER);
			}
			else {
				statement.setInt(1, movimiento.getIdMovimiento());
			}
			statement.setInt(2, movimiento.getTipo().getIDTipoMovimiento());
			statement.setInt(3, movimiento.getCuenta().getNumeroCuenta());
			statement.setString(4, movimiento.getFecha().toString());
			statement.setDouble(5, movimiento.getImporte());
			
			
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
		
		return false;
	}

	@Override
	public boolean delete(Movimiento movimiento) {
		PreparedStatement statement;
		Connection con = Conexion.getConexion().getSQLConexion();
		
		try {
			statement = con.prepareStatement("Update Movimientos Set Estado=? where IDMovimiento=?");
			statement.setBoolean(1, false);
			statement.setInt(2, movimiento.getIdMovimiento());
			
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
		
		return false;
	}

	@Override
	public boolean update(Movimiento movimiento) {
		PreparedStatement statement;
		Connection con = Conexion.getConexion().getSQLConexion();
		
		try {
			statement = con.prepareStatement("Update Movimientos Set IDTipoMovimiento=?, NumeroCuenta=?, Importe=? Where IDMovimiento=?");
			statement.setInt(1, movimiento.getTipo().getIDTipoMovimiento());
			statement.setInt(2, movimiento.getCuenta().getNumeroCuenta());
			statement.setDouble(3, movimiento.getImporte());
			statement.setInt(4, movimiento.getIdMovimiento());
			
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
		
		return false;
	}

	@Override
	public Movimiento getMovimientoByID(int id) {
		Connection cn = Conexion.getConexion().getSQLConexion();
		//Movimiento movimiento = new Movimiento();
		try {
			Statement st = cn.createStatement();
			ResultSet rs = st.executeQuery("Select m.IDMovimiento, tm.IDTipoMovimiento, tm.Descripcion DescripcionTipoMovimiento, cu.NumeroCuenta, m.Fecha, m.Importe\r\n" + 
					"From Movimientos m Inner Join TipoMovimiento tm on m.IDTipoMovimiento = tm.IDTipoMovimiento\r\n" + 
					"Inner Join Cuentas cu on m.NumeroCUenta = cu.NumeroCuenta\r\n" + 
					"Where m.idMovimiento = "+id);
			if(rs.next()) {
				inicializar();
				
				movimiento.setIdMovimiento(rs.getInt("IDMovimiento"));
				tmovimiento.setIDTipoMovimiento(rs.getInt("IDTipoMovimiento"));
				tmovimiento.setDescripcion(rs.getString("DescripcionTipoMovimiento"));
				movimiento.setTipo(tmovimiento);
				CuentaDao cdao = new CuentaDaoImpl();
				movimiento.setCuenta(cdao.getCuentaByID(rs.getInt("NumeroCuenta")));
				movimiento.setFecha(LocalDateTime.parse(rs.getString("Fecha")));
				movimiento.setImporte(rs.getDouble("Importe"));
				
			}
		} catch (Exception e) {
			e.printStackTrace();
			
		}
		return movimiento;
	}

	@Override
	public ArrayList<Movimiento> readAll() {
		ArrayList<Movimiento> movimientos = new ArrayList<Movimiento>();
		Connection cn = Conexion.getConexion().getSQLConexion();
		try {
			Statement st = cn.createStatement();
			ResultSet rs = st.executeQuery("Select m.IDMovimiento, tm.IDTipoMovimiento, tm.Descripcion DescripcionTipoMovimiento, cu.NumeroCuenta, m.Fecha, m.Importe\r\n" + 
					"From Movimientos m Inner Join TipoMovimiento tm on m.IDTipoMovimiento = tm.IDTipoMovimiento\r\n" + 
					"Inner Join Cuentas cu on m.NumeroCUenta = cu.NumeroCuenta");
			while(rs.next()) {
				inicializar();
				
				movimiento.setIdMovimiento(rs.getInt("IDMovimiento"));
				tmovimiento.setIDTipoMovimiento(rs.getInt("IDTipoMovimiento"));
				tmovimiento.setDescripcion(rs.getString("DescripcionTipoMovimiento"));
				movimiento.setTipo(tmovimiento);
				CuentaDao cdao = new CuentaDaoImpl();
				movimiento.setCuenta(cdao.getCuentaByID(rs.getInt("NumeroCuenta")));
				movimiento.setFecha(LocalDateTime.parse(rs.getString("Fecha")));
				movimiento.setImporte(rs.getDouble("Importe"));
				movimientos.add(movimiento);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return movimientos;
	}
	
	public void inicializar() {
		usuario = new Usuario();
		persona = new Persona();
		pais = new Pais();
		localidad = new Localidad();
		direccion = new Direccion();
		rol = new Rol();
		tipoCuenta = new TipoCuenta();
		cuenta = new Cuenta();
		tmovimiento = new TipoMovimiento();
		movimiento = new Movimiento();
	}

	@Override
	public ArrayList<Movimiento> getMovimientosByDNI(int dni) {
		ArrayList<Movimiento> movimientos = new ArrayList<Movimiento>();
		Connection cn = Conexion.getConexion().getSQLConexion();
		try {
			Statement st = cn.createStatement();
			ResultSet rs = st.executeQuery("Select mo.IDMovimiento, mo.IDTipoMovimiento, tm.Descripcion DescripcionTipoMovimiento, mo.NumeroCuenta, mo.Fecha, mo.Importe\r\n" + 
					"From Movimientos mo Inner Join Cuentas cu on mo.NumeroCuenta = cu.NumeroCuenta \r\n" + 
					"Inner Join TipoMovimiento tm on mo.IDTipoMovimiento = tm.IDTipoMovimiento\r\n" + 
					"Where cu.DNI = "+ dni + 
					" Order by mo.NumeroCuenta, Fecha desc");
			while(rs.next()) {
				inicializar();
				
				movimiento.setIdMovimiento(rs.getInt("IDMovimiento"));
				tmovimiento.setIDTipoMovimiento(rs.getInt("IDTipoMovimiento"));
				tmovimiento.setDescripcion(rs.getString("DescripcionTipoMovimiento"));
				movimiento.setTipo(tmovimiento);
				CuentaDao cdao = new CuentaDaoImpl();
				int i = rs.getInt("NumeroCuenta");
				cuenta = cdao.getCuentaByID(i);
				movimiento.setCuenta(cuenta);
				movimiento.setFecha(LocalDateTime.parse(rs.getString("Fecha"), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
				movimiento.setImporte(rs.getDouble("Importe"));
				movimientos.add(movimiento);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return movimientos;
	}

	@Override
	public int cantMovimientos() {
		int cantMovimientos;
		Connection cn = Conexion.getConexion().getSQLConexion();
		try {
			Statement st = cn.createStatement();
			ResultSet rs = st.executeQuery("Select COUNT(*) CantMovimientos From Movimientos mo Inner Join Cuentas cu"
					+ " on mo.NumeroCuenta = cu.NumeroCuenta");
			if(rs.next()) {
				cantMovimientos = rs.getInt("CantMovimientos");
				return cantMovimientos;
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1;
	}

}
