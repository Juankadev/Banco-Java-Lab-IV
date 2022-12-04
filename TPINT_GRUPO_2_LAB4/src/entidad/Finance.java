package entidad;

import java.time.LocalDate;
import java.util.ArrayList;;

public final class Finance {
	public static ArrayList<DetallesPrestamo> calcularArrayList(ArrayList<DetallesPrestamo> dp, double vp, int nper,
			double tem, double cuotaPura, LocalDate dt) {
		ArrayList<Double> cuotas = new ArrayList<>();
		ArrayList<Double> intereses = new ArrayList<>();
		ArrayList<Double> amortizaciones = new ArrayList<>();
		ArrayList<Double> saldos = new ArrayList<>();
		ArrayList<Double> valoresIVA = new ArrayList<>();
		ArrayList<Double> cuotasAPagar = new ArrayList<>();
		
		saldos.add(vp);

		for (int i = 0; i < nper; i++) {
			cuotas.add(cuotaPura);
			intereses.add(saldos.get(i) * tem);
			amortizaciones.add(cuotaPura - intereses.get(i));
			saldos.add(i + 1, saldos.get(i) - amortizaciones.get(i));
			valoresIVA.add(intereses.get(i) * 0.21);
			cuotasAPagar.add(cuotaPura + valoresIVA.get(i));

			dp.add(new DetallesPrestamo(i + 1, // Periodo
					cuotas.get(i), // Cuota
					intereses.get(i), // Interes
					amortizaciones.get(i), // Amortizacion
					saldos.get(i), // Saldo
					valoresIVA.get(i), // valorIVA
					cuotasAPagar.get(i), // Cuotas a pagar
					dt.plusMonths(i + 1)
			));
		}
		return dp;
	}

	public static double calculateTEA(double tir) {
		return Math.pow(1 + tir, 365.0 / 30) - 1;
	}

	public static double PMT(double rate, int nper, double pv, double fv, boolean type) {
		double pmt, pvif;

		if (rate == 0)
			return -(pv + fv) / nper;

		pvif = Math.pow(1 + rate, nper);
		pmt = -rate * (pv * pvif + fv) / (pvif - 1);

		if (type == true)
			pmt /= (1 + rate);
		return pmt;
	}

	static public double IRR(final double[] cashFlows, final double estimatedResult) {
		double result = Double.NaN;

		if (cashFlows != null && cashFlows.length > 0) {
			// check if business startup costs is not zero:
			if (cashFlows[0] != 0.0) {
				final double noOfCashFlows = cashFlows.length;

				double sumCashFlows = 0.0;
				// check if at least 1 positive and 1 negative cash flow exists:
				int noOfNegativeCashFlows = 0;
				int noOfPositiveCashFlows = 0;
				for (int i = 0; i < noOfCashFlows; i++) {
					sumCashFlows += cashFlows[i];
					if (cashFlows[i] > 0) {
						noOfPositiveCashFlows++;
					} else if (cashFlows[i] < 0) {
						noOfNegativeCashFlows++;
					}
				}

				if (noOfNegativeCashFlows > 0 && noOfPositiveCashFlows > 0) { // at least 1 negative and 1 positive cash
																				// flow available?
					// set estimated result:
					double irrGuess = 0.1; // default: 10%
					if (estimatedResult != Double.NaN) {
						irrGuess = estimatedResult;
						if (irrGuess <= 0.0)
							irrGuess = 0.5;
					}

					// initialize first IRR with estimated result:
					double irr = 0.0;
					if (sumCashFlows < 0) { // sum of cash flows negative?
						irr = -irrGuess;
					} else { // sum of cash flows not negative
						irr = irrGuess;
					}

					// iteration:
					final double minDistance = .0000001; // the smaller the distance, the smaller the interpolation
															// error
					final double cashFlowStart = cashFlows[0]; // business startup costs
					final int maxIteration = 50;
					boolean wasHi = false;
					double cashValue = 0.0; // cash value (Kapitalwert)
					for (int i = 0; i <= maxIteration; i++) { // for each iteration
						// calculate cash value with current irr:
						cashValue = cashFlowStart; // init with startup costs
						for (int j = 1; j < noOfCashFlows; j++) { // for each cash floe
							cashValue += cashFlows[j] / Math.pow(1.0 + irr, j);
						} // next cash flow

						if (Math.abs(cashValue) < 0.01) { // cash value is nearly zero
							result = irr;
							break;
						}

						// adjust irr for next iteration:
						if (cashValue > 0.0) { // cash value > 0 => next irr > current irr
							if (wasHi) {
								irrGuess /= 2;
							}

							irr += irrGuess;

							if (wasHi) {
								irrGuess -= minDistance;
								wasHi = false;
							}
						} else { // cash value < 0 => next irr < current irr
							irrGuess /= 2;
							irr -= irrGuess;
							wasHi = true;
						}

						if (irrGuess <= minDistance) { // estimated result too small to continue => end calculation
							result = irr;
							break;
						}
					} // next iteration
				} // else: noOfNegativeCashFlows == 0 || noOfPositiveCashFlows == 0
			} // else: first cash flow is 0
		} // else: cashFlows unavailable

		return result;
	}
}