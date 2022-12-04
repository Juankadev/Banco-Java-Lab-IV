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

import entidad.Finance;
import negocioImpl.TnaNegocioImpl;
import entidad.DetallesPrestamo;

/**
 * Servlet implementation class CalcularPrestamoServlet
 */
@WebServlet("/CalcularPrestamoServlet")
public class CalcularPrestamoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CalcularPrestamoServlet() {
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
		
		// Parametros obtenidos desde el usuario
		Double vp = Double.parseDouble(req.getParameter("montoPrestamo")); // Importe solicitado
		int nper = Integer.parseInt(req.getParameter("plazo")); // Periodo en meses
		//TODO Crear tabla singleton en db para guardar TNA
		Double tna = new TnaNegocioImpl().getTna().get(0); // Tasa Nominal Anual
		Double tem = tna / 12; // Tasa Efectiva Mensual
		Double cuotaPura = Finance.PMT(tem, nper, vp, 0, false) * -1;
		
		// Se calculan los valores para la tabla de prestamos
		ArrayList<DetallesPrestamo> dp =  new ArrayList<>();
		dp = Finance.calcularArrayList(dp, vp, nper, tem, cuotaPura, LocalDate.now());
		
		// Se parsea a un jsonArray
		Gson gson = new Gson();
		String jsonArray = gson.toJson(dp);
		
		// Se devuelve response del array
		PrintWriter out = res.getWriter();
		out.print(jsonArray);
		out.flush();
	}

}
