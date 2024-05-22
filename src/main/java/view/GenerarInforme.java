package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.border.EmptyBorder;

import org.bson.Document;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import controller.MedicoController;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JsonDataSource;

public class GenerarInforme extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	static String dni;
	String[] dniPaciente;
	JComboBox<String> comboBoxDniPacientes;
	JLabel lblGenerarInforme;
	JLabel lblIntroduzcaNombre;
	MedicoController controllerMedico = new MedicoController();
	JButton btnCancelar;
	VentanaPrincipalMedico principal;
	JButton btnGenerar;
	String selectedDni, nombrePaciente, apellidosPaciente, nombreMedico, apellidosMedico, fechaNaciemiento, sexo,
			especialidad, enfermedad, tipo, fechaIngreso, dniMedico;
	String[] alergenos;
	String[] medicamentos;
	String filePath = "C:\\Users\\dmartinjimenez\\JaspersoftWorkspace\\MyReports\\InformePrueba";
	String filejrxml = filePath + ".jrxml";
	HashMap<String, Object> parametros = new HashMap<>();
	JasperReport informeEXE;
	JasperPrint informeGenerado;

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
		setBounds(100, 100, 483, 357);
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

		btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(e -> {
			principal = new VentanaPrincipalMedico(dni);
			principal.setVisible(true);
			dispose();
		});
		btnCancelar.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnCancelar.setBounds(120, 213, 85, 33);
		contentPane.add(btnCancelar);

		btnGenerar = new JButton("Generar");
		btnGenerar.addActionListener(e -> {
			String filepdf = filePath + ".pdf";
			selectedDni = comboBoxDniPacientes.getSelectedItem().toString();
			int option = JOptionPane.showConfirmDialog(null,
					"¿Desea generar el informe al paciente con DNI " + selectedDni + "?", "Generar informe",
					JOptionPane.YES_NO_OPTION);
			if (option == JOptionPane.YES_OPTION) {
				Optional<Document> paciente = controllerMedico.findByDniPaciente(selectedDni);
				Optional<Document> medico = controllerMedico.findByDni(dni);
				alergenos = controllerMedico.findAlergenosPaciente(selectedDni);
				medicamentos = controllerMedico.findMedicamentosPaciente(selectedDni);
				nombrePaciente = controllerMedico.findNombrePacientePorDni(selectedDni);
				apellidosPaciente = controllerMedico.findApellidosPacientePorDni(selectedDni);
				fechaNaciemiento = controllerMedico.findFechaNacimientoPorDni(selectedDni);
				sexo = controllerMedico.findSexoPorDni(selectedDni);
				enfermedad = controllerMedico.findEnfermedadIngreso(selectedDni);
				tipo = controllerMedico.findTipo(selectedDni);
				fechaIngreso = controllerMedico.findFechaIngreso(selectedDni);
				dniMedico = controllerMedico.findDniMedico(selectedDni);
				nombreMedico = controllerMedico.findNombreMedicoPorDni(dniMedico);
				apellidosMedico = controllerMedico.findApellidosMedicoPorDni(dniMedico);
				especialidad = controllerMedico.findEspecialidadPorDni(dniMedico);

				parametros.put("dniPaciente", selectedDni);
				parametros.put("nombrePaciente", nombrePaciente);
				parametros.put("apellidosPaciente", apellidosPaciente);
				parametros.put("fechaNacimientoPaciente", fechaNaciemiento);
				parametros.put("sexoPaciente", sexo);
				parametros.put("alergenos", String.join(", ", alergenos));
				parametros.put("medicamentos", String.join(", ", medicamentos));
				parametros.put("enfermedad", enfermedad);
				parametros.put("tipo", tipo);
				parametros.put("fechaIngreso", fechaIngreso);
				parametros.put("dniMedico", dniMedico);
				parametros.put("nombreMedico", nombreMedico);
				parametros.put("apellidosMedico", apellidosMedico);
				parametros.put("especialidadMedico", especialidad);

				try {

					List<Document> documents = new ArrayList<>();
					paciente.ifPresent(documents::add);
					List<Document> data2 = new ArrayList<>();
					medico.ifPresent(data2::add);
					
					ObjectMapper mapper = new ObjectMapper();
					ArrayNode arrayNode = mapper.createArrayNode();

					for (Document doc : documents) {
						ObjectNode objectNode = mapper.readValue(doc.toJson(), ObjectNode.class);
						arrayNode.add(objectNode);
					}
					for (Document doc : data2) {
						ObjectNode objectNode = mapper.readValue(doc.toJson(), ObjectNode.class);
						arrayNode.add(objectNode);
					}

					String json = arrayNode.toString();

					ByteArrayInputStream jsonDataStream = new ByteArrayInputStream(json.getBytes());
					JsonDataSource dataSource = new JsonDataSource(jsonDataStream);

					JasperReport informeEXE = JasperCompileManager.compileReport(filejrxml);
					JasperPrint informeGenerado = JasperFillManager.fillReport(informeEXE, parametros, dataSource);

					JDialog progressDialog = new JDialog();
					progressDialog.setTitle("Generando Informe");
					progressDialog.setModal(true);
					progressDialog.setLayout(new BorderLayout());
					progressDialog.setSize(300, 100);
					progressDialog.setLocationRelativeTo(null);

					JProgressBar progressBar = new JProgressBar();
					progressBar.setIndeterminate(true);
					progressDialog.add(progressBar, BorderLayout.CENTER);

					JLabel progressLabel = new JLabel("Generando informe...");
					progressDialog.add(progressLabel, BorderLayout.SOUTH);
					progressDialog.setVisible(true);

					Thread.sleep(10);
					progressDialog.dispose();
					JasperExportManager.exportReportToPdfFile(informeGenerado, filepdf);
					JOptionPane.showMessageDialog(null, "Informe generado con éxito", "Generación de Informe",
							JOptionPane.INFORMATION_MESSAGE);

				} catch (Exception ex) {
					ex.printStackTrace();
				}

			}
		});
		btnGenerar.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnGenerar.setBounds(276, 213, 85, 33);
		contentPane.add(btnGenerar);
	}
}
