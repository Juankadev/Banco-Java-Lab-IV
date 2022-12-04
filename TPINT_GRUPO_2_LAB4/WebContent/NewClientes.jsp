<%@page import="daoImpl.RolDaoImpl"%>
<%@page import="dao.RolDao"%>
<%@page import="daoImpl.DireccionDaoImpl"%>
<%@page import="dao.DireccionDao"%>
<%@page import="daoImpl.LocalidadDaoImpl"%>
<%@page import="dao.LocalidadDao"%>
<%@page import="daoImpl.PaisDaoImpl"%>
<%@page import="dao.PaisDao"%>
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
<title>Clientes</title>
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
	ArrayList<Usuario> listado = (ArrayList<Usuario>)request.getAttribute("comboClientes");
	int i=0;
	%>
	<%!Date md = new Date();%>
	<%!SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");%>
	<% ArrayList<Pais> lPais = (ArrayList<Pais>)request.getAttribute("listPais");
	ArrayList<Localidad> lLoca = (ArrayList<Localidad>)request.getAttribute("listLocalidad");
	ArrayList<Direccion> lDir = (ArrayList<Direccion>)request.getAttribute("listDir");
	ArrayList<Rol> rList = (ArrayList<Rol>)request.getAttribute("listRol");%>
		<%if(userLogueado.getPersona().getSexo().equals("f") || userLogueado.getPersona().getSexo().equals("F")){ %>
	<p class="usuario">Bienvenida <%=userLogueado.getPersona().getNombre()+" "+userLogueado.getPersona().getApellido() %></p>
	<%}else if(userLogueado.getPersona().getSexo().equals("m") || userLogueado.getPersona().getSexo().equals("M")){ %>
	<p class="usuario">Bienvenido <%=userLogueado.getPersona().getNombre()+" "+userLogueado.getPersona().getApellido() %></p>
	<%} %>
	<div class="container-fluid" style="padding:3vh;">
	<div class="card mb-4">
        <div class="card-header" style="background-color:#033649;">
            <i class="fas fa-table me-1"></i><h6>Listado de Clientes</h6>
            
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
                    <button class="btn btn-success" data-bs-toggle="modal" data-bs-target="#altaClienteModal" style="background-color:#036564">Crear cuenta</button>
                </div>
                <div class="col-md-9 text-center">
                	<%
                	String msg = "";
                	if(request.getAttribute("usuarioCreado") != null){
                		msg=(String)request.getAttribute("usuarioCreado");
                	%>
                		<p class="successMsg"><%=msg %></p>
                	<%} %>
                	
                	<%if(request.getAttribute("eliminarUsuario") != null){
                		msg=(String)request.getAttribute("eliminarUsuario");
                	%>
                		<p class="successMsg"><%=msg %></p>
                	<% }%>
                	
                	<%if(request.getAttribute("modificado") != null){
                		msg=(String)request.getAttribute("modificado");
                	%>
                		<p class="successMsg"><%=msg %></p>
                	<% }%>
                	
                </div>
            </div>

            <hr />
            <%if(listado != null){ %>
            <table id="dt-filter-select" class="display responsive table table-striped" style="width: 100%">
                <thead>
                    <tr>
                        <th>DNI</th>
                        <th>Nombre y Apellido</th>
                        <th>Correo</th>   
                        <th>Estado</th> 
                        <%--<th>Stock</th>--%>
                        <th>&nbsp Acciones</th>

                    </tr>
                </thead>
                <tbody>
                
                
                    <%for(Usuario item : listado){ %>
                    <tr>
                        <td><%=item.getPersona().getDNI() %></td>
                        <td><%=item.getPersona().getApellido()+", "+item.getPersona().getNombre()%></td>
                        <td><%=item.getPersona().getCorreo()%></td> 
    					<%if(item.isEstado()){ %>
							<td>Activo</td>
						<% }
						else{%>
							<td>Inactivo</td>
						<%} %>           
							<td><a href="#" class="btn" data-bs-toggle="modal"
								data-bs-target="#detallesCuenta<%=i%>"> <svg
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
																		<p style="color: #033649;">Datos Personales</p>
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
																						<th>Sexo</th>
																						<th>Correo Electrónico</th>
																						<th>Dirección</th>
																						<th>Localidad</th>
																						<th>País</th>
																					</tr>
																				</thead>
																				<tbody>
																					<tr>
																						<td><%=item.getPersona().getDNI()%></td>
																						<td><%=item.getPersona().getCUIL() %></td>
																						<td><%=item.getPersona().getApellido()+", "+item.getPersona().getNombre() %></td>
																						<td><%=item.getPersona().getFechaNacimientoPrueba() %></td>
																						<td><%=item.getPersona().getSexo()%></td>
																						<td><%=item.getPersona().getCorreo() %></td>
																						<td><%=item.getPersona().getDireccion().getDescripicion() %></td>
																						<td><%=item.getPersona().getDireccion().getLocalidad().getDescripcion() %></td>
																						<td><%=item.getPersona().getPais().getDescripcion()%></td>
																					</tr>

																				</tbody>
																			</table>
																		</div>

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
																		<p style="color: #033649;">Datos de Usuario</p>
																	</button>
																</h2>
																<div id="panelsStayOpen-collapseTwo<%=i%>"
																	class="accordion-collapse collapse"
																	aria-labelledby="panelsStayOpen-headingTwo<%=i%>">
																	<div class="accordion-body">

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
																						<th>Username</th>
																						<th>Password</th>
																					</tr>
																				</thead>
																				<tbody>
																					<tr>
																						<td><%=item.getUser()%></td>
																						<td><%=item.getPass() %></td>
																					</tr>

																				</tbody>
																			</table>
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
								</div>
								</div>
								
								 <a href="#" class="btn" data-bs-toggle="modal"	data-bs-target="#editarCliente<%=i%>" onclick="completarDatos(<%=item.getPersona().getDNI()%>, <%=i%>)">
								 <svg
										xmlns="http://www.w3.org/2000/svg" width="16" height="16"
										fill="currentColor" class="bi bi-pencil-square"
										viewBox="0 0 16 16"> <path
										d="M15.502 1.94a.5.5 0 0 1 0 .706L14.459 3.69l-2-2L13.502.646a.5.5 0 0 1 .707 0l1.293 1.293zm-1.75 2.456-2-2L4.939 9.21a.5.5 0 0 0-.121.196l-.805 2.414a.25.25 0 0 0 .316.316l2.414-.805a.5.5 0 0 0 .196-.12l6.813-6.814z" />
									<path fill-rule="evenodd"
										d="M1 13.5A1.5 1.5 0 0 0 2.5 15h11a1.5 1.5 0 0 0 1.5-1.5v-6a.5.5 0 0 0-1 0v6a.5.5 0 0 1-.5.5h-11a.5.5 0 0 1-.5-.5v-11a.5.5 0 0 1 .5-.5H9a.5.5 0 0 0 0-1H2.5A1.5 1.5 0 0 0 1 2.5v11z" />
									</svg></a> 
									
									
									
									
									<div class="modal modal-xl fade" id="editarCliente<%=i%>" tabindex="-1" aria-labelledby="editarClienteLabel" aria-hidden="true">
									<div class="modal-dialog modal-dialog-centered">
										<div class="modal-content">
											<div class="modal-header" style="background-color:#036564">
												<h1 class="modal-title fs-5" style="color: #fff;" id="editarClienteLabel<%=i%>">
													Editar Cliente</h1>
													<%session.setAttribute("edit-client", item.getPersona().getDNI()); %>
												<button type="button" class="btn-close btn-close-white"
													data-bs-dismiss="modal" aria-label="Close"></button>
											</div>
											<div class="modal-body">
												<form  method="post" action="sClientes" onsubmit="return editValidate(<%=i%>)">
													<div class="row">
													<h5>Datos de Cliente</h5>
											<div class="col-md-4 mb-3">
												<label class="form-label">Nombre</label>
												<input class="form-control letter-required" type="text" name="txtNombre" value="<%=item.getPersona().getNombre() %>" />
											</div>
											<div class="col-md-4 mb-3">
												<label class="form-label">Apellidos</label>
												<input class="form-control letter-required" type="text" name="txtApellido" value="<%=item.getPersona().getApellido()%>"/>
											</div>
											<div class="col-md-4 mb-3">
												<label class="form-label">DNI</label>
												<p><%=item.getPersona().getDNI()%></p>
													<input class="form-control" id="edit-dni" type="hidden" name="txtDNI" value="<%=item.getPersona().getDNI()%>"/>
											</div>
											<div class="col-md-3 mb-3">
												<label class="form-label">CUIL</label>
												<p><%=item.getPersona().getCUIL() %></p>
											</div>
											<div class="col-md-3 mb-3">
												<label class="form-label">Sexo</label>
												<select class="form-select" name="sexo" id="sexo<%=i%>">
													<option value='-1'>Seleccione una opcion</option>
													<option value='M'>Masculino</option>
													<option value="F">Femenino</option>
													<option value="X">Otro</option>
												</select>
											</div>
											<div class="col-md-3 mb-3">
												<label class="form-label">Fecha de Nacimiento</label>
												<div class="item">
													<input class="form-control" type="date"
														max=<%=sdf.format(md)%>  name="fecnac" id="fecnac<%=i%>"/>
												</div>
											</div>
											<div class="col-md-3 mb-3">
												<label class="form-label">Correo</label>
												<div class="item">
													<input class="form-control" type="email"  name="email" value="<%=item.getPersona().getCorreo()%>"/>
												</div>
											</div>
										<div class="col-md-4 mb-3">
											<label class="form-label">Nacionalidad</label>
											<select class="form-select" id="nacionalidad<%=i%>" name="nacionalidad">
												<option value="-1">Seleccione una opcion</option>
												<%for(Pais p : lPais){ %>
													<option value="<%=p.getIdPais() %>"><%=p.getDescripcion() %></option>
												<% } %>
											</select>
										</div>
										<div class="col-md-4 mb-3">
											<label class="form-label">Localidad</label>
											<select class="form-select" name="localidad" id="localidad<%=i%>">
											<option value="-1">Seleccione una opcion</option>
												<%for(Localidad l : lLoca){ %>
													<option value="<%=l.getCP() %>"><%=l.getDescripcion() %></option>
												<% } %>
											</select>
										</div>
										<div class="col-md-4 mb-3">
											<label class="form-label">Direccion</label>
											<select class="form-select" name="direccion" id="direccion<%=i%>">
											<option value="-1">Seleccione una opcion</option>
												<%for(Direccion dir : lDir){ %>
													<option value="<%=dir.getIdDireccion() %>"><%=dir.getDescripicion() %></option>
												<% } %>
											</select>
										</div>
									<hr/>
									</div>
									<div class="row md-3">
									<h5>Datos de usuario</h5>
									<div class="col-md-4">
											<label class="form-label">Username</label>
											<input type="text" class="form-control" name="username" value="<%=item.getUser()%>">
										</div>
										<div class="col-md-4">
											<label class="form-label">Contraseña</label>
											<input type="text" class="form-control" name="password" value="<%=item.getPass()%>">
										</div>
										<div class="col-md-4 mb-4">
											<label class="form-label">Tipo de Usuario</label>
											<select class="form-select" name="tiporol" id="tiporol<%=i%>">
											<option value="-1">Seleccione una opcion</option>
												<%for(Rol rolcito : rList){ %>
													<option value="<%=rolcito.getIDRol()%>"><%=rolcito.getDescripcion()%></option>
												<% } %>
											</select>
										</div>
									<div class="col-md-12 text-center">
										<button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Atras</button>
										<input type="submit" class="btn btn-primary" name="Modificar" value="Modificar"></input>
									</div>
									</div>
								</form>
							</div>
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
									
									
									
									
									
									
									<div class="modal fade" id="eliminarCuenta<%=i %>" tabindex="-1" aria-labelledby="transferModalLabel" aria-hidden="true">
	<div class="modal-dialog modal-dialog-centered">
		<div class="modal-content">
			<div class="modal-header " style="background-color:#036564">
				<h1 class="modal-title fs-5" id="transferModalLabel" style="color: #fff">Atencion!</h1>
				<button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
			</div>
			<div class="modal-body">
			<form method="POST" action="ClientesServlet" class="form">
				<div class="col-md-12 text-center mb-3">
					<h3 style="color: #000">Advertencia!</h3>
					<h5>Estas a punto de dar de baja a <%=item.getPersona().getApellido() + ", " + item.getPersona().getApellido() %></h5>
					<input type="hidden" name="dni" value="<%=item.getPersona().getDNI()%>"/>;
				</div>
			<div class="col-md-12 text-center">
				<button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Atras</button>
				<button type="submit" name="eliminarUsuario" class="btn btn-primary">Dar de Baja</button>
			</div>
			</form>
		</div>
	</div>
</div>
</div>

						</tr>
                    
                    <%
                    i++;
                    } %>
                </tbody>
            </table>
            <%
            } else{
            %>
            <div class="row justify-content-center">
            	<div class="col-md-8 text-center">
            		                    <button class="btn btn-success" data-bs-toggle="modal" data-bs-target="#altaClienteModal" style="background-color:#036564">Crear cuenta</button>
            	</div>
            </div>
            <%} %>
            <a class="btn btn-primary" style="background-color:#036564" href="Home.jsp">Atrás</a>
        </div>
    </div>
</div>

<!-- Modal -->
<div class="modal modal-xl fade" id="altaClienteModal" tabindex="-1"
						aria-labelledby="transferModalLabel" aria-hidden="true">
						<div class="modal-dialog modal-dialog-centered">
							<div class="modal-content">
								<div class="modal-header" style="background-color:#036564">
									<h1 class="modal-title fs-5" id="transferModalLabel" style="color: #fff;">Alta
										de cliente</h1>
									<button type="button" class="btn-close  btn-close-white" data-bs-dismiss="modal"
										aria-label="Close"></button>
								</div>
								<div class="modal-body">
								<form action="ClientesServlet" method="post" class="form" onsubmit="return validate()">
									<div class="row mb-3">
											<h5>Datos Personales</h5>
											<div class="col-md-4 mb-3">
												<label class="form-label">Nombre</label>
												<input class="form-control letter-required" type="text" name="txtNombres"/>
											</div>
											<div class="col-md-4 mb-3">
												<label class="form-label">Apellidos</label>
												<input class="form-control letter-required" type="text" name="txtApellidos"/>
											</div>
											<div class="col-md-4 mb-3">
												<label class="form-label">DNI</label>
													<input class="form-control"  id="dni-requerido" type="text" name="txtDNI"/>
											</div>
											<div class="col-md-3">
												<label class="form-label">CUIL</label>
												<input type="text" class="form-control" id="cuil" name="cuil" />
											</div>
											<div class="col-md-3 mb-3">
												<label class="form-label">Sexo</label>
												<select class="form-select" id="sexo" name="sexo">
													<option value='-1'>Seleccione una opcion</option>
													<option value='M'>Masculino</option>
													<option value="F">Femenino</option>
													<option value="X">Otro</option>
												</select>
											</div>
											<div class="col-md-3 mb-3">
												<label class="form-label">Fecha de Nacimiento</label>
												<div class="item">
													<input class="form-control" id="fecnac" type="date"
														max=<%=sdf.format(md)%>  name="fecnac"/>
												</div>
											</div>
											<div class="col-md-3 mb-3">
												<label class="form-label">Correo</label>
												<div class="item">
													<input class="form-control" type="email"  name="email"/>
												</div>
											</div>
										<div class="col-md-4 mb-3">
											<label class="form-label">Nacionalidad</label>
											<select class="form-select desplegable-requerido" name="nacionalidad" id="pais">
												<option value="-1">Seleccione una opcion</option>
												<%for(Pais p : lPais){ %>
													<option value="<%=p.getIdPais() %>"><%=p.getDescripcion() %></option>
												<% } %>
											</select>
										</div>
										<div class="col-md-4 mb-3">
											<label class="form-label">Localidad</label>
											<select class="form-select desplegable-requerido" id="localidad" name="localidad">
											<option value="-1">Seleccione una opcion</option>
												<%for(Localidad l : lLoca){ %>
													<option value="<%=l.getCP() %>"><%=l.getDescripcion() %></option>
												<% } %>
											</select>
										</div>
										<div class="col-md-4 mb-3">
											<label class="form-label">Direccion</label>
											<select class="form-select" id="direccion" name="direccion">
											<option value="-1">Seleccione una opcion</option>
												<%for(Direccion dir : lDir){ %>
													<option value="<%=dir.getIdDireccion() %>"><%=dir.getDescripicion() %></option>
												<% } %>
											</select>
										</div>
									</div>
									<hr/>
									<div class="row mb-4">
										<h5>Datos de Usuario</h5>
										<div class="col-md-4">
											<label class="form-label">Username</label>
											<input type="text" class="form-control" id="letter-required" name="username">
										</div>
										<div class="col-md-4">
											<label class="form-label">Contraseña</label>
											<input type="text" class="form-control" id="letter-required" name="password" >
										</div>
										<div class="col-md-4">
											<label class="form-label">Tipo de Usuario</label>
											<select class="form-select" id="tiporol" name="tiporol">
											<option value="-1">Seleccione una opcion</option>
												<%for(Rol rolcito : rList){ %>
													<option value="<%=rolcito.getIDRol()%>"><%=rolcito.getDescripcion()%></option>
												<% } %>
											</select>
										</div>
									</div>
								<div class="col-md-12 text-center">
									<button type="button" class="btn btn-secondary"
										data-bs-dismiss="modal">Atras</button>
									<!--  <button type="button" class="btn btn-primary">Dar de alta</button>  -->
									<input type="submit" id="input-submit" class="btn btn-primary"
										value="Dar Alta" name="crearUsuario">
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
		  
		  
		});
	
	function validate(){
		
		if($('#dni-requerido').val().length != 8 || isNaN($('#dni-requerido').val()) || $('#dni-requerido').val() == ""){
			alert('Recuerde que el Dni debe contener 8 caracteres');
			return false;
		}
		
		
		if(!soloLetras($('.letter-required').val())){
			if($('.letter-required').val() ==""){

				alert('Esta dejando campos vacios');
				return false;
			}

			alert('Ha puesto numeros en campos donde solo se deben ingresar letras, por favor vuelva a completar')
			return false;
		}
		
		if($("#tiporol option").filter(":selected").val() == "-1"){
			alert('Por favor, seleccione un rol');
			return false
		}
		
		if($("#localidad option").filter(":selected").val() == "-1"){
			alert('Por favor, seleccione una Localidad');
			return false
		}
		if($("#direccion option").filter(":selected").val() == "-1"){
			alert('Por favor, seleccione una Direccion');
			return false
		}
		if($("#pais option").filter(":selected").val() == "-1"){
			alert('Por favor, seleccione un Pais');
			return false
		}
		if($("#sexo option").filter(":selected").val() == "-1"){
			alert('Por favor, seleccione un sexo');
			return false
		}
		if($('#fecnac').val() == ""){
			alert('Por favor, ingrese su fecha de nacimiento');
			return false;
		}
		
		return true;
	}
	
	
	$('#localidad').blur(function(){
		let idloca = $('option:selected', this).val();
		$.ajax({
			url: "filtrosUbicacionServlet",
			type: "POST",
			data: {
				idlocalidad: idloca
			},
			success: function(res){
				let localidades = $.parseJSON(res);
				let options = "<option value="+-1+">Selecciona una Direccion</option>"
				$.each(localidades, function(i, item){
					options += "<option value="+item.IdDireccion+">"+item.Descripcion+"</option>"
				})
				
				$('#direccion').html(options);
			}
		})
	})
	
	$('#dni-requerido').blur(function(){
		var identifier = $(this).val();
		console.log(identifier);
		
		$.ajax({
			url: "ClientesServlet",
			type: "POST",
			data: {
				isExist: identifier
			},
			success: function(res){
				let user = $.parseJSON(res);
				
				if(user != null){
					alert("el usuario ya existe");
					$('#input-submit').prop('disabled', true);

				}
				else{
					$('#input-submit').prop('disabled', false);

				}
			}
		})
	})
	
	function completarDatos(value, identifier) {
		var dni = value;
		var i = identifier;
  		console.log(dni);
		$.ajax({
			url: "EditarClienteServlet",
			type: "POST",
			data: {
				persona: dni
			},
			success: function(res) {
				let person = $.parseJSON(res);
				console.log(person);
				$("#localidad"+i).val(person.Persona.Direccion.Localidad.CP);
				$("#nacionalidad"+i).val(person.Persona.Pais.IdPais);
				$("#tiporol"+i).val(person.Rol.IDRol);
				$("#direccion"+i).val(person.Persona.Direccion.IdDireccion);
				$("#sexo"+i).val(person.Persona.Sexo);
				
				let day = person.Persona.FechaNac.day;
				let month = person.Persona.FechaNac.month;
				if(day < 10){
					day = "0"+day;
				}
				if(month < 10){
					month = "0"+month;
				}
				let dt = person.Persona.FechaNac.year + "-" +month + "-" + day;
				console.log("fecha:", dt);
				$("#fecnac"+i).val(dt);
			}
 	 })
  }
	
	function editValidate(id){
		if($('#edit-dni').val().length != 8 || isNaN($('#edit-dni').val()) || $('#edit-dni').val() == ""){
			console.log($('#edit-dni').val());
			console.log("error de tamaño o vacio");
			return false;
		}
		
		
		if(!soloLetras($('.letter-required').val())){
			if($('.letter-required').val() ==""){

				console.log("error de tamaño letras vacio");
				return false;
			}

			console.log("error de letras tamaño o vacio");
			return false;
		}
		
		if($("#tiporol"+id+" option").filter(":selected").val() == "-1"){
			return false
		}
		
		if($("#localidad"+id+" option").filter(":selected").val() == "-1"){
			return false
		}
		if($("#direccion"+id+" option").filter(":selected").val() == "-1"){
			return false
		}
		if($("#pais"+id+" option").filter(":selected").val() == "-1"){
			return false
		}
		if($("#nacionalidad"+id+" option").filter(":selected").val() == "-1"){
			return false
		}
		if($('#fecnac'+id).val() == ""){
			return false;
		}
		
		return true;
	}
	
	
	function soloLetras(val){
		let regex = /^[a-zA-Z ]+$/;
		return regex.test(val);
	}
	
	</script>
	<%}
else{%>
	<form action="CuentasServlet" method="post">
		<h1 style="color:white;">Error, no tiene permisos para acceder a esta página</h1>
		<input type="submit" value="Login" class="btn btn-primary" name="btnLogin">
	</form>
<%} %>
	<script src="./js/GeneralFunctions.js"></script>
	<script src="https://cdn.jsdelivr.net/npm/jquery-validation@1.19.5/dist/jquery.validate.js"></script>
	</body>
</html>