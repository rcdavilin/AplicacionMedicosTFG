package view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Optional;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

import org.bson.Document;

import controller.MedicoController;
import javax.swing.JButton;

public class VerPacientesCargo extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	static String dni;
	JLabel lblNewLabel;
	MedicoController controllerMedico = new MedicoController();
	JScrollPane scrollPane;
	JTextArea textAreaMostrar;
	String[] dniPaciente;
	JComboBox<String> comboBoxDniPacientes;
	String pacientes;
	String selectedDni;
	JButton btnCancelar;
	VentanaPrincipalMedico vpm;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VerPacientesCargo frame = new VerPacientesCargo(dni);
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
	public VerPacientesCargo(String dni) {
		VerPacientesCargo.dni = dni;
		dniPaciente = controllerMedico.dniPacientes(dni);
		
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 516, 432);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(230, 230, 250));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		lblNewLabel = new JLabel("Pacientes a cargo del medico " + dni);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel.setBounds(10, 33, 293, 29);
		contentPane.add(lblNewLabel);
		
		scrollPane = new JScrollPane();
		scrollPane.setVisible(false);
		scrollPane.setBounds(10, 84, 482, 249);
		contentPane.add(scrollPane);
		
		textAreaMostrar = new JTextArea();
		textAreaMostrar.setVisible(false);
		scrollPane.setViewportView(textAreaMostrar);
		
		comboBoxDniPacientes = new JComboBox<String>();
		DefaultComboBoxModel<String> model = new DefaultComboBoxModel<String>();
		model.addElement("");
		for (int i = 0; i < dniPaciente.length; i++) {
		    model.addElement(dniPaciente[i]);
		}
		comboBoxDniPacientes.setModel(model);
		comboBoxDniPacientes.setBounds(313, 39, 168, 21);
		contentPane.add(comboBoxDniPacientes);
		comboBoxDniPacientes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				selectedDni = (String) comboBoxDniPacientes.getSelectedItem();
				scrollPane.setVisible(true);
				textAreaMostrar.setVisible(true);
				Optional<Document> pacientes =controllerMedico.findByDniPaciente(selectedDni);
				textAreaMostrar.setText(controllerMedico.mostrar(pacientes));
				
			}
		});
		
		btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				vpm = new VentanaPrincipalMedico(dni);
				vpm.setVisible(true);
				dispose();
			}
		});
		btnCancelar.setBounds(192, 343, 104, 34);
		contentPane.add(btnCancelar);
		
		
	}
}
