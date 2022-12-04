package entidad;

public class Pais {
	private int IdPais;
	private String Descripcion;
	
	public Pais() {
		
	}
	public Pais(int IP) {
		this.IdPais = IP;
	}
	
	public Pais(int idPais, String descripcion) {
		this.IdPais = idPais;
		this.Descripcion = descripcion;
	}

	public int getIdPais() {
		return IdPais;
	}

	public void setIdPais(int idPais) {
		IdPais = idPais;
	}

	public String getDescripcion() {
		return Descripcion;
	}

	public void setDescripcion(String descripcion) {
		Descripcion = descripcion;
	}

	@Override
	public String toString() {
		return "Pais [IdPais=" + IdPais + ", Descripcion=" + Descripcion + "]";
	}
	
}
