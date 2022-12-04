$(document).ready(function() {

	var cuotas = 0
	var monto = 0
	var idPrestamo = 0
	var infoPrestamoGlobal;

	//Obtener calculos de un prestamo y mostrarlo en forma de tabla
	const ajaxGetPrestamos = (prestamoData) => {
		$.ajax({
			url: "CalcularPrestamoServlet", // URL del servlet
			type: "POST",
			cache: false, // No guardar en cache los datos retornados
			data: {
				montoPrestamo: prestamoData.ImporteSolicitado, // Aca se colocan las IDs de los campos a enviar
				plazo: prestamoData.PlazoPagoMeses,
			},
			dataType: "json",
			success: function(data) {
				let i = prestamoData.PlazoPagoMeses - prestamoData.CuotasRestantes
				var detallesPrestamos = data.filter(x => x.periodo > i)
				infoPrestamoGlobal = detallesPrestamos
				$('#calculadora-body').empty()
				// Se crea la tabla y se añade al body de prestamos
				$.each(detallesPrestamos, function(i, item) {
					var $tr = $('<tr>').append(
						$('<td class="d-none">').text(i + 1),
						$('<td>').text(item.periodo),
						$('<td>').text("$ " + item.cuota.toFixed(2)),
						$('<td>').text("$ " + item.interes.toFixed(2)),
						$('<td>').text("$ " + item.amortizacion.toFixed(2)),
						$('<td>').text("$ " + item.saldo.toFixed(2)),
						$('<td>').text("$ " + item.valorIVA.toFixed(2)),
						$('<td>').text("$ " + item.cuotaAPagar.toFixed(2)),
						$('<td>').text(item.fechaVencimiento.day + "-" + item.fechaVencimiento.month + "-" + item.fechaVencimiento.year)
					).appendTo('#calculadora-body')
				})
				if ($('#calculadora') != null) {
					initDataTable()
				}
			}
		})
	}

	//Obtener prestamos mediante usuario en session
	const ListarPrestamos = () => {
		$.ajax({
			url: "ListarPrestamosSessionServlet", // URL del servlet
			type: "POST",
			cache: false, // No guardar en cache los datos retornados
			data: {},
			dataType: "json",
			success: function(data) {

				//Guardar prestamos en session
				localStorage['data'] = JSON.stringify(data)

				//Refrescar DOM
				$('#prestamos-body').empty()

				//Por cada elemento en arreglo, crear una nueva fila 
				$.each(data, function(i, item) {

					var $div = $('<div>', { id: '#pago-' + i });
					$div.html('<a href="' + i + '" class="btn btn-pagos" data-bs-toggle="modal"data-bs-target="#detallesPrestamo"><svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-cash-coin" viewBox="0 0 16 16"> <path fill-rule="evenodd" d="M11 15a4 4 0 1 0 0-8 4 4 0 0 0 0 8zm5-4a5 5 0 1 1-10 0 5 5 0 0 1 10 0z"/> <path d="M9.438 11.944c.047.596.518 1.06 1.363 1.116v.44h.375v-.443c.875-.061 1.386-.529 1.386-1.207 0-.618-.39-.936-1.09-1.1l-.296-.07v-1.2c.376.043.614.248.671.532h.658c-.047-.575-.54-1.024-1.329-1.073V8.5h-.375v.45c-.747.073-1.255.522-1.255 1.158 0 .562.378.92 1.007 1.066l.248.061v1.272c-.384-.058-.639-.27-.696-.563h-.668zm1.36-1.354c-.369-.085-.569-.26-.569-.522 0-.294.216-.514.572-.578v1.1h-.003zm.432.746c.449.104.655.272.655.569 0 .339-.257.571-.709.614v-1.195l.054.012z"/> <path d="M1 0a1 1 0 0 0-1 1v8a1 1 0 0 0 1 1h4.083c.058-.344.145-.678.258-1H3a2 2 0 0 0-2-2V3a2 2 0 0 0 2-2h10a2 2 0 0 0 2 2v3.528c.38.34.717.728 1 1.154V1a1 1 0 0 0-1-1H1z"/> <path d="M9.998 5.083 10 5a2 2 0 1 0-3.132 1.65 5.982 5.982 0 0 1 3.13-1.567z"/></svg></a>')

					var $tr = $('<tr>').append(
						$('<td>').text(item.IDPrestamo),
						$('<td>').text(item.Fecha.day + "-" +
							item.Fecha.month + "-" +
							item.Fecha.year),
						$('<td>').text(item.ImporteSolicitado),
						$('<td>').text(item.PlazoPagoMeses),
						$('<td>').text(item.CuotasRestantes),
						$('<td>').html($div)
					).appendTo('#prestamos-body')
				})
				
				if($('dt-filter-select') != null) {
					initDataTablePrestamos()
				}
			}
		})
	}



	$('#calculadora').on('click', 'tbody td', function() {
		var table = $('#calculadora').DataTable()
		cuotas = table.cell({ row: this.parentNode.rowIndex - 1, column: 0 }).data()

		// Resetear monto
		monto = 0;
		for (let i = 0; i < cuotas; i++) {
			monto += infoPrestamoGlobal[i].cuotaAPagar
			monto = Math.round(monto * 100) / 100
		}
		$('#cuotas-header').text("Pagaras " + cuotas + " cuotas por un monto de $ " + monto.toFixed(2))
	})

	$(document).on('click', '#btn-pagar', function() {
		$.ajax({
			url: "PagarPrestamoServlet", // URL del servlet
			type: "POST",
			cache: false, // No guardar en cache los datos retornados
			data: {
				monto: monto, // Aca se colocan las IDs de los campos a enviar
				idPrestamo: idPrestamo,
				cantidadCuotas: cuotas,
				cbuCuenta: $("#loan-account-select").find(":selected").text()
			},
			dataType: "json",
			success: function(data) {

				if (data == 'BAD_INPUT') {
					const toast = new bootstrap.Toast($('#badInput'))
					toast.show()
				}
				else if (data == 'CONFLICT_SALDO') {
					const toast = new bootstrap.Toast($('#conflictSaldo'))
					toast.show()
				}
				else if (data == 'OK') {
					const toast = new bootstrap.Toast($('#allOk'))
					toast.show()
					$('#btn-cerrar').click()
					ListarPrestamos()
				}
				else if (data == 'PAID') {
					const toast = new bootstrap.Toast($('#allOk'))
					toast.show()
					const toast2 = new bootstrap.Toast($('#paid'))
					toast2.show()
					$('#btn-cerrar').click()
					ListarPrestamos()
				}

			}
		})
	})


	$(document).on("click", ".btn-pagos", function() {

		var data = JSON.parse(localStorage['data'])
		var i = $(this).attr('href')

		//Se guarda el id de prestamo seleccionado para uso posterior en AJAX
		idPrestamo = data[i].IDPrestamo
		ajaxGetPrestamos(data[i])
	});

	// Resetear valores
	$(document).on("click", "#btn-cerrar, .btn-close", function() {
		$('#calculadora').DataTable().clear();
		$('#calculadora').DataTable().destroy();
		$('#cuotas-header').text("Seleccione en la tabla un rango de cuotas a pagar")
		cuotas = 0;
		monto = 0;
		idPrestamo = 0
	});

	ListarPrestamos();

	const initDataTable = () => {
		$('#calculadora').DataTable({
		});
	}
	
	const initDataTablePrestamos = () => {
		var table = $('#dt-filter-select').DataTable();

		$('#min, #max').keyup(function() {
			table.draw()
		});

		$.fn.dataTable.ext.search.push(function(settings, data, dataIndex) {
			var min = parseInt($('#min').val(), 10);
			var max = parseInt($('#max').val(), 10);
			var importe = parseFloat(data[2]) || 0;

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


