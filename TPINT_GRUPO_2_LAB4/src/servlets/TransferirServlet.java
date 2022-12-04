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
import entidad.TipoMovimiento;
import entidad.Usuario;
import negocio.MovimientoNegocio;
import negocioImpl.CuentaNegocioImpl;
import negocioImpl.MovimientoNegocioImpl;

/**
 * Servlet implementation class TransferirServlet
 */
@WebServlet("/TransferirServlet")
public class TransferirServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TransferirServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		if(request.getParameter("transferir") != null) {
		
		try {
			Usuario user = (Usuario) request.getSession().getAttribute("userLogueado");
			ArrayList<Cuenta> cuentasuser = new CuentaNegocioImpl().getCuentaByUser(user.getPersona().getDNI());
			
			request.setAttribute("comboCuentas", cuentasuser);
			
		} 
		catch (Exception e) {
			throw e;
		}
		
		
		
		
		RequestDispatcher rd = request.getRequestDispatcher("/Transferir.jsp");
		rd.forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
		//FORMULARIO NO MODAL
		if(request.getParameter("btncontinuar") != null)
		{
			request.setAttribute("cuentaorigen", "");
			request.setAttribute("usuarioorigen", "");
			request.setAttribute("saldoorigen", "");
			
			request.setAttribute("usuariodestino", "");
			request.setAttribute("cuentadestino", "");
			
			Usuario user = (Usuario) request.getSession().getAttribute("userLogueado");
			ArrayList<Cuenta> cuentasuser = new CuentaNegocioImpl().getCuentaByUser(user.getPersona().getDNI());
			
			request.setAttribute("comboCuentas", cuentasuser);
			
			RequestDispatcher rd = request.getRequestDispatcher("/Transferir.jsp");
			rd.forward(request, response);
		}
		
		if(request.getParameter("transfer") != null) {
			
			Cuenta data = new CuentaNegocioImpl().get_datoscuenta_by_cbu(request.getParameter("transfer"));
			
			Gson gson = new Gson();
			String jsonArray = gson.toJson(data);
			
			PrintWriter out = response.getWriter();
			out.print(jsonArray);
			out.flush();
		}
		
		
		
		//FORMULARIO MODAL
		if(request.getParameter("destino") != null && request.getParameter("cuentaseleccionada") != null && request.getParameter("montoEnviar")!=null) {
			
			//verificar existencia de cuenta destino
			if(existe(request, response,request.getParameter("destino").toString())) {
				
				//verificar saldo de cuenta seleccionada
				if(verificarsaldo(request, response,request.getParameter("cuentaseleccionada").toString(), Double.parseDouble(request.getParameter("montoEnviar")) )){
					//transferir (enviar e insertar el movimiento con el tipo de movimiento)
					if(transferir(request, response, request.getParameter("cuentaseleccionada").toString(), request.getParameter("destino").toString(), Double.parseDouble(request.getParameter("montoEnviar"))));
					{
						request.setAttribute("enviado", "Transferencia Exitosa!");
					}
				}
			}
			else {
				request.setAttribute("noexiste", "Cuenta destino inexistente");
			}
			
			RequestDispatcher rd = request.getRequestDispatcher("/Transferir.jsp");
			rd.forward(request, response);
		}else if(request.getParameter("btntransferir") != null) {
			Double monto= Double.parseDouble(request.getParameter("montoEnviar"));
			String destino = request.getParameter("destino");
			String origen = request.getParameter("CuentaOrigen");
			if(verificarsaldo(request, response, origen, monto)){
				if(transferir(request, response, origen, destino, monto));
				{
					request.setAttribute("enviado", "Transferencia Exitosa!");
				}
			}else {
				request.setAttribute("saldoInsuficiente", "Saldo Insuficiente!");
			}
			Usuario user = (Usuario) request.getSession().getAttribute("userLogueado");
			ArrayList<Cuenta> cuentasuser = new CuentaNegocioImpl().getCuentaByUser(user.getPersona().getDNI());
			
			request.setAttribute("comboCuentas", cuentasuser);
			RequestDispatcher rd = request.getRequestDispatcher("/Transferir.jsp");
			rd.forward(request, response);
		}
		}
		
		catch(Exception e) {
			throw e;
		}
		
		

	}
	
	protected boolean verificarsaldo(HttpServletRequest request, HttpServletResponse response, String cuentaseleccionada, Double monto)throws ServletException, IOException{
		try {
			ArrayList<Cuenta> cuentas = new CuentaNegocioImpl().readAll();
			Cuenta micuenta = new Cuenta();
			
			//guardo la cuenta
			for (Cuenta item : cuentas) {
				if(item.getNumeroCuenta() == Integer.parseInt(cuentaseleccionada)) {
					micuenta = item;
				}
			}
			
			//chequeo saldo
			if(micuenta.getSaldo() - monto >= 0) {
				return true;
			}
			else {
				return false;
			}
			

		}
		catch (Exception e) {
			throw e;
		}
		//request.setAttribute("todaslascuentas", cuentas);

	}
	
	
	
	protected Cuenta existe(String destino)throws ServletException, IOException{
		try {
				ArrayList<Cuenta> cuentas = new CuentaNegocioImpl().readAll();
				for (Cuenta item : cuentas) {
					if(item.getCBU().equals(destino)) {
						return item;
					}
				}
			return null;
		}
		
		catch (Exception e) {
			throw e;
		}
		//request.setAttribute("todaslascuentas", cuentas);

	}
	
	protected boolean existe(HttpServletRequest request, HttpServletResponse response, String destino)throws ServletException, IOException{
		try {
				ArrayList<Cuenta> cuentas = new CuentaNegocioImpl().readAll();
				for (Cuenta item : cuentas) {
					if(item.getCBU() == destino) {
						return true;
					}
				}
			return false;
		}
		
		catch (Exception e) {
			throw e;
		}
		//request.setAttribute("todaslascuentas", cuentas);

	}


	protected boolean transferir(HttpServletRequest request, HttpServletResponse response, String cuentaseleccionada, String destino, Double monto)throws ServletException, IOException{
		try {
			ArrayList<Cuenta> cuentas = new CuentaNegocioImpl().readAll();
			MovimientoNegocio mNeg = new MovimientoNegocioImpl();
			Cuenta micuenta = new Cuenta();
			Cuenta cuentadestino = new Cuenta();
			Movimiento mOrigen = new Movimiento();
			Movimiento mDestino = new Movimiento();
			
			//guardo las cuentas
			for (Cuenta item : cuentas) {
				if(item.getNumeroCuenta() == Integer.parseInt(cuentaseleccionada)) {
					micuenta = item;
				}
				if(item.getCBU().equals(destino)) {
					cuentadestino = item;
				}
			}
			
			//seteo saldos
			micuenta.setSaldo(micuenta.getSaldo()-monto);
			cuentadestino.setSaldo(cuentadestino.getSaldo()+monto);
			
			mOrigen.setIdMovimiento(mNeg.cantMovimientos()+1);
			mOrigen.setTipo(new TipoMovimiento(4,""));
			mOrigen.setCuenta(micuenta);
			mOrigen.setFecha(LocalDateTime.now());
			mOrigen.setImporte(-monto);
			
			mDestino.setIdMovimiento(mOrigen.getIdMovimiento()+1);
			mDestino.setTipo(new TipoMovimiento(4,""));
			mDestino.setCuenta(cuentadestino);
			mDestino.setFecha(LocalDateTime.now());
			mDestino.setImporte(monto);
			
			CuentaNegocioImpl cNegocio = new CuentaNegocioImpl();
			if(cNegocio.update(micuenta) &&	cNegocio.update(cuentadestino) && mNeg.insert(mOrigen) && mNeg.insert(mDestino))
			{
				return true;
			}
			else {
				return false;
			}
			

		}
		
		catch (Exception e) {
			throw e;
		}
		//request.setAttribute("todaslascuentas", cuentas);

	}
	
	
}
