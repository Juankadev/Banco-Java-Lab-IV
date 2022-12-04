package servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//import entidad.TipoCuenta;
import entidad.Usuario;
//import negocio.CuentaNegocio;
import negocio.UsuarioNegocio;
//import negocioImpl.CuentaNegocioImpl;
import negocioImpl.UsuarioNegocioImpl;

/**
 * Servlet implementation class IndexServlet
 */
@WebServlet("/IndexServlet")
public class IndexServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public IndexServlet() {
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
		if(request.getParameter("btnIngresar") != null) {
			UsuarioNegocio un = new UsuarioNegocioImpl();
			Usuario usuario = new Usuario();
			try {
				if(request.getParameter("user") != "" && request.getParameter("password") != "") {
					String user=request.getParameter("user");
					String pass=request.getParameter("password");
					//obtenerUsuario(user, un);
					usuario = obtenerUsuario(user, un);
					if(usuario != null) {
						if(usuario.getPass().equals(pass)) {
							request.getSession().setAttribute("userLogueado", usuario);
							//request.setAttribute("loginOK", "El user"+usuario.getPersona().getNombre()+" se logueo con exito");
							
							RequestDispatcher rd = request.getRequestDispatcher("/Home.jsp");
							rd.forward(request, response);
						}else {
							request.setAttribute("loginFail", "Error al loguearse");
							RequestDispatcher rd = request.getRequestDispatcher("/Index.jsp");
							rd.forward(request, response);
						}
					}else {
						request.setAttribute("loginFail", "Error al loguearse");
						RequestDispatcher rd = request.getRequestDispatcher("/Index.jsp");
						rd.forward(request, response);
					}
					
				}else {
					request.setAttribute("loginFail", "Error al loguearse");
					RequestDispatcher rd = request.getRequestDispatcher("/Index.jsp");
					rd.forward(request, response);
				}
			} catch (Exception e) {
				request.setAttribute("loginFail", "Error al loguearse");
				throw e;
			}
			

		}
	}
	
	protected Usuario obtenerUsuario(String user, UsuarioNegocio un) {
		ArrayList<Usuario>listado = un.readAll();
		for (Usuario x : listado) {
			if(x.getUser().equals(user)) return x;
		}
		return null;
	}

}
