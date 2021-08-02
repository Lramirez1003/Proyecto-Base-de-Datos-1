package Logico;

import java.io.Serializable;
import java.util.ArrayList;


public class Fabrica implements Serializable{
	private static final long serialVersionUID = 1L;
	private ArrayList<Queso>misQuesos;
	private ArrayList<Factura>misFacturas;
	private ArrayList<Cliente>misClientes;
	private static Fabrica fabri = null;
	private static int codQueso = 1;
	
	public Fabrica() {
		super();
		this.misQuesos = new ArrayList<Queso>();
		this.misFacturas = new ArrayList<Factura>();
		this.misClientes = new ArrayList<Cliente>();
	}
	public static Fabrica getInstance(){
		 if(fabri == null){
			 fabri = new Fabrica();
		 }
		 
		 return fabri;
	 } 

	public ArrayList<Queso> getMisQuesos() {
		return misQuesos;
	}

	public void setMisQuesos(ArrayList<Queso> misQuesos) {
		this.misQuesos = misQuesos;
	}

	public ArrayList<Factura> getMisFacturas() {
		return misFacturas;
	}

	public void setMisFacturas(ArrayList<Factura> misFacturas) {
		this.misFacturas = misFacturas;
	}

	public ArrayList<Cliente> getMisClientes() {
		return misClientes;
	}

	public void setMisClientes(ArrayList<Cliente> misClientes) {
		this.misClientes = misClientes;
	}
	
	public int getCodQueso() {
		return codQueso;
	}
	public void aumentar() {
		codQueso += 1;
	}
	
	public Cliente buscarClienteByID(String codigo) {
		Cliente aux = null;
		boolean encontrado = false;
		int i = 0;
	
		while (i<misClientes.size() && !encontrado) {
			if(misClientes.get(i).getId().equalsIgnoreCase(codigo)){
				aux = misClientes.get(i);
				encontrado = true;
			}
			i++;
		}
		return aux;
	}
	public Queso buscarQuesoByID(String codigo) {
		Queso aux = null;
		boolean encontrado = false;
		int i = 0;
		while (i<misQuesos.size() && !encontrado) {
			if(misQuesos.get(i).getId().equalsIgnoreCase(codigo)){
				aux = misQuesos.get(i);
				encontrado = true;
			}
			i++;
		}
		return aux;
	}
	public Factura buscarFacturaByID(String codigo) {
		Factura aux = null;
		boolean encontrado = false;
		int i = 0;
		while (i<misFacturas.size() && !encontrado) {
			if(misFacturas.get(i).getId().equalsIgnoreCase(codigo)){
				aux = misFacturas.get(i);
				encontrado = true;
			}
			i++;
		}
		return aux;
	}
	
	public float totalFactura(String codigo) {
		Factura aux = buscarFacturaByID(codigo);
		if(aux != null) {
			return aux.totalFactura();
		}
		return -1;
	}
	
	public int[] totalQuesoPorTipo() {
		int[] quesos = {0,0,0};
		for (Queso aux : misQuesos) {
			if(aux instanceof Esfera) {
				quesos[0]++;
			}
			if(aux instanceof Cilindro) {
				quesos[1]++;
			}
			if(aux instanceof CilindroHueco) {
				quesos[2]++;
			}
		}
		return quesos;
	}
	
	public Esfera esferaMayorDeFactura(String codigo) {
		Factura aux = buscarFacturaByID(codigo);
		if(aux != null) {
			return aux.esferaMayor();
		}
		return null;
		
	}
	public static void setControl(Fabrica temp) {
		Fabrica.fabri = temp;

	}
	
}
