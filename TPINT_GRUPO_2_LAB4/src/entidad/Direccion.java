package entidad;

public class Direccion {
	private int IdDireccion;
	private Localidad Localidad;
	private String Descripcion;
	
	public Direccion() {
		
	}
	
	public Direccion(int idDireccion, Localidad localidad, String descripcion) {
		this.IdDireccion = idDireccion;
		this.Localidad = localidad;
		this.Descripcion = descripcion;
	}

	public int getIdDireccion() {
		return IdDireccion;
	}

	public void setIdDireccion(int idDireccion) {
		this.IdDireccion = idDireccion;
	}

	public Localidad getLocalidad() {
		return Localidad;
	}

	public void setLocalidad(Localidad localidad) {
		this.Localidad = localidad;
	}

	public String getDescripicion() {
		return Descripcion;
	}

	public void setDescripicion(String descripicion) {
		this.Descripcion = descripicion;
	}

	@Override
	public String toString() {
		return "Direccion [idDireccion=" + IdDireccion + ", localidad=" + Localidad.getDescripcion() + ", descripicion=" + Descripcion
				+ "]";
	}
	
	
}
