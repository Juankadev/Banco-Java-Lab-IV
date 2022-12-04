$(document).ready(function() {

	const ajaxGetCuentas = () => {
		$.ajax({
			url: "ListarCuentasServlet", // URL del servlet
			type: "POST",
			cache: false, // No guardar en cache los datos retornados
			data: {
			},
			dataType: "json",
			success: function(data) {
				var form = "";
				$.each(data, function(i, item) {
					form += "<option value=" + (i + 1) + "><b>"+ item.tipoCuenta.Descripcion + "</b> " +item.cuenta.CBU + "</option>"
				})
				$('#loan-account-select').append(form)
			}
		})
	}
	ajaxGetCuentas();

})