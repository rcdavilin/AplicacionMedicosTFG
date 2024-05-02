package view;

import java.awt.Color;
import java.awt.EventQueue;
import java.util.Optional;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;
import org.bson.Document;

import controller.MedicoController;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class VentanaVerInfoPersonal extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	MedicoController controllerMedico = new MedicoController();
	InicioSesion inicio;
	JScrollPane scrollPaneMostrar;
	JTextArea textAreaMostrar;
	JButton btnVerInfo;
	static String dni;
	Optional<Document> medico;
	JButton btnCancelar;
	VentanaPrincipalMedico principal;
	
	
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaVerInfoPersonal frame = new VentanaVerInfoPersonal(dni);
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
	public VentanaVerInfoPersonal(String dni) {
		VentanaVerInfoPersonal.dni = dni;
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 485, 438);
		
		contentPane = new JPanel();
		contentPane.setBackground(new Color(230, 230, 250));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		scrollPaneMostrar = new JScrollPane();
		scrollPaneMostrar.setBounds(0, 10, 471, 331);
		contentPane.add(scrollPaneMostrar);
		
		
		inicio = new InicioSesion();
		medico = controllerMedico.findByDni(dni);
		
		textAreaMostrar = new JTextArea();
		textAreaMostrar.setEditable(false);
		scrollPaneMostrar.setViewportView(textAreaMostrar);
		textAreaMostrar.setText(controllerMedico.mostrar(medico));
		
		btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				principal = new VentanaPrincipalMedico(dni);
				principal.setVisible(true);
				dispose();
			}
		});
		btnCancelar.setBounds(178, 370, 85, 21);
		contentPane.add(btnCancelar);
	

		
		
	}
}
