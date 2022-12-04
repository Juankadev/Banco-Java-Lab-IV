package entidad;

public class Rol {
	private int IDRol;
	private String Descripcion;
	
	public Rol() {
		
	}
	
	public Rol(int idRol, String descripcion) {
		this.IDRol = idRol;
		this.Descripcion = descripcion;
	}

	public int getIDRol() {
		return IDRol;
	}

	public void setIDRol(int iDRol) {
		IDRol = iDRol;
	}

	public String getDescripcion() {
		return Descripcion;
	}

	public void setDescripcion(String descripcion) {
		Descripcion = descripcion;
	}

	@Override
	public String toString() {
		return "Rol [IDRol=" + IDRol + ", Descripcion=" + Descripcion + "]";
	}
	
}
