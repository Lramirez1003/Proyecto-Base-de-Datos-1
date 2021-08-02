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

public class ListarQuesos extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private static JTable table;
	private static Object[] fila;
	private static DefaultTableModel tableModel;
	JComboBox cbxPublicType;
	
	 static Conexion link = new Conexion();
	 static Connection connect = link.getConexion();


	/**
	 * Create the dialog.
	 * @param fed 
	 */
	public ListarQuesos() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(ListarQuesos.class.getResource("/Auxiliares/quesito.png")));
		setTitle("Listado de Quesos");
		setBounds(100, 100, 440, 376);
		setResizable(false);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Listado de Quesos", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel.setBounds(10, 11, 403, 293);
		contentPanel.add(panel);
		panel.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 54, 383, 228);
		panel.add(scrollPane);
		
		table = new JTable();
		tableModel = new DefaultTableModel();
		String[] columnNames = {"Código","Tipo","Precio Unitario"};
		tableModel.setColumnIdentifiers(columnNames);
		cargarDatos(0);
		scrollPane.setViewportView(table);
		
		JLabel lblTipoDePublicacin = new JLabel("Tipo de Queso:");
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
		cbxPublicType.setModel(new DefaultComboBoxModel(new String[] {"<Todos>", "Esferas", "Cilindros", "Cilindros Huecos"}));
		cbxPublicType.setBounds(127, 26, 157, 20);
		panel.add(cbxPublicType);
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
		try {
			Statement read = connect.createStatement();
			ResultSet resultado = read.executeQuery("Select queso_id,tipo,precio_unitario from Queso");
			
			while (resultado.next()) {
				fila[0] = resultado.getString(1);
				fila[1] = resultado.getString(2);
				fila[2] = resultado.getString(3);
				tableModel.addRow(fila);
			}
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		break;
					
		case 1:
			
			try {
				Statement read = connect.createStatement();
				ResultSet resultado = read.executeQuery("Select queso_id,tipo,precio_unitario from Queso where tipo='Esferico'");
				
				while (resultado.next()) {
					fila[0] = resultado.getString(1);
					fila[1] = resultado.getString(2);
					fila[2] = resultado.getString(3);
					tableModel.addRow(fila);
				}
				
			} catch (Exception e) {
				// TODO: handle exception
			}
			break;	
			
		case 2:
			try {
				Statement read = connect.createStatement();
				ResultSet resultado = read.executeQuery("Select queso_id,tipo,precio_unitario from Queso where tipo='Cilindrico'");
				
				while (resultado.next()) {
					fila[0] = resultado.getString(1);
					fila[1] = resultado.getString(2);
					fila[2] = resultado.getString(3);
					tableModel.addRow(fila);
				}
				
			} catch (Exception e) {
				// TODO: handle exception
			}
			break;	
			
		case 3:
			try {
				Statement read = connect.createStatement();
				ResultSet resultado = read.executeQuery("Select queso_id,tipo,precio_unitario from Queso where tipo='Cilindrico-Hueco'");
				
				while (resultado.next()) {
					fila[0] = resultado.getString(1);
					fila[1] = resultado.getString(2);
					fila[2] = resultado.getString(3);
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
