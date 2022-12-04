package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import entidad.Prestamo;
import negocio.PrestamoNegocio;
import negocioImpl.PrestamoNegocioImpl;

/**
 * Servlet implementation class ListarPrestamoPorId
 */
@WebServlet("/ListarPrestamoPorId")
public class ListarPrestamoPorId extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ListarPrestamoPorId() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		PrestamoNegocio pn = new PrestamoNegocioImpl();
		try {
			Prestamo prestamo = pn.getPrestamoByID(Integer.parseInt(req.getParameter("idPrestamo")));
			if (prestamo.getIDPrestamo() == null) res.setStatus(HttpServletResponse.SC_NO_CONTENT);
			else {
				// Se parsea a un jsonArray
				String jsonArray = new Gson().toJson(prestamo);
				
				// Se devuelve response del array
				PrintWriter out = res.getWriter();
				out.print(jsonArray);
				out.flush();
			}
		} catch (Exception e) {

			req.setAttribute("estado", "Error al obtener prestamo");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
	}

}
