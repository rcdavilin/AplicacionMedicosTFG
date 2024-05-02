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
		btnVerPacientesCargo.setBounds(65, 99, 155, 31);
		contentPane.add(btnVerPacientesCargo);
		
	
	}
}
