<%@page import="entidad.Usuario"%>
<%@page import="entidad.Rol"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Solicitudes de Préstamos</title>
<style type="text/css">
	<jsp:include page="css\bootstrap.css"></jsp:include>
		<jsp:include page="css\global.css"></jsp:include>
</style>
<script src="js/jquery-3.6.1.js"></script>
<script type="text/javascript" src="js/SolicitudesPrestamosAjax.js"> </script>
</head>
<body>
<%Usuario userLogueado = new Usuario();
	if(session.getAttribute("userLogueado") != null) {
		userLogueado = (Usuario)session.getAttribute("userLogueado");
	}else{
		Rol rol = new Rol();
		rol.setDescripcion("");
		userLogueado.setRol(rol);
		
	}%>
<%if(userLogueado.getRol().getDescripcion().equals("Banco")){ %>
	<jsp:include page="./includes/Navbar.jsp"></jsp:include>
	<%if(userLogueado.getPersona().getSexo().equals("f") || userLogueado.getPersona().getSexo().equals("F")){ %>
	<p class="usuario">Bienvenida <%=userLogueado.getPersona().getNombre()+" "+userLogueado.getPersona().getApellido() %></p>
	<%}else if(userLogueado.getPersona().getSexo().equals("m") || userLogueado.getPersona().getSexo().equals("M")){ %>
	<p class="usuario">Bienvenido <%=userLogueado.getPersona().getNombre()+" "+userLogueado.getPersona().getApellido() %></p>
	<%} %>
	<div class="container-fluid" style="padding: 3vh;">
		<div class="card mb-4">
			<div class="card-header" style="background-color: #033649;">
				<i class="fas fa-table me-1"></i>
				<h6>Solicitudes de préstamos pendientes</h6>
				<div class="row" style="width: 100%; height: 100%">
					<div class="col">
						<div class="form-floating mb-3">
							<input id="min" name="min" class="form-control"
								placeholder="Importe Minimo"> 
							<label for="min">Importe Minimo</label>
						</div>
					</div>
					<div class="col">
						<div class="form-floating mb-3">
							<input id="max" name="max" class="form-control"
								placeholder="Importe Maximo"> 
							<label for="max">Importe Maximo</label>
						</div>
					</div>
					<div class="col col-background">
					</div>
					<div class="col col-background"></div>
					<div class="col col-background"></div>
					<div class="col col-background"></div>
				</div>
			</div>
			<div class="card-body">
				<div class="detalles-wrapper">
					<jsp:include page="./includes/DetallesBoton.jsp"></jsp:include>
				</div>
				<jsp:include page="./includes/Detalles.jsp"></jsp:include>
				<table id="dt-filter-select" class="table table-striped"
					style="width: 100%">
					<thead>
						<tr>
							<th>Número de Solicitud</th>
							<th>ID Usuario</th>
							<th>Nombre y Apellido</th>
							<th>Importe Solicitado</th>
							<th>Plazo de Pago (Meses)</th>
							<th>Detalles</th>
							<th>Aprobación</th>
						</tr>
					</thead>
					<tbody id="prestamos-body"></tbody>
				</table>
				<a class="btn btn-primary" style="background-color: #036564"
					href="Home.jsp">Atrás</a>
			</div>
		</div>
		<div class="toast-container position-fixed bottom-0 end-0 p-3">
			<div id="liveToastDesaprobado" class="toast text-bg-success "
				role="alert" aria-live="assertive" aria-atomic="true">
				<div class="toast-header">
					<strong class="me-auto">Banco G.R.G.M.R</strong> <small>Ahora</small>
					<button type="button" class="btn-close" data-bs-dismiss="toast"
						aria-label="Close"></button>
				</div>
				<div class="toast-body">Se ha desaprobado la solicitud de
					prestamo con exito</div>
			</div>
			<div id="liveToastAprobado" class="toast text-bg-success "
				role="alert" aria-live="assertive" aria-atomic="true">
				<div class="toast-header">
					<strong class="me-auto">Banco G.R.G.M.R</strong> <small>Ahora</small>
					<button type="button" class="btn-close" data-bs-dismiss="toast"
						aria-label="Close"></button>
				</div>
				<div class="toast-body">Se ha aprobado la solicitud de
					prestamo con exito</div>
			</div>
		</div>
		<jsp:include page="./includes/Footer.jsp"></jsp:include>
		<%}
else{%>
		<form action="CuentasServlet" method="post">
			<h1 style="color: white;">Error, no tiene permisos para acceder
				a esta página</h1>
			<input type="submit" value="Login" class="btn btn-primary"
				name="btnLogin">
		</form>
		<%} %>
	</div>
</body>
</html>