<%@page import="entidad.Usuario"%>
<%@page import="entidad.Rol"%>
<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.text.DateFormat"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Mis Prestamos</title>
<style type="text/css">
	<jsp:include page="css\bootstrap.css"></jsp:include>
	<jsp:include page="css\global.css"></jsp:include>
</style>
<script src="js/jquery-3.6.1.js"></script>
<script type="text/javascript" src="js/PagarPrestamoAjax.js"> </script>
<script type="text/javascript" src="js/GetCuentas.js"> </script>
</head>
<body style="background: #1c232e">
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
	
	<%! Date md = new Date();%>
<%! SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); %>
	<div class="container-fluid" style="padding:3vh;">
	<div class="card mb-4">
        <div class="card-header" style="background-color:#033649;">
            <i class="fas fa-table me-1"></i><h6 style="color: #fff">Listado de Prestamos</h6>
                        
              <div class="row" style="width:100%;height:100%">
  
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
				    <div class="col col-background"></div>
				    
				    <div class="col col-background"></div>
				    
				    <div class="col col-background"></div>
  			</div>
        </div>
        <div class="card-body">
            <table id="dt-filter-select" class="table table-striped" style="width: 100%">
                <thead>
                    <tr>
                        <th>ID Prestamo</th>
                        <th>Fecha solicitud</th>
                        <th>Importe solicitado</th>
                        <th>Plazo de pago</th>   
                        <th>Cuotas restantes</th>
                        <th>Ir al pago</th>   
                    </tr>
                </thead>
                <tbody id="prestamos-body">
                </tbody>
            </table>
            <a class="btn btn-primary" style="background-color:#036564" href="#">Atrás</a>
        </div>
    </div>
	<jsp:include page="./includes/ModalPagarPrestamo.jsp"></jsp:include>
	<div class="toast-container position-fixed bottom-0 end-0 p-3">
			<div id="conflictSaldo" class="toast text-bg-danger" role="alert"
				aria-live="assertive" aria-atomic="true">
				<div class="toast-header">
					<strong class="me-auto">Banco G.R.G.M.R</strong> <small>Ahora</small>
				</div>
				<div class="toast-body">Saldo insuficiente</div>
			</div>
			<div id="badInput" class="toast text-bg-warning "
				role="alert" aria-live="assertive" aria-atomic="true">
				<div class="toast-header">
					<strong class="me-auto">Banco G.R.G.M.R</strong> <small>Ahora</small>
				</div>
				<div class="toast-body">Debe elegir una o varias cuotas a pagar</div>
			</div>
			<div id="allOk" class="toast text-bg-success "
				role="alert" aria-live="assertive" aria-atomic="true">
				<div class="toast-header">
					<strong class="me-auto">Banco G.R.G.M.R</strong> <small>Ahora</small>
				</div>
				<div class="toast-body">Se ha realizado el pago de la o las cuotas</div>
			</div>
			<div id="paid" class="toast text-bg-success "
				role="alert" aria-live="assertive" aria-atomic="true">
				<div class="toast-header">
					<strong class="me-auto">Banco G.R.G.M.R</strong> <small>Ahora</small>
				</div>
				<div class="toast-body">Se ha realizado el pago completo del prestamo</div>
			</div>
		</div>
</div>

	<jsp:include page="./includes/Footer.jsp"></jsp:include>


			<script>
	$(document).ready(function () {
	
		});
	</script>
		<%}
else{%>
	<form action="CuentasServlet" method="post">
		<h1 style="color:white;">Error, no tiene permisos para acceder a esta página</h1>
		<input type="submit" value="Login" class="btn btn-primary" name="btnLogin">
	</form>
<%} %>
</body>
</html>