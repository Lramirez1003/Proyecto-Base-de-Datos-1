package Logico;

import java.io.*;
import java.net.*;

import Logico.Fabrica;

public class ClienteSocket {

	public static void main(String[] args) throws Exception {
		byte b[] =new byte[2002];
		Socket sr= new Socket("Localhost",8080);
		InputStream is = sr.getInputStream();
		FileOutputStream fr = new FileOutputStream("C:/Users/luis_/Desktop/Fabrica de queso/FacturasGuardadas/Factura-"+(Fabrica.getInstance().getMisFacturas().size()+1) +".txt");
		is.read(b,0,b.length);
		fr.write(b, 0, b.length);
	}
}
