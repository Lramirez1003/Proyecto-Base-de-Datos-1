package Logico;

import java.io.*;
import java.net.*;
import Logico.Fabrica;

public class Servidor {
	
	public static void main(String[] args) throws IOException {
		ServerSocket servidor = new ServerSocket(8080); 
		Socket sr = servidor.accept(); 
		FileInputStream fr = new FileInputStream("C:/Users/Luis/Desktop/Workspace/Tarea#3_FabricaDeQueso/Factura-"+(Fabrica.getInstance().getMisFacturas().size()+1) +".txt");
		byte b[] =new byte[2002];
		fr.read(b,0,b.length);
		OutputStream os = sr.getOutputStream();
		os.write(b,0,b.length);
		fr.close();
		
	}

}
