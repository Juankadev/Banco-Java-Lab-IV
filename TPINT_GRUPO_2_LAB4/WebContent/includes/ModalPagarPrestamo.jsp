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
		<div class="modal-dialog modal-fullscreen">
			<div class="modal-content">
				<div class="card-header modal-header"
					style="background-color: #033649;">
					<h1 class="modal-title fs-5" id="staticBackdropLabel"
						style="color: white;">Pago</h1>
					<button type="button" class="btn-close" data-bs-dismiss="modal"
						aria-label="Close"></button>
				</div>
				<div class="modal-body">
					<div id="cuotas-header" class="alert alert-primary hide"
						role="alert">Seleccione en la tabla un rango de cuotas a
						pagar</div>
					<div class="d-flex">
						<div class="floating-label border-form">
							<select id="loan-account-select" name="loan-term-select"
								onclick="this.setAttribute('value', this.value);"
								class="floating-select pe-5">
							</select> <label class="floating-label-style">Debitar de</label>
						</div>
					</div>
					<table id="calculadora" class="table table-striped table-hover w-100">
						<thead>
							<tr>
								<th class="d-none">I</th>
								<th>Periodo</th>
								<th>Cuota</th>
								<th>Interes</th>
								<th>Amortizacion</th>
								<th>Saldo</th>
								<th>IVA</th>
								<th>Cuota a pagar</th>
								<th>Fecha de vencimiento</th>
							</tr>
						</thead>
						<tbody id="calculadora-body">
						</tbody>
					</table>
					

				</div>
				<div class="modal-footer">
					<button id="btn-cerrar" type="button" class="btn btn-primary"
						data-bs-dismiss="modal">Cerrar</button>
					<button id="btn-pagar" class='btn btn-success'>Pagar</button>
				</div>
			</div>
		</div>
	</div>
</body>
</html>