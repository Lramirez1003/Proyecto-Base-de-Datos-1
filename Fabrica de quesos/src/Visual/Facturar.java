package Visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.MaskFormatter;

import Logico.Cilindro;
import Logico.CilindroHueco;
import Logico.Cliente;
import Logico.Conexion;
import Logico.Esfera;
import Logico.Fabrica;
import Logico.Factura;
import Logico.Queso;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import java.awt.Color;
import javax.swing.JFormattedTextField;
import java.awt.Toolkit;
import javax.swing.UIManager;
import java.awt.SystemColor;
import javax.swing.JList;
import javax.swing.JOptionPane;

import java.sql.*;

import java.awt.List;

public class Facturar extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtid;
	private JTextField txtNombre;
	private JTextField txtTelefono;
	private JTextField txtDireccion;
	private static DefaultListModel<String> model1;
	private static DefaultListModel<String> model2;
	private JTextField txttotal;
	private ArrayList desc= new ArrayList<>();
	private static GregorianCalendar gg = new GregorianCalendar();
	private static SimpleDateFormat dd = new SimpleDateFormat("dd/MM/YYYY");
	private static SimpleDateFormat ddd = new SimpleDateFormat("HH:mm");
	
	 static Conexion link = new Conexion();
	 static Connection connect = link.getConexion();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			Facturar dialog = new Facturar();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
		model1 = new DefaultListModel<String>();
		model2 = new DefaultListModel<String>();
		test_conexion();
		//System.out.println(FacturaID());
		
	}
	
	public static  void test_conexion() {
		if (connect !=null)
		{
			System.out.println("Conectado | Facturar");
		}
		else 
			System.out.println("No conectado | Facturar");
	}

	/**
	 * Create the dialog.
	 * @throws ParseException 
	 */
	public Facturar() throws ParseException {
		setTitle("Facturar");
		setIconImage(Toolkit.getDefaultToolkit().getImage(Facturar.class.getResource("/Auxiliares/receipt.png")));
		setBounds(100, 100, 546, 407);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Buscar Cliente", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel.setBounds(4, 2, 521, 123);
		contentPanel.add(panel);
		panel.setLayout(null);
		
		JLabel lblCodigo = new JLabel("Codigo:");
		lblCodigo.setBounds(12, 20, 46, 14);
		panel.add(lblCodigo);
		
		txtid = new JTextField();
		txtid.setToolTipText("C-#");
		txtid.setBounds(75, 17, 129, 20);
		panel.add(txtid);
		txtid.setColumns(10);
		
		JButton btnNewButton = new JButton("");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String codigo = txtid.getText();
				String codsql = "Select Nombre,Direccion,Telefono from Cliente where Cliente_id ='"+codigo+"'";

				try {
					Statement read = connect.createStatement();
					ResultSet resultado = read.executeQuery(codsql);
					
					if(resultado.next() ) {
						txtNombre.setText(resultado.getString(1)); 
						txtNombre.setEditable(false);
						txtDireccion.setText(resultado.getString(2));
						txtDireccion.setEditable(false);
						txtTelefono.setText(resultado.getString(3));
						txtTelefono.setEditable(false);
						JOptionPane.showMessageDialog(null, "Cliente existente", "Notificaci�n", JOptionPane.INFORMATION_MESSAGE);
					}
					else {
						txtNombre.setEnabled(true);
						txtDireccion.setEnabled(true);
						txtNombre.setText("");
						txtDireccion.setText("");
						JOptionPane.showMessageDialog(null, "Cliente no existe.\n Por favor ingrese los datos", "Notificaci�n", JOptionPane.INFORMATION_MESSAGE);
					
					}
			} catch (Exception e) {
				//todo: handle exception
			}

		}});
		btnNewButton.setForeground(Color.WHITE);
		btnNewButton.setBackground(Color.LIGHT_GRAY);
		btnNewButton.setIcon(new ImageIcon(Facturar.class.getResource("/Auxiliares/magnifying-glass.png")));
		btnNewButton.setBounds(214, 16, 46, 23);
		panel.add(btnNewButton);
		
		JLabel lblNombre = new JLabel("Nombre:");
		lblNombre.setBounds(12, 54, 56, 14);
		panel.add(lblNombre);
		
		txtNombre = new JTextField();
		txtNombre.setEnabled(false);
		txtNombre.setBounds(75, 51, 185, 20);
		panel.add(txtNombre);
		txtNombre.setColumns(10);
		
		JLabel lblTelfono = new JLabel("Tel\u00E9fono:");
		lblTelfono.setBounds(270, 54, 67, 14);
		panel.add(lblTelfono);
			
		txtTelefono= new JTextField();
		txtTelefono.setBounds(324, 51, 191, 20);
		panel.add(txtTelefono);
		txtTelefono.setColumns(10);
		
		JLabel lblDireccin = new JLabel("Direcci\u00F3n:");
		lblDireccin.setBounds(12, 88, 67, 14);
		panel.add(lblDireccin);
		
		txtDireccion = new JTextField();
		txtDireccion.setEnabled(false);
		txtDireccion.setBounds(75, 85, 440, 20);
		panel.add(txtDireccion);
		txtDireccion.setColumns(10);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "Factura", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBounds(4, 128, 521, 196);
		contentPanel.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblQuesosDisponibles = new JLabel("Quesos disponibles:");
		lblQuesosDisponibles.setBounds(86, 12, 118, 14);
		panel_1.add(lblQuesosDisponibles);
		
		JLabel lblCarrito = new JLabel("Carrito:");
		lblCarrito.setBounds(356, 12, 46, 14);
		panel_1.add(lblCarrito);
		

		List listQueso = new List();
		listQueso.setBounds(57, 38, 162, 114);
		panel_1.add(listQueso);
		try {
			Statement read = connect.createStatement();
			ResultSet resultado = read.executeQuery("Select queso_id,tipo,precio_unitario from Queso");
			
			while (resultado.next()) {
				String aux2 = resultado.getString(1) +" - "+ resultado.getString(2) + " - "+ resultado.getString(3);
				listQueso.add(aux2);
			}
			

			
		} catch (Exception e) {
			// TODO: handle exception
		}

		
		
		List listCarrito = new List();
		listCarrito.setBounds(301, 38, 162, 114);
		JButton button = new JButton(">");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String selected = listQueso.getSelectedItem().toString();
				listCarrito.add(selected);
				listQueso.remove(listQueso.getSelectedItem());
				float total = 0;
				
				for (int i = 0; i < listCarrito.getItemCount(); i++) {
					int pos = listCarrito.getItem(i).indexOf(" -");
					String cod = listCarrito.getItem(i).substring(0, pos);
					String quesosql = "Select queso_id,precio_unitario from Queso where queso_id ='"+cod+"'";

					try {
						Statement read = connect.createStatement();
						ResultSet resultado = read.executeQuery(quesosql);
						
						while(resultado.next() ) {
							total += resultado.getFloat("precio_unitario");
						}
						} catch (Exception e1) {
					//todo: handle exception
					}
				}
				
				txttotal.setText(Float.toString(total));
			}
		});
		button.setBounds(240, 63, 44, 23);
		panel_1.add(button);
		panel_1.add(listCarrito);
		
		JButton button_1 = new JButton("<");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String selected = listCarrito.getSelectedItem().toString();
				listQueso.add(selected);
				listCarrito.remove(listCarrito.getSelectedItem());
				float total = 0;
				for (int i = 0; i < listCarrito.getItemCount(); i++) {
					int pos = listCarrito.getItem(i).indexOf(" -");
					String cod = listCarrito.getItem(i).substring(0, pos);
					String quesosql = "Select queso_id,precio_unitario from Queso where queso_id ='"+cod+"'";

					try {
						Statement read = connect.createStatement();
						ResultSet resultado = read.executeQuery(quesosql);
						
						while(resultado.next() ) {
							total += resultado.getFloat("precio_unitario");
						}
						} catch (Exception e1) {
					//todo: handle exception
					}
					
					
				}
				
				txttotal.setText(Float.toString(total));
			}	
		});
		button_1.setBounds(240, 109, 44, 23);
		panel_1.add(button_1);
		
		JLabel lblTotal = new JLabel("Total:");
		lblTotal.setBounds(301, 170, 48, 14);
		panel_1.add(lblTotal);
		
		txttotal = new JTextField();
		txttotal.setEditable(false);
		txttotal.setText("0.0");
		txttotal.setBounds(338, 167, 125, 20);
		panel_1.add(txttotal);
		txttotal.setColumns(10);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("Facturar");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if(txtNombre.getText().equalsIgnoreCase("")|| txtDireccion.getText().equalsIgnoreCase("") || txtTelefono.getText().equalsIgnoreCase("") || txtid.getText().equalsIgnoreCase(""))
						{
							JOptionPane.showMessageDialog(null, "Campos del cliente vacios", "Notificaci�n", JOptionPane.INFORMATION_MESSAGE);

						}else {
						Cliente aux = Fabrica.getInstance().buscarClienteByID(txtid.getText());
							
							
							
						if(aux != null) {
							if(txttotal.getText().equalsIgnoreCase("0.0")) {
								JOptionPane.showMessageDialog(null, "No se agreg� ning�n art�culo al carrito", "Notificaci�n", JOptionPane.INFORMATION_MESSAGE);

							}else {
								ArrayList<String>misquesos = new ArrayList<String>();
								
								for (int i = 0; i < listCarrito.getItemCount(); i++) {
									int pos = listCarrito.getItem(i).indexOf(" -");
									String cod = listCarrito.getItem(i).substring(0, pos);
									
									String quesosql = "Select queso_id from Queso where queso_id ='"+cod+"'";
									
									try {
										Statement read = connect.createStatement();
										ResultSet resultado = read.executeQuery(quesosql);
										
										while(resultado.next() ) {
											misquesos.add(listCarrito.getItem(i));
											PreparedStatement EliminarQueso = connect.prepareStatement("DELETE from queso where queso_id in (SELECT queso_id from queso where queso_id='"+cod+"')");
											EliminarQueso.executeUpdate();
											
											PreparedStatement InsertarFact_Queso = connect.prepareStatement("INSERT INTO queso_factura (queso_id,Factura_id) VALUES (?,?)");
											InsertarFact_Queso.setString(1,resultado.getString(1));
											InsertarFact_Queso.setString(2,"F-"+FacturaID());
								        	InsertarFact_Queso.executeUpdate();
										}
											
										} catch (Exception e2) {
											JOptionPane.showMessageDialog(null,e2+"e2", "Notificaci�n", JOptionPane.INFORMATION_MESSAGE);
									}
									
									
									
									listCarrito.clear();
									
								
									
								}
								
								try {
									
									
						        	PreparedStatement InsertarFact = connect.prepareStatement("INSERT INTO Factura (Factura_id,Cliente_id,Monto,Fecha) VALUES (?,?,?,?)");
						        	InsertarFact.setString(1,"F-"+FacturaID());
						        	InsertarFact.setString(2,txtid.getText());
						        	InsertarFact.setString(3,txttotal.getText());
						        	InsertarFact.setString(4,dd.format(gg.getTime()));
						        	InsertarFact.executeUpdate();
						        	
						        }
						        catch (SQLException e1) {
						        	///////
						        }
								
								
								JOptionPane.showMessageDialog(null, "Operaci�n satisfactoria", "Notificaci�n", JOptionPane.INFORMATION_MESSAGE);
								clean();
							}
							
							
							
							
							
						}else {
							if(txttotal.getText().equalsIgnoreCase("0.0")) {
								JOptionPane.showMessageDialog(null, "No se agreg� ning�n art�culo al carrito", "Notificaci�n", JOptionPane.INFORMATION_MESSAGE);

							}else {
							
							//Se introduce el cliente creado en la base de datos///
					        try {
					        	PreparedStatement InsertarCliente = connect.prepareStatement("INSERT INTO Cliente (Cliente_id,Nombre,Direccion,Telefono) VALUES (?,?,?,?)");
					        InsertarCliente.setString(1,txtid.getText());
					        InsertarCliente.setString(2,txtNombre.getText());
					        InsertarCliente.setString(3,txtDireccion.getText());
					        InsertarCliente.setString(4,txtTelefono.getText());
					        InsertarCliente.executeUpdate();
					        }
					        catch (SQLException e1) {
					        	///////
					        }
							
							
							//Fabrica.getInstance().getMisClientes().add(aux);
					        ArrayList<Queso>misquesos2 = new ArrayList<Queso>();
							ArrayList<String>misquesos = new ArrayList<String>();
							
							for (int i = 0; i < listCarrito.getItemCount(); i++) {
								int pos = listCarrito.getItem(i).indexOf(" -");
								String cod = listCarrito.getItem(i).substring(0, pos);
								String quesosql = "Select queso_id,precio_unitario from Queso where queso_id ='"+cod+"'";
								
								try {
									Statement read = connect.createStatement();
									ResultSet resultado = read.executeQuery(quesosql);
									
									while(resultado.next() ) {
										misquesos.add(listCarrito.getItem(i));
										//BorraQueso(resultado.getString("queso_id"));
										PreparedStatement EliminarQueso = connect.prepareStatement("DELETE from queso where queso_id in (SELECT queso_id from queso where queso_id='"+cod+"')");
										EliminarQueso.executeUpdate();
										
										PreparedStatement InsertarFact_Queso = connect.prepareStatement("INSERT INTO queso_factura (queso_id,Factura_id) VALUES (?,?)");
										InsertarFact_Queso.setString(1,resultado.getString(1));
										InsertarFact_Queso.setString(2,"F-"+FacturaID());
							        	InsertarFact_Queso.executeUpdate();
									}
									

									} catch (Exception e1) {
										JOptionPane.showMessageDialog(null,e1+"ete", "Notificaci�n", JOptionPane.INFORMATION_MESSAGE);
								}
								listCarrito.remove(i);
							
	
							}
							// Se introduce factura dentro de la base de datos. //
							
							try {
							
								
					        	PreparedStatement InsertarFact = connect.prepareStatement("INSERT INTO Factura (Factura_id,Cliente_id,Monto,Fecha) VALUES (?,?,?,?)");
					        	InsertarFact.setString(1,"F-"+FacturaID());
					        	InsertarFact.setString(2,txtid.getText());
					        	InsertarFact.setString(3,txttotal.getText());
					        	InsertarFact.setString(4,dd.format(gg.getTime()));
					        	InsertarFact.executeUpdate();

					        }
					        catch (SQLException e1) {
					        	///////
					        }

							listCarrito.removeAll();
							JOptionPane.showMessageDialog(null, "Operaci�n satisfactoria", "Notificaci�n", JOptionPane.INFORMATION_MESSAGE);
							clean();
						
								/*try {
									String quesosql = "Select Factura_id,Nombre,Fecha,Monto from Factura,Cliente where factura.cliente_id=cliente.cliente_id ";
									Statement readfact = connect.createStatement();
									ResultSet rs1 = readfact.executeQuery(quesosql);
										
									
									 FileWriter factura = new FileWriter("Factura-"+FacturaID()+".txt");
									factura.write(""
									 		+ "****************************\n"
									 		+ "     Fabrica de quesos     \n"
									 		+ "          La Habana        \n"
									 		+ "****************************\n"
									 		+ "Fecha:"+rs1.getString(3) + " Time:"+ddd.format(gg.getTime())+"\n"
									 		+ "Fact#:"+rs1.getString(1)+"\n"
									 		+ "Cliente:"+rs1.getString(2)+"\n"
									 		+ "----------------------------\n"
									 		+ "Producto                   \n"
									 		+ "Codigo  Descripcion     Precio\n"
									 		+ "fac"
									 		+ "----------------------------\n"
									 		+ "Monto: "+ rs1.getString(4)+"\n"
									 		
									 		+ "Total a pagar: "+ rs1.getString(4)+"\n"
									 		+ "----------------------------\n"
									 		+ "     Gracias por su compra!\n"
									 		+ "");
									 
									 factura.close();
									 
							}catch(Exception ex)
								{
								ex.printStackTrace();
								}*/
							}	
						}
					}
				}
					private int posicionQueso(Queso queso) {
						int aux = 0;
						boolean encontrado = false;
						int i = 0;
						while (i < Fabrica.getInstance().getMisQuesos().size() && !encontrado) {
							if(Fabrica.getInstance().getMisQuesos().get(i) == queso){
								aux = i;
								encontrado = true;
							}
							i++;
						}
							return aux;
				
					}
					
					
					private void BorraQueso(String cod) {
						
						
						try {
							PreparedStatement EliminarQueso = connect.prepareStatement("DELETE from queso where queso_id='"+cod+"'");
							EliminarQueso.executeUpdate();
							}catch(Exception e2) {
									JOptionPane.showMessageDialog(null,e2 +"e2 ete", "Notificaci�n", JOptionPane.INFORMATION_MESSAGE);
							}
						
					}
					
					
					private int FacturaID() {
						int ids = 0;

						try {
							Statement read = connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, 
									  ResultSet.CONCUR_READ_ONLY);
							ResultSet resultado = read.executeQuery("Select COUNT(Factura_id) from Factura");
							
							resultado.next();
							ids = resultado.getInt(1);
						    
							} catch (Exception e1) {
						//todo: handle exception
						}
						
						return ids+1;
					}

					private void clean() {
						txtid.setText("");
						txtDireccion.setText("");
						txtNombre.setText("");
						txtTelefono.setText("");
						
					}
				});
				okButton.setBackground(SystemColor.inactiveCaptionBorder);
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancelar");
				cancelButton.setBackground(SystemColor.inactiveCaptionBorder);
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}			
	
}

/*
String fac = new String();
String quesofacsql = "Select queso_id,tipo,precio_unitario from queso ";
Statement read = connect.createStatement();
ResultSet rs = read.executeQuery(quesofacsql);
while (rs.next() ) {
	String tipos=rs.getString(2);
		if(tipos == "Esferico") {
			fac=  fac+rs.getString(1)+rs.getString(2)+ rs.getString(3)+""+"\n";
		}
		if(tipos == "Cilindrico") {
			fac=  fac+rs.getString(1)+rs.getString(2)+ rs.getString(3)+""+"\n";
		}
		if(tipos == "Cilindrico-Hueco") {
			fac=  fac+rs.getString(1)+rs.getString(2)+ rs.getString(3)+""+"\n";
		}
					}*/