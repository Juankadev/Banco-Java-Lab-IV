package entidad;

public class PrestamoTasas {
	private double tna;
	private double tem;
	private double tea;
	private double cfttea;

	public double getTna() {
		return tna;
	}

	public void setTna(double tna) {
		this.tna = tna;
	}

	public PrestamoTasas(double tna, double tem, double tea, double cfttea) {
		super();
		this.tna = tna;
		this.tem = tem;
		this.tea = tea;
		this.cfttea = cfttea;
	}

	public double getTem() {
		return tem;
	}

	public void setTem(double tem) {
		this.tem = tem;
	}

	public double getTea() {
		return tea;
	}

	public void setTea(double tea) {
		this.tea = tea;
	}

	public double getCfttea() {
		return cfttea;
	}

	public void setCfttea(double cfttea) {
		this.cfttea = cfttea;
	}
}