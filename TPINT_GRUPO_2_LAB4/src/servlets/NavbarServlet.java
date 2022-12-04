package servlets;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import negocio.CuentaNegocio;
import negocio.DireccionNegocio;
import negocio.LocalidadNegocio;
import negocio.PaisNegocio;
import negocio.RolNegocio;
import negocio.TipoCuentaNegocio;
import negocio.UsuarioNegocio;
import negocioImpl.CuentaNegocioImpl;
import negocioImpl.DireccionNegocioImpl;
import negocioImpl.LocalidadNegocioImpl;
import negocioImpl.PaisNegocioImpl;
import negocioImpl.RolNegocioImpl;
import negocioImpl.TipoCuentaNegocioImpl;
import negocioImpl.UsuarioNegocioImpl;

/**
 * Servlet implementation class NavbarServlet
 */
@WebServlet("/NavbarServlet")
public class NavbarServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NavbarServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		
		if(request.getParameter("clientes") != null ) {
			
			try {
				UsuarioNegocio uNeg= new UsuarioNegocioImpl();
				request.setAttribute("comboClientes", uNeg.ComboClientes());
				
				PaisNegocio pNeg = new PaisNegocioImpl(); 
				request.setAttribute("listPais", pNeg.readAll());
				
				LocalidadNegocio lNeg = new LocalidadNegocioImpl();
				request.setAttribute("listLocalidad", lNeg.readAll());
				
				DireccionNegocio dNeg = new DireccionNegocioImpl();
				request.setAttribute("listDir", dNeg.readAll());
				
				RolNegocio rNeg = new RolNegocioImpl();
				request.setAttribute("listRol", rNeg.readAll());
				
			} catch (Exception e) {
				throw e;
			}
			
			RequestDispatcher rd = request.getRequestDispatcher("/NewClientes.jsp");
			rd.forward(request, response);
		}
		
		if(request.getParameter("cuentas") != null) {
			try {
				
				CuentaNegocio cNeg = new CuentaNegocioImpl();
				request.setAttribute("listadocuentas", cNeg.readAll());
				
				TipoCuentaNegocio tcn = new TipoCuentaNegocioImpl();
				request.setAttribute("comboTipoCuentas", tcn.readAll());
				
				UsuarioNegocio uNeg = new UsuarioNegocioImpl();
				request.setAttribute("comboClientes", uNeg.ComboClientes());
			} catch (Exception e) {
				throw e;
			}
			
			RequestDispatcher rd = request.getRequestDispatcher("/Cuentas.jsp");
			rd.forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getParameter("logOut") != null) {
			request.getSession().setAttribute("userLogueado", null);
			RequestDispatcher rd = request.getRequestDispatcher("/Index.jsp");
			rd.forward(request, response);
		}
	}

}
