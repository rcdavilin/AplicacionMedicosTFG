package view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.util.Optional;

import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.text.MaskFormatter;

import org.bson.Document;

import controller.MedicoController;

public class InicioSesion extends JFrame {


    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
    JPasswordField passwordField;
    JLabel usernameLabel;
    private Registro registro; 
    JPanel contentPane;
    JLabel passwordLabel;
    JButton loginButton;
    JButton registerButton;
    JFormattedTextField formattedDni;
	private MaskFormatter mascara;
	MedicoController medicoController = new MedicoController();
	VentanaPrincipalMedico vpm;
	JRadioButton rdbtnMostrarContraseña, rdbtnCambioContraseña;
	CambioContraseña cambio;
	String username;

    
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    InicioSesion frame = new InicioSesion();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
    

    public InicioSesion() {
    	
    	setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 600, 456);

        contentPane = new JPanel();
        contentPane.setBackground(new Color(230, 230, 250));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        usernameLabel = new JLabel("DNI:");
        usernameLabel.setBounds(75, 96, 100, 30);
        usernameLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
        contentPane.add(usernameLabel);

        passwordLabel = new JLabel("Contraseña:");
        passwordLabel.setBounds(75, 147, 89, 30);
        passwordLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
        contentPane.add(passwordLabel);

        passwordField = new JPasswordField();
        passwordField.setBounds(164, 150, 200, 30);
        contentPane.add(passwordField);
        
        rdbtnMostrarContraseña = new JRadioButton("Mostrar contraseña");
        rdbtnMostrarContraseña.setFont(new Font("Tahoma", Font.PLAIN, 12));
        rdbtnMostrarContraseña.setBackground(new Color(230, 230, 250));
        rdbtnMostrarContraseña.setBounds(380, 153, 145, 21);
        contentPane.add(rdbtnMostrarContraseña);
        rdbtnMostrarContraseña.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		if (rdbtnMostrarContraseña.isSelected()) {
        			passwordField.setEchoChar((char) 0);
        		} else {
        			passwordField.setEchoChar('\u2022'); 
        		}
        	}
        });
        
        rdbtnCambioContraseña = new JRadioButton("Cambiar contraseña");
		rdbtnCambioContraseña.setFont(new Font("Tahoma", Font.PLAIN, 12));
		rdbtnCambioContraseña.setBackground(new Color(230, 230, 250));
		rdbtnCambioContraseña.setBounds(164, 190, 200, 21);
		contentPane.add(rdbtnCambioContraseña);
		rdbtnCambioContraseña.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(rdbtnCambioContraseña.isSelected()) {
					cambio = new CambioContraseña();
					cambio.setVisible(true);
					dispose();
				}
			}
		});
		

        loginButton = new JButton("Iniciar Sesión");
        loginButton.setBounds(292, 234, 150, 40);
        loginButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
        contentPane.add(loginButton);
        
        registerButton = new JButton("Registrarse");
        registerButton.setBounds(133, 234, 121, 40);
        registerButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
        contentPane.add(registerButton);
        registerButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		registro=new Registro();
        		registro.setVisible(true);
        		dispose();        		
        	}
        });
        
        try {
			mascara = new MaskFormatter("########?");
			mascara.setValidCharacters("0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ");
			formattedDni = new JFormattedTextField(mascara);
			formattedDni.setBounds(164, 100, 200, 27);
			contentPane.add(formattedDni);
			
			

		} catch (ParseException e) {
			e.printStackTrace();
		}
 
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Aquí iría el código para verificar las credenciales y realizar el inicio de sesión
                username = formattedDni.getText();
                String password = new String(passwordField.getPassword());
                
                Optional<Document> dni = medicoController.comprobarDni(username);
                Optional<Document> contraseña = medicoController.comprobarContraseña(password);
                if(dni.isPresent() && contraseña.isPresent()) {
                	vpm = new VentanaPrincipalMedico(username);
                	vpm.setVisible(true);
                	dispose();
                }else {
                	
                	JOptionPane.showMessageDialog(InicioSesion.this, "Usuario o contraseña incorrecto" );
                }
            }
        });
        
    }

}
