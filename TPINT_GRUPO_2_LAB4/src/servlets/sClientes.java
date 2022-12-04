package servlets;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entidad.Direccion;
import entidad.Localidad;
import entidad.Pais;
import entidad.Persona;
import entidad.Rol;
import entidad.Usuario;
import negocio.DireccionNegocio;
import negocio.LocalidadNegocio;
import negocio.PaisNegocio;
import negocio.PersonaNegocio;
import negocio.RolNegocio;
import negocio.UsuarioNegocio;
import negocioImpl.DireccionNegocioImpl;
import negocioImpl.LocalidadNegocioImpl;
import negocioImpl.PaisNegocioImpl;
import negocioImpl.PersonaNegocioImpl;
import negocioImpl.RolNegocioImpl;
import negocioImpl.UsuarioNegocioImpl;

/**
 * Servlet implementation class sClientes
 */
@WebServlet("/sClientes")
public class sClientes extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public sClientes() {
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
		PersonaNegocio pNegocio = new PersonaNegocioImpl();
		UsuarioNegocio uNegocio = new UsuarioNegocioImpl();
		Usuario user = new Usuario();
		Persona pPersona = new Persona();
		try {
			
			pPersona.setApellido(request.getParameter("txtApellido"));
			pPersona.setNombre(request.getParameter("txtNombre"));
			pPersona.setDNI(Integer.parseInt(request.getParameter("txtDNI")));
			pPersona.setSexo(request.getParameter("sexo"));
			pPersona.setFechaNac(LocalDate.parse(request.getParameter("fecnac"), DateTimeFormatter.ofPattern("yyyy-MM-dd")));
			pPersona.setCorreo(request.getParameter("email"));
			pPersona.setPais(new Pais());
			pPersona.getPais().setIdPais(Integer.parseInt(request.getParameter("nacionalidad")));
			pPersona.setDireccion(new Direccion());
			pPersona.getDireccion().setIdDireccion(Integer.parseInt(request.getParameter("direccion")));
			pPersona.getDireccion().setLocalidad(new Localidad());
			pPersona.getDireccion().getLocalidad().setCP(request.getParameter("localidad"));
			pPersona.setEstado(true);
			
			if(pNegocio.update(pPersona)) {
				
				user.setEstado(true);
				user.setPersona(pPersona);
				user.setRol(new Rol());
				user.getRol().setIDRol(Integer.parseInt(request.getParameter("tiporol")));
				user.setUser(request.getParameter("username"));
				user.setPass(request.getParameter("password"));
				
				if(uNegocio.update(user)) {
					request.setAttribute("modificado", "Se ha Modificado el usuario con exito");
				}
				else {
					request.setAttribute("modificado", "Ocurrio un error al modificar el usuario");
				}
				
			}
			else {
				request.setAttribute("modificado", "Ocurrio un error al modificar los datos de la persona");
			}
			
			
			UsuarioNegocio uNeg= new UsuarioNegocioImpl();
			request.setAttribute("comboClientes", uNeg.ComboClientes());
			
			PaisNegocio pNeg = new PaisNegocioImpl(); 
			request.setAttribute("listPais", pNeg.readAll());
			
			LocalidadNegocio lNeg = new LocalidadNegocioImpl();
			request.setAttribute("listLocalidad", lNeg.readAll());
			
			DireccionNegocio dNeg = new DireccionNegocioImpl();
			request.setAttribute("listDir", dNeg.readAll());
			
			RolNegocio rNeg = new RolNegocioImpl();
			request.setAttribute("listRol", rNeg.readAll());
			
			
		} catch (Exception e) {
			
			request.setAttribute("modificado", "Ocurrio un error al crear la cuenta");
			throw e;
		}
		
		RequestDispatcher rd = request.getRequestDispatcher("/NewClientes.jsp");
		rd.forward(request, response);
	}

}
