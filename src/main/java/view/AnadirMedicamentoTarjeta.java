package view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Optional;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import org.bson.Document;

import controller.MedicoController;

public class AnadirMedicamentoTarjeta extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	String[] dniPaciente;
	MedicoController controllerMedico = new MedicoController();
	String selectedDni;
	private JTextField textFieldIntroduzcaMedicamentos;
	JLabel lblIntroduzcaMedicamentos;
	JButton btnCancelar;
	JButton btnAceptar;
	JComboBox<String> comboBoxDniPacientes;
	private JLabel lblTitulo;
	VentanaPrincipalMedico principal;
	static String dni;
	private JLabel lblMensaje;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AnadirMedicamentoTarjeta frame = new AnadirMedicamentoTarjeta(dni);
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
	public AnadirMedicamentoTarjeta(String dni) {
		AnadirMedicamentoTarjeta.dni = dni;
		dniPaciente = controllerMedico.dniPacientes(dni);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 646, 378);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(230, 230, 250));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		comboBoxDniPacientes = new JComboBox<String>();
		DefaultComboBoxModel<String> model = new DefaultComboBoxModel<String>();
		model.addElement("");
		for (int i = 0; i < dniPaciente.length; i++) {
			model.addElement(dniPaciente[i]);
		}
		comboBoxDniPacientes.setModel(model);
		comboBoxDniPacientes.setBounds(394, 38, 212, 21);
		comboBoxDniPacientes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				selectedDni = (String) comboBoxDniPacientes.getSelectedItem();
				lblIntroduzcaMedicamentos.setVisible(true);
				textFieldIntroduzcaMedicamentos.setVisible(true);
			}
		});
		contentPane.add(comboBoxDniPacientes);

		lblIntroduzcaMedicamentos = new JLabel("Introduzca los medicamentos a la tarjeta sanitaria del paciente");
		lblIntroduzcaMedicamentos.setVisible(false);
		lblIntroduzcaMedicamentos.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblIntroduzcaMedicamentos.setBounds(10, 121, 381, 31);
		contentPane.add(lblIntroduzcaMedicamentos);

		textFieldIntroduzcaMedicamentos = new JTextField();
		textFieldIntroduzcaMedicamentos.setVisible(false);
		textFieldIntroduzcaMedicamentos.setBounds(384, 121, 222, 26);
		contentPane.add(textFieldIntroduzcaMedicamentos);
		textFieldIntroduzcaMedicamentos.setColumns(10);

		btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				principal = new VentanaPrincipalMedico(dni);
				principal.setVisible(true);
				dispose();
			}
		});
		btnCancelar.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnCancelar.setBounds(134, 213, 95, 31);
		contentPane.add(btnCancelar);

		btnAceptar = new JButton("Aceptar");
		btnAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Optional<Document> paciente = controllerMedico.findByDniPaciente(selectedDni);
				if (paciente.isPresent()) {
					String medicamentos = textFieldIntroduzcaMedicamentos.getText();
					String [] listaMedicamentos = medicamentos.split(" ");
					Boolean anadido = controllerMedico.anadirTarjeta(paciente, listaMedicamentos);
					if (anadido == true) {
						lblMensaje.setText("El medico ha sido actualizado con exito");
						lblMensaje.setForeground(Color.GREEN);
					} else {
						lblMensaje.setText("El medico no ha sido actualizado con exito");
						lblMensaje.setForeground(Color.RED);
					}
				} else {
					lblMensaje.setText("No existe el paciente con el DNI " + selectedDni);
					lblMensaje.setForeground(Color.RED);
				}

			}
		});
		btnAceptar.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnAceptar.setBounds(359, 213, 90, 31);
		contentPane.add(btnAceptar);

		lblTitulo = new JLabel("Añadir medicamentos a la tarjeta sanitaria\r\n");
		lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblTitulo.setBounds(10, 36, 359, 25);
		contentPane.add(lblTitulo);

		lblMensaje = new JLabel("");
		lblMensaje.setBounds(156, 297, 293, 34);
		contentPane.add(lblMensaje);

	}
}
