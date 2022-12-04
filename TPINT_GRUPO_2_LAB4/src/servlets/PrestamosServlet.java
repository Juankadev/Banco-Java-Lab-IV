package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
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
 * Servlet implementation class PrestamosServlet
 */
@WebServlet("/PrestamosServlet")
public class PrestamosServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PrestamosServlet() {
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
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getParameter("btnAprobarSolicitud") != null) {
			aprobarSolicitud(request, response);
		}
		if(request.getParameter("btnRechazarSolicitud") != null) {
			rechazarSolicitud(request, response);
		}
		
		if(request.getParameter("prestamoDni") != null) {
			prestamosAsociados(request, response);
		}
	}
	
	
	protected void prestamosAsociados(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
		
		PrestamoNegocio pn = new PrestamoNegocioImpl();
		
		int dni = Integer.parseInt(request.getParameter("prestamoDni"));
		
		ArrayList<Prestamo> list = pn.get_allPrestamos_byDni(dni);
		
		Gson gson = new Gson();
		String jsonArray = gson.toJson(list);
		
		PrintWriter out = response.getWriter();
		out.print(jsonArray);
		out.flush();
	}
	
	protected void aprobarSolicitud(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrestamoNegocio pNeg = new PrestamoNegocioImpl();
		if(request.getParameter("idPrestamo") != null) {
			Prestamo p = pNeg.getPrestamoByID(Integer.parseInt(request.getParameter("idPrestamo")));
			p.setAprobado(true);
			request.setAttribute("update", pNeg.update(p));
		}
		RequestDispatcher rd = request.getRequestDispatcher("/SolicitudesPrestamos.jsp");
		rd.forward(request, response);
	}
	
	protected void rechazarSolicitud(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrestamoNegocio pNeg = new PrestamoNegocioImpl();
		if(request.getParameter("idPrestamo") != null) {
			int i = Integer.parseInt(request.getParameter("idPrestamo"));
			Prestamo p = pNeg.getPrestamoByID(i);
			p.setAprobado(false);
			request.setAttribute("update", pNeg.update(p));
		}
		RequestDispatcher rd = request.getRequestDispatcher("/SolicitudesPrestamos.jsp");
		rd.forward(request, response);
	}

}
