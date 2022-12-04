package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entidad.Prestamo;
import negocio.PrestamoNegocio;
import negocioImpl.PrestamoNegocioImpl;

/**
 * Servlet implementation class EliminarPrestamoServlet
 */
@WebServlet("/EliminarPrestamoServlet")
public class EliminarPrestamoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EliminarPrestamoServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// TODO Auto-generated method stub
		res.getWriter().append("Served at: ").append(req.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		PrestamoNegocio pn = new PrestamoNegocioImpl();
		
		int id = Integer.parseInt(req.getParameter("idPrestamo")); // Importe solicitado
		
		try {
			Prestamo p = new Prestamo();
			p.setIDPrestamo(id);
			
			if(pn.delete(p)) res.setStatus(HttpServletResponse.SC_NO_CONTENT);
			else res.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			
		} catch (Exception e) {

			req.setAttribute("estado", "Error al eliminar prestamo");
		}
	}

}
