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

import negocioImpl.TnaNegocioImpl;

/**
 * Servlet implementation class ListarTnaServlet
 */
@WebServlet("/ListarTnaServlet")
public class ListarTnaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ListarTnaServlet() {
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
		try {
			ArrayList<Double> tna = new ArrayList<>();
			tna = new TnaNegocioImpl().getTna();
			if (tna.size() > 1) res.setStatus(HttpServletResponse.SC_CONFLICT);
			else if (tna.isEmpty()) res.setStatus(HttpServletResponse.SC_NO_CONTENT);
			else {
				// Se parsea a un jsonArray
				String jsonArray = new Gson().toJson(tna);
				
				// Se devuelve response del array
				PrintWriter out = res.getWriter();
				out.print(jsonArray);
				out.flush();
			}
		} catch (Exception e) {

			req.setAttribute("estado", "Error al obtener tna");
		}
	}

}
