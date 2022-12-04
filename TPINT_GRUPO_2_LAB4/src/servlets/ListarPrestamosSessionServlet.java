package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import entidad.Prestamo;
import entidad.Usuario;
import negocioImpl.PrestamoNegocioImpl;

/**
 * Servlet implementation class ListarPrestamosSessionServlet
 */
@WebServlet("/ListarPrestamosSessionServlet")
public class ListarPrestamosSessionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ListarPrestamosSessionServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		try {
			Usuario user = (Usuario) req.getSession().getAttribute("userLogueado");
			ArrayList <Prestamo> prestamos = new PrestamoNegocioImpl().getPrestamosByDNI(user.getPersona().getDNI());
			if (prestamos.isEmpty()) res.setStatus(HttpServletResponse.SC_NO_CONTENT);
			else {
				prestamos.removeIf(p -> !p.isAprobado());
				// Se parsea a un jsonArray
				String jsonArray = new Gson().toJson(prestamos);
				
				// Se devuelve response del array
				PrintWriter out = res.getWriter();
				out.print(jsonArray);
				out.flush();
			}
		} catch (Exception e) {
			req.setAttribute("estado", "Error al obtener prestamo");
		}
	}

}
