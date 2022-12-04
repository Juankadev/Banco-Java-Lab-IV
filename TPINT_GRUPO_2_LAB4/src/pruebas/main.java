package pruebas;

import java.time.LocalDate;
import java.time.LocalDateTime;

import dao.CuentaDao;
import daoImpl.CuentaDaoImpl;
import entidad.Cuenta;
import entidad.Direccion;
import entidad.Localidad;
import entidad.Pais;
import entidad.Persona;
import entidad.Rol;
import entidad.TipoCuenta;
import entidad.Usuario;

public class main {

	public static void main(String[] args) {
		CuentaDao cuentaDao = new CuentaDaoImpl();
		/*Pais pais = new Pais(1, "Argentina");
		Localidad localidad = new Localidad("B1646", pais,"San Fernando");
		Direccion direccion = new Direccion(20, localidad, "Av. Libertador 864");
		TipoCuenta tipoCuenta = new TipoCuenta(1, "Caja de ahorro");
		Persona persona = new Persona(999999999, pais, "M",	direccion, "20-999999799-2", "Leandro", "Gomez",
				LocalDate.now(), "leandro@gmail.com", true);
		Rol rol = new Rol(1, "Banco");
		Usuario usuario = new Usuario(persona, "lgomez", "123", rol, true);
		Cuenta cuenta = new Cuenta(1000, usuario, "0000006338531381329", tipoCuenta,
				LocalDateTime.now(), 299999.50, true);
		
		
		if(cuentaDao.delete(cuenta)) {
			System.out.println("La cuenta ha sido BORRADA con exito");	
		}else {
			System.out.println("No se borro cuenta");
		}*/
		//System.out.println(cuentaDao.getCuentaByID(1000));
		for (Cuenta index : cuentaDao.readAll()) {
			System.out.println(index);
		}
		/*if(cuentaDao.insert(cuenta)) {
			System.out.println("La cuenta ha sido agregada con exito");				
		}else {
			System.out.println("No se agrego cuenta");
		}*/
		
		/*if(cuentaDao.update(cuenta)) System.out.println("La cuenta ha sido MODIFICADA con exito");
		else System.out.println("No se modifico cuenta");*/
	}

}
