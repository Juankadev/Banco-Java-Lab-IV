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

import entidad.Direccion;
import negocio.DireccionNegocio;
import negocioImpl.DireccionNegocioImpl;

/**
 * Servlet implementation class filtrosUbicacionServlet
 */
@WebServlet("/filtrosUbicacionServlet")
public class filtrosUbicacionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public filtrosUbicacionServlet() {
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
		
		//FILTRAR DIRECCIONES POR LOCALIDAD
		if(request.getParameter("idlocalidad") != null) {
			int idlocalidad = Integer.parseInt(request.getParameter("idlocalidad"));
			DireccionNegocio lneg = new DireccionNegocioImpl();
			
			ArrayList<Direccion> list = lneg.getDireccionByID(idlocalidad);
			
			Gson gson = new Gson();
			String jsonArray = gson.toJson(list);
			
			PrintWriter out = response.getWriter();
			out.print(jsonArray);
			out.flush();
		}
		
	}

}
