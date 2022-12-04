package entidad;

import java.util.ArrayList;

public class Reportes {
	private ArrayList<Prestamo> lPrestamos;
	private ArrayList<Usuario> lUsers;
	private ArrayList<Movimiento> lMovimientos;
	
	
	
	
	public Reportes(ArrayList<Prestamo> lPrestamos, ArrayList<Usuario> lUsers, ArrayList<Movimiento> lMovimientos) {
		this.lPrestamos = lPrestamos;
		this.lUsers = lUsers;
		this.lMovimientos = lMovimientos;
	}
	
	
	public ArrayList<Prestamo> getlPrestamos() {
		return lPrestamos;
	}
	public void setlPrestamos(ArrayList<Prestamo> lPrestamos) {
		this.lPrestamos = lPrestamos;
	}
	public ArrayList<Usuario> getlUsers() {
		return lUsers;
	}
	public void setlUsers(ArrayList<Usuario> lUsers) {
		this.lUsers = lUsers;
	}
	public ArrayList<Movimiento> getlMovimientos() {
		return lMovimientos;
	}
	public void setlMovimientos(ArrayList<Movimiento> lMovimientos) {
		this.lMovimientos = lMovimientos;
	}
	
	
	
}
