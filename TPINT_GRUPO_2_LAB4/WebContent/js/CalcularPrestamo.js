$(document).ready(function() {

	const ajaxGetPrestamos = () => {
		$.ajax({
			url: "CalcularPrestamoServlet", // URL del servlet
			type: "POST",
			cache: false, // No guardar en cache los datos retornados
			data: {
				montoPrestamo: $('#floatingInput').val(), // Aca se colocan las IDs de los campos a enviar
				plazo: $('#loan-term-select').val(),
			},
			dataType: "json",
			success: function(data) {
				// Obtener tasas
				ajaxGetTasas(data)
				var cuotaPura = document.getElementById("pure-installment")
				cuotaPura.innerHTML = data[0].cuota.toFixed(2);

				var tabla = document.getElementById("prestamos-body")
				tabla.innerHTML = ""
				// Se crea la tabla y se añade al body de prestamos
				$.each(data, function(i, item) {
					var $tr = $('<tr>').append(
						$('<td>').text(item.periodo),
						$('<td>').text("$ " + item.cuota.toFixed(2)),
						$('<td>').text("$ " + item.interes.toFixed(2)),
						$('<td>').text("$ " + item.amortizacion.toFixed(2)),
						$('<td>').text("$ " + item.saldo.toFixed(2)),
						$('<td>').text("$ " + item.valorIVA.toFixed(2)),
						$('<td>').text("$ " + item.cuotaAPagar.toFixed(2)),
						$('<td>').text(item.fechaVencimiento.day + "-" + item.fechaVencimiento.month + "-" + item.fechaVencimiento.year)
					).appendTo('#prestamos-body')
				});
			}
		})
	}

	const ajaxGetTasas = (more_data) => {
		// Se serializa el objeto en un string
		var json = JSON.stringify(more_data)
		$.ajax({
			url: "CalcularTasasPrestamosServlet",
			type: "POST",
			cache: false,
			data: { prestamos: json }, // Se envia el string al servlet
			dataType: "json",
			success: function(data) {
				// Se modifica el DOM con los valores calculados
				$("#tna").html((data.tna * 100).toFixed(2))
				$("#tem").html((data.tem * 100).toFixed(2))
				$("#tea").html((data.tea * 100).toFixed(2))
				$("#cftsiva").html((data.tea * 100).toFixed(2))
				$("#cftciva").html((data.cfttea * 100).toFixed(2))
			}
		})
	}
	
	const setMensajeDineroModal = () => {
		$("#monto").html($('#floatingInput').val())
	}

	// Realizar calculos cuando se cambie de periodo
	$('#loan-term-select').on('change', function() {
		ajaxGetPrestamos()
	});

	// Realizar calculos cuando se cambie el monto del prestamo
	$('#floatingInput').on('input', function() {
		ajaxGetPrestamos()
		setMensajeDineroModal()
	});
	
	ajaxGetPrestamos();
	
	setMensajeDineroModal()
})