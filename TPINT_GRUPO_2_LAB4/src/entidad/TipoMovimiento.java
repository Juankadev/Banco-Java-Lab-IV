package entidad;

public class TipoMovimiento {
	
	private int IDTipoMovimiento;
	private String Descripcion;
	
	public TipoMovimiento() {
		
	}
	
	public TipoMovimiento(int idTipoMovimiento, String descripcion) {
		super();
		this.IDTipoMovimiento = idTipoMovimiento;
		this.Descripcion = descripcion;
	}

	public int getIDTipoMovimiento() {
		return IDTipoMovimiento;
	}

	public void setIDTipoMovimiento(int iDTipoMovimiento) {
		IDTipoMovimiento = iDTipoMovimiento;
	}

	public String getDescripcion() {
		return Descripcion;
	}

	public void setDescripcion(String descripcion) {
		Descripcion = descripcion;
	}

	@Override
	public String toString() {
		return "TipoMovimiento [IDTipoMovimiento=" + IDTipoMovimiento + ", Descripcion=" + Descripcion + "]";
	}

}
