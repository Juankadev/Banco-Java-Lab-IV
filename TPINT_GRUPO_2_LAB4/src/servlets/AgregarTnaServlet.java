package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import negocioImpl.TnaNegocioImpl;

/**
 * Servlet implementation class AgregarTnaServlet
 */
@WebServlet("/AgregarTnaServlet")
public class AgregarTnaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AgregarTnaServlet() {
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
			if (new TnaNegocioImpl().insert(Double.parseDouble(req.getParameter("tna"))))
				res.setStatus(HttpServletResponse.SC_CREATED);
			else res.setStatus(HttpServletResponse.SC_CONFLICT);
		} catch (Exception e) {

			req.setAttribute("estado", "Error al crear TNA");
		}
	}

}
