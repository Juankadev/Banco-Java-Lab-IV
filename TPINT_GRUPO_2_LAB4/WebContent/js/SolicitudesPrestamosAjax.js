$(document).ready(function() {
	const ListarSolicitudes = () => {
		$.ajax({
			url: "ListarSolicitudesPrestamosServlet", // URL del servlet
			type: "POST",
			cache: false, // No guardar en cache los datos retornados
			data: {},
			dataType: "json",
			success: function(data) {
				$('#prestamos-body').empty()
				if (typeof data == 'undefined'){
					$('#dt-filter-select').after('<div class="d-flex justify-content-center w-100">No data</div>')
				}
				else{
					localStorage['data'] = JSON.stringify(data)
					$.each(data, function(i, item) {
						var $div = $('<div>', { id: '#detalles-' + i });
						$div.html('<a href="' + i + '" class="btn btn-detalles" data-bs-toggle="modal"data-bs-target="#detallesPrestamo"><svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-box-arrow-in-up-right" viewBox="0 0 16 16"> <path fill-rule="evenodd" d="M6.364 13.5a.5.5 0 0 0 .5.5H13.5a1.5 1.5 0 0 0 1.5-1.5v-10A1.5 1.5 0 0 0 13.5 1h-10A1.5 1.5 0 0 0 2 2.5v6.636a.5.5 0 1 0 1 0V2.5a.5.5 0 0 1 .5-.5h10a.5.5 0 0 1 .5.5v10a.5.5 0 0 1-.5.5H6.864a.5.5 0 0 0-.5.5z"/> <path fill-rule="evenodd" d="M11 5.5a.5.5 0 0 0-.5-.5h-5a.5.5 0 0 0 0 1h3.793l-8.147 8.146a.5.5 0 0 0 .708.708L10 6.707V10.5a.5.5 0 0 0 1 0v-5z"/></svg></a>')

						var $tr = $('<tr>').append(
							$('<td>').text(item.IDPrestamo),
							$('<td>').text(item.Cuenta.Usuario.Persona.DNI),
							$('<td>').text(item.Cuenta.Usuario.Persona.Nombre + " " +
								item.Cuenta.Usuario.Persona.Apellido),
							$('<td>').text(item.ImporteSolicitado),
							$('<td>').text(item.PlazoPagoMeses),
							$('<td>').html($($div)),
							$('<td>').html(
								'<a href=' + i + ' class="btn btn-outline-success aprobado btn-sm">Aprobar solicitud</a>' +
								'<a href=' + i + ' class="btn btn-outline-danger desaprobado btn-sm">Rechazar solicitud</a>'
							)
						).appendTo('#prestamos-body')
					});
					initDataTable()
				}
			},
		})
	}
	
	const ListarCuentas = (dni) => {
		$.ajax({
			url: "ListarCuentasPorDniServlet", // URL del servlet
			type: "POST",
			cache: false, // No guardar en cache los datos retornados
			data: {dni: dni},
			dataType: "json",
			success: function(data) {
				$('#cuentas-body').empty()
				$.each(data, function(i, item) {
					var $tr = $('<tr>').append(
						$('<td>').text(item.NumeroCuenta),
						$('<td>').text(item.CBU),
						$('<td>').text(item.TipoCuenta.Descripcion),
						$('<td>').text(item.FechaCreacion.date.day + "-" +
						item.FechaCreacion.date.month + "-" +
						item.FechaCreacion.date.year),
						$('<td>').text(item.Saldo)
					).appendTo('#cuentas-body')
				})
			}
		})
	}
	
	const ListarHistorialPrestamos = (dni) => {
		$.ajax({
			url: "ListarPrestamosPorDNI", // URL del servlet
			type: "POST",
			cache: false, // No guardar en cache los datos retornados
			data: {dni: dni},
			dataType: "json",
			success: function(data) {
				$('#historial-body').empty()
				$.each(data, function(i, item) {
					$estado = JSON.parse(item.Estado)
					if ($estado == true){
						$estado = "Activo"
					}
					
					var $tr = $('<tr>').append(
						$('<td>').text(item.IDPrestamo),
						$('<td>').text(item.Fecha.day + "-" +
						item.Fecha.month + "-" +
						item.Fecha.year),
						$('<td>').text(item.ImporteSolicitado),
						$('<td>').text(item.PlazoPagoMeses),
						$('<td>').text(item.CuotasRestantes),
						$('<td>').text($estado)
					).appendTo('#historial-body')
				})
			}
		})
	}
	
	$(document).on("click", ".aprobado", function (e) {
		// Prevenir redirect
		e.preventDefault()
		// Obtener href del elemento 
		var i = $(this).attr('href')
		// Obtener datos desde el session
		var prestamoData = JSON.parse(localStorage['data'])
		$.ajax({
			url: "AprobarPrestamoServlet", // URL del servlet
			type: "POST",
			cache: false, // No guardar en cache los datos retornados
			data: {
				idPrestamo: prestamoData[i].IDPrestamo,
				aprobado: true
				},
			success: function() {
				// Refresh
				ListarSolicitudes()
				const liveToastAprobado = document.getElementById('liveToastAprobado')
				const toast = new bootstrap.Toast(liveToastAprobado)
				toast.show()
			}
		})
	})
	
	
	
	$(document).on("click", ".desaprobado", function (e) {
		// Prevenir redirect
		e.preventDefault()
		// Obtener href del elemento 
		var i = $(this).attr('href')
		// Obtener datos desde el session
		var prestamoData = JSON.parse(localStorage['data'])
		$.ajax({
			url: "AprobarPrestamoServlet", // URL del servlet
			type: "POST",
			cache: false, // No guardar en cache los datos retornados
			data: {
				idPrestamo: prestamoData[i].IDPrestamo,
				aprobado: false
				},
			success: function() {
				// Refresh
				ListarSolicitudes();
				const liveToastDesprobado = document.getElementById('liveToastDesaprobado')
				const toast = new bootstrap.Toast(liveToastDesprobado)
				toast.show()
			}
		})
	})
	
	$(document).on("click", ".btn-detalles", function () {
     var data = JSON.parse(localStorage['data'])
		var i = $(this).attr('href')
		$('#detalles-body').empty()
		$('#detalles-body').append(
			$('<td>').text(data[i].Cuenta.Usuario.Persona.DNI),
			$('<td>').text(data[i].Cuenta.Usuario.Persona.CUIL),
			$('<td>').text(data[i].Cuenta.Usuario.Persona.Nombre + " " +
				data[i].Cuenta.Usuario.Persona.Apellido),
			$('<td>').text(data[i].Cuenta.Usuario.Persona.FechaNac.day + "-" +
				data[i].Cuenta.Usuario.Persona.FechaNac.month + "-" +
				data[i].Cuenta.Usuario.Persona.FechaNac.year),
			$('<td>').text(data[i].Cuenta.Usuario.User),
			$('<td>').text(data[i].Cuenta.Usuario.Persona.Correo),
			$('<td>').text(data[i].Cuenta.Usuario.Persona.Direccion.Descripcion + " " +
				data[i].Cuenta.Usuario.Persona.Direccion.Localidad.CP),
			$('<td>').text(data[i].Cuenta.Usuario.Persona.Direccion.Localidad.Descripcion),
			$('<td>').text(data[i].Cuenta.Usuario.Persona.Direccion.Localidad.Pais.Descripcion)
		)
		ListarCuentas(JSON.stringify(data[i].Cuenta.Usuario.Persona.DNI))
		ListarHistorialPrestamos(JSON.stringify(data[i].Cuenta.Usuario.Persona.DNI))
	});

	ListarSolicitudes();

	const initDataTable = () => {
		var table = $('#dt-filter-select').DataTable();

		$('#min, #max').keyup(function() {
			table.draw()
		});

		$.fn.dataTable.ext.search.push(function(settings, data, dataIndex) {
			var min = parseInt($('#min').val(), 10);
			var max = parseInt($('#max').val(), 10);
			var importe = parseFloat(data[3]) || 0; // use data for the age column

			if (
				(isNaN(min) && isNaN(max)) ||
				(isNaN(min) && importe <= max) ||
				(min <= importe && isNaN(max)) ||
				(min <= importe && importe <= max)
			) {
				return true;
			}
			return false;
		});
	}


})


