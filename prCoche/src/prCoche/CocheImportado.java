package prCoche;

public class CocheImportado extends Coche{
	private double homologacion;
	
	public CocheImportado(String nombre, double PIVA, double homologacion){
		super(nombre,PIVA);
		this.homologacion = homologacion;
		
	}
	@Override
	public double precioTotal(){
		double total = super.precioTotal() + ( super.precioTotal() * homologacion / 100);
		return total;
	}

}
