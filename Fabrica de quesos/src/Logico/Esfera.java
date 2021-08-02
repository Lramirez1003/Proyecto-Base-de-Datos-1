package Logico;

public class Esfera extends Queso{
	private float radio;

	public Esfera(float precioBase, float precioUnitario,float radio, String id) {
		super(precioBase, precioUnitario, id);
		this.radio = radio;
	}

	public float volumen() {
		return (float)((4*Math.PI*Math.pow(radio, 3)/3));
	}

	public float getRadio() {
		return radio;
	}

	public void setRadio(float radio) {
		this.radio = radio;
	}
	
	
	
}
