package servlets;

import java.io.IOException;
import java.time.LocalDateTime;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entidad.Cuenta;
import entidad.Movimiento;
import entidad.Prestamo;
import entidad.TipoMovimiento;
import negocio.PrestamoNegocio;
import negocioImpl.CuentaNegocioImpl;
import negocioImpl.MovimientoNegocioImpl;
import negocioImpl.PrestamoNegocioImpl;

/**
 * Servlet implementation class AprobarPrestamoServlet
 */
@WebServlet("/AprobarPrestamoServlet")
public class AprobarPrestamoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AprobarPrestamoServlet() {
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
		PrestamoNegocio pn = new PrestamoNegocioImpl();
		Prestamo p = pn.getPrestamoByID(Integer.parseInt(req.getParameter("idPrestamo")));
		if (p == null || !p.getEstado()) {
			res.setStatus(HttpServletResponse.SC_NO_CONTENT);
		}
		// Prestamo aprobado
		else if (Boolean.parseBoolean(req.getParameter("aprobado"))) {
			
			p.setAprobado(true);
			
			// Actualizar prestamo a aprobado
			if (pn.update(p)) {
				double importeSolicitado = p.getImporteSolicitado();
				
				// Crear movimiento del ingreso de dinero
				Cuenta cuenta = p.getCuenta();
				Movimiento movimiento = new Movimiento();
				movimiento.setCuenta(cuenta);
				movimiento.setFecha(LocalDateTime.now());
				movimiento.setImporte(importeSolicitado);
				movimiento.setTipo(new TipoMovimiento(2, null));
				
				if (new MovimientoNegocioImpl().insert(movimiento)) {
					
					// Se agrega el importe solicitado a la cuenta
					cuenta.setSaldo(cuenta.getSaldo() + p.getImporteSolicitado());
					
					if (new CuentaNegocioImpl().update(cuenta)) {
						
						res.setStatus(HttpServletResponse.SC_OK);
						
					} // TODO: revertir cambios si hay falla
					else {
						res.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
					}
				}
			}
		}
		// Prestamo desaprobado
		else {
			p.setEstado(false);
			if (pn.update(p))
				res.setStatus(HttpServletResponse.SC_OK);
		}
		
	}

}
