package pckCuenta;

/*******************************************************************************
 * Modelo: Clase cuenta para ejercicio de pruebas
 * Datos Persitentes
 * Sin colaboradores
 ******************************************************************************/

public class Cuenta {
	private double saldo;

	public Cuenta(double si) {
		saldo = si;
	}

	public void ingresar(double cantidad) {
		saldo += cantidad;
	}

	public double pagar(double cantidad) { // limitado por saldo
		if (saldo <= cantidad) 
			cantidad = saldo;
			else
				saldo = saldo - cantidad;
		
		return cantidad;
	}
	
	

	public double saldo() {
		return saldo;
	}
}
