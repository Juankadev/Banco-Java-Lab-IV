//Deprecado

const getNper = () => {
	return Number(document.getElementById("loan-term-select").value)
}

const getVp = () => {
	return Number(document.getElementById("floatingInput").value)
}

const getTNA = () => {
	return document.getElementById("tna").textContent
}

const calcularCuotaPura = (tem, nper, pv) => {
	return PMT(tem, nper, pv, 0, 0) *-1
}

const calcularTEA = (TIR) => {
	return ((Math.pow(1 + TIR, 365/30) -1)*100)
}


const calculateTablaPrestamos = () => {
	var tem = getTNA() / 1200
	var nper = getNper();
	var vp = getVp();
	var cuotaPura = calcularCuotaPura(tem, nper, vp);
	// Cuotas
	var cuotas = []
	// Intereses
	var intereses = []
	// Amortizaciones
	var amortizaciones = []
	// Saldos
	var saldos = []
	// IVA
	var valoresIVA = []
	// Cuota a pagar
	var cuotasAPagar = []
	
	// Crear tabla
	saldos.push(vp)
	var headers = ["Periodo", "Cuota", "Interes", "Amortizacion", "Saldo", "IVA", "Cuota a pagar"]
	var tabla = document.getElementById("tabla-prestamos")
	tabla.innerHTML = ""
	for (var i = 0; i < getNper(); i++) {
		// Calcular valores de tabla
		cuotas.push(cuotaPura)
		intereses.push(saldos[i] * tem)
		amortizaciones.push(cuotaPura - intereses[i])
		saldos[i + 1] =  saldos[i] - amortizaciones[i]
		valoresIVA.push(intereses[i] * 0.21)
		cuotasAPagar.push(cuotaPura + valoresIVA[i])
		
		//Poblar tabla
		var row = tabla.insertRow(i)
		row.insertCell(0).innerHTML = "Mes " + (i + 1)
		row.insertCell(1).innerHTML = cuotas[i].toFixed(2)
		row.insertCell(2).innerHTML = intereses[i].toFixed(2)
		row.insertCell(3).innerHTML = amortizaciones[i].toFixed(2)
		row.insertCell(4).innerHTML = saldos[i + 1].toFixed(2)
		row.insertCell(5).innerHTML = valoresIVA[i].toFixed(2)
		row.insertCell(6).innerHTML = cuotasAPagar[i].toFixed(2)
	}
	
	cuotasAPagar.unshift(vp * -1)
	cuotas.unshift(vp * -1)
	
	var TIRCIVA = IRR(cuotasAPagar)
	var TIRSIVA = IRR (cuotas)
	
	cuotasAPagar.shift()
	cuotas.shift()
	saldos.shift()
	
	// Modificar DOM con valores calculados
	document.getElementById("pure-installment").innerHTML = cuotaPura.toFixed(2)
	document.getElementById("tem").innerHTML = (tem*100).toFixed(2)
	document.getElementById("tea").innerHTML = calcularTEA(TIRSIVA).toFixed(2)
	document.getElementById("cftsiva").innerHTML = calcularTEA(TIRSIVA).toFixed(2)
	document.getElementById("cftciva").innerHTML = calcularTEA(TIRCIVA).toFixed(2)
	
	var header = tabla.createTHead()
	var headerRow = header.insertRow(0)
	
	for(var i = 0; i < headers.length; i++) {
		headerRow.insertCell(i).innerHTML = headers[i]
	}
}

const PMT = (rate, nper, pv, fv, type) => {
	/*
	 * rate   - interest rate per month
	 * nper   - number of periods (months)
	 * pv   - present value
	 * fv   - future value
	 * type - when the payments are due:
	 *        0: end of the period, e.g. end of month (default)
	 *        1: beginning of period
	 */
	let pmt, pvif;

	fv || (fv = 0);
	type || (type = 0);

	if (rate === 0)
		return -(pv + fv) / nper;

	pvif = Math.pow(1 + rate, nper);
	pmt = - rate * (pv * pvif + fv) / (pvif - 1);

	if (type === 1)
		pmt /= (1 + rate);
	return pmt;
}

function IRR(values, guess) {
	// Credits: algorithm inspired by Apache OpenOffice

	// Calculates the resulting amount
	var irrResult = function(values, dates, rate) {
		var r = rate + 1;
		var result = values[0];
		for (var i = 1; i < values.length; i++) {
			result += values[i] / Math.pow(r, (dates[i] - dates[0]) / 365);
		}
		return result;
	}

	// Calculates the first derivation
	var irrResultDeriv = function(values, dates, rate) {
		var r = rate + 1;
		var result = 0;
		for (var i = 1; i < values.length; i++) {
			var frac = (dates[i] - dates[0]) / 365;
			result -= frac * values[i] / Math.pow(r, frac + 1);
		}
		return result;
	}

	// Initialize dates and check that values contains at least one positive value and one negative value
	var dates = [];
	var positive = false;
	var negative = false;
	for (var i = 0; i < values.length; i++) {
		dates[i] = (i === 0) ? 0 : dates[i - 1] + 365;
		if (values[i] > 0) positive = true;
		if (values[i] < 0) negative = true;
	}

	// Return error if values does not contain at least one positive value and one negative value
	if (!positive || !negative) return '#NUM!';

	// Initialize guess and resultRate
	var guess = (typeof guess === 'undefined') ? 0.1 : guess;
	var resultRate = guess;
	
	// Set maximum epsilon for end of iteration
	var epsMax = 1e-10;

	// Set maximum number of iterations
	var iterMax = 50;

	// Implement Newton's method
	var newRate, epsRate, resultValue;
	var iteration = 0;
	var contLoop = true;
	do {
		resultValue = irrResult(values, dates, resultRate);
		newRate = resultRate - resultValue / irrResultDeriv(values, dates, resultRate);
		epsRate = Math.abs(newRate - resultRate);
		resultRate = newRate;
		contLoop = (epsRate > epsMax) && (Math.abs(resultValue) > epsMax);
	} while (contLoop && (++iteration < iterMax));

	if (contLoop) return '#NUM!';

	// Return internal rate of return
	return resultRate;
}