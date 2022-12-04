package servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entidad.Usuario;
import negocio.CuentaNegocio;
import negocio.PrestamoNegocio;
import negocioImpl.CuentaNegocioImpl;
import negocioImpl.PrestamoNegocioImpl;

/**
 * Servlet implementation class DatosUserServlet
 */
@WebServlet("/DatosUserServlet")
public class DatosUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DatosUserServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		if(request.getParameter("datos") != null) {
		CuentaNegocio cNeg = new CuentaNegocioImpl(); 
		PrestamoNegocio pNeg = new PrestamoNegocioImpl();
		
		try {
			Usuario user = (Usuario)request.getSession().getAttribute("userLogueado");

			request.setAttribute("comboCuentas", cNeg.getCuentaByUser(user.getPersona().getDNI()));
			request.setAttribute("comboPrestamos", pNeg.getPrestamosByDNI(user.getPersona().getDNI()));
		} catch (Exception e) {
			throw e;
		}
		
		RequestDispatcher rd = request.getRequestDispatcher("/DatosUser.jsp");
		rd.forward(request, response);
		}
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
