package Visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.*;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
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
import javax.swing.JTextField;

public class ListarReportes extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private static JTable table;
	private static Object[] fila;
	private static DefaultTableModel tableModel;
	JComboBox cbxPublicType;
	
	 static Conexion link = new Conexion();
	 static Connection connect = link.getConexion();
	 private static JTextField txtTotal = new JTextField();


	/**
	 * Create the dialog.
	 * @param fed 
	 */
	public ListarReportes() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(ListarReportes.class.getResource("/Auxiliares/quesito.png")));
		setTitle("Lista de Reportes");
		setBounds(100, 100, 501, 416);
		setResizable(false);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Lista de Reportes", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel.setBounds(10, 11, 475, 311);
		contentPanel.add(panel);
		panel.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 54, 455, 246);
		panel.add(scrollPane);
		
		table = new JTable();
		tableModel = new DefaultTableModel();
		String[] columnNames = {"Código","Cliente","Monto","Fecha"};
		tableModel.setColumnIdentifiers(columnNames);
		cargarDatos(0);
		scrollPane.setViewportView(table);
		
		JLabel lblTipoDePublicacin = new JLabel("Filtrado por fecha:");
		lblTipoDePublicacin.setBounds(10, 29, 116, 14);
		panel.add(lblTipoDePublicacin);
		
		cbxPublicType = new JComboBox();
		cbxPublicType.setBackground(Color.WHITE);
		cbxPublicType.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selection = cbxPublicType.getSelectedIndex();
				cargarDatos(selection);
			}
		});
		cbxPublicType.setModel(new DefaultComboBoxModel(new String[] {"<Todos>", "Ultima semana", "Ultimo mes", "Ultimo a\u00F1o"}));
		cbxPublicType.setBounds(127, 26, 157, 20);
		panel.add(cbxPublicType);
		
		txtTotal = new JTextField();
		txtTotal.setBounds(336, 323, 86, 20);
		contentPanel.add(txtTotal);
		txtTotal.setText("0.0");
		txtTotal.setColumns(10);
		
		JLabel lblTotal = new JLabel("Monto total:");
		lblTotal.setBounds(247, 326, 79, 14);
		contentPanel.add(lblTotal);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton cancelButton = new JButton("Aceptar");
				cancelButton.setBackground(SystemColor.inactiveCaptionBorder);
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}



	public static void cargarDatos(int selection) {
		tableModel.setRowCount(0);
		fila = new Object[tableModel.getColumnCount()];
		
		switch (selection) {
		case 0:
			float total=0;
			try {
				Statement read = connect.createStatement();
				ResultSet resultado = read.executeQuery("Select Factura_id,cliente.Nombre,Monto,Fecha from Factura,Cliente where cliente.cliente_id=factura.cliente_id");
				
				
				while (resultado.next()) {
					fila[0] = resultado.getString(1);
					fila[1] = resultado.getString(2);
					fila[2] = resultado.getString(3);
					fila[3] = resultado.getString(4);
					total += resultado.getFloat("Monto");
					txtTotal.setText(Float.toString(total));
					
					tableModel.addRow(fila);
				}
			
		} catch (Exception e) {
			// TODO: handle exception
			JOptionPane.showMessageDialog(null, e, "Notificación", JOptionPane.INFORMATION_MESSAGE);
		}
			
		break;
					
		case 1:
			float total1=0;
			try {
				Statement read = connect.createStatement();
				ResultSet resultado = read.executeQuery("select Factura_id,cliente.Nombre,Monto,Fecha from Factura,Cliente where cliente.Cliente_id=Factura.Cliente_id and Fecha >= DATEADD(day,-7, GETDATE())");
				
				while (resultado.next()) {
					fila[0] = resultado.getString(1);
					fila[1] = resultado.getString(2);
					fila[2] = resultado.getString(3);
					fila[3] = resultado.getString(4);
					total1 += resultado.getFloat("Monto");
					txtTotal.setText(Float.toString(total1));
					tableModel.addRow(fila);
				}
				
			} catch (Exception e) {
				// TODO: handle exception
			}
			break;	
			
		case 2:
			float total11=0;
			try {
				Statement read = connect.createStatement();
				ResultSet resultado = read.executeQuery("select Factura_id,cliente.Nombre,Monto,Fecha from Factura,Cliente where cliente.Cliente_id=Factura.Cliente_id and Fecha >= DATEADD(day,-33, GETDATE())");
				
				while (resultado.next()) {
					fila[0] = resultado.getString(1);
					fila[1] = resultado.getString(2);
					fila[2] = resultado.getString(3);
					fila[3] = resultado.getString(4);
					total11 += resultado.getFloat("Monto");
					txtTotal.setText(Float.toString(total11));
					tableModel.addRow(fila);
				}
				
			} catch (Exception e) {
				// TODO: handle exception
			}
			break;	
			
		case 3:
			float total111=0;
			try {
				Statement read = connect.createStatement();
				ResultSet resultado = read.executeQuery("select Factura_id,cliente.Nombre,Monto,Fecha from Factura,Cliente where cliente.Cliente_id=Factura.Cliente_id and Fecha >= DATEADD(day,-365, GETDATE())");
				
				while (resultado.next()) {
					fila[0] = resultado.getString(1);
					fila[1] = resultado.getString(2);
					fila[2] = resultado.getString(3);
					fila[3] = resultado.getString(4);
					total111 += resultado.getFloat("Monto");
					txtTotal.setText(Float.toString(total111));
					tableModel.addRow(fila);
				}
				
			} catch (Exception e) {
				// TODO: handle exception
			}
			break;	
		}
		
		table.setModel(tableModel);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		table.getTableHeader().setReorderingAllowed(false);
		TableColumnModel columnModel = table.getColumnModel();
		columnModel.getColumn(0).setPreferredWidth(60);
		columnModel.getColumn(1).setPreferredWidth(180);
		columnModel.getColumn(2).setPreferredWidth(140);
	
		
		
	}
}
