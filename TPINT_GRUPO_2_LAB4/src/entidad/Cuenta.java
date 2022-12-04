package entidad;
import java.time.LocalDateTime;


public class Cuenta {
	
	private int NumeroCuenta;
	private Usuario Usuario;
	private String CBU;
	private TipoCuenta TipoCuenta;
	private LocalDateTime FechaCreacion;
	private double Saldo;
	private boolean Estado;
	
	public Cuenta() {
		
	}

	public Cuenta(int numeroCuenta, entidad.Usuario Usuario, String cBU, entidad.TipoCuenta tipoCuenta,
			LocalDateTime fechaCreacion, double saldo, boolean estado) {
		this.NumeroCuenta = numeroCuenta;
		this.Usuario = Usuario;
		this.CBU = cBU;
		this.TipoCuenta = tipoCuenta;
        this.FechaCreacion = fechaCreacion;
		this.Saldo = saldo;
		this.Estado = estado;
	}

	public int getNumeroCuenta() {
		return NumeroCuenta;
	}

	public void setNumeroCuenta(int numeroCuenta) {
		NumeroCuenta = numeroCuenta;
	}

	public Usuario getUsuario() {
		return Usuario;
	}

	public void setUsuario(Usuario Usuario) {
		this.Usuario = Usuario;
	}

	public String getCBU() {
		return CBU;
	}

	public void setCBU(String cBU) {
		CBU = cBU;
	}

	public TipoCuenta getTipoCuenta() {
		return TipoCuenta;
	}

	public void setTipoCuenta(TipoCuenta tipoCuenta) {
		TipoCuenta = tipoCuenta;
	}

	public LocalDateTime getFechaCreacion() {
		return FechaCreacion;
	}

	public void setFechaCreacion(LocalDateTime fechaCreacion) {
		FechaCreacion = fechaCreacion;
	}

	public double getSaldo() {
		return Saldo;
	}

	public void setSaldo(double saldo) {
		Saldo = saldo;
	}

	public boolean isEstado() {
		return Estado;
	}

	public void setEstado(boolean estado) {
		Estado = estado;
	}

	@Override
	public String toString() {
		return "Cuenta [NumeroCuenta=" + NumeroCuenta + ", Usuario=" + Usuario + ", CBU=" + CBU + ", TipoCuenta="
				+ TipoCuenta + ", FechaCreacion=" + FechaCreacion + ", Saldo=" + Saldo + ", Estado=" + Estado + "]";
	}
	
}
