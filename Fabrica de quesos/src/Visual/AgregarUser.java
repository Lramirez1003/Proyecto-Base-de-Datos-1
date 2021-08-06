package Visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.*;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

import Logico.Cilindro;
import Logico.CilindroHueco;
import Logico.Conexion;
import Logico.Esfera;
import Logico.Fabrica;
import Logico.Queso;


import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.UIManager;
import java.awt.Color;
import java.awt.Toolkit;
import java.awt.SystemColor;

public class AgregarUser extends JDialog {

	private JPanel contentPane;
	private JTextField txtUsuario;
	private JTextField txtPass;
	static Conexion link = new Conexion();
	static Connection connect = link.getConexion();
	private JPasswordField passwordField;



	/**
	 * Create the dialog.
	 * @param fed 
	 */
		public AgregarUser() {
			setTitle("Agregar Usuario");
			setBounds(100, 100, 347, 255);
			contentPane = new JPanel();
			contentPane.setBackground(Color.WHITE);
			contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
			setContentPane(contentPane);
			contentPane.setLayout(null);
			setLocationRelativeTo(null);
			
			JPanel panel = new JPanel();
			panel.setBounds(10, 11, 302, 191);
			panel.setBackground(Color.WHITE);
			contentPane.add(panel);
			panel.setLayout(null);
			
			JLabel lblUser = new JLabel("Usuario:");
			lblUser.setBounds(116, 11, 65, 28);
			panel.add(lblUser);
			lblUser.setFont(new Font("Dubai Medium", Font.PLAIN, 16));
			
			txtUsuario = new JTextField();
			txtUsuario.setBounds(62, 50, 183, 20);
			panel.add(txtUsuario);
			txtUsuario.setColumns(10);
			
			JLabel lblContrasea = new JLabel("Contrase\u00F1a:");
			lblContrasea.setBounds(112, 81, 86, 28);
			panel.add(lblContrasea);
			lblContrasea.setFont(new Font("Dubai Medium", Font.PLAIN, 16));
			

			txtPass = new JPasswordField();
			txtPass.setBounds(62, 120, 183, 20);
			panel.add(txtPass);
			txtPass.setColumns(10);
			
			JButton btnLogin = new JButton("Agregar");
			btnLogin.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
					try {
					
						String usuario = txtUsuario.getText();
						String passwrd = txtPass.getText();
						

					       PreparedStatement InsertarUser = connect.prepareStatement("INSERT INTO Usuario (usuario,passwrd) VALUES (?,?)");
					        InsertarUser.setString(1,usuario);
					        InsertarUser.setString(2,passwrd);
					        InsertarUser.executeUpdate();
				        	JOptionPane.showMessageDialog(null, "Usuario agregado \n Usuario:" +usuario+" \n Contraseña:" +passwrd+" ","Notificación", JOptionPane.INFORMATION_MESSAGE);
					
							dispose();	
						}

						 catch (Exception e1) {
							JOptionPane.showMessageDialog(null, e1, "Notificación", JOptionPane.INFORMATION_MESSAGE);
					}
					
				}
			});
			
			
			btnLogin.setBounds(109, 151, 89, 23);
			panel.add(btnLogin);
			btnLogin.setBackground(SystemColor.controlHighlight);
		}

		
		
	}

