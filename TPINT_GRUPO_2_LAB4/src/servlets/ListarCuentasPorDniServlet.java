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

import entidad.Cuenta;
import negocioImpl.CuentaNegocioImpl;

/**
 * Servlet implementation class ListarCuentasPorDniServlet
 */
@WebServlet("/ListarCuentasPorDniServlet")
public class ListarCuentasPorDniServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ListarCuentasPorDniServlet() {
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
			ArrayList<Cuenta> cuentas = new CuentaNegocioImpl().getCuentaByUser(Integer.parseInt(req.getParameter("dni")));
			
			// Se parsea a un jsonArray
			Gson gson = new Gson();
			String jsonArray = gson.toJson(cuentas);
			
			// Se devuelve response del array
			PrintWriter out = res.getWriter();
			out.print(jsonArray);
			out.flush();
			
		} catch (Exception e) {
			
		}
	}

}
