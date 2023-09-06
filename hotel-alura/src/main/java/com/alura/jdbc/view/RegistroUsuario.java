package com.alura.jdbc.view;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import java.awt.Color;

import com.alura.jdbc.controller.HuespedesController;
import com.alura.jdbc.modelo.Huespedes;
import com.toedter.calendar.JDateChooser;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Font;
import javax.swing.ImageIcon;
import java.awt.SystemColor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.text.Format;
import java.awt.Toolkit;
import javax.swing.SwingConstants;
import javax.swing.JSeparator;

@SuppressWarnings("serial")
public class RegistroUsuario extends JFrame {

	private JPanel contentPane;
	private JTextField txtNombre;
	private JTextField txtApellido;
	private JTextField txtTelefono;
	private JTextField txtId;
	private JDateChooser txtFechaN;
	private JComboBox<Format> txtNacionalidad;
	private JPasswordField txtContrasena;
	private JLabel labelExit;
	private JLabel labelAtras;
	int xMouse, yMouse;

	HuespedesController usuarioController;

	/**
	 * Create the frame.
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public RegistroUsuario() {

		this.usuarioController = new HuespedesController();
		setIconImage(Toolkit.getDefaultToolkit()
				.getImage(RegistroUsuario.class.getResource("/com/alura/jdbc/imagenes/lOGO-50PX.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 910, 655);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.text);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		setLocationRelativeTo(null);
		setUndecorated(true);
		contentPane.setLayout(null);

		JPanel header = new JPanel();
		header.setBounds(0, 0, 910, 36);
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
		header.setLayout(null);
		header.setBackground(SystemColor.text);
		header.setOpaque(false);
		header.setBounds(0, 0, 910, 36);
		contentPane.add(header);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 489, 634);
		panel.setBackground(new Color(12, 138, 199));
		contentPane.add(panel);
		panel.setLayout(null);

		JLabel imagenFondo = new JLabel("");
		imagenFondo.setBounds(0, 121, 479, 502);
		panel.add(imagenFondo);
		imagenFondo.setIcon(new ImageIcon(RegistroUsuario.class.getResource("/com/alura/jdbc/imagenes/registro.png")));

		JLabel logo = new JLabel("");
		logo.setBounds(194, 39, 104, 107);
		panel.add(logo);
		logo.setIcon(new ImageIcon(RegistroUsuario.class.getResource("/com/alura/jdbc/imagenes/Ha-100px.png")));


		txtId = new JTextField();
		txtId.setFont(new Font("Roboto", Font.PLAIN, 16));
		txtId.setBounds(560, 125, 285, 33);
		txtId.setColumns(10);
		txtId.setBackground(Color.WHITE);
		txtId.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		contentPane.add(txtId);

		txtNombre = new JTextField();
		txtNombre.setFont(new Font("Roboto", Font.PLAIN, 16));
		txtNombre.setBounds(560, 189, 285, 33);
		txtNombre.setBackground(Color.WHITE);
		txtNombre.setColumns(10);
		txtNombre.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		contentPane.add(txtNombre);

		txtApellido = new JTextField();
		txtApellido.setFont(new Font("Roboto", Font.PLAIN, 16));
		txtApellido.setBounds(560, 252, 285, 36); // 560, 204, 285, 33
		txtApellido.setColumns(10);
		txtApellido.setBackground(Color.WHITE);
		txtApellido.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		contentPane.add(txtApellido);

		txtFechaN = new JDateChooser();
		txtFechaN.setBounds(560, 325, 289, 36);
		txtFechaN.getCalendarButton().setIcon(
				new ImageIcon(RegistroHuesped.class.getResource("/com/alura/jdbc/imagenes/icon-reservas.png")));
		txtFechaN.getCalendarButton().setBackground(SystemColor.textHighlight);
		txtFechaN.setDateFormatString("yyyy-MM-dd");
		contentPane.add(txtFechaN);

		txtTelefono = new JTextField();
		txtTelefono.setFont(new Font("Roboto", Font.PLAIN, 16));
		txtTelefono.setBounds(560, 394, 285, 33);
		txtTelefono.setColumns(10);
		txtTelefono.setBackground(Color.WHITE);
		txtTelefono.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		contentPane.add(txtTelefono);

		txtNacionalidad = new JComboBox();
		txtNacionalidad.setBounds(560, 465, 285, 33);
		txtNacionalidad.setBackground(SystemColor.text);
		txtNacionalidad.setFont(new Font("Roboto", Font.PLAIN, 16));
		txtNacionalidad.setModel(new DefaultComboBoxModel(new String[] { "COLOMBIANO-COLOMBIANA", "AFGANO-AFGANA",
				"ALEMÁN-ALEMANA", "ÁRABE-ÁRABE", "ARGENTINO-ARGENTINA", "AUSTRALIANO-AUSTRALIANA", "BELGA-BELGA",
				"BOLIVIANO-BOLIVIANA", "BRASILEÑO-BRASILEÑA", "CAMBOYANO-CAMBOYANA", "CANADIENSE-CANADIENSE",
				"CHILENO-CHILENA", "CHINO-CHINA", "COREANO-COREANA", "COSTARRICENSE-COSTARRICENSE", "CUBANO-CUBANA",
				"DANÉS-DANESA", "ECUATORIANO-ECUATORIANA", "EGIPCIO-EGIPCIA", "SALVADOREÑO-SALVADOREÑA",
				"ESCOCÉS-ESCOCESA", "ESPAÑOL-ESPAÑOLA", "ESTADOUNIDENSE-ESTADOUNIDENSE", "ESTONIO-ESTONIA",
				"ETIOPE-ETIOPE", "FILIPINO-FILIPINA", "FINLANDÉS-FINLANDESA", "FRANCÉS-FRANCESA", "GALÉS-GALESA",
				"GRIEGO-GRIEGA", "GUATEMALTECO-GUATEMALTECA", "HAITIANO-HAITIANA", "HOLANDÉS-HOLANDESA",
				"HONDUREÑO-HONDUREÑA", "INDONÉS-INDONESA", "INGLÉS-INGLESA", "IRAQUÍ-IRAQUÍ", "IRANÍ-IRANÍ",
				"IRLANDÉS-IRLANDESA", "ISRAELÍ-ISRAELÍ", "ITALIANO-ITALIANA", "JAPONÉS-JAPONESA", "JORDANO-JORDANA",
				"LAOSIANO-LAOSIANA", "LETÓN-LETONA", "LETONÉS-LETONESA", "MALAYO-MALAYA", "MARROQUÍ-MARROQUÍ",
				"MEXICANO-MEXICANA", "NICARAGÜENSE-NICARAGÜENSE", "NORUEGO-NORUEGA", "NEOZELANDÉS-NEOZELANDESA",
				"PANAMEÑO-PANAMEÑA", "PARAGUAYO-PARAGUAYA", "PERUANO-PERUANA", "POLACO-POLACA", "PORTUGUÉS-PORTUGUESA",
				"PUERTORRIQUEÑO-PUERTORRIQUEÑO", "DOMINICANO-DOMINICANA", "RUMANO-RUMANA", "RUSO-RUSA", "SUECO-SUECA",
				"SUIZO-SUIZA", "TAILANDÉS-TAILANDESA", "TAIWANES-TAIWANESA", "TURCO-TURCA", "UCRANIANO-UCRANIANA",
				"URUGUAYO-URUGUAYA", "VENEZOLANO-VENEZOLANA", "VIETNAMITA-VIETNAMITA" }));
		contentPane.add(txtNacionalidad);

		txtContrasena = new JPasswordField();
		txtContrasena.setText("123456");
		txtContrasena.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				if (String.valueOf(txtContrasena.getPassword()).equals("********")) {
					txtContrasena.setText("");
					txtContrasena.setForeground(Color.black);
				}
			}
		});
		txtContrasena.setForeground(SystemColor.activeCaptionBorder);
		txtContrasena.setFont(new Font("Roboto", Font.PLAIN, 16));
		txtContrasena.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		txtContrasena.setBounds(562, 545, 253, 14);
		contentPane.add(txtContrasena);
		
		
		

		JLabel lblId = new JLabel("NÚMERO IDENTIFICACIÓN");
		lblId.setBounds(562, 109, 253, 14);
		lblId.setForeground(SystemColor.textInactiveText);
		lblId.setFont(new Font("Roboto Black", Font.PLAIN, 16));
		contentPane.add(lblId);

		JLabel lblNombre = new JLabel("NOMBRE");
		lblNombre.setBounds(560, 175, 255, 14);
		lblNombre.setForeground(SystemColor.textInactiveText);
		lblNombre.setFont(new Font("Roboto Black", Font.PLAIN, 16));
		contentPane.add(lblNombre);

		JLabel lblApellido = new JLabel("APELLIDO");
		lblApellido.setBounds(560, 236, 255, 14);
		lblApellido.setForeground(SystemColor.textInactiveText);
		lblApellido.setFont(new Font("Roboto Black", Font.PLAIN, 16));
		contentPane.add(lblApellido);

		JLabel lblFechaN = new JLabel("FECHA DE NACIMIENTO");
		lblFechaN.setBounds(560, 301, 255, 14);
		lblFechaN.setForeground(SystemColor.textInactiveText);
		lblFechaN.setFont(new Font("Roboto Black", Font.PLAIN, 16));
		contentPane.add(lblFechaN);

		JLabel lblNacionalidad = new JLabel("NACIONALIDAD");
		lblNacionalidad.setBounds(560, 440, 253, 14);
		lblNacionalidad.setForeground(SystemColor.textInactiveText);
		lblNacionalidad.setFont(new Font("Roboto Black", Font.PLAIN, 16));
		contentPane.add(lblNacionalidad);

		JLabel lblTelefono = new JLabel("TELÉFONO");
		lblTelefono.setBounds(562, 376, 253, 14);
		lblTelefono.setForeground(SystemColor.textInactiveText);
		lblTelefono.setFont(new Font("Roboto Black", Font.PLAIN, 16));
		contentPane.add(lblTelefono);

		JLabel lblContraseña = new JLabel("CONTRASEÑA");
		lblContraseña.setBounds(562, 513, 253, 14);
		lblContraseña.setForeground(SystemColor.textInactiveText);
		lblContraseña.setFont(new Font("Roboto Black", Font.PLAIN, 16));
		contentPane.add(lblContraseña);

		JLabel lblTitulo = new JLabel("REGISTRO USUARIO");
		lblTitulo.setBounds(585, 55, 334, 42);
		lblTitulo.setForeground(new Color(12, 138, 199));
		lblTitulo.setFont(new Font("Roboto Black", Font.BOLD, 23));
		contentPane.add(lblTitulo);

		JSeparator separator_1_2 = new JSeparator();
		separator_1_2.setBounds(560, 160, 289, 2);
		separator_1_2.setForeground(new Color(12, 138, 199));
		separator_1_2.setBackground(new Color(12, 138, 199));
		contentPane.add(separator_1_2);

		JSeparator separator_1_2_1 = new JSeparator();
		separator_1_2_1.setBounds(560, 225, 289, 2);
		separator_1_2_1.setForeground(new Color(12, 138, 199));
		separator_1_2_1.setBackground(new Color(12, 138, 199));
		contentPane.add(separator_1_2_1);

		JSeparator separator_1_2_2 = new JSeparator();
		separator_1_2_2.setBounds(560, 288, 289, 2);
		separator_1_2_2.setForeground(new Color(12, 138, 199));
		separator_1_2_2.setBackground(new Color(12, 138, 199));
		contentPane.add(separator_1_2_2);

		JSeparator separator_1_2_3 = new JSeparator();
		separator_1_2_3.setBounds(560, 361, 289, 2);
		separator_1_2_3.setForeground(new Color(12, 138, 199));
		separator_1_2_3.setBackground(new Color(12, 138, 199));
		contentPane.add(separator_1_2_3);

		JSeparator separator_1_2_4 = new JSeparator();
		separator_1_2_4.setBounds(560, 427, 289, 2);
		separator_1_2_4.setForeground(new Color(12, 138, 199));
		separator_1_2_4.setBackground(new Color(12, 138, 199));
		contentPane.add(separator_1_2_4);

		JSeparator separator_1_2_5 = new JSeparator();
		separator_1_2_5.setBounds(560, 498, 289, 2);
		separator_1_2_5.setForeground(new Color(12, 138, 199));
		separator_1_2_5.setBackground(new Color(12, 138, 199));
		contentPane.add(separator_1_2_5);

		JSeparator separator_1_2_6 = new JSeparator();
		separator_1_2_6.setBounds(560, 570, 289, 2);
		separator_1_2_6.setForeground(new Color(12, 138, 199));
		separator_1_2_6.setBackground(new Color(12, 138, 199));
		contentPane.add(separator_1_2_6);


		
		JPanel btnAtras = new JPanel();
		btnAtras.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				MenuPrincipal menuprincipal = new MenuPrincipal(); 
				menuprincipal.setVisible(true);
				dispose();				
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				btnAtras.setBackground(new Color(12, 138, 199));
				labelAtras.setForeground(Color.white);
			}			
			@Override
			public void mouseExited(MouseEvent e) {
				 btnAtras.setBackground(Color.white);
			     labelAtras.setForeground(Color.black);
			}
		});
		btnAtras.setLayout(null);
		btnAtras.setBackground(Color.WHITE);
		btnAtras.setBounds(0, 0, 53, 36);
		header.add(btnAtras);
		
		labelAtras = new JLabel("<");
		labelAtras.setBounds(0, 0, 53, 36);
		btnAtras.add(labelAtras);
		labelAtras.setHorizontalAlignment(SwingConstants.CENTER);
		labelAtras.setFont(new Font("Roboto", Font.PLAIN, 23));
	
			
		JPanel btnguardar = new JPanel();
		btnguardar.setBounds(640, 600, 122, 35);
		btnguardar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				if (txtFechaN.getDate() == null || txtId.getText().isEmpty() || txtNombre.getText().isEmpty()
						|| txtApellido.getText().isEmpty() || txtTelefono.getText().isEmpty()) {

					JOptionPane.showMessageDialog(null, "Debes llenar todos los campos.");

				} else {
					String id= txtId.getText();
					
					System.out.println(id);
					
					String contraseña=new String (txtContrasena.getPassword());

					var usuarios = new Huespedes(txtId.getText().toUpperCase(), txtNombre.getText().toUpperCase(),
							txtApellido.getText().toUpperCase(), txtFechaN.getDate(), (String) txtNacionalidad.getSelectedItem(),
							txtTelefono.getText().toUpperCase(), contraseña);

					if(usuarioController.RegistrarUsuario(usuarios)) {
						JOptionPane.showMessageDialog(null, "Registro Exitoso.");
					
						MenuUsuario menuUsuario = new MenuUsuario(usuarios);
						menuUsuario.setVisible(true);
						dispose();
						
					} else {
						JOptionPane.showMessageDialog(null, "Este documento de identidad ya se encuentra registrado");
					}
				}

			}
		});

		btnguardar.setLayout(null);
		btnguardar.setBackground(new Color(12, 138, 199));
		contentPane.add(btnguardar);
		btnguardar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

		JLabel labelGuardar = new JLabel("GUARDAR");
		labelGuardar.setHorizontalAlignment(SwingConstants.CENTER);
		labelGuardar.setForeground(Color.WHITE);
		labelGuardar.setFont(new Font("Roboto", Font.BOLD, 18));
		labelGuardar.setBounds(0, 0, 122, 35);
		btnguardar.add(labelGuardar);
		
		labelExit = new JLabel("X");
		labelExit.setHorizontalAlignment(SwingConstants.CENTER);
		labelExit.setForeground(Color.BLACK);
		labelExit.setFont(new Font("Roboto", Font.PLAIN, 23));
		labelExit.setBounds(0, 0, 53, 36);

	
		
		JPanel btnexit = new JPanel();
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
				 btnexit.setBackground(Color.white);
			     labelExit.setForeground(Color.black);
			}
		});
		btnexit.setLayout(null);
		btnexit.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		btnexit.setBackground(Color.WHITE);
		btnexit.add(labelExit);
		btnexit.setBounds(857, 0, 53, 36);
		header.add(btnexit);
	}

	// Código que permite mover la ventana por la pantalla según la posición de "x"
	// y "y"
	private void headerMousePressed(java.awt.event.MouseEvent evt) {
		xMouse = evt.getX();
		yMouse = evt.getY();
	}

	private void headerMouseDragged(java.awt.event.MouseEvent evt) {
		int x = evt.getXOnScreen();
		int y = evt.getYOnScreen();
		this.setLocation(x - xMouse, y - yMouse);
	}

}
