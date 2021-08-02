package Visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.border.TitledBorder;

import Logico.Cilindro;
import Logico.CilindroHueco;
import Logico.Esfera;
import Logico.Fabrica;
import Logico.Queso;
import Logico.Conexion;

import javax.swing.JTextField;
import javax.swing.JSpinner;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import java.awt.event.ActionListener;
import java.sql.*;
import java.awt.event.ActionEvent;
import javax.swing.SpinnerNumberModel;
import java.awt.Toolkit;
import java.awt.SystemColor;

public class RegistrarQueso extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JTextField txtid;
	
	 static Conexion link = new Conexion();
	 static Connection connect = link.getConexion();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {		
		try {
			RegistrarQueso dialog = new RegistrarQueso();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		test_conexion();
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
	 * Create the dialog.
	 */
	public RegistrarQueso() {
		setTitle("Registrar Queso");
		setIconImage(Toolkit.getDefaultToolkit().getImage(RegistrarQueso.class.getResource("/Auxiliares/quesito.png")));
		setBounds(100, 100, 511, 282);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Precios", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(0, 0, 495, 77);
		contentPanel.add(panel);
		panel.setLayout(null);
		
		JLabel lblPrecioBase = new JLabel("Precio base:");
		lblPrecioBase.setBounds(12, 46, 86, 14);
		panel.add(lblPrecioBase);
		
		JLabel lblPrecioUnitario = new JLabel("Precio unitario:");
		lblPrecioUnitario.setBounds(246, 46, 103, 14);
		panel.add(lblPrecioUnitario);
		
		JSpinner spnBase = new JSpinner();
		spnBase.setModel(new SpinnerNumberModel(new Integer(1), new Integer(1), null, new Integer(1)));
		spnBase.setBounds(95, 43, 139, 20);
		panel.add(spnBase);
		
		JSpinner spnunitario = new JSpinner();
		spnunitario.setModel(new SpinnerNumberModel(new Integer(1), new Integer(1), null, new Integer(1)));
		spnunitario.setBounds(344, 43, 139, 20);
		panel.add(spnunitario);
		
		JLabel lblId = new JLabel("Id:");
		lblId.setBounds(12, 16, 46, 14);
		panel.add(lblId);
		
		txtid = new JTextField();
		txtid.setEditable(false);
		txtid.setEnabled(false);
		txtid.setBounds(95, 13, 139, 20);
		panel.add(txtid);
		txtid.setColumns(10);
		txtid.setText("Q-"+(Fabrica.getInstance().getCodQueso()));
		Fabrica.getInstance().aumentar();;
		
		JPanel panel_Esfera = new JPanel();
		panel_Esfera.setBorder(new TitledBorder(null, "Datos de la Esfera", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_Esfera.setBounds(0, 127, 495, 72);
		contentPanel.add(panel_Esfera);
		panel_Esfera.setLayout(null);
		
		JLabel lblRadio_1 = new JLabel("Radio:");
		lblRadio_1.setBounds(14, 29, 87, 14);
		panel_Esfera.add(lblRadio_1);
		
		JSpinner spnradioEsfera = new JSpinner();
		spnradioEsfera.setModel(new SpinnerNumberModel(new Integer(1), new Integer(1), null, new Integer(1)));
		spnradioEsfera.setBounds(115, 26, 365, 20);
		panel_Esfera.add(spnradioEsfera);
		
		JPanel panel_Cilindro = new JPanel();
		panel_Cilindro.setBounds(0, 127, 495, 72);
		contentPanel.add(panel_Cilindro);
		panel_Cilindro.setBorder(new TitledBorder(null, "Datos del Cilindro", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_Cilindro.setLayout(null);
		
		JLabel lblRadio = new JLabel("Radio:");
		lblRadio.setBounds(8, 29, 55, 14);
		panel_Cilindro.add(lblRadio);
		
		JSpinner spn_Cilindrorad = new JSpinner();
		spn_Cilindrorad.setModel(new SpinnerNumberModel(new Integer(1), new Integer(1), null, new Integer(1)));
		spn_Cilindrorad.setBounds(71, 26, 158, 20);
		panel_Cilindro.add(spn_Cilindrorad);
		
		JLabel lblAltura_1 = new JLabel("Altura:");
		lblAltura_1.setBounds(237, 29, 55, 14);
		panel_Cilindro.add(lblAltura_1);
		
		JSpinner spnAlturacilindro = new JSpinner();
		spnAlturacilindro.setModel(new SpinnerNumberModel(new Integer(1), new Integer(1), null, new Integer(1)));
		spnAlturacilindro.setBounds(300, 26, 158, 20);
		panel_Cilindro.add(spnAlturacilindro);
		
		JPanel panel_Cilindrohueco = new JPanel();
		panel_Cilindrohueco.setBorder(new TitledBorder(null, "Datos de Cilindro Hueco", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_Cilindrohueco.setBounds(0, 127, 495, 72);
		contentPanel.add(panel_Cilindrohueco);
		panel_Cilindrohueco.setLayout(null);
		
		JLabel lblRadioInterior = new JLabel("Radio Interior:");
		lblRadioInterior.setBounds(183, 29, 87, 14);
		panel_Cilindrohueco.add(lblRadioInterior);
		
		JLabel lblRadioExterior = new JLabel("Radio Exterior:");
		lblRadioExterior.setBounds(10, 29, 87, 14);
		panel_Cilindrohueco.add(lblRadioExterior);
		
		JLabel lblAltura = new JLabel("Altura:");
		lblAltura.setBounds(349, 29, 46, 14);
		panel_Cilindrohueco.add(lblAltura);
		
		JSpinner spnradexterior = new JSpinner();
		spnradexterior.setModel(new SpinnerNumberModel(new Integer(1), new Integer(1), null, new Integer(1)));
		spnradexterior.setBounds(95, 26, 73, 20);
		panel_Cilindrohueco.add(spnradexterior);
		
		JSpinner spnradInterior = new JSpinner();
		spnradInterior.setModel(new SpinnerNumberModel(new Integer(1), new Integer(1), null, new Integer(1)));
		spnradInterior.setBounds(263, 26, 73, 20);
		panel_Cilindrohueco.add(spnradInterior);
		
		JSpinner spnAltura = new JSpinner();
		spnAltura.setModel(new SpinnerNumberModel(new Integer(1), new Integer(1), null, new Integer(1)));
		spnAltura.setBounds(390, 26, 73, 20);
		panel_Cilindrohueco.add(spnAltura);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "Tipo de Queso", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBounds(0, 78, 495, 49);
		contentPanel.add(panel_1);
		panel_1.setLayout(null);
		
		JRadioButton rdbtnEsfera = new JRadioButton("Esfera");
		rdbtnEsfera.setSelected(true);
		rdbtnEsfera.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				rdbtnEsfera.setSelected(true);
				panel_Esfera.setVisible(true);
				panel_Cilindro.setVisible(false);
				panel_Cilindrohueco.setVisible(true);
			}
		});
		buttonGroup.add(rdbtnEsfera);
		rdbtnEsfera.setBounds(42, 13, 109, 23);
		panel_1.add(rdbtnEsfera);
		
		JRadioButton rdbtnCilindro = new JRadioButton("Cilindro");
		rdbtnCilindro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				rdbtnCilindro.setSelected(true);
				panel_Esfera.setVisible(false);
				panel_Cilindrohueco.setVisible(false);
				panel_Cilindro.setVisible(true);
			}
		});
		buttonGroup.add(rdbtnCilindro);
		rdbtnCilindro.setBounds(193, 13, 109, 23);
		panel_1.add(rdbtnCilindro);
		
		JRadioButton rdbtnCilindroHueco = new JRadioButton("Cilindro Hueco");
		rdbtnCilindroHueco.setSelected(true);
		rdbtnCilindroHueco.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				rdbtnCilindroHueco.setSelected(true);
				panel_Cilindro.setVisible(false);
				panel_Esfera.setVisible(false);
				panel_Cilindrohueco.setVisible(true);
			}
		});
		
		
		buttonGroup.add(rdbtnCilindroHueco);
		rdbtnCilindroHueco.setBounds(344, 13, 109, 23);
		panel_1.add(rdbtnCilindroHueco);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("Aceptar");
				okButton.setBackground(SystemColor.inactiveCaptionBorder);
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						Queso aux = null;
						String id = txtid.getText();
						float precioBase= new Float(spnBase.getValue().toString());
						float precioUnitario= new Float(spnunitario.getValue().toString());
						
						if(rdbtnEsfera.isSelected()){
							float radio= new Float(spnradioEsfera.getValue().toString());
							aux = new Esfera(precioBase,precioUnitario,radio,id);	
					        try {
					        	PreparedStatement InsertarQueso = connect.prepareStatement("INSERT INTO queso (queso_id,precio_unitario,precio_base,radio,tipo) VALUES (?,?,?,?,?)");
					        InsertarQueso.setString(1,txtid.getText());
					        InsertarQueso.setString(2,spnunitario.getValue().toString());
					        InsertarQueso.setString(3,spnBase.getValue().toString());
					        InsertarQueso.setString(4,spnradioEsfera.getValue().toString());
					        InsertarQueso.setString(5,"Esferico");
					        InsertarQueso.executeUpdate();
				        	JOptionPane.showMessageDialog(null, "Queso Agregado");
					        }
					        catch (SQLException e1) {
					        	JOptionPane.showMessageDialog(null,e1 + "Queso no Agregado");
					        }
						    
						}
					    
					
						if(rdbtnCilindro.isSelected()){
							float radio = new Float(spn_Cilindrorad.getValue().toString());
							float altura = new Float(spnAlturacilindro.getValue().toString());
							aux = new Cilindro(precioBase, precioUnitario, radio, altura, id);
							
							try {
					        	PreparedStatement InsertarQueso = connect.prepareStatement("INSERT INTO queso (queso_id,precio_unitario,precio_base,radio_exterior,altura,tipo) VALUES (?,?,?,?,?,?)");
					        InsertarQueso.setString(1,txtid.getText());
					        InsertarQueso.setString(2,spnunitario.getValue().toString());
					        InsertarQueso.setString(3,spnBase.getValue().toString());
					        InsertarQueso.setString(4,spn_Cilindrorad.getValue().toString());
					        InsertarQueso.setString(5,spnAlturacilindro.getValue().toString());
					        InsertarQueso.setString(6,"Cilindrico");
					        InsertarQueso.executeUpdate();
				        	JOptionPane.showMessageDialog(null, "Queso Agregado");
					        }
					        catch (SQLException e1) {
					        	JOptionPane.showMessageDialog(null,e1 + "Queso no Agregado");
					        }
							
						}
						if(rdbtnCilindroHueco.isSelected()){
							float radioEx = new Float(spnradexterior.getValue().toString());
							float radioIn = new Float(spnradInterior.getValue().toString());
							float altura = new Float(spnAltura.getValue().toString());
							aux = new CilindroHueco(precioBase, precioUnitario, radioEx, altura, radioIn, id);
							
							try {
								PreparedStatement InsertarQueso = connect.prepareStatement("INSERT INTO queso (queso_id,precio_unitario,precio_base,radio_exterior,radio_interno,altura,tipo) VALUES (?,?,?,?,?,?,?)");
						        InsertarQueso.setString(1,txtid.getText());
						        InsertarQueso.setString(2,spnunitario.getValue().toString());
						        InsertarQueso.setString(3,spnBase.getValue().toString());
						        InsertarQueso.setString(4,spnradexterior.getValue().toString());
						        InsertarQueso.setString(5,spnradInterior.getValue().toString());
						        InsertarQueso.setString(6,spnAltura.getValue().toString());
						        InsertarQueso.setString(7,"Cilindrico-Hueco");
					        InsertarQueso.executeUpdate();
				        	JOptionPane.showMessageDialog(null, "Queso Agregado");
					        }
					        catch (SQLException e1) {
					        	JOptionPane.showMessageDialog(null,e1 + "Queso no Agregado");
					        }
							
						}
						//Fabrica.getInstance().getMisQuesos().add(aux);
						
						
						//JOptionPane.showMessageDialog(null, "Operación satisfactoria", "Notificación", JOptionPane.INFORMATION_MESSAGE);
						clean();
						
					}

				
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
				
			}
			
			{
				JButton cancelButton = new JButton("Cancel");
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
	private void clean() {
		
		txtid.setText("Q-"+(Fabrica.getInstance().getCodQueso()));
		Fabrica.getInstance().aumentar();
	}
}
