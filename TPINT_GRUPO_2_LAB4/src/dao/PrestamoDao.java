package dao;

import java.util.ArrayList;

import entidad.Prestamo;

public interface PrestamoDao {
	public boolean insert(Prestamo prestamo);
	public boolean delete(Prestamo prestamo);
	public boolean update(Prestamo prestamo);
	public Prestamo getPrestamoByID(int idPrestamo);
	public ArrayList<Prestamo> readAll();
	public ArrayList<Prestamo> readSolicitudes();
	public int cantidad_totalPrestamos(String desde, String hasta);
	public int cantidad_prestamosSolicitados(String desde, String hasta);
	public int cantidad_prestamosAprobados(String desde,String hasta);
	public int cantidad_prestamosRechazados(String desde, String hasta);
	public Double promedio_prestamos(String desde, String hasta);
	public ArrayList<Prestamo> getPrestamosByDNI(int dni);
	public ArrayList<Prestamo> get_allPrestamos_byDni(int dni);
}
