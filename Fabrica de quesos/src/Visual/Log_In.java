package Visual;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Logico.Conexion;
import Logico.Fabrica;

import java.awt.FlowLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.sql.*;


import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.Color;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.awt.event.ActionEvent;

public class Log_In extends JFrame {

	private JPanel contentPane;
	private JTextField txtUsuario;
	private JTextField txtPass;
	static Conexion link = new Conexion();
	static Connection connect = link.getConexion();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				FileInputStream fab;
				FileOutputStream fab2;
				ObjectInputStream fabRead;
				ObjectOutputStream fabWrite;
				try {
					fab = new FileInputStream ("Fabrica.dat");
					fabRead = new ObjectInputStream(fab);
					Fabrica temp = (Fabrica)fabRead.readObject();
					Fabrica.setControl(temp);
					fab.close();
					fabRead.close();
				} catch (FileNotFoundException e) {
					try {
						fab2 = new  FileOutputStream("Fabrica.dat");
						fabWrite = new ObjectOutputStream(fab2);
						fabWrite.writeObject(Fabrica.getInstance());
						fab2.close();
						fabWrite.close();
					} catch (FileNotFoundException e1) {
					} catch (IOException e1) {
						// TODO Auto-generated catch block
					}
				} catch (IOException e) {
					
					
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				try {
					Log_In frame = new Log_In();
					frame.setVisible(true);
					test_conexion();
				} catch (Exception e) {
					e.printStackTrace();
				}

			}
		});
		
	}
	
	public static  void test_conexion() {
		if (connect !=null)
		{
			System.out.println("Conectado | RegistrarQueso");
		}
		else 
			System.out.println("No conectado | RegistrarQueso");
	}

	
	public Log_In() {
		setTitle("Log-In");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 574, 324);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(235, 11, 300, 263);
		panel.setBackground(new Color(153, 204, 204));
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblIniciarSesin = new JLabel("Iniciar Sesi\u00F3n");
		lblIniciarSesin.setBounds(93, 27, 120, 28);
		panel.add(lblIniciarSesin);
		lblIniciarSesin.setFont(new Font("Dubai Medium", Font.PLAIN, 20));
		
		JLabel lblUser = new JLabel("Usuario:");
		lblUser.setBounds(121, 66, 65, 28);
		panel.add(lblUser);
		lblUser.setFont(new Font("Dubai Medium", Font.PLAIN, 16));
		
		txtUsuario = new JTextField();
		txtUsuario.setBounds(62, 105, 183, 20);
		panel.add(txtUsuario);
		txtUsuario.setColumns(10);
		
		JLabel lblContrasea = new JLabel("Contrase\u00F1a:");
		lblContrasea.setBounds(110, 136, 86, 28);
		panel.add(lblContrasea);
		lblContrasea.setFont(new Font("Dubai Medium", Font.PLAIN, 16));
		
		txtPass = new JTextField();
		txtPass.setBounds(62, 175, 183, 20);
		panel.add(txtPass);
		txtPass.setColumns(10);
		
		JButton btnLogin = new JButton("Log-in");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(txtPass.getText().equalsIgnoreCase("Admin")&& txtUsuario.getText().equalsIgnoreCase("Admin")) {
					Principal principal = new Principal();
					principal.setVisible(true);
					principal.setLocationRelativeTo(null);
					dispose();	
				}else {
					JOptionPane.showMessageDialog(null, "Usuario o contraseña incorrecto/a", "Notificación", JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});
		btnLogin.setBounds(109, 206, 89, 23);
		panel.add(btnLogin);
		btnLogin.setBackground(SystemColor.controlHighlight);
		
		JLabel lblAsociacinD = new JLabel("  Fabrica de Quesos");
		lblAsociacinD.setFont(new Font("Dubai Medium", Font.PLAIN, 22));
		lblAsociacinD.setBounds(22, 42, 191, 38);
		contentPane.add(lblAsociacinD);
		
		JLabel lblGestinDeLa = new JLabel("Gesti\u00F3n De La Fabrica");
		lblGestinDeLa.setFont(new Font("Arial", Font.PLAIN, 16));
		lblGestinDeLa.setBounds(34, 144, 167, 38);
		contentPane.add(lblGestinDeLa);
		
		JLabel lblBasketball = new JLabel("La Habana");
		lblBasketball.setFont(new Font("Dubai Medium", Font.PLAIN, 22));
		lblBasketball.setBounds(63, 81, 109, 38);
		contentPane.add(lblBasketball);
	}
}
