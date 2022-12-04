<%@page import="entidad.Usuario"%>
<%@page import="entidad.Rol"%>
<%@page import="entidad.Cuenta"%>
<%@page import="java.util.ArrayList"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Transferir</title>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-OERcA2EqjJCMA+/3y+gxIOqMEjwtxJY7qPCqsdltbNJuaOe923+mo//f6V8Qbsw3" crossorigin="anonymous"></script>
<style type="text/css">
	<jsp:include page="css\bootstrap.css"></jsp:include>
	<jsp:include page="css\bootstrap.min.css"></jsp:include>
	<jsp:include page="css\global.css"></jsp:include>
</style>
</head>
<body  style="background: #1c232e">
<%Usuario userLogueado = new Usuario();

			ArrayList<Cuenta> cuentasUserLogueado = (ArrayList<Cuenta>)request.getAttribute("comboCuentas");

	if(session.getAttribute("userLogueado") != null) {
		userLogueado = (Usuario)session.getAttribute("userLogueado");
		if(request.getAttribute("comboCuentas") != null){
		}
	}else{
		Rol rol = new Rol();
		rol.setDescripcion("");
		userLogueado.setRol(rol);
		
	}%>
	
	
	
<%if(userLogueado.getRol().getDescripcion().equals("Cliente")){ %>
	<jsp:include page="./includes/Navbar.jsp"></jsp:include>
	
	
	<div class="container-fluid">
		<div class="row justify-content-center">
			<div class="col-md-8 text-center mt-5 mb-3">
				<div class="card mb-4">
					<div class="card-header"
						style="background-color: #033649;">
						<h3 style="color: #fff">Transferir</h3>
					</div>
					<div class="card-body">
					

						<div class="filters-content">
							<div class="current-money-wrapper mb-3">
								<!-- <small>Tu saldo en la cuenta seleccionada es: $120000</small> --> 
							</div>
							<p class="mb-0 text-start"><small>Desde:</small></p>
							<div class="form-check text-start">
												<select class="form-select" name="cuenta" id="cuentaselect">
											<%for(Cuenta item : cuentasUserLogueado){ %>
													<option value="<%=item.getNumeroCuenta()%>">CBU - <%=item.getCBU().toString()%></option>	
												<%} %>
												</select>
							</div>

							<p class="mb-0 text-start"><small>Hasta:</small></p>
							<div class="form-floating mb-3">
								<input class="form-control" id="cbu-transferir"
									placeholder="Numero de cuenta a transferir"> <label
									for="floatingInputNumber">Numero de cuenta a transferir</label>
							</div>
						</div>
						<!-- <div class="mt-4 botoner"> -->
							<a href="Home.jsp">
								<button class="btn btn-secondary">Atras</button>
							</a>
								
								
								
							<div class="modal fade" id="transferModal" tabindex="-1"
								aria-labelledby="transferModalLabel" aria-hidden="true">
								<div class="modal-dialog modal-dialog-centered">
									<div class="modal-content">
										<div class="modal-header" style="background-color:#036564">
											<h1 class="modal-title fs-5" id="transferModalLabel" style="color: #fff;">
											Transferis a <%-- <% userDestino.getNombre() + " " + userDestino.getApellido(); %> --%></h1>
											<button type="button" class="btn-close"
												data-bs-dismiss="modal" aria-label="Close"></button>
										</div>
										<form method="post" action="TransferirServlet" name="modaltransferir">
										<div class="modal-body">
											<div class="after-money-wrapper mb-3">
											<input id="origenCBU" type="hidden" name="CuentaOrigen">
												<label class="form-label">Nombre y Apellido: </label>
												<p id="transfer-name"></p>
											</div>
											<div class="dni-wrapper text-start">
												<label class="form-label">DNI: </label>
												<p id="transfer-dni"></p>
											</div>
											<div class="account-wrapper text-start">
												<label class="form-label">CBU: </label>
												<input type="hidden" readonly class="form-control" id="transfer-cbu" name="destino" value="">
												
												<p id="show-cbu-transfer"></p>
											</div>
											<div class="account-wrapper text-start">
												<label class="form-label">Transfieres: </label>
												<input type="number" required class="form-control" id="transfer-money" name="montoEnviar" value="">
											</div>
										</div>
										<div class="modal-footer">
											<button type="button" class="btn btn-secondary"
												data-bs-dismiss="modal">Atras</button>
											<input type="submit" name="btntransferir" value="Transferir" class="btn btn-primary"></input>
										</div>
										</form>
									</div>
								</div>
							</div>
						
						
							
						</div>
					<!-- </div> -->
				</div>
			</div>
		</div>
	</div>
	
	                	  	<%if(request.getAttribute("enviado") != null){
                			String msg=(String)request.getAttribute("enviado");
                	%>
                		<p class="successMsg"><%=msg %></p>
                	<% }%>
                	
                	
                	<%if(request.getAttribute("noexiste") != null){
                			String msg=(String)request.getAttribute("noexiste");
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

<script>
	
	$('#cbu-transferir').blur(function(e){
	 	let cbu = $(this).val();
	 	let origenCBU = $("#cuentaselect option").filter(":selected").val();
	 	console.log(origenCBU);
	 	
	 	$.ajax({
	 		url:"TransferirServlet",
	 		type: "Post",
	 		data:{
	 			transfer: cbu,
	 			origen: origenCBU
	 		},
	 		success: function(res){
	 			let datos = $.parseJSON(res);
	 			if(datos.NumeroCuenta != 0){
	 			$('#transfer-name').text(datos.Usuario.Persona.Nombre + ", "+ datos.Usuario.Persona.Apellido);
	 			$('#transfer-dni').text(datos.Usuario.Persona.DNI);
	 			$('#transfer-cbu').val(datos.CBU);
	 			$('#show-cbu-transfer').text(datos.CBU);
	 			$('#origenCBU').val(origenCBU);
	 			
	 			$('#transferModal').modal('show');
	 			}
	 			else{
	 				alert('La cuenta ingresada no existe');
	 			}
	 		},
	 		error: function(jqXHR, textStatus, errorThrown){
	 			alert('El CBU INGRESADO NO EXISTE');
	 		}
	 	});
	 });
	
</script>

</body>
</html>