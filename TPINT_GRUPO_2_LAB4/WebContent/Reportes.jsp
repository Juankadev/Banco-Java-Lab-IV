<%@page import="entidad.Usuario"%>
<%@page import="entidad.Rol"%>
<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.text.DateFormat"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
 
 
<% 
	int porRechazados = 0;
	int porAprobados = 0;
	int porSolicitados = 0;
	
	if(request.getAttribute("aprobados") != null && request.getAttribute("rechazados") != null && request.getAttribute("solicitados") != null){
		porAprobados = (Integer)request.getAttribute("aprobados");
		porRechazados = (Integer)request.getAttribute("rechazados");
		porSolicitados = (Integer)request.getAttribute("solicitados");
	}
%>    
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Reportes</title>
<style type="text/css">
	<jsp:include page="css\bootstrap.css"></jsp:include>
	<jsp:include page="css\reportes.css"></jsp:include>
	<jsp:include page="css\global.css"></jsp:include>
</style>

<script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
    <script type="text/javascript">

      // Load the Visualization API and the corechart package.
      google.charts.load('current', {'packages':['corechart']});

      // Set a callback to run when the Google Visualization API is loaded.
      google.charts.setOnLoadCallback(drawChart);

      // Callback that creates and populates a data table,
      // instantiates the pie chart, passes in the data and
      // draws it.
      function drawChart() {

        // Create the data table.
        var data = new google.visualization.DataTable();
        data.addColumn('string', 'Topping');
        data.addColumn('number', 'Slices');
        data.addRows([
          ['Aprobados', <%=porAprobados %>],
          ['Rechazados', <%=porRechazados %>],
          ['Solicitados', <%=porSolicitados %>],
        ]);

        // Set chart options
        var options = {'title':'Porcentaje de estado de Prestamos',
                       'width':900,
                       'height':500,
                       'is3D': true
                       };

        // Instantiate and draw our chart, passing in some options.
        var chart = new google.visualization.PieChart(document.getElementById('chart_div'));
        chart.draw(data, options);
      }
    </script>



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
<%if(userLogueado.getRol().getDescripcion().equals("Banco")){ %>
	<jsp:include page="./includes/Navbar.jsp"></jsp:include>
	
	
	<%if(userLogueado.getPersona().getSexo().equals("f") || userLogueado.getPersona().getSexo().equals("F")){ %>
	<p class="usuario">Bienvenida <%=userLogueado.getPersona().getNombre()+" "+userLogueado.getPersona().getApellido() %></p>
	<%}else if(userLogueado.getPersona().getSexo().equals("m") || userLogueado.getPersona().getSexo().equals("M")){ %>
	<p class="usuario">Bienvenido <%=userLogueado.getPersona().getNombre()+" "+userLogueado.getPersona().getApellido() %></p>
	<%} %>
<%! Date md = new Date();%>
<%! SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); %>
<%! int total = 0; %>
<%! Double promedio = 0.0; %>
	<div class="container-fluid">
	<div class="row justify-content-center">
		<div class="col-md-8 text-center mt-5 mb-3">
			<div class="card mb-4">
		        <div class="card-header" style="background-color:#033649; text-align: center">
		            <i class="fas fa-table me-1"></i><h3 style="color: #fff">Reportes</h3>
		        </div>
		        <div class="card-body">
		        	<div class="filters-content">
		        		<form method="post" action="ReportesServlet" onsubmit="return validate()">
			        	<div class="item">
			          		<h5>Fecha de inicio</h5>
			          		<input class="form-control" id="fechainic" name="desde" type="date" max=<%= sdf.format(md) %> />
			          	</div>
			          	<div class="item">
			          		<h5>Fecha de Fin</h5>
			          		<input class="form-control" name="hasta"  id="fechadesde" type="date" max=<%= sdf.format(md) %> />
			          	</div>
			          	<div class="mt-4 botoner">
		            		<a href="#">
		            			<button class="sub" type="submit" >Generar Reporte</button>
		            		</a>
		        		</div>
		        	</form>
		        	</div>
		        	<div class="row mt-5">
		        		<div class="col-md-6 item">
		        			<h5 class="item__header">Total de Prestamos registrados</h5>
		        			<hr class="line-bottom"/>
		        			<%if(request.getAttribute("total") != null){
		        				  total = (Integer)request.getAttribute("total");
		        			%>
		        				<p><%=total %></p>
		        			<% }  else { %>
		        				<p>0</p>
		        			<%} %>
		        		</div>
		        		<div class="col-md-6 item">
		        			<h5 class="item__header">Promedio de Importe Solicitado en Prestamos</h5>
		        			<hr class="line-bottom"/>
		        			<%if(request.getAttribute("promedio") != null){
		        				promedio = (Double)request.getAttribute("promedio");
		        			%>
		        				<p>$<%=promedio %></p>
		        			<% }  else { %>
		        				<p>$0.0</p>
		        			<%} %>
		        		</div>
		        		<div class="col-md-12  item">
		        			<div id="chart_div"></div>
		        		</div>

<!-- 		        		<div class="col-md-6 item">
		        			<h5 class="item__header">Promedio Monto Solicitado en Prestamos</h5>
		        			<hr class="line-bottom"/>
		        			<p>0</p>
		        		</div> -->
		        		<div></div>
		        	
		        	</div>

		    </div>
		</div>
	</div>
</div>


	<jsp:include page="./includes/Footer.jsp"></jsp:include>
		<%}
else{%>
	<form action="CuentasServlet" method="post">
		<h1 style="color:white;">Error, no tiene permisos para acceder a esta página</h1>
		<input type="submit" value="Login" class="btn btn-primary" name="btnLogin">
	</form>
<%} %>
	
<script>
	
	function validate(){
		if($('#fechainic').val() == ""){
			alert('Debe seleccionar una fecha de inicio');
			return false;
		}
		if($('#fechadesde').val() == ""){
			alert('Debe seleccionar una fecha de fin');
			return false
		}
		
	}
	
	
</script>	
	
</body>
</html>