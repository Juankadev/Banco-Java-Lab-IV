package entidad;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Movimiento {
	
	private Integer IdMovimiento;
	private TipoMovimiento Tipo;
	private Cuenta Cuenta;
	private LocalDateTime Fecha;
	private double Importe;
	
	public Movimiento() {
		
	}
	
	public Movimiento(Integer idMovimiento, TipoMovimiento tipo, Cuenta cuenta, LocalDateTime fecha, double importe) {
		this.IdMovimiento = idMovimiento;
		this.Tipo = tipo;
		this.Cuenta = cuenta;
		this.Fecha = fecha;
		this.Importe = importe;
	}

	public Integer getIdMovimiento() {
		return IdMovimiento;
	}

	public void setIdMovimiento(Integer idMovimiento) {
		IdMovimiento = idMovimiento;
	}

	public TipoMovimiento getTipo() {
		return Tipo;
	}

	public void setTipo(TipoMovimiento tipo) {
		Tipo = tipo;
	}

	public Cuenta getCuenta() {
		return Cuenta;
	}

	public void setCuenta(Cuenta cuenta) {
		Cuenta = cuenta;
	}

	public LocalDateTime getFecha() {
		return Fecha;
	}

	public void setFecha(LocalDateTime fecha) {
		Fecha = fecha;
	}

	public double getImporte() {
		return Importe;
	}

	public void setImporte(double importe) {
		Importe = importe;
	}

	@Override
	public String toString() {
		return "Movimiento [IdMovimiento=" + IdMovimiento + ", Tipo=" + Tipo + ", Cuenta=" + Cuenta + ", Fecha=" + Fecha
				+ ", Importe=" + Importe + "]";
	}
	
}
