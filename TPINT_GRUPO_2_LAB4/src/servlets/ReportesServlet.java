package servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import negocio.PrestamoNegocio;
import negocioImpl.PrestamoNegocioImpl;


@WebServlet("/ReportesServlet")
public class ReportesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public ReportesServlet() {
        super();
        // TODO Auto-generated constructor stub
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			
		if(request.getParameter("desde") != null && request.getParameter("hasta") != null) {
			
			String desde = request.getParameter("desde");
			String hasta = request.getParameter("hasta");
			
			PrestamoNegocio pn = new PrestamoNegocioImpl();
			
			try {
				int total = pn.cantidad_totalPrestamos(desde, hasta);
				int solicitados = pn.cantidad_prestamosSolicitados(desde, hasta);
				int aprobados = pn.cantidad_prestamosAprobados(desde, hasta);
				int rechazados = pn.cantidad_prestamosRechazados(desde, hasta);
				Double promedio = pn.promedio_prestamos(desde, hasta);
				
				
				
				request.setAttribute("total", total);
				request.setAttribute("solicitados", solicitados);
				request.setAttribute("aprobados", aprobados);
				request.setAttribute("rechazados", rechazados);
				request.setAttribute("promedio", promedio);
			} catch (Exception e) {
				throw e;
			}
			

			
		}
		if(request.getParameter("btnLogin") != null) {
			login(request, response);
		}
		RequestDispatcher rd = request.getRequestDispatcher("/Reportes.jsp");
		rd.forward(request, response);
	}
	
	protected void login(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
		RequestDispatcher rd = request.getRequestDispatcher("/Index.jsp");
		rd.forward(request, response);
	}
	

}
