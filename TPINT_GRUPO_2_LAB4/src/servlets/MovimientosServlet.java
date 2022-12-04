package servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entidad.Movimiento;
import entidad.Usuario;

import negocio.MovimientoNegocio;
import negocioImpl.MovimientoNegocioImpl;


/**
 * Servlet implementation class MovimientosServlet
 */
@WebServlet("/MovimientosServlet")
public class MovimientosServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MovimientosServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		if(request.getParameter("movement") != null) {
			MovimientoNegocio mNeg = new MovimientoNegocioImpl();
			try {
				Usuario user = (Usuario)request.getSession().getAttribute("userLogueado");
				
				ArrayList<Movimiento> movimientos = mNeg.getMovimientosByDNI(user.getPersona().getDNI());
				request.setAttribute("movimientos", movimientos);
				
				
			} catch (Exception e) {
				throw e;
			}
			RequestDispatcher rd = request.getRequestDispatcher("/Movimientos.jsp");
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
