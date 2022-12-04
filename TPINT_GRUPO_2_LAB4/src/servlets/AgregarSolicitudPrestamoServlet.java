package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import entidad.Prestamo;
import entidad.msg;
import negocio.PrestamoNegocio;
import negocioImpl.CuentaNegocioImpl;
import negocioImpl.PrestamoNegocioImpl;
import negocioImpl.TnaNegocioImpl;

/**
 * Servlet implementation class AgregarPrestamo
 */
@WebServlet("/AgregarSolicitudPrestamoServlet")
public class AgregarSolicitudPrestamoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AgregarSolicitudPrestamoServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("OK");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String fileName = "AgregarSolicitudPrestamoServlet: ";
		
		ArrayList<String> estado = new ArrayList<>();
		
		try {
			
			Double vp = Double.parseDouble(req.getParameter("montoPrestamo")); // Importe solicitado
			int nper = Integer.parseInt(req.getParameter("plazo")); // Periodo en meses

			PrestamoNegocio pn = new PrestamoNegocioImpl();
			if (vp >= 10000) {
				Prestamo p = new Prestamo();
				p.setCuenta(new CuentaNegocioImpl().getCuentaByCbu(req.getParameter("cbuCuenta").replaceAll("\\D+", "")));
				p.setTna(new TnaNegocioImpl().getTna().get(0));
				p.setFecha(LocalDate.now());
				p.setImporteSolicitado(vp);
				p.setPlazoPagoMeses(nper);
				p.setCuotasRestantes(nper);
				p.setAprobado(false);
				p.setEstado(true);

				// Devolver codigo 201
				if (pn.insert(p)) {
					msg.success(fileName + "INSERT prestamo OK");
					res.setStatus(HttpServletResponse.SC_CREATED);
					estado.add("OK");
				}

			} else {
				msg.warning(fileName + "montoPrestamo BAD INPUT");
				estado.add("BAD_INPUT");
			}

		} catch (NumberFormatException e) {
			msg.warning(fileName + "montoPrestamo BAD INPUT FORMAT");
			estado.add("BAD_INPUT_FORMAT");
		}
		
		// Se parsea a un jsonArray
		Gson gson = new Gson();
		String jsonArray = gson.toJson(estado);

		// Se devuelve response del array
		PrintWriter out = res.getWriter();
		out.print(jsonArray);
		out.flush();

	}
}
