package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JComboBox;

public class AsignarEnfemerdadYTipo extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AsignarEnfemerdadYTipo frame = new AsignarEnfemerdadYTipo();
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
	public AsignarEnfemerdadYTipo() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(230, 230, 250));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblTipo = new JLabel("Tipo");
		lblTipo.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblTipo.setBounds(163, 170, 49, 21);
		contentPane.add(lblTipo);

		JComboBox<String> comboBoxTipo = new JComboBox<String>();
		comboBoxTipo.setBounds(245, 170, 115, 21);
		contentPane.add(comboBoxTipo);

		JLabel lblEnfermedad = new JLabel("Enfermedad");
		lblEnfermedad.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblEnfermedad.setBounds(163, 139, 89, 21);
		contentPane.add(lblEnfermedad);

		JComboBox<String> comboBoxEnfermedad = new JComboBox<String>();
		comboBoxEnfermedad.setBounds(241, 140, 119, 21);
		contentPane.add(comboBoxEnfermedad);
	}
}
