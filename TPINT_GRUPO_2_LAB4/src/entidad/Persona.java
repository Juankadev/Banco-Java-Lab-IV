package entidad;

import java.time.LocalDate;
import java.util.Date;

public class Persona {
	private int DNI;
	private Pais Pais;
	private String Sexo;
	private Direccion Direccion;
	private Localidad loca;
	private String CUIL;
	private String Nombre;
	private String Apellido;
	private LocalDate FechaNac;
	private Date FechaNacimientoPrueba;
	private String Correo;
	private boolean Estado;
	
	public Persona() {
		
	}
	
	public Persona(int dNi,Pais ps, String sexo) {
		this.DNI = dNi;
		this.setPais(ps);
		this.Sexo = sexo;
	}

	public Persona(int dNI, entidad.Pais pais, String sexo, entidad.Direccion direccion, String cUIL, String nombre,
			String apellido, LocalDate fechaNac, String correo, boolean estado, Localidad l) {
		this.DNI = dNI;
		this.Pais = pais;
		this.Sexo = sexo;
		this.Direccion = direccion;
		this.CUIL = cUIL;
		this.Nombre = nombre;
		this.Apellido = apellido;
		this.FechaNac = fechaNac;
		this.Correo = correo;
		this.Estado = estado;
		this.loca = l;
	}
	
	public Date getFechaNacimientoPrueba() {
		return FechaNacimientoPrueba;
	}

	public void setFechaNacimientoPrueba(Date fechaNacimientoPrueba) {
		FechaNacimientoPrueba = fechaNacimientoPrueba;
	}

	public int getDNI() {
		return DNI;
	}

	public void setDNI(int dNI) {
		DNI = dNI;
	}

	public Pais getPais() {
		return Pais;
	}

	public void setPais(Pais pais) {
		Pais = pais;
	}

	public String getSexo() {
		return Sexo;
	}

	public void setSexo(String sexo) {
		Sexo = sexo;
	}

	public Direccion getDireccion() {
		return Direccion;
	}

	public void setDireccion(Direccion direccion) {
		Direccion = direccion;
	}

	public String getCUIL() {
		return CUIL;
	}

	public void setCUIL(String cUIL) {
		CUIL = cUIL;
	}

	public String getNombre() {
		return Nombre;
	}

	public void setNombre(String nombre) {
		Nombre = nombre;
	}

	public String getApellido() {
		return Apellido;
	}

	public void setApellido(String apellido) {
		Apellido = apellido;
	}

	public LocalDate getFechaNac() {
		return FechaNac;
	}

	public void setFechaNac(LocalDate fechaNac) {
		FechaNac = fechaNac;
	}

	public String getCorreo() {
		return Correo;
	}

	public void setCorreo(String correo) {
		Correo = correo;
	}

	public boolean isEstado() {
		return Estado;
	}

	public void setEstado(boolean estado) {
		Estado = estado;
	}
	
	public Localidad getLoca() {
		return loca;
	}

	public void setLoca(Localidad loca) {
		this.loca = loca;
	}

	@Override
	public String toString() {
		return "Persona [DNI=" + DNI + ", Pais=" + Pais + ", Sexo=" + Sexo + ", Direccion=" + Direccion + ", loca="
				+ loca + ", CUIL=" + CUIL + ", Nombre=" + Nombre + ", Apellido=" + Apellido + ", FechaNac=" + FechaNac
				+ ", Correo=" + Correo + ", Estado=" + Estado + "]";
	}
	
}
