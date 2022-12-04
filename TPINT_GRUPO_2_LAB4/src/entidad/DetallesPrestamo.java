package entidad;

import java.time.LocalDate;

public class DetallesPrestamo{
	private int periodo;
	private double cuota;
	private double interes; 
	private double amortizacion;
	private double saldo;
	private double valorIVA; 
	private double cuotaAPagar;
	private LocalDate fechaVencimiento;
	
	public DetallesPrestamo(int periodo, double cuota, double interes, double amortizacion, double saldo,
			double valorIVA, double cuotaAPagar, LocalDate fechaVencimiento) {
		super();
		this.periodo = periodo;
		this.cuota = cuota;
		this.interes = interes;
		this.amortizacion = amortizacion;
		this.saldo = saldo;
		this.valorIVA = valorIVA;
		this.cuotaAPagar = cuotaAPagar;
		this.setFechaVencimiento(fechaVencimiento);
	}
	
	public int getPeriodo() {
		return periodo;
	}
	public void setPeriodo(int periodo) {
		this.periodo = periodo;
	}
	public double getCuota() {
		return cuota;
	}
	public void setCuota(double cuota) {
		this.cuota = cuota;
	}
	public double getInteres() {
		return interes;
	}
	public void setInteres(double interes) {
		this.interes = interes;
	}
	public double getAmortizacion() {
		return amortizacion;
	}
	public void setAmortizacion(double amortizacion) {
		this.amortizacion = amortizacion;
	}
	public double getSaldo() {
		return saldo;
	}
	public void setSaldo(double saldo) {
		this.saldo = saldo;
	}
	public double getValorIVA() {
		return valorIVA;
	}
	public void setValorIVA(double valorIVA) {
		this.valorIVA = valorIVA;
	}
	public double getCuotaAPagar() {
		return cuotaAPagar;
	}
	public void setCuotaAPagar(double cuotaAPagar) {
		this.cuotaAPagar = cuotaAPagar;
	}

	public LocalDate getFechaVencimiento() {
		return fechaVencimiento;
	}

	public void setFechaVencimiento(LocalDate fechaVencimiento) {
		this.fechaVencimiento = fechaVencimiento;
	} 
}