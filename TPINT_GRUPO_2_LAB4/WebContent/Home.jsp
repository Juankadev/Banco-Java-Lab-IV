<%@page import="entidad.Usuario"%>
<%@page import="entidad.Rol"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<style type="text/css">
	<jsp:include page="css\bootstrap.css"></jsp:include>
	<jsp:include page="css\home.css"></jsp:include>
	<jsp:include page="css\global.css"></jsp:include>
</style>
<title>Home</title>
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
<%if(userLogueado.getRol().getDescripcion() != null){ %>
	<!-- INCLUYE NAVBAR -->
	<jsp:include page="./includes/Navbar.jsp"></jsp:include>
	
	<!-- INICIO CONTENIDO -->
		<%if(userLogueado.getPersona().getSexo().equals("f") || userLogueado.getPersona().getSexo().equals("F")){ %>
	<p class="usuario">Bienvenida <%=userLogueado.getPersona().getNombre()+" "+userLogueado.getPersona().getApellido() %></p>
	<%}else if(userLogueado.getPersona().getSexo().equals("m") || userLogueado.getPersona().getSexo().equals("M")){ %>
	<p class="usuario">Bienvenido <%=userLogueado.getPersona().getNombre()+" "+userLogueado.getPersona().getApellido() %></p>
	<%} %>
	
	<%if(userLogueado.getRol().getDescripcion().equals("Banco")){ %>
		<div class="container home-content">
		<div class="row">
			<div class="col-md-12">
				<div class="home-title text-center">
					<h1>Bienvenido al Sistema de Gestion del Banco G.R.G.M.R</h1>
				</div>
				<div class="row">
					<div class="col-md-4">
						<div class="item">
							<h4>Administra tus clientes</h4>
							<p>Aca podes gestionar el alta, ver las caracteristicas y darles la baja a los clientes que necesites</p>
							<a href="NavbarServlet?clientes=1">Ir a Clientes</a>
						</div>
					</div>
					
					<div class="col-md-4">
						<div class="item">
							<h4>Administra las cuentas de tus clientes</h4>
							<p>Gestiona las cuentas de tus clientes de forma rapida y sencilla</p>
							<a href="NavbarServlet?cuentas=1">Ir a Cuentas</a>
						</div></div>
						
					<div class="col-md-4">
						<div class="item">
							<h4>Reportes</h4>
							<p>Saca un reporte con los estados de los prestamos gestionados del banco</p>
							<a href="Reportes.jsp">Ir a Reportes</a>
						</div>
					</div>
		
				</div>
			</div>
		</div>
	</div>
	
	<%} else { %>
	<div class="container home-content">
		<div class="row">
			<div class="col-md-12">
				<div class="home-title text-center">
					<h1>Bienvenido al Sistema de Gestion del Banco G.R.G.M.R</h1>
				</div>
				<div class="row">
					<div class="col-md-4">
						<div class="item">
							<h4>Prestamos que son para vos</h4>
							<p>Un préstamo para lo que necesites, en cuotas a tasa fija y en pesos.</p>
							<a href="SolicitarPrestamo.jsp">Solicitá un Prestamo</a>
						</div>
					</div>
					
					<div class="col-md-4">
						<div class="item">
							<h4>Transferencias sin limites</h4>
							<p>Podés enviar y recibir plata desde cualquier cuenta. Gratis y en el acto.</p>
							<a href="TransferirServlet?transferir=1">Hacer una transferencia</a>
						</div></div>
						
					<div class="col-md-4">
						<div class="item">
							<h4>Gestioná tus movimientos</h4>
							<p>Consultá tus resúmenes de cuentas de manera simple y cómoda.</p>
							<a href="MovimientosServlet?movement=1">Ver Movimientos</a>
						</div>
					</div>
		
				</div>
			</div>
		</div>
	</div>
	<% } %>
	
	
	<!-- FIN CONTENIDO -->
	
	<!-- INCLUYE FOOTER -->
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