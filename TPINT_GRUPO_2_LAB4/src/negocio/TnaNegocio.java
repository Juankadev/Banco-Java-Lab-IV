package negocio;

import java.util.ArrayList;

public interface TnaNegocio{
	public boolean insert(double t);
	public boolean delete(double t);
	public boolean update(double t);
	public ArrayList<Double> getTna();
}