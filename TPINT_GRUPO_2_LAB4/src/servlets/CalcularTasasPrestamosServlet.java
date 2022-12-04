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
import com.google.gson.reflect.TypeToken;

import entidad.DetallesPrestamo;
import entidad.Finance;
import entidad.PrestamoTasas;
import negocioImpl.TnaNegocioImpl;

/**
 * Servlet implementation class CalcularTasasPrestamosServlet
 */
@WebServlet("/CalcularTasasPrestamosServlet")
public class CalcularTasasPrestamosServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CalcularTasasPrestamosServlet() {
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
		res.setContentType("application/json");
		
		Double tna = new TnaNegocioImpl().getTna().get(0); // Tasa Nominal Anual
		Double tem = tna / 12; // Tasa efectiva mensual
		
		// Obtener prestamos desde AJAX Post y convertir a arraylist
		String payload = req.getParameter("prestamos");
		ArrayList<DetallesPrestamo> dp = new Gson().fromJson(payload,  new TypeToken<ArrayList<DetallesPrestamo>>(){}.getType());
		
		// Obtener flujo de dinero con y sin IVA
		double ffnsiva [] = new double [dp.size() + 1];
		double ffnciva [] = new double [dp.size() + 1];
		
		// Agregar el flujo negativo
		ffnsiva[0] = dp.get(0).getSaldo() * -1;
		ffnciva[0] = dp.get(0).getSaldo() * -1;
		
		// Agregar los valores de los flujos positivos
		for(int i = 0; i < dp.size(); i ++) {
			ffnsiva[i + 1] = dp.get(i).getCuota();
			ffnciva[i + 1] = dp.get(i).getCuotaAPagar();
		}
		
		// Calcular TIR con y sin IVA
		double tirsiva = Finance.IRR(ffnsiva, 0.10);
		double tirciva = Finance.IRR(ffnciva, 0.10);
		
		// Calcular TEA y CFTTEA
		double tea = Finance.calculateTEA(tirsiva);
		double cfttea = Finance.calculateTEA(tirciva);
		
		// Construir objeto para enviar como response
		PrestamoTasas pt = new PrestamoTasas(tna, tem, tea, cfttea);
		
		// Parsear a jsonArray
		Gson gson = new Gson();
		String jsonArray = gson.toJson(pt);
		
		// Devolver response
		PrintWriter out = res.getWriter();
		out.print(jsonArray);
		out.flush();
	}

}
