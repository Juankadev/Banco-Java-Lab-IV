package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import entidad.Cuenta;
import entidad.Movimiento;
import entidad.Prestamo;
import entidad.TipoMovimiento;
import entidad.msg;
import negocio.CuentaNegocio;
import negocio.PrestamoNegocio;
import negocioImpl.CuentaNegocioImpl;
import negocioImpl.MovimientoNegocioImpl;
import negocioImpl.PrestamoNegocioImpl;

/**
 * Servlet implementation class PagarPrestamoServlet
 */
@WebServlet("/PagarPrestamoServlet")

public class PagarPrestamoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public PagarPrestamoServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String fileName = "PagarPrestamoServlet: ";
		try {
			Double monto = Double.parseDouble(req.getParameter("monto"));
			int idPrestamo = Integer.parseInt(req.getParameter("idPrestamo"));
			int cantidadCuotas = Integer.parseInt(req.getParameter("cantidadCuotas"));
			String cbuCuenta = req.getParameter("cbuCuenta").replaceAll("\\D+", "");

			CuentaNegocio cn = new CuentaNegocioImpl();

			Cuenta cuenta = cn.getCuentaByCbu(cbuCuenta);
			double saldoCuenta = cuenta.getSaldo();

			ArrayList<String> estado = new ArrayList<>();

			if (monto == 0 || cantidadCuotas == 0) {
				// Error no seleccion de cuotas
				msg.warning(fileName + "monto, cantidadCuotas BAD INPUT");
				estado.add("BAD_INPUT");
			} else {
				if (saldoCuenta < monto) {
					// Error de saldo insuficiente
					estado.add("CONFLICT_SALDO");
					msg.warning(fileName + "Saldo insuficiente");
				} else {
					msg.success(fileName + "Saldo OK");
					// Crear movimiento correspondiente
					Movimiento movimiento = new Movimiento();
					movimiento.setCuenta(cuenta);
					movimiento.setFecha(LocalDateTime.now());
					movimiento.setImporte(monto * -1);
					movimiento.setTipo(new TipoMovimiento(3, null));

					if (new MovimientoNegocioImpl().insert(movimiento)) {
						msg.success(fileName + "INSERT movimiento OK");
						// Actualizar saldo en la cuenta
						cuenta.setSaldo(saldoCuenta - monto);
						if (cn.update(cuenta)) {
							msg.success(fileName + "UPDATE cuenta OK");
							// Obtener prestamo
							PrestamoNegocio pn = new PrestamoNegocioImpl();
							Prestamo prestamo = pn.getPrestamoByID(idPrestamo);

							// Actualizar cuotas pagas
							prestamo.setCuotasRestantes(prestamo.getCuotasRestantes() - cantidadCuotas);

							if (prestamo.getCuotasRestantes() == 0) {
								// Prestamo finalizado
								prestamo.setEstado(false);
								msg.info(fileName + "Cuotas restantes 0");
							}

							if (pn.update(prestamo)) {
								msg.success(fileName + "UPDATE prestamo OK");
								msg.success(fileName + "ALL OK");
								if(prestamo.getCuotasRestantes() == 0) {
									estado.add("PAID");
								}
								else estado.add("OK");
							} else {
								msg.failed(fileName + "UPDATE prestamo FAILED");
							}
						} else {
							msg.failed(fileName + "UPDATE cuenta FAILED");
						}
					} else {
						msg.failed(fileName + "INSERT movimiento FAILED");
					}
				}
			}

			// Se parsea a un jsonArray
			Gson gson = new Gson();
			String jsonArray = gson.toJson(estado);

			// Se devuelve response del array
			PrintWriter out = res.getWriter();
			out.print(jsonArray);
			out.flush();

		} catch (Exception e) {
		}
	}

}
