package negocio;

import java.util.ArrayList;

import entidad.Prestamo;

public interface PrestamoNegocio{
	public boolean insert(Prestamo p);
	public boolean delete(Prestamo p);
	public boolean update(Prestamo p);
	public Prestamo getPrestamoByID(int id);
	public ArrayList<Prestamo> readAll();
	public ArrayList<Prestamo> readSolicitudes();
	public int cantidad_prestamosSolicitados(String desde, String hasta);
	public int cantidad_prestamosAprobados(String desde,String hasta);
	public Double promedio_prestamos(String desde, String hasta);
	public ArrayList<Prestamo>getPrestamosByDNI(int dni);

	public int cantidad_totalPrestamos(String desde, String hasta);

	public int cantidad_prestamosRechazados(String desde, String hasta);

	public ArrayList<Prestamo> get_allPrestamos_byDni(int dni);
}