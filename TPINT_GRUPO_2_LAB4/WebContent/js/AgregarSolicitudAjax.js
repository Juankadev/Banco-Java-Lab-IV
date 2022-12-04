$(document).ready(function() {
	
	const toastTrigger = document.getElementById('liveToastBtn')
	const toastLiveExample = document.getElementById('liveToast')
	if (toastTrigger) {
		toastTrigger.addEventListener('click', () => {
			const toast = new bootstrap.Toast(toastLiveExample)

			toast.show()
		})
	}

	$("#enviarSolicitud").on("click", () => {
		$.ajax({
			url: "AgregarSolicitudPrestamoServlet", // URL del servlet
			type: "POST",
			cache: false, // No guardar en cache los datos retornados
			data: {
				montoPrestamo: $('#floatingInput').val(), // Aca se colocan las IDs de los campos a enviar
				plazo: $('#loan-term-select').val(),
				cbuCuenta: $("#loan-account-select").find(":selected").text()
			},
			dataType: "json",
			success: function(data) {
				if (data == "OK") {
					const toast = new bootstrap.Toast($('#allOk'))
					toast.show()
				}
				else if (data == "BAD_INPUT") {
					const toast = new bootstrap.Toast($('#badInput'))
					toast.show()
				}
				else if (data == "BAD_INPUT_FORMAT") {
					const toast = new bootstrap.Toast($('#badInputFormat'))
					toast.show()
				}
			},
		})
	})
})