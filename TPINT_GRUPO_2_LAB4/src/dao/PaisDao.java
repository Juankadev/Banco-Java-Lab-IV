package dao;
import java.util.ArrayList;

import entidad.Pais;

public interface PaisDao {
	public boolean insert(Pais p);
	public boolean delete(Pais p);
	public boolean update(Pais p);
	public ArrayList<Pais> filter(int id);
	public ArrayList<Pais> readAll();
}
