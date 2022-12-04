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
import negocio.PrestamoNegocio;
import negocioImpl.PrestamoNegocioImpl;

/**
 * Servlet implementation class ListarPrestamosServlet
 */
@WebServlet("/ListarPrestamosServlet")
public class ListarPrestamosServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ListarPrestamosServlet() {
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
		try {
			ArrayList<Prestamo> prestamos = pn.readAll();
			ArrayList<Prestamo> aprobados = new ArrayList<>();
			if (prestamos.isEmpty()) res.setStatus(HttpServletResponse.SC_NO_CONTENT);
			else {

				// Se agregan solo los prestamos aprobados
				for(Prestamo p : prestamos) {
					if (p.isAprobado())
						aprobados.add(p);
				}
				// Se parsea a un jsonArray
				String jsonArray = new Gson().toJson(aprobados);
				
				// Se devuelve response del array
				PrintWriter out = res.getWriter();
				out.print(jsonArray);
				out.flush();
			}
		} catch (Exception e) {

			req.setAttribute("estado", "Error al obtener prestamos");
		}
	}

}
