package view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.MedicoController;
import javax.swing.JButton;

public class VentanaPrincipalMedico extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	JLabel lblTitulo;
	VentanaVerInfoPersonal info;
	InicioSesion inicio;
	MedicoController controllerMedico = new MedicoController();
	static String dni;
	JCheckBox chckbxInfoPersonal;
	JButton btnVerPacientesCargo;
	VerPacientesCargo pacientesCargo;
	private JButton btnVerCitasCon;
	VerCitasConPaciente citas;
	private JButton btnAadirMedicamentosAl;
	AnadirMedicamentoTarjeta anadir;
	private JButton btnEliminarMedicamentosAl;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaPrincipalMedico frame = new VentanaPrincipalMedico(dni);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public VentanaPrincipalMedico(String dni) {
		VentanaPrincipalMedico.dni = dni;
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 558, 379);
		
		contentPane = new JPanel();
		contentPane.setBackground(new Color(230, 230, 250));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		lblTitulo = new JLabel("Bienvenido usuario: " + dni);
		lblTitulo.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblTitulo.setBounds(39, 40, 243, 21);
		contentPane.add(lblTitulo);
		
		chckbxInfoPersonal = new JCheckBox("Informacion personal");
		chckbxInfoPersonal.setFont(new Font("Tahoma", Font.PLAIN, 12));
		chckbxInfoPersonal.setBackground(new Color(230, 230, 250));
		chckbxInfoPersonal.setBounds(366, 41, 172, 21);
		chckbxInfoPersonal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(chckbxInfoPersonal.isSelected()) {
					info = new VentanaVerInfoPersonal(dni);
					info.setVisible(true);
					dispose();
				}
			}
		});
		contentPane.add(chckbxInfoPersonal);
		
		btnVerPacientesCargo = new JButton("Ver pacientes a cargo");
		btnVerPacientesCargo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pacientesCargo = new VerPacientesCargo(dni);
				pacientesCargo.setVisible(true);
				dispose();
				
			}
		});
		btnVerPacientesCargo.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnVerPacientesCargo.setBounds(54, 99, 188, 31);
		contentPane.add(btnVerPacientesCargo);
		
		btnVerCitasCon = new JButton("Ver citas con los pacientes");
		btnVerCitasCon.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				citas = new VerCitasConPaciente(dni);
				citas.setVisible(true);
				dispose();
			}
		});
		btnVerCitasCon.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnVerCitasCon.setBounds(54, 164, 188, 31);
		contentPane.add(btnVerCitasCon);
		
		btnAadirMedicamentosAl = new JButton("Añadir medicamentos al paciente");
		btnAadirMedicamentosAl.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				anadir = new AnadirMedicamentoTarjeta(dni);
				anadir.setVisible(true);
				dispose();
			}
		});
		btnAadirMedicamentosAl.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnAadirMedicamentosAl.setBounds(54, 224, 217, 31);
		contentPane.add(btnAadirMedicamentosAl);
		
		btnEliminarMedicamentosAl = new JButton("Eliminar medicamentos al paciente");
		btnEliminarMedicamentosAl.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnEliminarMedicamentosAl.setBounds(312, 99, 222, 31);
		contentPane.add(btnEliminarMedicamentosAl);
		
	
	}
}
