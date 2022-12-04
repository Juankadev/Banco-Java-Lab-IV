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
import entidad.ListarCuentasRespuesta;
import entidad.Usuario;
import negocioImpl.CuentaNegocioImpl;

/**
 * Servlet implementation class ListarCuentasServlet
 */
@WebServlet("/ListarCuentasServlet")
public class ListarCuentasServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ListarCuentasServlet() {
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
			ArrayList<Cuenta> cuentas = new CuentaNegocioImpl().getCuentaByUser(user.getPersona().getDNI());
			ArrayList<ListarCuentasRespuesta> listRespuesta = new ArrayList<>();
			ListarCuentasRespuesta respuesta = new ListarCuentasRespuesta();
			for (Cuenta cuenta : cuentas) {
				respuesta.setCuenta(cuenta);
				respuesta.setTipoCuenta(cuenta.getTipoCuenta());
				listRespuesta.add(respuesta);
			}
			
			
			// Se parsea a un jsonArray
			Gson gson = new Gson();
			String jsonArray = gson.toJson(listRespuesta);
			
			// Se devuelve response del array
			PrintWriter out = res.getWriter();
			out.print(jsonArray);
			out.flush();
			
		} catch (Exception e) {
			
		}
	}

}
