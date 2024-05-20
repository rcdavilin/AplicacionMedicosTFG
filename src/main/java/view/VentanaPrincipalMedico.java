package view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import controller.MedicoController;

public class VentanaPrincipalMedico extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	JLabel lblTitulo;
	VentanaVerInfoPersonal info;
	InicioSesion inicio;
	MedicoController controllerMedico = new MedicoController();
	static String dni;
	VerPacientesCargo pacientesCargo;
	AnadirMedicamentoTarjeta anadir;
	JMenuBar menuBar;
	JMenu mnVerPacientesCargo;
	JMenuItem mntmVerPacientesCargo;
	JMenu mnGestionCitas;
	JMenuItem mntmAsignarCitasPacientes;
	JMenuItem mntmAbrirCitasMedicas;
	JMenuItem mntmVerCitasPacientes;
	JMenu mnGestionMedicamentos;
	JMenuItem mntmAñadirMedicamentosPaciente;
	JMenuItem mntmEliminarMedicamentosPaciente;
	JMenuItem mntmVerHistorialMedico ;
	AsignarCitaPaciente asignar;
	EliminarMedicamentoTarjeta eliminar;
	VerCitasConLosPacientes verCitas;
	AbrirCitas abrir;
	private JMenuItem mntmVerMedicamentosPaciente;
	VerMedicamentosPaciente verMedicamentos;
	VerHistorialMedico historial;
	private JMenu mnAnadirEnfermedadTipo;
	JMenuItem mntmEnfermedadTipo;
	AsignarEnfemerdadYTipo enfermedadTipo;
	VerEnfermedadTipo enfermedad;
	JMenuItem mntmVerEnferemedadTipo;
	private JMenuItem mntmModificarEnfermedadTipo;
	ModificarEnfermedadTipo modificarEnfermedad;
	private JLabel lblNewLabel;
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
		
		menuBar = new JMenuBar();
		menuBar.setBorder(new LineBorder(new Color(0, 0, 0)));
		menuBar.setBackground(new Color(230, 230, 250));
		menuBar.setBounds(0, 89, 534, 22);
		contentPane.add(menuBar);
		
		mnVerPacientesCargo = new JMenu("Ver pacientes a cargo");
		mnVerPacientesCargo.setBorder(new LineBorder(new Color(0, 0, 0)));
		mnVerPacientesCargo.setBackground(new Color(230, 230, 250));
		menuBar.add(mnVerPacientesCargo);
		
		mntmVerPacientesCargo = new JMenuItem("Ver pacientes a cargo");
		mntmVerPacientesCargo.setBorder(new LineBorder(new Color(0, 0, 0)));
		mntmVerPacientesCargo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pacientesCargo = new VerPacientesCargo(dni);
				pacientesCargo.setVisible(true);
				dispose();
			}
		});
		mnVerPacientesCargo.add(mntmVerPacientesCargo);
		
		mntmVerHistorialMedico = new JMenuItem("Ver historial medico");
		mntmVerHistorialMedico.setBorder(new LineBorder(new Color(0, 0, 0)));
		mntmVerHistorialMedico.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				historial = new VerHistorialMedico(dni);
				historial.setVisible(true);
				dispose();
			}
		});
		mnVerPacientesCargo.add(mntmVerHistorialMedico);
		
		mnGestionCitas = new JMenu("Gestion citas");
		mnGestionCitas.setBackground(new Color(230, 230, 250));
		mnGestionCitas.setBorder(new LineBorder(new Color(0, 0, 0)));
		menuBar.add(mnGestionCitas);
		
		mntmAsignarCitasPacientes = new JMenuItem("Asignar citas a pacientes");
		mntmAsignarCitasPacientes.setBorder(new LineBorder(new Color(0, 0, 0)));
		mntmAsignarCitasPacientes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				asignar = new AsignarCitaPaciente(dni);
				asignar.setVisible(true);
				dispose();
			}
		});
		mnGestionCitas.add(mntmAsignarCitasPacientes);
		
		mntmAbrirCitasMedicas = new JMenuItem("Abrir citas medicas");
		mntmAbrirCitasMedicas.setBorder(new LineBorder(new Color(0, 0, 0)));
		mntmAbrirCitasMedicas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				abrir = new AbrirCitas(dni);
				abrir.setVisible(true);
				dispose();
			}
		});
		mnGestionCitas.add(mntmAbrirCitasMedicas);
		
		mntmVerCitasPacientes = new JMenuItem("Ver citas con los pacientes");
		mntmVerCitasPacientes.setBorder(new LineBorder(new Color(0, 0, 0)));
		mntmVerCitasPacientes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				verCitas = new VerCitasConLosPacientes(dni);
				verCitas.setVisible(true);
				dispose();
			}
		});
		mnGestionCitas.add(mntmVerCitasPacientes);
		
		mnGestionMedicamentos = new JMenu("Gestion medicamentos");
		mnGestionMedicamentos.setBorder(new LineBorder(new Color(0, 0, 0)));
		mnGestionMedicamentos.setBackground(new Color(230, 230, 250));
		menuBar.add(mnGestionMedicamentos);
		
		mntmAñadirMedicamentosPaciente = new JMenuItem("Añadir medicamentos a paciente");
		mntmAñadirMedicamentosPaciente.setBorder(new LineBorder(new Color(0, 0, 0)));
		mntmAñadirMedicamentosPaciente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				anadir = new AnadirMedicamentoTarjeta(dni);
				anadir.setVisible(true);
				dispose();
			}
		});
		mnGestionMedicamentos.add(mntmAñadirMedicamentosPaciente);
		
		mntmEliminarMedicamentosPaciente = new JMenuItem("Eliminar medicamentos del paciente");
		mntmEliminarMedicamentosPaciente.setBorder(new LineBorder(new Color(0, 0, 0)));
		mntmEliminarMedicamentosPaciente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				eliminar = new EliminarMedicamentoTarjeta(dni);
				eliminar.setVisible(true);
				dispose();
			}
		});
		mnGestionMedicamentos.add(mntmEliminarMedicamentosPaciente);
		
		mntmVerMedicamentosPaciente = new JMenuItem("Ver medicamentos del paciente");
		mntmVerMedicamentosPaciente.setBorder(new LineBorder(new Color(0, 0, 0)));
		mntmVerMedicamentosPaciente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				verMedicamentos = new VerMedicamentosPaciente(dni);
				verMedicamentos.setVisible(true);
				dispose();
			}
		});
		mnGestionMedicamentos.add(mntmVerMedicamentosPaciente);
		
		mnAnadirEnfermedadTipo = new JMenu("Enfermedad ingreso y tipo");
		mnAnadirEnfermedadTipo.setBorder(new LineBorder(new Color(0, 0, 0)));
		menuBar.add(mnAnadirEnfermedadTipo);
		
		mntmEnfermedadTipo = new JMenuItem("Añadir enfermedad y tipo");
		mntmEnfermedadTipo.setBorder(new LineBorder(new Color(0, 0, 0)));
		mntmEnfermedadTipo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				enfermedadTipo = new AsignarEnfemerdadYTipo(dni);
				enfermedadTipo.setVisible(true);
				dispose();
			}
		});
		mnAnadirEnfermedadTipo.add(mntmEnfermedadTipo);
		
		mntmVerEnferemedadTipo = new JMenuItem("Ver enfermedad ingreso y tipo");
		mntmVerEnferemedadTipo.setBorder(new LineBorder(new Color(0, 0, 0)));
		mntmVerEnferemedadTipo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				enfermedad = new VerEnfermedadTipo(dni);
				enfermedad.setVisible(true);
				dispose();
			}
		});
		mnAnadirEnfermedadTipo.add(mntmVerEnferemedadTipo);
		
		mntmModificarEnfermedadTipo = new JMenuItem("Modificar enfermedad ingreso y tipo");
		mntmModificarEnfermedadTipo.setBorder(new LineBorder(new Color(0, 0, 0)));
		mntmModificarEnfermedadTipo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				modificarEnfermedad = new ModificarEnfermedadTipo(dni);
				modificarEnfermedad.setVisible(true);
				dispose();
			}
		});
		mnAnadirEnfermedadTipo.add(mntmModificarEnfermedadTipo);
		
		lblNewLabel = new JLabel("<html><u>Ver informacion personal</html></u>");
		lblNewLabel.setForeground(new Color(0, 128, 255));
		lblNewLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				info = new VentanaVerInfoPersonal(dni);
				info.setVisible(true);
				dispose();
			}
		});
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel.setBounds(360, 41, 147, 21);
		contentPane.add(lblNewLabel);
		
	
	}
}