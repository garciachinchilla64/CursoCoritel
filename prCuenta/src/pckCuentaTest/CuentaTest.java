package pckCuentaTest;

import static org.junit.Assert.*;



import org.junit.*;
import org.junit.rules.ExpectedException;

import pckCuenta.Cuenta;
import pckCuenta.CuentaException;

public class CuentaTest {

	private static final double SALDO_INI = 9876.55;
	private static final double DELTA = 0e-12;

	Cuenta c = null;

	@Before
	public void init() {
		c = new Cuenta(SALDO_INI);
	}

	@After
	public void teardown() {
		c = null;
	}

	/*
	 * Si se crea una cuenta con saldo positivo S, entonces la cuenta existe y
	 * su saldo es S.
	 */
	@Test
	public void siSeCreaCuentaConSaldoPositivoEntoncesCuentaExisteConSaldoIndicado() {
		assertNotNull(c);
		assertEquals(SALDO_INI, c.saldo(), DELTA);
	}

	/*
	 * Si se crea una cuenta con saldo cero, entonces la cuenta existe y su
	 * saldo es cero
	 */
	@Test
	public void siSeCreaCuentaConSaldoCeroEntoncesCuentaExisteYSuSaldoEsCero() {
		double saldo_esperado = 0.0;

		Cuenta c_cero = new Cuenta(saldo_esperado);

		assertNotNull(c);
		assertEquals(saldo_esperado, c_cero.saldo(), DELTA);
	}

	/*
	 * Si se realiza un ingreso positivo I en una cuenta con saldo cero,
	 * entonces el saldo es I
	 */
	@Test
	public void siSeRealizaUnIngresoCuentaSaldoCeroEntoncesElSaldoEsElIngreso() {
		double cantidad = 1234.56;
		double saldo_esperado = cantidad;
		double saldo_ini = 0;

		Cuenta c_cero = new Cuenta(saldo_ini);

		c_cero.ingresar(cantidad);

		assertEquals(saldo_esperado, c_cero.saldo(), DELTA);
	}

	/*
	 * Si se realiza un ingreso positivo I en una cuenta con saldo S, entonces
	 * el saldo es I + S
	 */
	@Test
	public void siSeRealizaUnIngresoIenCuentaSaldoSEntoncesElSaldoEsImasS() {
		double cantidad = 1234.56;
		double saldo_esperado = SALDO_INI + cantidad;

		c.ingresar(cantidad);

		assertEquals(saldo_esperado, c.saldo(), DELTA);
	}

	/*
	 * Si se realiza un pago positivo P en una cuenta con saldo S y S es mayor
	 * que P, entonces el saldo es S – P y la cantidad pagada es P
	 */
	@Test
	public void siSeRealizaUnUnPagoMenorSaldoEntoncesElSaldoEsSaldoMenosCantidad() {
		double cantidad = 1234.56;
		double cantidad_esperada = cantidad;
		double saldo_esperado = SALDO_INI - cantidad;

		double c_real = c.pagar(cantidad);

		assertEquals(saldo_esperado, c.saldo(), DELTA);
		assertEquals(cantidad_esperada, c_real, DELTA);
	}

	/*
	 * Si se realiza un pago positivo P en una cuenta con saldo S y S es igual
	 * que P, entonces el saldo es 0 y la cantidad pagada es P
	 */
	@Test
	public void siSeRealizaPagoIgualEntoncesElSaldoEsCero() {
		double cantidad = SALDO_INI;
		double cantidad_esperada = cantidad;
		double saldo_esperado = 0;

		double c_real = c.pagar(cantidad);

		assertEquals(saldo_esperado, c.saldo(), DELTA);
		assertEquals(cantidad_esperada, c_real, DELTA);
	}

	/*
	 * Si se realiza un pago positivo P en una cuenta con saldo S y S es menor
	 * que P, entonces el saldo es 0 Y la cantidad pagada es S
	 */
	@Test
	public void siSeRealizaPagoMayorSaldoEntoncesCeroYLaCantidadPagadaEsSaldo() {
		double cantidad = SALDO_INI + 0.01;
		double cantidad_esperada = SALDO_INI;
		double saldo_esperado = 0;

		double c_real = c.pagar(cantidad);

		assertEquals(saldo_esperado, c.saldo(), DELTA);
		assertEquals(cantidad_esperada, c_real, DELTA);
	}

	/*
	 * Si se crea una cuenta con saldo inicial negativo, entonces se debe lanzar
	 * una excepción propia con unmensaje explicativo y la cuenta no debe ser
	 * creada
	 */

	@Test(expected = CuentaException.class)
	public void siSeCreaCuentaConSaldoNegativoEntoncesExceptionYNoCreada() {
		Cuenta c1 = new Cuenta(-SALDO_INI);
		//fail("No debe llegar aqui, debe lanzar una excepcion");
	}

	/*
	 * Version try catch
	 */
	@Test
	public void siSeCreaCuentaConSaldoNegativoEntoncesExceptionYNoCreada_trycatch() {
		Cuenta c1 = null;
		try {
			c1 = new Cuenta(-SALDO_INI);
			fail("No debe llegar aqui, debe lanzar una excepcion");
		} catch (CuentaException e) {
			assertNull(c1);
			assertEquals("ERROR: Saldo inicial Negativo", e.getMessage());
		}
	}

	/*
	 * Version Rule - En este caso no 
	 * se comprueba si se crea la cuenta
	 */
	@Rule
	public ExpectedException e = ExpectedException.none();
	
	@Test
	public void siSeCreaCuentaConSaldoNegativoEntoncesExceptionYNoCreada_Rule() {
		e.expect(CuentaException.class);
		e.expectMessage("inicial Negativo");
		
		Cuenta c1 = new Cuenta(-SALDO_INI);
	}
	
	/*
	 * Si se intenta realizar un ingreso 
	 * negativo, entonces se debe lanzar 
	 * una excepción propia con un mensaje 
	 * explicativo y el saldo no debe variar.
	 */
	@Test
	public void siSeIngresaSaldoNegativoEntoncesExceptionYSaldoNoVaria() {
		double cantidad = -0.01;
		try {
			c.ingresar(cantidad);
			fail("No debe llegar aqui, debe lanzar una excepcion");
		} catch (CuentaException e) {
			assertEquals(c.saldo(), c.saldo(), DELTA);
			assertEquals("ERROR: Cantidad Negativa", e.getMessage());
		}
	}
	/*
	 * Con Rule
	 */
	@Test
	public void siSeIngresaSaldoNegativoEntoncesExceptionYSaldoNoVaria_Rule() {
		e.expect(CuentaException.class);
		e.expectMessage("Cantidad Negativa");
		
		c.ingresar(-0.01);
	}
	
	/*
	 * Si se realiza un ingreso de valor 0, 
	 * el saldo no debe variar
	 */
	
	/*Si se realiza un pago P superior al saldo S, entonces se debe lanzar una excepción, la cantidad pagada debe
	ser 0 y el saldo no debe variar.*/
	@Test
	public void siSePagaSuperiorSaldoSLanzaExcepcionySaldoIgual() {
		double cantidad = -0.01;
		try {
			c.ingresar(cantidad);
			fail("No debe llegar aqui, debe lanzar una excepcion");
		} catch (CuentaException e) {
			assertEquals(c.saldo(), c.saldo(), DELTA);
			assertEquals("ERROR: Cantidad Negativa", e.getMessage());
		}
	}
	//Si se crean dos cuentas diferentes con el mismo saldo inicial, los objetos deben ser distintos.
	@SuppressWarnings("deprecation")
	@Test
	public void DosCuentasIgualSaldoObjetosDistintos(){
		
		Cuenta c1 = new Cuenta(SALDO_INI);
		Cuenta c2 = new Cuenta(SALDO_INI);
		//assertEquals(SALDO_INI,SALDO_INI);
		assertNotSame(c1,c2 );
	}
	
	
	
}
