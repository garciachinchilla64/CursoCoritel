package prCoche;

public class Coche {
	private String nombre;
	private double precio;
	private static double PIVA = 16.0;
	
	public Coche(String nombre, double precio){
		this.nombre = nombre;
		this.precio = precio;
	}
	public static void setPiva(double iva){
		PIVA = iva;
	}
	public String getNombre(Coche a){
		return a.nombre;
	}
	public double precioTotal(){
		double total = precio + (precio*PIVA/100);
		return total;
	}
	public String toString(Coche a){
		a = new Coche(nombre,precio);
		String coche = getNombre(a) + " -> " + precioTotal();
		
		return coche;
	}
}
