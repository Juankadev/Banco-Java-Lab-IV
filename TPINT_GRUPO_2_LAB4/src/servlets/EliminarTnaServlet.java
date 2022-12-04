package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import negocio.TnaNegocio;
import negocioImpl.TnaNegocioImpl;

/**
 * Servlet implementation class EliminarTnaServlet
 */
@WebServlet("/EliminarTnaServlet")
public class EliminarTnaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EliminarTnaServlet() {
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
			TnaNegocio t = new TnaNegocioImpl();
			double tna = t.getTna().get(0);
			if (new TnaNegocioImpl().delete(tna))
				res.setStatus(HttpServletResponse.SC_NO_CONTENT);

		} catch (Exception e) {

			req.setAttribute("estado", "Error al eliminar tna");
		}
	}

}
