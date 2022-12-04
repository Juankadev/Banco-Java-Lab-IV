package entidad;

public class Telefono {
	private Persona Persona;
	private String Telefono;
	private boolean Estado;
	
	public Telefono() {
		
	}

	public Telefono(entidad.Persona persona, String telefono, boolean estado) {
		this.Persona = persona;
		this.Telefono = telefono;
		this.Estado = estado;
	}

	public Persona getPersona() {
		return Persona;
	}

	public void setPersona(Persona persona) {
		Persona = persona;
	}

	public String getTelefono() {
		return Telefono;
	}

	public void setTelefono(String telefono) {
		Telefono = telefono;
	}

	public boolean isEstado() {
		return Estado;
	}

	public void setEstado(boolean estado) {
		Estado = estado;
	}

	@Override
	public String toString() {
		return "Telefono [Persona=" + Persona + ", Telefono=" + Telefono + ", Estado=" + Estado + "]";
	}
	
}

