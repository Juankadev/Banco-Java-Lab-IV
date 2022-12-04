package entidad;

public class Usuario {
	private Persona Persona;
	private String User;
	private String Pass;
	private Rol Rol;
	private boolean Estado;
	private TipoUsuario userType;
	
	public Usuario() {
		
	}

	public Usuario(entidad.Persona persona, String user, String pass, entidad.Rol rol, boolean estado) {
		this.Persona = persona;
		this.User = user;
		this.Pass = pass;
		this.Rol = rol;
		this.Estado = estado;
	}

	public Persona getPersona() {
		return Persona;
	}

	public void setPersona(Persona object) {
		this.Persona = object;
	}

	public String getUser() {
		return User;
	}

	public void setUser(String user) {
		User = user;
	}

	public String getPass() {
		return Pass;
	}

	public void setPass(String pass) {
		Pass = pass;
	}

	public Rol getRol() {
		return Rol;
	}

	public void setRol(Rol rol) {
		Rol = rol;
	}

	public boolean isEstado() {
		return Estado;
	}

	public void setEstado(boolean estado) {
		Estado = estado;
	}

	@Override
	public String toString() {
		return "Usuario [Persona=" + Persona + ", User=" + User + ", Pass=" + Pass + ", Rol=" + Rol + ", Estado="
				+ Estado + "]";
	}


}
