package com.alura.jdbc.view;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.alura.jdbc.controller.HuespedesController;
import com.alura.jdbc.modelo.Huespedes;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import javax.swing.JTextField;
import javax.swing.JSeparator;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.awt.Font;
import javax.swing.JPasswordField;
import javax.swing.SwingConstants;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.Date;

public class Login extends JFrame {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtUsuario;
	private JPasswordField txtContrasena;
	int xMouse, yMouse;
	private JLabel labelExit;
	private HuespedesController usuarioController;
	String nombre,apellido,nacionalidad,telefono;
	Date fechaNacimiento;
	

	/**
	 * Create the frame.
	 */

	public Login() {

		this.usuarioController = new HuespedesController();
		
		setIconImage(Toolkit.getDefaultToolkit().getImage(MenuPrincipal.class.getResource("/com/alura/jdbc/imagenes/aH-40px.png")));

		setResizable(false);
		setUndecorated(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 788, 527);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);

		
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 788, 527);
		panel.setBackground(Color.WHITE);
		contentPane.add(panel);
		panel.setLayout(null);

		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(12, 138, 199));
		panel_1.setBounds(484, 0, 304, 527);
		panel.add(panel_1);
		panel_1.setLayout(null);

		JLabel imgHotel = new JLabel("");
		imgHotel.setBounds(0, 0, 304, 538);
		panel_1.add(imgHotel);
		imgHotel.setIcon(new ImageIcon(Login.class.getResource("/com/alura/jdbc/imagenes/img-hotel-login-.png")));

		JPanel btnexit = new JPanel();
		btnexit.setBounds(251, 0, 53, 36);
		panel_1.add(btnexit);
		btnexit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				System.exit(0);
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				btnexit.setBackground(Color.red);
				labelExit.setForeground(Color.white);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				btnexit.setBackground(new Color(12, 138, 199));
				labelExit.setForeground(Color.white);
			}
		});
		btnexit.setBackground(new Color(12, 138, 199));
		btnexit.setLayout(null);
		btnexit.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

		labelExit = new JLabel("X");
		labelExit.setBounds(0, 0, 53, 36);
		btnexit.add(labelExit);
		labelExit.setForeground(SystemColor.text);
		labelExit.setFont(new Font("Roboto", Font.PLAIN, 18));
		labelExit.setHorizontalAlignment(SwingConstants.CENTER);

		txtUsuario = new JTextField();
		txtUsuario.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				if (txtUsuario.getText().equals("Ingrese su nombre de usuario")) {
					txtUsuario.setText("123456");
					txtUsuario.setForeground(Color.black);
				}
				if (String.valueOf(txtContrasena.getPassword()).isEmpty()) {
					txtContrasena.setText("********");
					txtContrasena.setForeground(Color.gray);
				}
			}
		});
		txtUsuario.setFont(new Font("Roboto", Font.PLAIN, 16));
		txtUsuario.setText("1118536893");
		txtUsuario.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		txtUsuario.setForeground(SystemColor.activeCaptionBorder);
		txtUsuario.setBounds(65, 256, 324, 32);
		panel.add(txtUsuario);
		txtUsuario.setColumns(10);

		JSeparator separator = new JSeparator();
		separator.setBackground(new Color(0, 120, 215));
		separator.setBounds(65, 292, 324, 2);
		panel.add(separator);

		JLabel labelTitulo = new JLabel("INICIAR SESIÓN");
		labelTitulo.setForeground(SystemColor.textHighlight);
		labelTitulo.setFont(new Font("Roboto Black", Font.PLAIN, 26));
		labelTitulo.setBounds(140, 149, 400, 26);

		panel.add(labelTitulo);

		JSeparator separator_1 = new JSeparator();
		separator_1.setBackground(SystemColor.textHighlight);
		separator_1.setBounds(65, 393, 324, 2);
		panel.add(separator_1);

		txtContrasena = new JPasswordField();
		txtContrasena.setText("123456");
		txtContrasena.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				if (String.valueOf(txtContrasena.getPassword()).equals("********")) {
					txtContrasena.setText("");
					txtContrasena.setForeground(Color.black);
				}
				if (txtUsuario.getText().isEmpty()) {
					txtUsuario.setText("Ingrese su nombre de usuario");
					txtUsuario.setForeground(Color.gray);
				}
			}
		});
		txtContrasena.setForeground(SystemColor.activeCaptionBorder);
		txtContrasena.setFont(new Font("Roboto", Font.PLAIN, 16));
		txtContrasena.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		txtContrasena.setBounds(65, 353, 324, 32);
		panel.add(txtContrasena);

		JLabel LabelUsuario = new JLabel("USUARIO");
		LabelUsuario.setForeground(SystemColor.textInactiveText);
		LabelUsuario.setFont(new Font("Roboto Black", Font.PLAIN, 20));
		LabelUsuario.setBounds(65, 219, 107, 26);
		panel.add(LabelUsuario);

		JLabel lblContrasea = new JLabel("CONTRASEÑA");
		lblContrasea.setForeground(SystemColor.textInactiveText);
		lblContrasea.setFont(new Font("Roboto Black", Font.PLAIN, 20));
		lblContrasea.setBounds(65, 316, 140, 26);
		panel.add(lblContrasea);

		JPanel btnLogin = new JPanel();
		btnLogin.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnLogin.setBackground(new Color(0, 156, 223));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				btnLogin.setBackground(SystemColor.textHighlight);
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				iniciarSesion();
			}
		});
		btnLogin.setBackground(SystemColor.textHighlight);
		btnLogin.setBounds(175, 415, 122, 44);
		panel.add(btnLogin);
		btnLogin.setLayout(null);
		btnLogin.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

		JLabel lblLogin = new JLabel("ENTRAR");
		lblLogin.setBounds(0, 0, 122, 44);
		btnLogin.add(lblLogin);
		lblLogin.setForeground(SystemColor.controlLtHighlight);
		lblLogin.setHorizontalAlignment(SwingConstants.CENTER);
		lblLogin.setFont(new Font("Roboto", Font.PLAIN, 18));

		JLabel lblRegistrar = new JLabel("¿Aun no eres miembro?");
		lblRegistrar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		lblRegistrar.setBounds(35, 470, 400, 26);
		lblRegistrar.setForeground(SystemColor.textHighlight);
		lblRegistrar.setHorizontalAlignment(SwingConstants.CENTER);
		lblRegistrar.setFont(new Font("Roboto Black", Font.PLAIN, 16));
		panel.add(lblRegistrar);

		lblRegistrar.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseEntered(MouseEvent e) {
				lblRegistrar.setFont(new Font("Roboto Black", Font.PLAIN, 18));
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				RegistroUsuario registroUsuario = new RegistroUsuario();
				registroUsuario.setVisible(true);
				dispose();
			}
		});

		JLabel lblIcon = new JLabel("");
		lblIcon.setHorizontalAlignment(SwingConstants.CENTER);
		lblIcon.setIcon(new ImageIcon(Login.class.getResource("/com/alura/jdbc/imagenes/lOGO-50PX.png")));
		lblIcon.setBounds(65, 65, 350, 59); // 65, 65, 48, 59
		panel.add(lblIcon);

		JPanel header = new JPanel();
		header.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				headerMouseDragged(e);

			}
		});
		header.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				headerMousePressed(e);
			}
		});
		header.setBackground(SystemColor.window);
		header.setBounds(0, 0, 784, 36);
		panel.add(header);
		header.setLayout(null);
	}

	private void iniciarSesion() {
		
		
		String id = txtUsuario.getText().toUpperCase();
		String contraseña = new String(txtContrasena.getPassword());
		var consultaUsuario = usuarioController.compararContraseña(id, contraseña);
		
		if(consultaUsuario.isEmpty()) {
			JOptionPane.showMessageDialog(this,"Usuario o Contraseña no válidos"); 
		}else {
			
			consultaUsuario.forEach((n)->{ 
				nombre = n.getNombre();
				apellido = n.getApellido();
				fechaNacimiento = n.getFechaNacimiento();
				nacionalidad =n.getNacionalidad();
				telefono = n.getTelefono();
			
		});
			
			var usuario = new Huespedes(id, nombre, apellido, fechaNacimiento, nacionalidad, telefono, contraseña);
			
			MenuUsuario menu = new MenuUsuario(usuario);
			menu.setVisible(true);
			dispose();
			
		}
	}

	private void headerMousePressed(java.awt.event.MouseEvent evt) {
		xMouse = evt.getX();
		yMouse = evt.getY();
	}// GEN-LAST:event_headerMousePressed

	private void headerMouseDragged(java.awt.event.MouseEvent evt) {
		int x = evt.getXOnScreen();
		int y = evt.getYOnScreen();
		this.setLocation(x - xMouse, y - yMouse);
	}

}
