<%@page import="entidad.Usuario"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">


<script type="text/javascript" src="https://code.jquery.com/jquery-3.5.1.js"></script>
<link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/v/bs5/dt-1.12.1/sl-1.4.0/datatables.min.css"/>
<script type="text/javascript" src="https://cdn.datatables.net/v/bs5/dt-1.12.1/sl-1.4.0/datatables.min.js"></script>


</head>
<body>
<%Usuario userLogueado = (Usuario)session.getAttribute("userLogueado"); %>
<nav class="navbar navbar-expand-lg bg-light">
  <div class="container-fluid">
    <a class="navbar-brand" href="#">Banco G.R.G.M.R</a>
    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
      <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse " id="navbarNav">
    

      <ul class="navbar-nav ms-auto mb-2 mb-lg-0">
      
      
          <!-- MENU PARA EL CLIENTE 
          if (usuario.tostring == "cliente") .....  -->
        <li class="nav-item">
          <a class="nav-link active" aria-current="page" href="Home.jsp">Inicio</a>
        </li>
        <%if(userLogueado.getRol().getDescripcion().equals("Banco")){ %>
        <li class="nav-item">
          <a class="nav-link" href="NavbarServlet?cuentas=2">Cuentas</a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="NavbarServlet?clientes=1">Clientes</a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="Reportes.jsp">Reportes</a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="SolicitudesPrestamos.jsp">Prestamos</a>
        </li>
        <%} %>
        <%if(userLogueado.getRol().getDescripcion().equals("Cliente")){ %>
        <li class="nav-item">
          <a class="nav-link" href="TransferirServlet?transferir=1">Transferir</a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="MovimientosServlet?movement=1">Movimientos</a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="SolicitarPrestamo.jsp">Solicitar Prestamo</a>
        </li>
         <li class="nav-item">
          <a class="nav-link" href="PagarPrestamos.jsp">Pagar Prestamo</a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="DatosUserServlet?datos=1">Mis Datos</a>
        </li>
        <%} %>
        
        
        <!--   MENU PARA EL ADMIN  -->
        <!--  
        <li class="nav-item">
          <a class="nav-link active" aria-current="page" href="Home.jsp">Inicio</a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="Clientes.jsp">Clientes</a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="Cuentas.jsp">Cuentas</a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="AsignarCuentas.jsp">Asignar Cuentas</a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="SolicitudesPrestamos.jsp">Solicitudes de Prestamo</a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="Reportes.jsp">Reportes</a>
        </li>
        -->
        
      </ul>
    </div>
  </div>
  <div class="row">
  
  <form action="NavbarServlet" method="post">
  	<div class="col">
	  <%=userLogueado.getPersona().getNombre() %>  
  	</div>
  	<div class="col">
  		<input type="submit" class="form-control" name="logOut" value="Cerrar Sesión">
  	</div>
  		</form>
  	
  </div>
</nav>

</body>
</html>