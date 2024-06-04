package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import controller.MedicoController;

public class VerInformes extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	static String dni;
	String[] dniPaciente;
	String selectedDni;
	MedicoController controllerMedico = new MedicoController();
	JLabel lblVerCitasCon;
	JComboBox<String> comboBoxDniPacientes;
	JLabel lblNewLabel;
	JPanel panelInformes;
	JScrollPane scrollPane;
	private JButton btnCancelar;
	VentanaPrincipalMedico principal;
	ArrayList<byte[]> informes;
	ArrayList<String> horaCreacion;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VerInformes frame = new VerInformes(dni);
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
	public VerInformes(String dni) {

		VerInformes.dni = dni;
		dniPaciente = controllerMedico.dniPacientes(dni);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 523, 389);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(230, 230, 250));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);

		setContentPane(contentPane);

		lblVerCitasCon = new JLabel("Ver informes de los pacientes a cargo");
		lblVerCitasCon.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblVerCitasCon.setBounds(10, 32, 269, 27);
		contentPane.add(lblVerCitasCon);

		comboBoxDniPacientes = new JComboBox<String>();
		DefaultComboBoxModel<String> model = new DefaultComboBoxModel<String>();
		model.addElement("");
		for (String dniPaciente : dniPaciente) {
			model.addElement(dniPaciente);
		}
		comboBoxDniPacientes.setModel(model);
		comboBoxDniPacientes.setBounds(289, 36, 179, 21);
		comboBoxDniPacientes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					selectedDni = (String) comboBoxDniPacientes.getSelectedItem();
					informes = controllerMedico.findInformeJaspersoft(selectedDni);
					horaCreacion = controllerMedico.findHoraCreacion(selectedDni);
					Collections.reverse(informes);
					Collections.reverse(horaCreacion);
					panelInformes.removeAll();
					int totalHeight = 10 + informes.size() * 40;
					panelInformes.setPreferredSize(new Dimension(400, totalHeight));
					for (int i = 0; i < informes.size() && i < horaCreacion.size(); i++) {
						JLabel informeLabel = new JLabel("Informe " + (i + 1) + ": generado el " + horaCreacion.get(i));
						informeLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
						informeLabel.setBounds(8, 10 + (i * 40), 350, 15);
						panelInformes.add(informeLabel);

						JButton botonDescargar = new JButton("Descargar " + (i + 1));
						botonDescargar.setFont(new Font("Tahoma", Font.PLAIN, 10));
						botonDescargar.setBounds(380, 10 + (i * 40), 100, 15);
						int index = i;
						botonDescargar.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent e) {
								descargarInforme(index);
							}
						});
						panelInformes.add(botonDescargar);
					}

					panelInformes.revalidate();
					panelInformes.repaint();

				} catch (NullPointerException e1) {
					JOptionPane.showMessageDialog(VerInformes.this,
							"El DNI " + selectedDni + " no tiene informes a cargo");
				}
			}
		});

		contentPane.add(comboBoxDniPacientes);

		lblNewLabel = new JLabel("Informes de los pacientes");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel.setBounds(31, 70, 157, 37);
		contentPane.add(lblNewLabel);

		scrollPane = new JScrollPane();
		scrollPane.setBackground(new Color(230, 230, 250));
		scrollPane.setBounds(10, 100, 487, 201);
		contentPane.add(scrollPane);

		panelInformes = new JPanel();
		panelInformes.setBackground(new Color(230, 230, 250));
		panelInformes.setLayout(null);
		scrollPane.setViewportView(panelInformes);

		btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				principal = new VentanaPrincipalMedico(dni);
				principal.setVisible(true);
				dispose();
			}
		});
		btnCancelar.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnCancelar.setBounds(194, 315, 85, 27);
		contentPane.add(btnCancelar);
	}

	private void descargarInforme(int indice) {
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setDialogTitle("Guardar Informe");
		int userSelection = fileChooser.showSaveDialog(this);

		if (userSelection == JFileChooser.APPROVE_OPTION) {
			File fileToSave = fileChooser.getSelectedFile();
			String filePath = fileToSave.getAbsolutePath();

			if (!filePath.toLowerCase().endsWith(".pdf")) {
				filePath += ".pdf";
			}

			try (FileOutputStream fos = new FileOutputStream(filePath)) {
				fos.write(informes.get(indice));
				JOptionPane.showMessageDialog(this, "Informe descargado con éxito.");
			} catch (IOException ex) {
				ex.printStackTrace();
				JOptionPane.showMessageDialog(this, "Error al descargar el informe.");
			}
		}
	}
}
