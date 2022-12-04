package generalFunctions;

import java.util.ArrayList;

import entidad.Cuenta;

public class GeneralFunctions {
	
	
	public int retornarUltimoNroCuenta(ArrayList<Cuenta> list) {
		
		return list.get(list.size() - 1).getNumeroCuenta();
	}
}
