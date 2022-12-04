<%@page import="entidad.Usuario"%>
<%@page import="entidad.Rol"%>
<%@page import="org.apache.catalina.LifecycleState"%>
<%@page import="org.apache.jasper.tagplugins.jstl.core.ForEach"%>
<%@page import="java.util.ArrayList"%>
<%@page import="entidad.Finance"%>
<%@page import="entidad.DetallesPrestamo"%>
<%@page import="java.math.BigDecimal"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Solicitar prestamo</title>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-OERcA2EqjJCMA+/3y+gxIOqMEjwtxJY7qPCqsdltbNJuaOe923+mo//f6V8Qbsw3" crossorigin="anonymous"></script>
<script src="js/jquery-3.6.1.js"></script>
<script type="text/javascript" src="js/CalcularPrestamo.js"> </script>
<script type="text/javascript" src="js/GetCuentas.js"> </script>
<script type="text/javascript" src="js/AgregarSolicitudAjax.js"> </script>
<style type="text/css">
	<jsp:include page="css\bootstrap.css"></jsp:include>
	<jsp:include page="css\bootstrap.min.css"></jsp:include>
	<jsp:include page="css\global.css"></jsp:include>
</style>
</head>
<body  style="background: #1c232e">
<%Usuario userLogueado = new Usuario();
	if(session.getAttribute("userLogueado") != null) {
		userLogueado = (Usuario)session.getAttribute("userLogueado");
	}else{
		Rol rol = new Rol();
		rol.setDescripcion("");
		userLogueado.setRol(rol);
		
	}%>
<%if(userLogueado.getRol().getDescripcion().equals("Cliente")){ %>
	<jsp:include page="./includes/Navbar.jsp"></jsp:include>
		<%if(userLogueado.getPersona().getSexo().equals("f") || userLogueado.getPersona().getSexo().equals("F")){ %>
	<p class="usuario">Bienvenida <%=userLogueado.getPersona().getNombre()+" "+userLogueado.getPersona().getApellido() %></p>
	<%}else if(userLogueado.getPersona().getSexo().equals("m") || userLogueado.getPersona().getSexo().equals("M")){ %>
	<p class="usuario">Bienvenido <%=userLogueado.getPersona().getNombre()+" "+userLogueado.getPersona().getApellido() %></p>
	<%} %>
	<div class="toast-container position-fixed bottom-0 end-0 p-3">
		<div id="allOk" class="toast text-bg-success " role="alert" aria-live="assertive"
			aria-atomic="true">
			<div class="toast-header">
				<strong class="me-auto">Banco G.R.G.M.R</strong> <small>Ahora</small>
				<button type="button" class="btn-close" data-bs-dismiss="toast"
					aria-label="Close"></button>
			</div>
			<div class="toast-body">Se ha enviado la solicitud de prestamo
				con exito</div>
		</div>
		<div id="badInput" class="toast text-bg-warning" role="alert"
			aria-live="assertive" aria-atomic="true">
			<div class="toast-header">
				<strong class="me-auto">Banco G.R.G.M.R</strong> <small>Ahora</small>
			</div>
			<div class="toast-body">Ingrese un valor mayor que $10000</div>
		</div>
		<div id="badInputFormat" class="toast text-bg-warning" role="alert"
			aria-live="assertive" aria-atomic="true">
			<div class="toast-header">
				<strong class="me-auto">Banco G.R.G.M.R</strong> <small>Ahora</small>
			</div>
			<div class="toast-body">Ingrese un valor valido</div>
		</div>
	</div>
	<div class="container-fluid">
		<div class="row justify-content-center">
			<div class="col-md-10 mt-5 mb-3">
				<div class="card mb-4">
					<div class="card-header" style="background-color: #033649;">
						<h3 style="color: #fff">Solicitar prestamo</h3>
					</div>
					<div class="card-body">
						<div class="filters-content d-flex">
								<div class="form-floating mb-3">
									<input id="floatingInput" class="form-control w-auto"
										name="floatingInput" placeholder="Monto del prestamo"
										value=100000> <label for="floatingInput">Monto
										del prestamo</label>
								</div>
								<div class="floating-label border-form ms-a">
									<select id="loan-term-select" name="loan-term-select"
										onclick="this.setAttribute('value', this.value);"
										class="floating-select">
										<option value="12" selected>12 meses</option>
										<option value="18">18 meses</option>
										<option value="24">24 meses</option>
										<option value="36">36 meses</option>
										<option value="48">48 meses</option>
										<option value="60">60 meses</option>
										<option value="72">72 meses</option>
										<option value="84">84 meses</option>
										<option value="96">96 meses</option>
									</select> <label class="floating-label-style">Plazo</label>
								</div>
								<div class="floating-label border-form ms-a">
									<select id="loan-account-select" name="loan-term-select"
										onclick="this.setAttribute('value', this.value);"
										class="floating-select pe-5">
									</select> <label class="floating-label-style">Depositar en</label>
								</div>
						</div>
						<div>
							<div class="loan-wrapper">
								<p>Caracteristicas de la operacion</p>
							</div>
							<hr>
							<div class="loan-wrapper">
								<small class="fw-light">Cuota pura</small>
								<div class="value-wrapper">
									<span>$</span><span id="pure-installment"></span>
								</div>
							</div>
							<hr>
							<div class="loan-row d-flex justify-content-between">
								<div>
									<!-- TODO: Obtener TNA desde metodo JAVA -->
									<small class="fw-light">Tasa Nominal Anual</small>
									<%
									BigDecimal days = BigDecimal.valueOf(12);
									BigDecimal tna = BigDecimal.valueOf(91.68);
									%>
									<div class="value-wrapper">
										<span id="tna"><%=tna%></span><span>%</span>
									</div>
								</div>
								<div>
									<small class="fw-light">Tasa Efectiva Anual</small>
									<div class="value-wrapper">
										<span id="tea"></span><span>%</span>
									</div>
								</div>
								<div>
									<small class="fw-light">Tasa Efectiva Mensual</small>
									<div class="value-wrapper">
										<span id="tem"></span><span>%</span>
									</div>
								</div>
							</div>
							<hr>
							<div class="loan-row">
								<small class="fw-light">Costo Financiero Total sin IVA</small>
								<div class="value-wrapper">
									<span id="cftsiva" class="display-1 text-black-50"></span><span
										class="display-1 text-black-50">%</span>
								</div>
							</div>
							<hr>
							<div class="loan-row">
								<small class="fw-light">Costo Financiero Total con IVA</small>
								<div class="value-wrapper">
									<span id="cftciva" class="display-1 text-black-50"></span><span
										class="display-1 text-black-50">%</span>
								</div>
							</div>
							<div class="accordion" id="accordionPrestamos">
								<div class="accordion-item">
									<h2 class="accordion-header" id="heading">
										<button class="accordion-button collapsed" type="button"
											data-bs-toggle="collapse" data-bs-target="#collapse"
											aria-expanded="false" aria-controls="collapse">
											Detalles del prestamo</button>
									</h2>
									<div id="collapse" class="accordion-collapse collapse"
										aria-labelledby="heading" data-bs-parent="#accordionPrestamos">
										<div class="accordion-body">
										<small>
											<table id="tabla-prestamos" class="table table-stripped text-center">
												<thead>
												<tr>
													<th>Periodo</th>
													<th>Cuota</th>
													<th>Interes</th>
													<th>Amortizacion</th>
													<th>Saldo</th>
													<th>IVA</th>
													<th><p class="mb-0">Cuota</p> a pagar</th>
													<th><p class="mb-0">Fecha</p>vencimiento</th>
												</tr>
												</thead>
												<tbody id="prestamos-body"></tbody>
											</table>
										</small>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
					<div class="mt-4 botoner text-center">
						<a href="Home.jsp" type="button" class="btn btn-secondary">Atras</a>
						<button type="button" class="btn btn-primary"
							data-bs-toggle="modal" data-bs-target="#transferModal">
							Continuar</button>
						<div class="modal fade" id="transferModal" tabindex="-1"
							aria-labelledby="transferModalLabel" aria-hidden="true">
							<div class="modal-dialog modal-dialog-centered">
								<div class="modal-content">
									<div class="modal-header">
										<h1 class="modal-title fs-5" id="transferModalLabel">
											Solicitar prestamo</h1>
										<button type="button" class="btn-close"
											data-bs-dismiss="modal" aria-label="Close"></button>
									</div>
									<div class="modal-body">
										<div class="after-money-wrapper mb-3">
											<small><b>Solicitaras un prestamo por monto de $<span id="monto"></span></b></small>
										</div>
										<p class="text-start">Recorda que debes mantener un buen
											historial crediticio y uso de tu linea de credito para
											acceder a un prestamo personal. En caso que aprobemos tu
											solicitud, recibiras el dinero en tu cuenta.</p>
									</div>
									<div class="modal-footer">
										<button type="button" class="btn btn-secondary"
											data-bs-dismiss="modal">Atras</button>
										<button id="enviarSolicitud"
											class="btn btn-primary"
											data-bs-dismiss="modal">Solicitar</button>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	
	
	
		            <%if(request.getAttribute("enviado") != null){
                			String msg=(String)request.getAttribute("enviado");
                	%>
                		<p class="successMsg"><%=msg %></p>
                	<% }%>
	
	
	<jsp:include page="./includes/Footer.jsp"></jsp:include>
	
		<%}
else{%>
	<form action="CuentasServlet" method="post">
		<h1 style="color:white;">Error, no tiene permisos para acceder a esta página</h1>
		<input type="submit" value="Login" class="btn btn-primary" name="btnLogin">
	</form>
<%} %>
</body>
</html>