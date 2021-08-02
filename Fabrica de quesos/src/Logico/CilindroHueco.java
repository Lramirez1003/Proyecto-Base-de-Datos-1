package Logico;

public class CilindroHueco extends Cilindro {
	private float radioInterior;
	
	public CilindroHueco(float precioBase, float precioUnitario, float radioExterior, float altura, float radioInterior, String id) {
		super(precioBase, precioUnitario, radioExterior, altura, id);
		this.radioInterior = radioInterior;	
		}
		
	public float volumen() {
		if(radioInterior > radioExterior) {
			return (float)(Math.PI*altura*(Math.pow(radioInterior, 2) - Math.pow(radioExterior, 2)));
		}
		return (float)(Math.PI*altura*(Math.pow(radioExterior, 2) - Math.pow(radioInterior, 2)));
	}

	public float getRadioInterior() {
		return radioInterior;
	}

	public void setRadioInterior(float radioInterior) {
		this.radioInterior = radioInterior;
	}
	

}
