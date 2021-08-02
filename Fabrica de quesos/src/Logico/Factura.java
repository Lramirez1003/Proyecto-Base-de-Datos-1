package Logico;

import java.io.Serializable;
import java.util.ArrayList;

public class Factura implements Serializable {
	private static final long serialVersionUID = 1L;
	private ArrayList<Queso>misQuesos;
	private Cliente miCliente;
	private String id;
	
	public Factura(Cliente miCliente, String id, ArrayList<Queso> misqueso) {
		super();
		this.misQuesos = misqueso;
		this.miCliente = miCliente;
		this.id = id;
	}
	public ArrayList<Queso> getMisQuesos() {
		return misQuesos;
	}
	public void setMisQuesos(ArrayList<Queso> misQuesos) {
		this.misQuesos = misQuesos;
	}
	public Cliente getMiCliente() {
		return miCliente;
	}
	public void setMiCliente(Cliente miCliente) {
		this.miCliente = miCliente;
	}
	public String getId() {
		return id;
	}
	
	public float totalFactura() {
		float total = 0;
		for (Queso queso : misQuesos) {
			total += queso.PrecioReal();
		}
		return total;
	}
	
	public Esfera esferaMayor() {
		Esfera aux = new Esfera(0, 0, 0,null); 
		for (Queso queso : misQuesos) {
			if(queso instanceof Esfera) {
				if(queso.volumen() > aux.volumen()) {
					aux = (Esfera)queso;
				}
			}
		}
		return aux;
	}
	
}
