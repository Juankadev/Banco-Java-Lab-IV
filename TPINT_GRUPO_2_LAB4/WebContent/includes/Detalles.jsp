<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<div class="modal fade" id="detallesPrestamo" data-bs-backdrop="static"
		data-bs-keyboard="false" tabindex="-1"
		aria-labelledby="staticBackdropLabel" aria-hidden="true">
		<div class="modal-dialog modal-xl">
			<div class="modal-content">
				<div class="card-header modal-header"
					style="background-color: #033649;">
					<h1 class="modal-title fs-5" id="staticBackdropLabel"
						style="color: white;">Detalles</h1>
					<button type="button" class="btn-close" data-bs-dismiss="modal"
						aria-label="Close"></button>
				</div>
				<div class="modal-body">

					<div class="container-fluid" style="padding: 1vh;">
						<div class="card mb-4 overflow-auto" style="height: 60vh;">

							<div class="accordion" id="accordionPanelsStayOpenExample">
								<div class="accordion-item">
									<h2 class="accordion-header" id="panelsStayOpen-headingOne">
										<button class="accordion-button" type="button"
											data-bs-toggle="collapse"
											data-bs-target="#panelsStayOpen-collapseOne"
											aria-expanded="true"
											aria-controls="panelsStayOpen-collapseOne">
											<p style="color: #033649;">Cliente</p>
										</button>
									</h2>
									<div id="panelsStayOpen-collapseOne"
										class="accordion-collapse collapse show"
										aria-labelledby="panelsStayOpen-headingOne">
										<div class="accordion-body">

											<div class="card-body">
												<table id="tablaPrestamos"
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
													<tbody id="detalles-body">
													</tbody>
												</table>
											</div>

										</div>
									</div>
								</div>
								<div class="accordion-item">
									<h2 class="accordion-header" id="panelsStayOpen-headingTwo">
										<button class="accordion-button collapsed" type="button"
											data-bs-toggle="collapse"
											data-bs-target="#panelsStayOpen-collapseTwo"
											aria-expanded="false"
											aria-controls="panelsStayOpen-collapseTwo">
											<p style="color: #033649;">Cuentas Asociadas</p>
										</button>
									</h2>
									<div id="panelsStayOpen-collapseTwo"
										class="accordion-collapse collapse"
										aria-labelledby="panelsStayOpen-headingTwo">
										<div class="accordion-body">

											<div class="card-body">
												<table id="tablaCuentas"
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
													<tbody id="cuentas-body">
													</tbody>
												</table>
											</div>

										</div>
									</div>
								</div>
								<div class="accordion-item">
									<h2 class="accordion-header" id="panelsStayOpen-headingThree">
										<button class="accordion-button collapsed" type="button"
											data-bs-toggle="collapse"
											data-bs-target="#panelsStayOpen-collapseThree"
											aria-expanded="false"
											aria-controls="panelsStayOpen-collapseThree">
											<p style="color: #033649;">Historial de Préstamos</p>
										</button>
									</h2>
									<div id="panelsStayOpen-collapseThree"
										class="accordion-collapse collapse"
										aria-labelledby="panelsStayOpen-headingThree">
										<div class="accordion-body">

											<div class="card-body">
												<table id="tablaPrestamos"
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
													<tbody id="historial-body">
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
					<button type="button" class="btn btn-primary"
						data-bs-dismiss="modal">Cerrar</button>
				</div>
			</div>
		</div>
	</div>
</body>
</html>