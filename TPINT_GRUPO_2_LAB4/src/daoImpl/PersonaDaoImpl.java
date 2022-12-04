package daoImpl;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import dao.PersonaDao;
import entidad.Cuenta;
import entidad.Direccion;
import entidad.Localidad;
import entidad.Pais;
import entidad.Persona;
import entidad.Rol;
import entidad.TipoCuenta;
import entidad.Usuario;

public class PersonaDaoImpl implements PersonaDao{

	private Cuenta cuenta;
	private Usuario usuario;
	private Persona persona;
	private Pais pais;
	private Localidad localidad;
	private Direccion direccion;
	private Rol rol;
	private TipoCuenta tipoCuenta;
	@Override
	public boolean insert(Persona persona) {
		
		PreparedStatement statement;
		Connection con = Conexion.getConexion().getSQLConexion();
		
		try {
			statement = con.prepareStatement("INSERT into personas values(?,?,?,?,?,?,?,?,?,?,?)");
			
			statement.setInt(1, persona.getDNI());
			//statement.setInt(1, 99999);
			
			statement.setInt(2, persona.getPais().getIdPais());
			//statement.setInt(2, 1);
			
			statement.setString(3, persona.getSexo());
			//statement.setString(3, "M");
			
			statement.setInt(4, persona.getDireccion().getIdDireccion());
			//statement.setInt(4, 1);
			
			//REVISAR
			statement.setString(5, persona.getDireccion().getLocalidad().getCP());
			//statement.setString(5, "1643");
			
			statement.setString(6, persona.getCUIL());
			//statement.setString(6, "2-99999-5");
			
			statement.setString(7, persona.getNombre());
			//statement.setString(7, "German Emanuel");
			
			statement.setString(8, persona.getApellido());
			//statement.setString(8,"Mena");
			
			statement.setString(9, persona.getFechaNac().toString());
			//long millis=System.currentTimeMillis();   
		    //java.sql.Date date = new java.sql.Date(millis); 
			//statement.setDate(9, date);
			
			statement.setString(10, persona.getCorreo());
			//statement.setString(10, "correo@correo.com");
			
			statement.setInt(11, 1);
			
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
	public boolean delete(Persona persona) {
		PreparedStatement statement;
		Connection con = Conexion.getConexion().getSQLConexion();
		
		try {
			statement = con.prepareStatement("Update Personas Set Estado=? where DNI=?");
			
			statement.setBoolean(1, persona.isEstado());
			statement.setInt(2, persona.getDNI());
						
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
	public boolean update(Persona persona) {
		PreparedStatement statement;
		Connection con = Conexion.getConexion().getSQLConexion();
		
		try {
			statement = con.prepareStatement("update personas Set IDPais=?, Sexo=?, IDDireccion=?, CP=?, Nombre=?,"
					+ " Apellido=?, FechaNac=?, Correo=?, Estado=? Where DNI=?");
			
			statement.setInt(1, persona.getPais().getIdPais());
			statement.setString(2, persona.getSexo());
			statement.setInt(3, persona.getDireccion().getIdDireccion());
			statement.setString(4, persona.getDireccion().getLocalidad().getCP());
			statement.setString(5, persona.getNombre());
			statement.setString(6, persona.getApellido());
			statement.setString(7, persona.getFechaNac().toString());
			statement.setString(8, persona.getCorreo());
			statement.setBoolean(9, persona.isEstado());
			statement.setInt(10, persona.getDNI());
			
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
	public Persona getPersonaByID(int dni) {
		Connection cn = Conexion.getConexion().getSQLConexion();
		try {
			Statement st = cn.createStatement();
			ResultSet rs = st.executeQuery("Select pe.DNI, u.Usuario, u.Contrasena, u.IDRol, r.Descripcion DescripcionRol, u.Estado EstadoUsuario ,pe.IDPais, pa.Descripcion DescripcionPais, pe.Sexo,\r\n" + 
					"d.IDDireccion, d.CP, d.Descripcion DescripcionDireccion, pe.CUIL, pe.Nombre, pe.Apellido, pe.FechaNac, pe.Correo, pe.Estado EstadoPersona\r\n" + 
					"From Personas pe Inner Join Usuarios u on pe.DNI = u.DNI\r\n" + 
					"Inner Join Roles r on u.IDRol = r.IDRol\r\n" + 
					"Inner Join Paises pa on pe.IDPais = pa.IDPais\r\n" +
					"Inner Join Direcciones d on pe.IDDireccion = d.IDDireccion AND pe.CP = d.CP\r\n" + 
					"Where pe.DNI = " + dni);
			if(rs.next()) {
				inicializar();
				
				persona.setDNI(rs.getInt("DNI"));
				usuario.setUser(rs.getString("Usuario"));
				usuario.setPass(rs.getString("Contrasena"));
				rol.setIDRol(rs.getInt("IDRol"));
				rol.setDescripcion(rs.getString("DescripcionRol"));
				usuario.setRol(rol);
				usuario.setEstado(rs.getBoolean("EstadoUsuario"));
				pais.setIdPais(rs.getInt("IDPais"));
				pais.setDescripcion(rs.getString("DescripcionPais"));
				persona.setPais(pais);
				persona.setSexo(rs.getString("Sexo"));
				direccion.setIdDireccion(rs.getInt("IDDireccion"));
				direccion.setLocalidad(new Localidad(rs.getString("CP"), pais, ""));
				direccion.setDescripicion(rs.getString("DescripcionDireccion"));
				persona.setDireccion(direccion);
				persona.setCUIL(rs.getString("CUIL"));
				persona.setNombre(rs.getString("Nombre"));
				persona.setApellido(rs.getString("Apellido"));
				persona.setFechaNac(LocalDate.parse(rs.getString("FechaNac")));
				persona.setCorreo(rs.getString("Correo"));
				persona.setEstado(rs.getBoolean("EstadoPersona"));
				
				
			}
		} catch (Exception e) {
			e.printStackTrace();
			
		}
		return persona;
	}

	@Override
	public ArrayList<Persona> readAll() {
		ArrayList<Persona> personas = new ArrayList<Persona>();
		Connection cn = Conexion.getConexion().getSQLConexion();
		try {
			Statement st = cn.createStatement();
			ResultSet rs = st.executeQuery("Select pe.DNI, u.Usuario, u.Contrasena, u.IDRol, r.Descripcion DescripcionRol, u.Estado EstadoUsuario ,pe.IDPais, pa.Descripcion DescripcionPais, pe.Sexo,\r\n" + 
					"d.IDDireccion, d.CP, d.Descripcion DescripcionDireccion, pe.CUIL, pe.Nombre, pe.Apellido, pe.FechaNac, pe.Correo, pe.Estado EstadoPersona\r\n" + 
					"From Personas pe Inner Join Usuarios u on pe.DNI = u.DNI\r\n" + 
					"Inner Join Roles r on u.IDRol = r.IDRol\r\n" + 
					"Inner Join Paises pa on pe.IDPais = pa.IDPais\r\n" + 
					"Inner Join Direcciones d on pe.IDDireccion = d.IDDireccion AND pe.CP = d.CP");
			while(rs.next()) {
				inicializar();
				
				persona.setDNI(rs.getInt("DNI"));
				usuario.setUser(rs.getString("Usuario"));
				usuario.setPass(rs.getString("Contrasena"));
				rol.setIDRol(rs.getInt("IDRol"));
				rol.setDescripcion(rs.getString("DescripcionRol"));
				usuario.setRol(rol);
				usuario.setEstado(rs.getBoolean("EstadoUsuario"));
				pais.setIdPais(rs.getInt("IDPais"));
				pais.setDescripcion(rs.getString("DescripcionPais"));
				persona.setPais(pais);
				persona.setSexo(rs.getString("Sexo"));
				direccion.setIdDireccion(rs.getInt("IDDireccion"));
				direccion.setLocalidad(new Localidad(rs.getString("CP"), pais, ""));
				direccion.setDescripicion(rs.getString("DescripcionDireccion"));
				persona.setDireccion(direccion);
				persona.setCUIL(rs.getString("CUIL"));
				persona.setNombre(rs.getString("Nombre"));
				persona.setApellido(rs.getString("Apellido"));
				persona.setLoca(localidad);
				persona.setFechaNac(LocalDate.parse(rs.getString("FechaNac")));
				persona.setCorreo(rs.getString("Correo"));
				persona.setEstado(rs.getBoolean("EstadoPersona"));
				personas.add(persona);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return personas;
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
	}

}
