package entidad;

public class TipoCuenta {
	
	private int IDTipoCuenta;
	private String Descripcion;

	public TipoCuenta() {
		
	}

	public TipoCuenta(int idTipoCuenta, String descripcion) {
		super();
		this.IDTipoCuenta = idTipoCuenta;
		this.Descripcion = descripcion;
	}

	public int getIDTipoCuenta() {
		return IDTipoCuenta;
	}

	public void setIDTipoCuenta(int iDTipoCuenta) {
		IDTipoCuenta = iDTipoCuenta;
	}

	public String getDescripcion() {
		return Descripcion;
	}

	public void setDescripcion(String descripcion) {
		Descripcion = descripcion;
	}

	@Override
	public String toString() {
		return "TipoCuenta [IDTipoCuenta=" + IDTipoCuenta + ", Descripcion=" + Descripcion + "]";
	}
	
}
