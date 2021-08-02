package Logico;

import java.io.Serializable;

public class Cilindro extends Queso implements Serializable{
	private static final long serialVersionUID = 1L;
	protected float radioExterior;
	protected float altura;
	public Cilindro(float precioBase, float precioUnitario,float radioExterior, float altura, String id) {
		super(precioBase, precioUnitario, id);
		this.radioExterior  = radioExterior;
		this.altura = altura;
	}
	public float volumen() {
		return (float)(Math.PI*Math.pow(radioExterior, 2)*altura);
	}
	public float getRadioExterior() {
		return radioExterior;
	}
	public void setRadioExterior(float radioExterior) {
		this.radioExterior = radioExterior;
	}
	public float getAltura() {
		return altura;
	}
	public void setAltura(float altura) {
		this.altura = altura;
	}
	
	

	
	
}
