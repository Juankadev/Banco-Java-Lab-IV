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

	<%if(userLogueado.getPersona().getSexo().equals("f") || userLogueado.getPersona().getSexo().equals("F")){ %>
	<p class="usuario">Bienvenida <%=userLogueado.getPersona().getNombre()+" "+userLogueado.getPersona().getApellido() %></p>
	<%}else if(userLogueado.getPersona().getSexo().equals("m") || userLogueado.getPersona().getSexo().equals("M")){ %>
	<p class="usuario">Bienvenido <%=userLogueado.getPersona().getNombre()+" "+userLogueado.getPersona().getApellido() %></p>
	<%} %>
	<div class="container-fluid" style="padding:3vh;">
	<div class="card mb-4">
        <div class="card-header" style="background-color:#033649;">
            <i class="fas fa-table me-1"></i><h6 style="color: #fff">Listado de Prestamos</h6>
            
            
                        
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
            <div class="row">
                <div class="col-sm-12">
                    <a class="btn btn-success" style="background-color:#036564" href="ABMMarcas?Type=a">Solicitar prestamo</a>
                </div>
            </div>

            <hr />
            <table id="dt-filter-select" class="table" style="width: 100%">
                <thead>
                    <tr>
                        <th>Número de Cuenta</th>
                      <!--   <th>ID Usuario</th>   --> 
                        <th>Nombre y Apellido</th>
                        <th>Descripción Prestamo</th>
                        <th>Fecha</th>   
                        <th>Importe Solicitado</th>  
                        <th>Importe Solicitado + Interés</th>   
                        <th>Plazo de Pago (meses)</th>  
                        <th>Importe Mensual</th>  
                        <th>Cantidad de Cuotas</th>  
                        <th>&nbsp Pagar</th>
                    </tr>
                </thead>
                <tbody>
                    <%for(int i=0; i<10; i++){ %>
                    <tr>
                        <td>0123456789</td>
                        <!-- <td>55</td>    -->
                        <td>Nombre y Apellido</td>
                        <td>Descripcion Prestamo</td>
                        <td>12/10/2019</td>   
                        <td>$5000</td>  
                        <td>$5500</td>   
                        <td>3</td>  
                        <td>2600</td>  
                        <td>3</td>    
                        
                        <td>
                            <a href="#" class="btn btn-outline-success btn-sm">Pagar Cuota</a>
                        </td>                                       
                    </tr>
                    
                    <%} %>
                </tbody>
                
                					
					<tfoot>
						<tr>
                        <th>Número de Cuenta</th>
                        <!-- <th>ID Usuario</th>  -->  
                        <th>Nombre y Apellido</th>
                        <th>Descripción Prestamo</th>
                        <th>Fecha</th>   
                        <th>Importe Solicitado</th>  
                        <th>Importe Solicitado + Interés</th>   
                        <th>Plazo de Pago (meses)</th>  
                        <th>Importe Mensual</th>  
                        <th>Cantidad de Cuotas</th>  

						</tr>
					</tfoot>
					
					
            </table>
            <a class="btn btn-primary" style="background-color:#036564" href="#">Atrás</a>
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