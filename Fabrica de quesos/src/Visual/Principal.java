package Visual;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Logico.Conexion;
import Logico.Fabrica;

import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.JLabel;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.text.ParseException;
import java.awt.event.ActionEvent;
import java.awt.SystemColor;
import javax.swing.UIManager;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Canvas;
import java.sql.*;

public class Principal extends JFrame {

	private JPanel contentPane;
	private Dimension dim;
	 static Conexion link = new Conexion();
	 static Connection connect = link.getConexion();


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Principal frame = new Principal();
					frame.setVisible(true);
					
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
	/**
	 * Create the frame.
	 */
	public Principal() {
		setTitle("Fabrica de Quesos de La Habana");
		setIconImage(Toolkit.getDefaultToolkit().getImage(Principal.class.getResource("/Auxiliares/cheeseStand.png")));
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		dim = super.getToolkit().getScreenSize();
		super.setSize(dim.width, dim.height-40);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		addWindowListener(new WindowAdapter() {
			
			public void windowClosing(WindowEvent e) {
				FileOutputStream fab2;
				ObjectOutputStream fabWrite;
				try {
					fab2 = new  FileOutputStream("Fabrica.dat");
					fabWrite = new ObjectOutputStream(fab2);
					fabWrite.writeObject(Fabrica.getInstance());
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(5, 5, 1264, 22);
		menuBar.setFont(new Font("Segoe UI", Font.PLAIN, 23));
		menuBar.setForeground(Color.WHITE);
		menuBar.setBackground(Color.LIGHT_GRAY);
		contentPane.add(menuBar);
		
		JMenu mnClientes = new JMenu("Clientes");
		mnClientes.setIcon(new ImageIcon(Principal.class.getResource("/Auxiliares/avatar.png")));
		menuBar.add(mnClientes);
		
		JMenuItem mntmListar = new JMenuItem("Listar");
		mntmListar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ListClientes list = new ListClientes();
				list.setModal(true);
				list.setLocationRelativeTo(null);
				list.setVisible(true);
			}
		});
		mnClientes.add(mntmListar);
		
		JMenu mnQuesos = new JMenu("Quesos");
		mnQuesos.setIcon(new ImageIcon(Principal.class.getResource("/Auxiliares/cheese.png")));
		menuBar.add(mnQuesos);
		
		JMenuItem mntmAadirQueso = new JMenuItem("A\u00F1adir");
		mntmAadirQueso.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RegistrarQueso reg = new RegistrarQueso();
				reg.setModal(true);
				reg.setLocationRelativeTo(null);
				reg.setVisible(true);
			}
		});
		mnQuesos.add(mntmAadirQueso);
		
		JMenuItem mntmListar_1 = new JMenuItem("Listar");
		mntmListar_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ListarQuesos list = new ListarQuesos();
				list.setModal(true);
				list.setLocationRelativeTo(null);
				list.setVisible(true);
			}
		});
		mnQuesos.add(mntmListar_1);
		
		JMenu mnFacturas = new JMenu("Facturas");
		mnFacturas.setIcon(new ImageIcon(Principal.class.getResource("/Auxiliares/bill.png")));
		mnFacturas.setBackground(Color.WHITE);
		menuBar.add(mnFacturas);
		
		JMenuItem mntmFacturar = new JMenuItem("Facturar");
		mntmFacturar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Facturar fact;
				try {
					fact = new Facturar();
					fact.setLocationRelativeTo(null);
					fact.setModal(true);
					fact.setVisible(true);
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			
			}
		});
		mnFacturas.add(mntmFacturar);
		
		JMenuItem mntmReportes = new JMenuItem("Reportes");
		mntmReportes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ListarReportes rep = new ListarReportes();
				rep.setModal(true);
				rep.setLocationRelativeTo(null);
				rep.setVisible(true);
			}
		});
		mnFacturas.add(mntmReportes);
		
		JMenuItem mntmListar_2 = new JMenuItem("Listar");
		mntmListar_2.setEnabled(false);
		mntmListar_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ListFacturas list = new ListFacturas();
				list.setLocationRelativeTo(null);
				list.setModal(true);
				list.setVisible(true);
			}
		});
		mnFacturas.add(mntmListar_2);
		
		JMenu mnAdministrar = new JMenu("Administrar");
		menuBar.add(mnAdministrar);
		
		JMenuItem mntmInsertUser = new JMenuItem("A\u00F1adir usuario");
		mntmInsertUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				AgregarUser adduser = new AgregarUser();
				adduser.setLocationRelativeTo(null);
				adduser.setModal(true);
				adduser.setVisible(true);
			}
		});
		mnAdministrar.add(mntmInsertUser);
		ImageIcon imagen = new ImageIcon("src/Auxiliares/Sin título-1.png");
		
		
		
	}
}
