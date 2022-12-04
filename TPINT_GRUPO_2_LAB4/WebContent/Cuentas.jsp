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
<%@page import="entidad.Pais"%>
<%@page import="entidad.Localidad"%>
<%@page import="java.time.LocalDate"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.time.format.DateTimeFormatter"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Cuentas</title>
<style type="text/css">
	<jsp:include page="css\bootstrap.css"></jsp:include>
	<jsp:include page="css\bootstrap.min.css"></jsp:include>
	<jsp:include page="css\global.css"></jsp:include>
	<jsp:include page="css\cuentas.css"></jsp:include>
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
<%if(userLogueado.getRol().getDescripcion().equals("Banco")){ %>
	<jsp:include page="./includes/Navbar.jsp"></jsp:include>
	
	<%
	ArrayList<Cuenta> listado = (ArrayList<Cuenta>)request.getAttribute("listadocuentas");
	int i=0;
	ArrayList<TipoCuenta> comboTipoCuentas = (ArrayList<TipoCuenta>)request.getAttribute("comboTipoCuentas");
	ArrayList<Usuario> comboClientes = (ArrayList<Usuario>)request.getAttribute("comboClientes");%>
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
            <i class="fas fa-table me-1"></i><h6>Listado de cuentas</h6>
            
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
                <div class="col-md-3">
                    <button class="btn btn-success" data-bs-toggle="modal" data-bs-target="#newClient" style="background-color:#036564">Crear cuenta</button>
                </div>
                <div class="col-md-9 text-center">
                	<%
                	String msg = "";
                	if(request.getAttribute("creado") != null){
                		msg=(String)request.getAttribute("creado");
                	%>
                		<p class="msgServlet"><%=msg %></p>
                	<%} %>
                	
                	<%if(request.getAttribute("msgDeleted") != null){
                		msg=(String)request.getAttribute("msgDeleted");
                	%>
                		<p class="msgServlet"><%=msg %></p>
                	<% }%>
                	
                	<%if(request.getAttribute("edit") != null){
                		msg=(String)request.getAttribute("edit");
                	%>
                		<p class="msgServlet"><%=msg %></p>
                	<% }%>
                	
                </div>
            </div>

            <hr />
            <table id="dt-filter-select" class="display responsive table table-striped" style="width: 100%">
                <thead>
                    <tr>
                        <th>Número de cuenta</th>
                        <th>ID Usuario</th>   
                        <th>Nombre y Apellido</th>
                        <th>Tipo de Cuenta</th>   
                        <th>Saldo</th> 
                        <th>Estado</th>  
                        <%--<th>Stock</th>--%>
                        <th>&nbsp Acciones</th>

                    </tr>
                </thead>
                <tbody>
                
                
                    <%for(Cuenta item: listado){ %>
                    <tr>
                        <td><%=item.getNumeroCuenta() %></td>
                        <td><%=item.getUsuario().getPersona().getDNI()%></td>
                        <td><%=item.getUsuario().getPersona().getApellido()+", "+item.getUsuario().getPersona().getNombre()%></td>   
                        <td><%=item.getTipoCuenta().getDescripcion() %></td>   
                        <td><%=item.getSaldo() %></td>
    					<%if(item.isEstado()){ %>
							<td>Activo</td>
						<% }
						else{%>
							<td>Inactivo</td>
						<%} %>           
							<td><a href="#" class="btn" data-bs-toggle="modal"
								data-bs-target="#detallesCuenta<%=i%>" onClick="mostrarCuentasAsociadas(<%=item.getUsuario().getPersona().getDNI()%>, <%=i %>)"> <svg
										xmlns="http://www.w3.org/2000/svg" width="16" height="16"
										fill="currentColor" class="bi bi-box-arrow-up-right"
										viewBox="0 0 16 16"> <path fill-rule="evenodd"
										d="M8.636 3.5a.5.5 0 0 0-.5-.5H1.5A1.5 1.5 0 0 0 0 4.5v10A1.5 1.5 0 0 0 1.5 16h10a1.5 1.5 0 0 0 1.5-1.5V7.864a.5.5 0 0 0-1 0V14.5a.5.5 0 0 1-.5.5h-10a.5.5 0 0 1-.5-.5v-10a.5.5 0 0 1 .5-.5h6.636a.5.5 0 0 0 .5-.5z" />
									<path fill-rule="evenodd"
										d="M16 .5a.5.5 0 0 0-.5-.5h-5a.5.5 0 0 0 0 1h3.793L6.146 9.146a.5.5 0 1 0 .708.708L15 1.707V5.5a.5.5 0 0 0 1 0v-5z" />
									</svg></a>

								<div class="modal fade" id="detallesCuenta<%=i%>"
									data-bs-backdrop="static" data-bs-keyboard="false"
									tabindex="-1" aria-labelledby="staticBackdropLabel"
									aria-hidden="true">
									<div class="modal-dialog modal-xl">
										<div class="modal-content">
											<div class="card-header modal-header"
												style="background-color: #033649;">
												<h1 class="modal-title fs-5" id="staticBackdropLabel<%=i%>"
													style="color: white;">Detalles</h1>
												<button type="button" class="btn-close"
													data-bs-dismiss="modal" aria-label="Close"></button>
											</div>
											<div class="modal-body">

												<div class="container-fluid" style="padding: 1vh;">
													<div class="card mb-4 overflow-auto" style="height:60vh;">

														<div class="accordion"
															id="accordionPanelsStayOpenExample<%=i%>">
															<div class="accordion-item">
																<h2 class="accordion-header"
																	id="panelsStayOpen-headingOne<%=i%>">
																	<button class="accordion-button" type="button"
																		data-bs-toggle="collapse"
																		data-bs-target="#panelsStayOpen-collapseOne<%=i%>"
																		aria-expanded="true"
																		aria-controls="panelsStayOpen-collapseOne<%=i%>">
																		<p style="color: #033649;">Cliente</p>
																	</button>
																</h2>
																<div id="panelsStayOpen-collapseOne<%=i%>"
																	class="accordion-collapse collapse show"
																	aria-labelledby="panelsStayOpen-headingOne<%=i%>">
																	<div class="accordion-body">

																		<div class="card-body">
																			<table id="tablaCuentas"
																				class="display responsive table table-striped"
																				style="width: 100%">
																				<thead>
																					<tr>
																						<th>DNI</th>
																						<th>CUIL</th>
																						<th>Nombre y Apellido</th>
																						<th>Fecha Nacimiento</th>
																						<th>Usuario</th>
																						<th>Correo Electrónico</th>
																						<th>Dirección</th>
																						<th>Localidad</th>
																						<th>País</th>
																					</tr>
																				</thead>
																				<tbody>
																					<tr>
																						<td><%=item.getUsuario().getPersona().getDNI() %></td>
																						<td><%=item.getCBU() %></td>
																						<td><%=item.getUsuario().getPersona().getApellido()+", "+item.getUsuario().getPersona().getNombre() %></td>
																						<td><%=item.getUsuario().getPersona().getFechaNac() %></td>
																						<td><%=item.getUsuario().getUser() %></td>
																						<td><%=item.getUsuario().getPersona().getCorreo() %></td>
																						<td><%=item.getUsuario().getPersona().getDireccion().getDescripicion() %></td>
																						<td><%=item.getUsuario().getPersona().getDireccion().getLocalidad().getDescripcion() %></td>
																						<td><%=item.getUsuario().getPersona().getPais().getDescripcion() %></td>
																					</tr>

																				</tbody>
																			</table>
																		</div>

																	</div>
																</div>
															</div>
															<div class="accordion-item">
																<h2 class="accordion-header"
																	id="panelsStayOpen-headingTwo<%=i%>">
																	<button class="accordion-button collapsed"
																		type="button" data-bs-toggle="collapse"
																		data-bs-target="#panelsStayOpen-collapseTwo<%=i%>"
																		aria-expanded="false"
																		aria-controls="panelsStayOpen-collapseTwo<%=i%>">
																		<p style="color: #033649;">Cuentas Asociadas</p>
																	</button>
																</h2>
																<div id="panelsStayOpen-collapseTwo<%=i%>"
																	class="accordion-collapse collapse"
																	aria-labelledby="panelsStayOpen-headingTwo<%=i%>">
																	<div class="accordion-body">

																		<div class="card-body">
																			<table id="tablaMarca"
																				class="display responsive table table-striped"
																				style="width: 100%">
																				<thead>
																					<tr>
																						<th>Numero de Cuenta</th>
																						<th>CBU</th>
																						<th>Tipo de Cuenta</th>
																						<th>Fecha de Creación</th>
																						<th>Saldo</th>
																					</tr>
																				</thead>
																				<tbody id="tablaCuentasAsociadasBody<%=i%>">
																					
																				</tbody>
																			</table>
																		</div>

																	</div>
																</div>
															</div>
															<div class="accordion-item">
																<h2 class="accordion-header"
																	id="panelsStayOpen-headingThree<%=i%>">
																	<button class="accordion-button collapsed"
																		type="button" data-bs-toggle="collapse"
																		data-bs-target="#panelsStayOpen-collapseThree<%=i%>"
																		aria-expanded="false"
																		aria-controls="panelsStayOpen-collapseThree<%=i%>">
																		<p style="color: #033649;">Historial de Préstamos</p>
																	</button>
																</h2>
																<div id="panelsStayOpen-collapseThree<%=i%>"
																	class="accordion-collapse collapse"
																	aria-labelledby="panelsStayOpen-headingThree<%=i%>">
																	<div class="accordion-body">

																		<div class="card-body">
																			<table id="tablaMarca"
																				class="display responsive table table-striped"
																				style="width: 100%">
																				<thead>
																					<tr>
																						<th>Préstamo Nro.</th>
																						<th>Fecha de Solicitud</th>
																						<th>Importe Solicitado</th>
																						<th>Plazo de Pago (Meses)</th>
																						<th>Cuotas Restantes</th>
																						<th>Estado</th>
																					</tr>
																				</thead>
																				<tbody id="historialPrestamosBody<%=i%>">
																				</tbody>
																			</table>
																		</div>
																	</div>
																</div>
															</div>
														</div>
													</div>
												</div>




											</div>
											<div class="modal-footer">
												<button type="button" class="btn btn-primary" data-bs-dismiss="modal">Cerrar</button>
											</div>
										</div>
									</div>
								</div>
								 <a href="#" class="btn" data-bs-toggle="modal"	data-bs-target="#editarCuenta<%=i%>">
								 <svg
										xmlns="http://www.w3.org/2000/svg" width="16" height="16"
										fill="currentColor" class="bi bi-pencil-square"
										viewBox="0 0 16 16"> <path
										d="M15.502 1.94a.5.5 0 0 1 0 .706L14.459 3.69l-2-2L13.502.646a.5.5 0 0 1 .707 0l1.293 1.293zm-1.75 2.456-2-2L4.939 9.21a.5.5 0 0 0-.121.196l-.805 2.414a.25.25 0 0 0 .316.316l2.414-.805a.5.5 0 0 0 .196-.12l6.813-6.814z" />
									<path fill-rule="evenodd"
										d="M1 13.5A1.5 1.5 0 0 0 2.5 15h11a1.5 1.5 0 0 0 1.5-1.5v-6a.5.5 0 0 0-1 0v6a.5.5 0 0 1-.5.5h-11a.5.5 0 0 1-.5-.5v-11a.5.5 0 0 1 .5-.5H9a.5.5 0 0 0 0-1H2.5A1.5 1.5 0 0 0 1 2.5v11z" />
									</svg></a> 
									
									
									
									
									<div class="modal fade" id="editarCuenta<%=i%>"
									data-bs-backdrop="static" data-bs-keyboard="false"
									tabindex="-1" aria-labelledby="staticBackdropLabel"
									aria-hidden="true">
									<div class="modal-dialog modal-xs">
										<div class="modal-content">
											<div class="card-header modal-header"
												style="background-color: #033649;">
												<h1 class="modal-title fs-5" id="staticBackdropLabel<%=i%>"
													style="color: white;">Editar Cuenta</h1>
												<button type="button" class="btn-close"
													data-bs-dismiss="modal" aria-label="Close"></button>
											</div>
										<form action="CuentasServlet" method="post" name="editarCuenta">
											<div class="modal-body">

												<div class="container-fluid" style="padding: 1vh;">
													<div class="mb-4">
													
															  <div class="mb-3 row">
															  	<div class="col-3">
																    <label for="lblCliente" class="form-label">Cliente</label>
																    <%session.setAttribute("cuenta", item); %>
															  	</div>
															  	<div class="col-9">
																    <label><%=item.getUsuario().getPersona().getDNI() + " - " + item.getUsuario().getPersona().getApellido() + ", " + item.getUsuario().getPersona().getNombre()%></label>
																    <input type="hidden" class="form-control" name="nroCuenta" value="<%=item.getNumeroCuenta()%>">
															  	</div>
															  </div>
															  <div class="mb-3 row">
															  <div class="col-3">
															  	<label for="lblTipoCuenta" class="form-label">Tipo de Cuenta</label>
															  </div>
															  <div class="col-9">
															  	<select class="form-select" name="SelTipoCuenta">
															  		<%for(TipoCuenta x: comboTipoCuentas){%>
															  			<%if(item.getTipoCuenta().getDescripcion().toLowerCase().equals(x.getDescripcion().toLowerCase())){ %>
																			<option value="<%=x.getIDTipoCuenta()%>" selected><%=x.getDescripcion()%></option>
															  			<% }
															  			else{%>
															  				<option value="<%=x.getIDTipoCuenta()%>"><%=x.getDescripcion()%></option>
															  			<%} %>
															  		<%} %>
																	</select>
															  </div>
															 </div>
															  <div class="mb-3 row">
															  <div class="col-3">
															    <label class="form-label">Saldo</label>
															  </div>
															  <div class="col-9">
															    <input type="number" min="0" class="form-control" name="Saldo" value="<%=item.getSaldo() %>">
															  </div>
															  </div>
															  <div class="mb-3 row">
															  	<div class="col-3">
															  		<label class="form-label">Estado de Cuenta</label>
															  	</div>
															  	<div class="col-9">
																  	<select class="form-select" name="SelEstado">
																  		<%if(item.isEstado()){ %>
																			<option value="true" selected>Activo</option>
																			<option value="false">Inactivo</option>
															  			<% }
															  			else{%>
															  				<option value="true">Activo</option>
																		  	<option value="false" selected>Inactivo</option>
															  			<%} %>
																		</select>
															  	</div>
															 </div>
													</div>
												</div>
											</div>
											<div class="modal-footer">
												<button type="button" class="btn btn-secondary"
													data-bs-dismiss="modal">Close</button>
												<input type="submit" value="Aceptar" class="btn btn-primary" name="btnEditar">
											</div>
										</form>
										
										</div>
									</div>
								</div> 
									
								

									
									
									
									
									
									<a href="#" class="btn" data-bs-toggle="modal" data-bs-target="#eliminarCuenta<%=i%>">
									<svg
										xmlns="http://www.w3.org/2000/svg" width="16" height="16"
										fill="currentColor" class="bi bi-trash-fill"
										viewBox="0 0 16 16"> <path
										d="M2.5 1a1 1 0 0 0-1 1v1a1 1 0 0 0 1 1H3v9a2 2 0 0 0 2 2h6a2 2 0 0 0 2-2V4h.5a1 1 0 0 0 1-1V2a1 1 0 0 0-1-1H10a1 1 0 0 0-1-1H7a1 1 0 0 0-1 1H2.5zm3 4a.5.5 0 0 1 .5.5v7a.5.5 0 0 1-1 0v-7a.5.5 0 0 1 .5-.5zM8 5a.5.5 0 0 1 .5.5v7a.5.5 0 0 1-1 0v-7A.5.5 0 0 1 8 5zm3 .5v7a.5.5 0 0 1-1 0v-7a.5.5 0 0 1 1 0z" />
									</svg></a></td>
									
									
									
									
									
									
									<div class="modal fade" id="eliminarCuenta<%=i%>"
									data-bs-backdrop="static" data-bs-keyboard="false"
									tabindex="-1" aria-labelledby="staticBackdropLabel"
									aria-hidden="true">
									<div class="modal-dialog modal-xs">
										<div class="modal-content">
											<div class="card-header modal-header"
												style="background-color: #033649;">
												<h1 class="modal-title fs-5" id="staticBackdropLabel<%=i%>"
													style="color: white;">Eliminar Cuenta</h1>
												<button type="button" class="btn-close"
													data-bs-dismiss="modal" aria-label="Close"></button>
											</div>
										<form action="CuentasServlet" method="post">
											<div class="modal-body">

												<div class="container-fluid" style="padding: 1vh;">
													<div class="mb-4">
													
															  <p>Desea eliminar la cuenta?</p>
														<input type="hidden" name="idCuenta" value="<%=item.getNumeroCuenta()%>">
													</div>
												</div>




											</div>
											<div class="modal-footer">
												<button type="button" class="btn btn-secondary"
													data-bs-dismiss="modal">Cancelar</button>
												<input type="submit" value="Aceptar" class="btn btn-primary" name="btnEliminar">
											</div>
										</form>
										
										</div>
									</div>
								</div> 

						</tr>
                    
                    <%
                    i++;
                    } %>
                </tbody>
            </table>
            <a class="btn btn-primary" style="background-color:#036564" href="Home.jsp">Atrás</a>
        </div>
    </div>
</div>

<!-- Modal -->
<div class="modal fade" id="newClient" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1" aria-labelledby="newClientLabel" aria-hidden="true">
  <div class="modal-dialog modal-lg">
    <div class="modal-content">
      <div class="modal-header" style="background-color:#036564">
        <h5 class="modal-title" id="newClientLabel" style="color: #fff" >Nueva Cuenta</h5>
        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
      </div>
      <div class="modal-body">
        <form class="form" action="CuentasServlet" onsubmit="return validate();" name="nuevaCuenta" id="nuevaCuenta" method="post">
        	<div class="row">
        		<%if(listado.size() != 0){ %>
        			<input type="hidden" name="new_cuenta" class="form-control" id="new_acount" value='<%=listado.get(listado.size() -1 ).getNumeroCuenta() +1%>'/>
        			<label class="form-label">Nro de Cuenta</label>
        			<p><%=listado.get(listado.size() -1 ).getNumeroCuenta() +1%></p>
        		<% } else {%>
        			<input type="hidden" name="new_cuenta" class="form-control" id="new_acount" value='1'/>
        			<label>Nro de Cuenta</label>
        			<p>1</p>
        		<%} %>
        		<div class="col-md-6 mt-3">
        			<label class="form-label" for="new_cbu">CBU</label>
        			<input type="text" name="new_cbu" id="new_cbu" class="form-control requerido" />
        		</div>
        		<div class="col-md-6 mt-3">
        			<label class="form-label" for="new_tipocuenta">Tipo de Cuenta</label>
				    <select class="form-select select-requerido" name="tipocuenta" id="tipocuenta">
				    	<option value="-1">Seleccione un tipo de cuenta</option>
				    	<%for(TipoCuenta tc : comboTipoCuentas) { %>
				    	<option value=<%=tc.getIDTipoCuenta()%>><%=tc.getDescripcion()%></option>
				    	<%} %>
					</select>
					
        			<p class="error" id="error-tipocuenta">Por favor, seleccione un tipo de cuenta</p>
        		</div>
        		<div class="col-md-6 mt-3">
        			<label>Fecha de Creacion</label>
        			<input type="text" class="form-control" name="today" disabled value=<%= sdf.format(md) %> />
        		</div>
        		<div class="col-md-8 mt-3">
        			<label class="form-label">Estado</label>
        			<select disabled class="form-select" name="new_estado">
        				<option value="1">Activa</option>
        			</select>
        		</div>
        		<div class="col-md-6 mt-3">
        			<label>Asignar a Cliente</label>
        			<select class="form-select select-requerido" id="new_person" name="new_person" >
        				<option value="-1">Seleccione un Cliente</option>
        				<%for (Usuario u : comboClientes) {%>        				
        				<option value=<%=u.getPersona().getDNI()%>><%= u.getPersona().getDNI() + " - " + u.getPersona().getNombre() + " " + u.getPersona().getApellido()%></option>
        				<%} %>
        			</select>
        		</div>
        		         <div class="errorTxt alert alert-danger" style="display:none;height: 20px;"></div>
        	</div>
        	<div class="col-md-12 mt-3 text-center">
        	<button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cerrar</button>
        <button type="submit" class="btn btn-primary" name="btnCrear" id="btnCrear" value="crear">Hecho</button>
        	</div>
        </form>
      </div>
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
		  
		  
/* 		  $.validator.addMethod("valueNotEquals", function(value, element, arg){
			  return arg !== value;
			 });

			 // configure your validation
			 $("#nuevaCuenta").validate({
			  rules: {
				new_cbu: {
					required: true,
				},
			 new_person: {required: true }
			  },
			  messages: {
				  new_cbu: { required: "Por favor, ingrese un numero de cbu"},
			  new_person: { valueNotEquals: "Por favor seleccione un cliente" }
			  },
			  errorElement: "div",
			  errorLabelContainer: ".errorTxt"
			 });
		   */
		  
		  
		});
	
	function validate(){
		
		if($(".requerido").val() == ""){
			alert('Falta completar campos');
			return false;
		}
		
		if($("#new_person option").filter(":selected").val() == "-1"){
			alert('Por favor, seleccione un cliente a asignar');
			return false
		}
		if($("#tipocuenta option").filter(":selected").val() == "-1"){
			alert('Por favor, seleccione un tipo de cuenta');
			return false
		}
		
		return true;
	}
	
	
	function mostrarCuentasAsociadas(dni, identifier, nroCuenta){
		
		$.ajax({
			url: "CuentasServlet",
			type: "POST",
			data: {
				dniCuenta: dni
			},
			success: function(res){
				let cuentas = $.parseJSON(res);
					
				$.each(cuentas, function(i, item){
					let day = item.FechaCreacion.date.day;
					let month = item.FechaCreacion.date.month;
					let year = item.FechaCreacion.date.year;
					
					if(day <10){
						day = "0"+day;
					}
					if(month < 10){
						month = "0"+month;
					}
					
					let date = year+"-"+month+"-"+day;
					
					let tableRow = "<tr><td>"+item.NumeroCuenta+"</td><td>"+item.CBU+"</td><td>"+item.TipoCuenta.Descripcion+"</td><td>"+date+"</td><td>"+item.Saldo+"</td></tr>"
					
					$('#tablaCuentasAsociadasBody'+identifier).html(tableRow);
				})
			}
		});
		
		$.ajax({
			url: "PrestamosServlet",
			type: "POST",
			data: {
				prestamoDni: dni
			},
			success: function(res){
				let prestamos = $.parseJSON(res);
				console.log(prestamos);
				if(prestamos){
				$.each(prestamos, function(i, item){
					
					let day = item.Fecha.day;
					let month = item.Fecha.month;
					let year = item.Fecha.year;
					
					if(day <10){
						day = "0"+day;
					}
					if(month < 10){
						month = "0"+month;
					}
					
					let date = year+"-"+month+"-"+day;
					
					
					let tableRow = "<tr><td>"+item.IDPrestamo+"</td><td>"+date+"</td><td>"+item.ImporteSolicitado+"</td><td>"+item.PlazoPagoMeses+"</td><td>"+item.CuotasRestantes+"</td><td>"+item.Aprobado+"</td></tr>";
					
					$('#historialPrestamosBody'+identifier).html(tableRow);
				})
				}
			}
		});
		
		
	}
	
	$('#new_person').blur(function(){
		let buscar = $(this).val();
		
		$.ajax({
			url: "CuentasServlet",
			type: "POST",
			data: {dni: buscar},
			success: function(res){
				console.log(res);
				if(res > 3) {
					alert("El usuario tiene mas de 3 cuentas");
					$('#btnCrear').prop('disabled', true);

				}
				else{
					$('#btnCrear').prop('disabled', false);

				}
			}
			
		});
	})
	


	</script>
	<script src="https://cdn.jsdelivr.net/npm/jquery-validation@1.19.5/dist/jquery.validate.js"></script>
	<%}
else{%>
	<form action="CuentasServlet" method="post">
		<h1 style="color:white;">Error, no tiene permisos para acceder a esta página</h1>
		<input type="submit" value="Login" class="btn btn-primary" name="btnLogin">
	</form>
<%} %>
	</body>
</html>