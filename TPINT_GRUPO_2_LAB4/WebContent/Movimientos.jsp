<%@page import="entidad.Usuario"%>
<%@page import="entidad.Rol"%>
<%@page import="dao.UsuarioDao"%>
<%@page import="daoImpl.UsuarioDaoImpl"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<%@page import="daoImpl.TipoCuentaDaoImpl"%>
<%@page import="dao.TipoCuentaDao"%>
<%@page import="java.time.LocalDateTime"%>
<%@page import="dao.CuentaDao"%>
<%@page import="daoImpl.CuentaDaoImpl"%>
<%@page import="entidad.Cuenta"%>
<%@page import="entidad.Persona"%>
<%@page import="entidad.Direccion"%>
<%@page import="entidad.TipoCuenta"%>
<%@page import="entidad.Movimiento"%>
<%@page import="entidad.Pais"%>
<%@page import="entidad.Localidad"%>
<%@page import="java.time.LocalDate"%>
<%@page import="java.util.ArrayList"%>
<%@page import="negocio.MovimientoNegocio"%>
<%@page import="negocioImpl.MovimientoNegocioImpl"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Cuentas</title>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-OERcA2EqjJCMA+/3y+gxIOqMEjwtxJY7qPCqsdltbNJuaOe923+mo//f6V8Qbsw3" crossorigin="anonymous"></script>
<style type="text/css">
	<jsp:include page="css\bootstrap.css"></jsp:include>
	<jsp:include page="css\bootstrap.min.css"></jsp:include>
	<jsp:include page="css\global.css"></jsp:include>
</style>
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
<%if(userLogueado.getRol().getDescripcion().equals("Cliente")){ %>
<%
		ArrayList<Movimiento> movimientos = (ArrayList<Movimiento>)request.getAttribute("movimientos");%>
		
		
	<jsp:include page="./includes/Navbar.jsp"></jsp:include>
		<%if(userLogueado.getPersona().getSexo().equals("f") || userLogueado.getPersona().getSexo().equals("F")){ %>
	<p class="usuario">Bienvenida <%=userLogueado.getPersona().getNombre()+" "+userLogueado.getPersona().getApellido() %></p>
	<%}else if(userLogueado.getPersona().getSexo().equals("m") || userLogueado.getPersona().getSexo().equals("M")){ %>
	<p class="usuario">Bienvenido <%=userLogueado.getPersona().getNombre()+" "+userLogueado.getPersona().getApellido() %></p>
	<%} %>
	
	<div class="container-fluid" style="padding:3vh;">
		<div class="card mb-4">
			<div class="card-header" style="background-color: #033649;">
				<i class="fas fa-table me-1"></i>
				<h6>Movimientos</h6><!-- 
				<nav class="navbar navbar-expand bg-light">
					<div class="container-fluid">
						<div id="navbarSupportedContent">
							<ul class="navbar-nav me-auto">
								<li class="nav-item dropdown"><a
									class="nav-link dropdown-toggle" href="#" role="button"
									data-bs-toggle="dropdown" aria-expanded="false">Periodo</a>
									<ul class="dropdown-menu">
										<li><a class="dropdown-item" href="#">Hoy</a></li>
										<li><a class="dropdown-item" href="#">Ayer</a></li>
										<li><a class="dropdown-item" href="#">Ultimo mes</a></li>
										<li><a class="dropdown-item" href="#">Ultimo año</a></li>
										<li><a class="dropdown-item" href="#">Desde el principio</a></li>
									</ul>
								</li>
								<li class="nav-item dropdown"><a
									class="nav-link dropdown-toggle" href="#" role="button"
									data-bs-toggle="dropdown" aria-expanded="false">Operacion</a>
									<ul class="dropdown-menu">
										<li><a class="dropdown-item" href="#">Todos</a></li>
										<li><a class="dropdown-item" href="#">Ingreso</a></li>
										<li><a class="dropdown-item" href="#">Egreso</a></li>
									</ul>
								</li>
								<li class="nav-item dropdown"><a
									class="nav-link dropdown-toggle" href="#" role="button"
									data-bs-toggle="dropdown" aria-expanded="false">Cuenta</a>
									<ul class="dropdown-menu">
										<li><a class="dropdown-item" href="#">Todas</a></li>
										<li><a class="dropdown-item" href="#">C C. A A. <b>04123021312408124</b></a></li>
										<li><a class="dropdown-item" href="#">C. C. <b>03123021312408124</b></a></li>
									</ul>
								</li>
							</ul>
						</div>
						<form class="d-flex" role="search">
							<input class="form-control me-2" type="search"
								placeholder="Buscar" aria-label="Buscar">
							<button class="btn btn-primary" style="background-color: #036564" type="submit">Buscar</button>
						</form>
					</div>
				</nav> -->
				
				
				         <div class="row" style="width:100%;height:100%">
  
				    <div class="col">
					    <input class="form-control" type="number" placeholder="Importe Minimo: $..." value="" />
		            	            	
				    </div>
				    
				    <div class="col col-background">	
				    	<input class="form-control" type="number" placeholder="Importe Maximo: $..." value="" />	    	
				    </div>
				    
				    				    <div class="col col-background">
                    	<a class="btn btn-success" style="background-color:#036564" href="ABMMarcas?Type=a">Buscar</a>
                	</div>
				    
				    <div class="col col-background"></div>
				    
				    <div class="col col-background"></div>
				    
				    <div class="col col-background"></div>

    
  			</div>
			</div>
			<div class="card-body">
				<table id="dt-filter-select"
					class="display responsive table table-striped" style="width: 100%">
					<thead>
						<tr>
							<th>Tipo de Cuenta</th>
							<th>Numero de CBU</th>
							<!-- <th>IDMovimiento</th> -->
							<th>Tipo de Movimiento</th>
							<th>Fecha</th>
							<th>Importe</th>
						</tr>
					</thead>
					<tbody>
						<%for(Movimiento x:movimientos){ %>
						<tr>
							<td><%= x.getCuenta().getTipoCuenta().getDescripcion()%></td>
							<td><%= x.getCuenta().getCBU()%></td>
							<%-- <td><%= x.getIdMovimiento()%></td> --%>
							<td><%= x.getTipo().getDescripcion()%></td>
							<td><%= x.getFecha().getDayOfMonth()%>/<%= x.getFecha().getMonthValue()%>/<%= x.getFecha().getYear()%>  
							<%=x.getFecha().getHour() %>:<%=x.getFecha().getMinute() %></td>
							<td>$ <%= x.getImporte()%></td>
						</tr>

						<%} %>
					</tbody>
					
					<tfoot>
						<tr>
							<th>Tipo Cuenta</th>
							<th>CBU</th>
							<th>Tipo Movimiento</th>
							<th>Fecha</th>
							<th>Importe</th>
						</tr>
					</tfoot>
				</table>
				
				<a class="btn btn-primary" style="background-color: #036564"
					href="#">Atras</a>
			</div>
		</div>
	</div>
	
		<jsp:include page="./includes/Footer.jsp"></jsp:include>
		
			<script>
	$(document).ready(function () {
		  $('#dt-filter-select').dataTable({

		    initComplete: function () {
		      this.api().columns().every( function () {
		          var column = this;
		          var select = $('<select  class="browser-default custom-select form-control-sm"><option value="" selected>Buscar</option></select>')
		              .appendTo( $(column.footer()).empty() )
		              .on( 'change', function () {
		                  var val = $.fn.dataTable.util.escapeRegex(
		                      $(this).val()
		                  );

		                  column
		                      .search( val ? '^'+val+'$' : '', true, false )
		                      .draw();
		              } );

		          column.data().unique().sort().each( function ( d, j ) {
		              select.append( '<option value="'+d+'">'+d+'</option>' )
		          } );
		      } );
		  }
		  });
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