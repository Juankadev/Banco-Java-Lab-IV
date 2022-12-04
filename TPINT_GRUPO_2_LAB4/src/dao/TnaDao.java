package dao;

import java.util.ArrayList;

public interface TnaDao {
	public boolean insert(double tna);
	public boolean delete(double tna);
	public boolean update(double tna);
	public ArrayList<Double> getTna();
}
