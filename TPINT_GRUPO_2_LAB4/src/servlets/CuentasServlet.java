package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import entidad.Cuenta;
import entidad.Movimiento;
import entidad.Persona;
import entidad.TipoCuenta;
import entidad.TipoMovimiento;
import entidad.Usuario;
import negocio.CuentaNegocio;
import negocio.MovimientoNegocio;
import negocio.TipoCuentaNegocio;
import negocio.UsuarioNegocio;
import negocioImpl.CuentaNegocioImpl;
import negocioImpl.MovimientoNegocioImpl;
import negocioImpl.TipoCuentaNegocioImpl;
import negocioImpl.UsuarioNegocioImpl;

/**
 * Servlet implementation class CuentasServlet
 */
@WebServlet("/CuentasServlet")
public class CuentasServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CuentasServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		if(request.getParameter("btnEditar") != null) {
			CuentaNegocio cn = new CuentaNegocioImpl();
			try {
				Cuenta cuenta = (Cuenta)request.getSession().getAttribute("cuenta");
				if(request.getParameter("Cliente") != "" && request.getParameter("Saldo") != "") {
					//Cuenta u = 
					//cuenta.setTipoCuenta(new TipoCuenta(0, request.getParameter("SelTipoCuenta")));
					cuenta.setNumeroCuenta(Integer.parseInt(request.getParameter("nroCuenta")));
					cuenta.setTipoCuenta(new TipoCuenta(Integer.parseInt(request.getParameter("SelTipoCuenta")),""));
					cuenta.setSaldo(Double.parseDouble(request.getParameter("Saldo")));
					//int i = Integer.parseInt(request.getParameter("SelEstado"));
					cuenta.setEstado(Boolean.parseBoolean(request.getParameter("SelEstado")));
					request.setAttribute("Estado", cuenta.isEstado());
					request.setAttribute("updated", cn.update(cuenta));
					request.setAttribute("edit", "Modificado con exito");
					//request.getSession().setAttribute("cuentaModificada", cuenta);
					
					request.setAttribute("listadocuentas", cn.readAll());
					
					TipoCuentaNegocio tcn = new TipoCuentaNegocioImpl();
					request.setAttribute("comboTipoCuentas", tcn.readAll());
					
					UsuarioNegocio uNeg = new UsuarioNegocioImpl();
					request.setAttribute("comboClientes", uNeg.ComboClientes());
				}
			} catch (Exception e) {

				request.setAttribute("edit", "Error al modificar");
			}
			
			RequestDispatcher rd = request.getRequestDispatcher("/Cuentas.jsp");
			rd.forward(request, response);
		}
		
		if(request.getParameter("btnCrear") != null) {
			
			insertarCuenta(request, response);
		}
		
		if(request.getParameter("btnEliminar")!=null) {
			eliminarCuenta(request, response);
		}
		if(request.getParameter("btnLogin") != null) {
			login(request, response);
		}
		if(request.getParameter("dni") != null ) {
			busqueda(request, response);
		}
		if(request.getParameter("dniCuenta") != null) {
			cuentasAsociadas(request, response);
		}

	}
	
	protected void login(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
		RequestDispatcher rd = request.getRequestDispatcher("/Index.jsp");
		rd.forward(request, response);
	}
		
	protected void cuentasAsociadas(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
		
		CuentaNegocio cn = new CuentaNegocioImpl();
		
		int dni = Integer.parseInt(request.getParameter("dniCuenta"));
		ArrayList<Cuenta> list = cn.getCuentaByUser(dni);
		
		Gson gson = new Gson();
		String jsonArray = gson.toJson(list);
		
		PrintWriter out = response.getWriter();
		out.print(jsonArray);
		out.flush();
		
	}
	
	protected void busqueda(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
		
			CuentaNegocio cn = new CuentaNegocioImpl();
		
			
			int dni = Integer.parseInt(request.getParameter("dni"));
			int cant = cn.total_cuentas(dni);
			Gson gson = new Gson();
			String jsonArray = gson.toJson(cant);
			
			PrintWriter out = response.getWriter();
			out.print(jsonArray);
			out.flush();

	}

	
	protected void insertarCuenta(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
		CuentaNegocio cn = new CuentaNegocioImpl();
		Usuario uPrueba = new Usuario();
		Persona pPrueba = new Persona();
		TipoCuenta tc = new TipoCuenta();
		Cuenta cta = new Cuenta();
		MovimientoNegocio mneg = new MovimientoNegocioImpl();
		Movimiento mov = new Movimiento();
		
		try {
			pPrueba.setDNI(Integer.parseInt(request.getParameter("new_person")));
			uPrueba.setPersona(pPrueba);
			cta.setUsuario(uPrueba);
			cta.setCBU(request.getParameter("new_cbu"));
			cta.setNumeroCuenta(Integer.parseInt(request.getParameter("new_cuenta")));
			tc.setIDTipoCuenta(Integer.parseInt(request.getParameter("tipocuenta")));
			cta.setTipoCuenta(tc);
			cta.setFechaCreacion(LocalDateTime.now());
			cta.setEstado(true);
			cta.setSaldo(0);
			
			if(cn.insert(cta)) {

				
				mov.setCuenta(cta);
				mov.setIdMovimiento(mneg.cantMovimientos()+1);
				mov.setImporte(10000);
				mov.setFecha(LocalDateTime.now());
				mov.setTipo(new TipoMovimiento(1,""));
				
				if(mneg.insert(mov)) {
					request.setAttribute("creado", "creado con exito");
				}
				
			
			}
			else {
				request.setAttribute("creado", "ocurrio un error al crear");
			}
		
			request.setAttribute("listadocuentas", cn.readAll());
			
			TipoCuentaNegocio tcn = new TipoCuentaNegocioImpl();
			request.setAttribute("comboTipoCuentas", tcn.readAll());
			
			UsuarioNegocio uNeg = new UsuarioNegocioImpl();
			request.setAttribute("comboClientes", uNeg.ComboClientes());
			
		} catch (Exception e) {

			request.setAttribute("creado", "Ocurrio un error al crear la cuenta");
		}
		
		RequestDispatcher rd = request.getRequestDispatcher("/Cuentas.jsp");
		rd.forward(request, response);

	}
	
	protected void eliminarCuenta(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		CuentaNegocio cn = new CuentaNegocioImpl();
		try {
			Cuenta cuenta = new Cuenta();
			if(request.getParameter("idCuenta") != "") {
				cuenta.setNumeroCuenta(Integer.parseInt(request.getParameter("idCuenta")));
				cuenta.setEstado(false);
				//request.setAttribute("Estado", cuenta.isEstado());
				request.setAttribute("deleted", cn.delete(cuenta));
				request.setAttribute("msgDeleted", "Eliminado con exito");
			}
			
			request.setAttribute("listadocuentas", cn.readAll());
			
			TipoCuentaNegocio tcn = new TipoCuentaNegocioImpl();
			request.setAttribute("comboTipoCuentas", tcn.readAll());
			
			UsuarioNegocio uNeg = new UsuarioNegocioImpl();
			request.setAttribute("comboClientes", uNeg.ComboClientes());
			
		} catch (Exception e) {

			request.setAttribute("msgDeleted", "Ocurrio un error al eliminar");
		}
		
		RequestDispatcher rd = request.getRequestDispatcher("/Cuentas.jsp");
		rd.forward(request, response);
	}

}
