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
import negocioImpl.PrestamoNegocioImpl;

/**
 * Servlet implementation class ListarSolicitudesPrestamosServlet
 */
@WebServlet("/ListarSolicitudesPrestamosServlet")
public class ListarSolicitudesPrestamosServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ListarSolicitudesPrestamosServlet() {
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
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		try {
			ArrayList<Prestamo> solicitudes = new PrestamoNegocioImpl().readSolicitudes();
			if (solicitudes.isEmpty()) res.setStatus(HttpServletResponse.SC_NO_CONTENT);
			else {
				// Se parsea a un jsonArray
				String jsonArray = new Gson().toJson(solicitudes);
				
				// Se devuelve response del array
				PrintWriter out = res.getWriter();
				out.print(jsonArray);
				out.flush();
			}
		} catch (Exception e) {

			req.setAttribute("estado", "Error al obtener solicitudes");
		}
		
		
		
		
	}

}
