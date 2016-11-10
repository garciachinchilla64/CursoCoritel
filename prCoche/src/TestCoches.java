import prCoche.Coche;
import prCoche.CocheImportado;

public class TestCoches {
	static Coche[] cs = { 
			new Coche("Seat Panda", 15000),
			new CocheImportado("Ferrari T-R", 65000, 8000),
			new Coche("Seat Toledo", 21000),
			new CocheImportado("Jaguar XK", 41000, 6000),
			new CocheImportado("Porche GT3", 44000, 7000) };

	static public void main(String[] args) {
		for (Coche c : cs) {
			
			System.out.println(c.toString(c));
		}

		Coche.setPiva(18.0);
		System.out.println("Con IVA de 18%");

		for (Coche c : cs) {
			
			System.out.println(c.toString(c));
		}
	}
}

/*Salida
Seat Panda -> 17400.0
Ferrari T-R -> 83400.0
Seat Toledo -> 24360.0
Jaguar XK -> 53560.0
Porche GT3 -> 58040.0
Con IVA de 18%
Seat Panda -> 17700.0
Ferrari T-R -> 84700.0
Seat Toledo -> 24780.0
Jaguar XK -> 54380.0
	 Porche GT3 -> 58920.0
*/