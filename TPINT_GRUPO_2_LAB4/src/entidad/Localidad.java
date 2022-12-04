package entidad;

public class Localidad {
	private String CP;
	private Pais Pais;
	private String Descripcion;
	
	public Localidad() {
		
	}
	
	public Localidad(String CP, Pais Pais, String Descripcion) {
		this.CP = CP;
		this.Pais = Pais;
		this.Descripcion = Descripcion;
	}
	
	public Localidad(String cpcito) {
		this.CP = cpcito;
	}

	public String getCP() {
		return CP;
	}

	public void setCP(String cP) {
		CP = cP;
	}

	public Pais getPais() {
		return Pais;
	}

	public void setPais(Pais pais) {
		Pais = pais;
	}

	public String getDescripcion() {
		return Descripcion;
	}

	public void setDescripcion(String descripcion) {
		Descripcion = descripcion;
	}

	@Override
	public String toString() {
		return "Localidad [CP=" + CP + ", Pais=" + Pais + ", Descripcion=" + Descripcion + "]";
	}
	
}
