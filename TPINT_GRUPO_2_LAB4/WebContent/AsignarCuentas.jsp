<%@page import="entidad.Usuario"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<style type="text/css">
	<jsp:include page="css\bootstrap.css"></jsp:include>
	<jsp:include page="css\global.css"></jsp:include>
</style>
<title>Asignar Cuentas</title>
</head>
<%Usuario userLogueado = (Usuario)session.getAttribute("userLogueado"); %>
<body style="background: #1c232e">
<%if(userLogueado.getRol().getDescripcion().equals("Banco")){ %>
	<p class="usuario">¡Hola, Cristian Martinez!</p>
<div class="container-fluid">
	<div class="row justify-content-center">
		<div class="col-md-8 text-center mt-5 mb-3">
			<div class="card mb-4">
		        <div class="card-header" style="background-color:#033649; text-align: center">
		            <i class="fas fa-table me-1"></i><h3 style="color: #fff">Asignar Cuenta</h3>
		        </div>
		        <div class="card-body">
		        	<div class="filters-content">
		        		<form>
			        	<div class="item">
			        	<h5>Clientes</h5>
							<select class="form-select">
								<option value="-1">Seleccione una cliente</option>
							</select>
			          	</div>
			          	<div class="item">
			          		<h5>Cuentas</h5>
							<select class="form-select">
								<option value="-1">Seleccione una cuenta disponible</option>
							</select>
			          	</div>
		        		</form>
		        	</div>
		        	<div class="mt-4 botoner">
		        		<a href="#">
		        		<button class="btn btn-secondary">Atras</button>
		        		</a>
		            	<a href="#">
		            		<button class="btn btn-primary" style="background-color:#036564" >Ejecutar</button>
		            	</a>
		        	</div>
		        </div>
		    </div>
		</div>
	</div>
</div>
<%}
else{%>
	<h1>Error, no tiene permisos para acceder a esta página</h1>
	<button onclick="Index.jsp">Llévame a otro lado</button>
<%} %>
</body>
</html>