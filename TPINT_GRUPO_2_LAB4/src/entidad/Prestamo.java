package entidad;

import java.time.LocalDate;

public class Prestamo {
	private Integer IDPrestamo;
	private Cuenta Cuenta;
	private LocalDate Fecha;
	private double ImporteSolicitado;
	private int PlazoPagoMeses;
	private double tna;
	private int CuotasRestantes;
	private boolean Aprobado;
	private boolean Estado;

	public Prestamo() {

	}

	public Prestamo(Integer iDPrestamo, Cuenta cuenta, LocalDate fecha, /* double importeAPagar, */
			double importeSolicitado, int plazoPagoMeses, /* double cuotaMensual, */ double tna, int cuotasRestantes,
			boolean aprobado) {
		this.IDPrestamo = iDPrestamo;
		this.Cuenta = cuenta;
		this.Fecha = fecha;
		this.ImporteSolicitado = importeSolicitado;
		this.PlazoPagoMeses = plazoPagoMeses;
		this.tna = tna;
		this.CuotasRestantes = cuotasRestantes;
		this.Aprobado = aprobado;
	}

	public Integer getIDPrestamo() {
		return IDPrestamo;
	}

	public void setIDPrestamo(Integer iDPrestamo) {
		IDPrestamo = iDPrestamo;
	}

	public Cuenta getCuenta() {
		return Cuenta;
	}

	public void setCuenta(Cuenta cuenta) {
		Cuenta = cuenta;
	}

	public LocalDate getFecha() {
		return Fecha;
	}

	public void setFecha(LocalDate fecha) {
		Fecha = fecha;
	}

	public double getImporteSolicitado() {
		return ImporteSolicitado;
	}

	public void setImporteSolicitado(double importeSolicitado) {
		ImporteSolicitado = importeSolicitado;
	}

	public int getPlazoPagoMeses() {
		return PlazoPagoMeses;
	}

	public void setPlazoPagoMeses(int plazoPagoMeses) {
		PlazoPagoMeses = plazoPagoMeses;
	}

	public double getTna() {
		return tna;
	}

	public void setTna(double tna) {
		this.tna = tna;
	}

	public int getCuotasRestantes() {
		return CuotasRestantes;
	}

	public void setCuotasRestantes(int cuotasRestantes) {
		CuotasRestantes = cuotasRestantes;
	}

	public boolean isAprobado() {
		return Aprobado;
	}

	public void setAprobado(boolean aprobado) {
		Aprobado = aprobado;
	}

	public boolean getEstado() {
		return Estado;
	}

	public void setEstado(boolean estado) {
		Estado = estado;
	}
}
