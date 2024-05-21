package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.MedicoController;

import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;

public class GenerarInforme extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	static String dni;
	String[] dniPaciente;
	JComboBox<String> comboBoxDniPacientes;
	JLabel lblGenerarInforme;
	JLabel lblIntroduzcaNombre;
	MedicoController controllerMedico = new MedicoController();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GenerarInforme frame = new GenerarInforme(dni);
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
	public GenerarInforme(String dni) {
		GenerarInforme.dni = dni;
		dniPaciente = controllerMedico.dniPacientes(dni);
		setBackground(new Color(230, 230, 250));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 531, 391);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(230, 230, 250));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		lblIntroduzcaNombre = new JLabel("Introduzca el DNI del paciente");
		lblIntroduzcaNombre.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblIntroduzcaNombre.setBounds(47, 81, 210, 22);
		contentPane.add(lblIntroduzcaNombre);

		comboBoxDniPacientes = new JComboBox<String>();
		DefaultComboBoxModel<String> model = new DefaultComboBoxModel<String>();
		model.addElement("");
		for (int i = 0; i < dniPaciente.length; i++) {
			model.addElement(dniPaciente[i]);
		}
		comboBoxDniPacientes.setModel(model);
		comboBoxDniPacientes.setBounds(272, 83, 179, 21);
		contentPane.add(comboBoxDniPacientes);

		lblGenerarInforme = new JLabel("Generar informe");
		lblGenerarInforme.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblGenerarInforme.setBounds(193, 23, 148, 21);
		contentPane.add(lblGenerarInforme);
	}
}
