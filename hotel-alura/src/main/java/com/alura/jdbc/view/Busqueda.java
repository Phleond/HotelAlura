package com.alura.jdbc.view;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

import com.alura.jdbc.controller.ReservasController;
import com.alura.jdbc.controller.HuespedesController;
import com.alura.jdbc.modelo.Huespedes;

import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ImageIcon;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTabbedPane;
import java.awt.Toolkit;
import javax.swing.SwingConstants;
import javax.swing.ListSelectionModel;
import javax.swing.RowFilter;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

@SuppressWarnings("serial")
public class Busqueda extends JFrame {

	private JPanel contentPane;
	private JTextField txtBuscar;
	private JTable tbHuespedes;
	private JTable tbReservas;
	private JTable tbHuespedesConReserva;
	private JTable tbtablaTemporal;
	private DefaultTableModel modelo;
	private DefaultTableModel modeloHuesped;
	private DefaultTableModel modeloHuespedConReserva;
	private JLabel labelAtras;
	private JLabel labelExit;
	private ReservasController reservaController;
	private HuespedesController huespedController;
	int xMouse, yMouse;

	/**
	 * Create the frame.
	 * 
	 * @param huesped
	 */
	public Busqueda(Huespedes huesped) {

		this.reservaController = new ReservasController();
		this.huespedController = new HuespedesController();

		tbtablaTemporal = new JTable();

		setIconImage(
				Toolkit.getDefaultToolkit().getImage(Busqueda.class.getResource("/com/alura/jdbc/imagenes/lupa2.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 910, 571);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);
		setUndecorated(true);

		txtBuscar = new JTextField();
		txtBuscar.setBounds(536, 127, 193, 31);
		txtBuscar.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		contentPane.add(txtBuscar);
		txtBuscar.setColumns(15);

		JLabel lblNewLabel_4 = new JLabel("SISTEMA DE BÚSQUEDA");
		lblNewLabel_4.setForeground(new Color(12, 138, 199));
		lblNewLabel_4.setFont(new Font("Roboto Black", Font.BOLD, 24));
		lblNewLabel_4.setBounds(320, 62, 350, 42);
		contentPane.add(lblNewLabel_4);

		JTabbedPane panel = new JTabbedPane(JTabbedPane.TOP);
		panel.setBackground(new Color(12, 138, 199));
		panel.setFont(new Font("Roboto", Font.PLAIN, 16));
		panel.setBounds(20, 169, 865, 328);

		tbReservas = new JTable();
		tbReservas.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tbReservas.setFont(new Font("Roboto", Font.PLAIN, 16));
		tbReservas.setName("tbReservas");
		modelo = (DefaultTableModel) tbReservas.getModel();
		modelo.addColumn("Numero de Reserva");
		modelo.addColumn("Fecha Check In");
		modelo.addColumn("Fecha Check Out");
		modelo.addColumn("Valor");
		modelo.addColumn("Forma de Pago");

		JScrollPane scroll_table = new JScrollPane(tbReservas);
		panel.addTab("Reservas", new ImageIcon(Busqueda.class.getResource("/com/alura/jdbc/imagenes/reservado.png")),
				scroll_table);
		cargaReservas();

		tbHuespedesConReserva = new JTable();
		tbHuespedesConReserva.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tbHuespedesConReserva.setFont(new Font("Roboto", Font.PLAIN, 16));
		tbHuespedesConReserva.setName("tbHuespedesConReserva");
		modeloHuespedConReserva = (DefaultTableModel) tbHuespedesConReserva.getModel();
		modeloHuespedConReserva.addColumn("Número de Huesped");
		modeloHuespedConReserva.addColumn("Nombre");
		modeloHuespedConReserva.addColumn("Apellido");
		modeloHuespedConReserva.addColumn("Fecha de Nacimiento");
		modeloHuespedConReserva.addColumn("Nacionalidad");
		modeloHuespedConReserva.addColumn("Telefono");
		modeloHuespedConReserva.addColumn("Número de Reserva");

		JScrollPane scroll_tableHuespedesConReserva = new JScrollPane(tbHuespedesConReserva);

		panel.addTab("Huéspedes con Reservas",
				new ImageIcon(Busqueda.class.getResource("/com/alura/jdbc/imagenes/pessoas.png")),
				scroll_tableHuespedesConReserva);
		cargarHuespedConReservas();

		tbHuespedes = new JTable();
		tbHuespedes.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tbHuespedes.setFont(new Font("Roboto", Font.PLAIN, 16));
		tbHuespedes.setName("tbHuespedes");
		modeloHuesped = (DefaultTableModel) tbHuespedes.getModel();
		modeloHuesped.addColumn("Número de Huesped");
		modeloHuesped.addColumn("Nombre");
		modeloHuesped.addColumn("Apellido");
		modeloHuesped.addColumn("Fecha de Nacimiento");
		modeloHuesped.addColumn("Nacionalidad");
		modeloHuesped.addColumn("Telefono");

		JScrollPane scroll_tableHuespedes = new JScrollPane(tbHuespedes);
		panel.addTab("Huéspedes", new ImageIcon(Busqueda.class.getResource("/com/alura/jdbc/imagenes/persona.png")),
				scroll_tableHuespedes);
		cargarHuesped();

		panel.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {

				int tabIndex = panel.getSelectedIndex();
				if (tabIndex != -1) {
					tbtablaTemporal.setName(panel.getTitleAt(tabIndex));
					tbHuespedes.clearSelection();
					tbReservas.clearSelection();
					tbHuespedesConReserva.clearSelection();

				}
			}
		});

		contentPane.add(panel);
		setResizable(false);

		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setIcon(new ImageIcon(Busqueda.class.getResource("/com/alura/jdbc/imagenes/Ha-100px.png")));
		lblNewLabel_2.setBounds(56, 51, 104, 107);
		contentPane.add(lblNewLabel_2);

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
		header.setLayout(null);
		header.setBackground(Color.WHITE);
		header.setBounds(0, 0, 910, 36);
		contentPane.add(header);

		JPanel btnAtras = new JPanel();
		btnAtras.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				MenuUsuario menu = new MenuUsuario(huesped);
				menu.setVisible(true);
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
		labelAtras.setHorizontalAlignment(SwingConstants.CENTER);
		labelAtras.setFont(new Font("Roboto", Font.PLAIN, 23));
		labelAtras.setBounds(0, 0, 53, 36);
		btnAtras.add(labelAtras);

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
				btnexit.setBackground(new Color(12, 138, 199));
				labelExit.setForeground(Color.white);
			}
		});
		btnexit.setLayout(null);
		btnexit.setBackground(Color.WHITE);
		btnexit.setBounds(857, 0, 53, 36);
		header.add(btnexit);

		labelExit = new JLabel("X");
		labelExit.setHorizontalAlignment(SwingConstants.CENTER);
		labelExit.setForeground(Color.BLACK);
		labelExit.setFont(new Font("Roboto", Font.PLAIN, 18));
		labelExit.setBounds(0, 0, 53, 36);
		btnexit.add(labelExit);

		JSeparator separator_1_2 = new JSeparator();
		separator_1_2.setForeground(new Color(12, 138, 199));
		separator_1_2.setBackground(new Color(12, 138, 199));
		separator_1_2.setBounds(539, 159, 193, 2);
		contentPane.add(separator_1_2);

		JPanel btnbuscar = new JPanel();

		btnbuscar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				String BuscarTermino = txtBuscar.getText().toUpperCase();

				if (panel.getSelectedIndex() == 0) {
					buscarEnTabla(tbReservas, BuscarTermino);
				} else if (panel.getSelectedIndex() == 1) {
					buscarEnTabla(tbHuespedesConReserva, BuscarTermino);
				} else if (panel.getSelectedIndex() == 2) {
					buscarEnTabla(tbHuespedes, BuscarTermino);
				}
			}

		});

		btnbuscar.setLayout(null);
		btnbuscar.setBackground(new Color(12, 138, 199));
		btnbuscar.setBounds(748, 125, 122, 35);
		btnbuscar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		contentPane.add(btnbuscar);

		JLabel lblBuscar = new JLabel("BUSCAR");
		lblBuscar.setBounds(0, 0, 122, 35);
		btnbuscar.add(lblBuscar);
		lblBuscar.setHorizontalAlignment(SwingConstants.CENTER);
		lblBuscar.setForeground(Color.WHITE);
		lblBuscar.setFont(new Font("Roboto", Font.PLAIN, 18));

		JPanel btnEditar = new JPanel();
		btnEditar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				if (!filaSeleccionada(tbHuespedes) || !filaSeleccionada(tbReservas)
						|| !filaSeleccionada(tbHuespedesConReserva)) {

					int caso = panel.getSelectedIndex();

					switch (caso) {
					case 0:
						String id = modelo.getValueAt(tbReservas.getSelectedRow(), 0).toString().toUpperCase();
						String fechaEntrada = modelo.getValueAt(tbReservas.getSelectedRow(), 1).toString()
								.toUpperCase();
						String fechaSalida = modelo.getValueAt(tbReservas.getSelectedRow(), 2).toString().toUpperCase();
						Double valor = Double.parseDouble(
								modelo.getValueAt(tbReservas.getSelectedRow(), 3).toString().toUpperCase());
						String formaDePago = modelo.getValueAt(tbReservas.getSelectedRow(), 4).toString().toUpperCase();
						editarReserva(id, fechaEntrada, fechaSalida, valor, formaDePago);

						break;

					case 1:
						String idHuesped = modeloHuespedConReserva.getValueAt(tbHuespedesConReserva.getSelectedRow(), 0)
								.toString().toUpperCase();
						String nombre = modeloHuespedConReserva.getValueAt(tbHuespedesConReserva.getSelectedRow(), 1)
								.toString().toUpperCase();
						String apellido = modeloHuespedConReserva.getValueAt(tbHuespedesConReserva.getSelectedRow(), 2)
								.toString();
						String fechaNacimiento = modeloHuespedConReserva
								.getValueAt(tbHuespedesConReserva.getSelectedRow(), 3).toString();
						String Nacionalidad = modeloHuespedConReserva
								.getValueAt(tbHuespedesConReserva.getSelectedRow(), 4).toString().toUpperCase();
						String telefono = modeloHuespedConReserva.getValueAt(tbHuespedesConReserva.getSelectedRow(), 5)
								.toString().toUpperCase();
//						String idReserva = modeloHuespedConReserva.getValueAt(tbHuespedesConReserva.getSelectedRow(), 6)
//								.toString().toUpperCase();

						editarHuesped(idHuesped, nombre, apellido, fechaNacimiento, Nacionalidad, telefono);
						break;

					case 2:
						idHuesped = modeloHuesped.getValueAt(tbHuespedes.getSelectedRow(), 0).toString().toUpperCase();
						nombre = modeloHuesped.getValueAt(tbHuespedes.getSelectedRow(), 1).toString().toUpperCase();
						apellido = modeloHuesped.getValueAt(tbHuespedes.getSelectedRow(), 2).toString().toUpperCase();
						fechaNacimiento = modeloHuesped.getValueAt(tbHuespedes.getSelectedRow(), 3).toString()
								.toUpperCase();
						Nacionalidad = modeloHuesped.getValueAt(tbHuespedes.getSelectedRow(), 4).toString()
								.toUpperCase();
						telefono = modeloHuesped.getValueAt(tbHuespedes.getSelectedRow(), 5).toString().toUpperCase();

						editarHuesped(idHuesped, nombre, apellido, fechaNacimiento, Nacionalidad, telefono);
						break;
					}
				} else {
					JOptionPane.showMessageDialog(null, "Por favor, seleccione un registro");
				}

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
		btnEditar.setLayout(null);
		btnEditar.setBackground(new Color(12, 138, 199));
		btnEditar.setBounds(635, 508, 122, 35);
		btnEditar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		contentPane.add(btnEditar);

		JLabel lblEditar = new JLabel("EDITAR");
		lblEditar.setHorizontalAlignment(SwingConstants.CENTER);
		lblEditar.setForeground(Color.WHITE);
		lblEditar.setFont(new Font("Roboto", Font.PLAIN, 18));
		lblEditar.setBounds(0, 0, 122, 35);
		btnEditar.add(lblEditar);

		JPanel btnEliminar = new JPanel();
		btnEliminar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				if (!filaSeleccionada(tbHuespedes) || !filaSeleccionada(tbReservas)
						|| !filaSeleccionada(tbHuespedesConReserva)) {

					String id = "";
					int caso = panel.getSelectedIndex();
					System.out.println("SE CREA EL CASO PARA UN SWITCH : # CASO REAL:        " + caso);

					switch (caso) {
					case 0:
						System.out.println("INGRESANDO A LA OPCION 0 : # CASO REAL:        " + caso);
						id = modelo.getValueAt(tbReservas.getSelectedRow(), 0).toString();
						System.out.println("PARA ELIMINAR:    " + tbtablaTemporal.getName() + "     VALOR ID:   " + id);
						eliminarReserva(id);
						break;

					case 1:
						System.out.println("INGRESANDO A LA OPCION 1 : # CASO REAL:        " + caso);
						id = modeloHuespedConReserva.getValueAt(tbHuespedesConReserva.getSelectedRow(), 6).toString();
						System.out.println("PARA ELIMINAR:    " + tbtablaTemporal.getName() + "    VALOR ID:   " + id);
						eliminarReserva(id);
						break;

					case 2:
						System.out.println("INGRESANDO A LA OPCION 2 : # CASO REAL:        " + caso);
						id = modeloHuesped.getValueAt(tbHuespedes.getSelectedRow(), 0).toString();
						System.out.println("PARA ELIMINAR:    " + tbtablaTemporal.getName() + "     VALOR ID:   " + id);
						eliminarHuesped(id);
						break;
					}

				} else {
					JOptionPane.showMessageDialog(null, "Por favor, seleccione un registro");
				}

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

		btnEliminar.setLayout(null);
		btnEliminar.setBackground(new Color(12, 138, 199));
		btnEliminar.setBounds(767, 508, 122, 35);
		btnEliminar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		contentPane.add(btnEliminar);

		JLabel lblEliminar = new JLabel("ELIMINAR");
		lblEliminar.setHorizontalAlignment(SwingConstants.CENTER);
		lblEliminar.setForeground(Color.WHITE);
		lblEliminar.setFont(new Font("Roboto", Font.PLAIN, 18));
		lblEliminar.setBounds(0, 0, 122, 35);
		btnEliminar.add(lblEliminar);

	}

	private void buscarEnTabla(JTable table, String buscarTermino) {
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(model);
		table.setRowSorter(sorter);

		sorter.setRowFilter(RowFilter.regexFilter(buscarTermino));
	}

	public void mostrarDatos() {
		
		System.out.println("BORRANDO SELECCION Y TABLAS");
		
		modelo.getDataVector().clear();
		modeloHuesped.getDataVector().clear();
		modeloHuespedConReserva.getDataVector().clear();

		cargarHuespedConReservas();
		cargarHuesped();
		cargaReservas();
	}

	protected void editarHuesped(String idHuesped, String nombre, String apellido, String fechaNacimiento,
			String nacionalidad, String telefono) {

		// falta crear las consultas

		System.out.println("SE VAN A ACTUALIZAR LOS DATOS DEL HUESPED:  " + idHuesped);

		int opcion = JOptionPane.showConfirmDialog(this, "Va a editar la informacion de un Huesped, ¿Desea Continurar?",
				"Continuar", JOptionPane.OK_OPTION);

		if (opcion == JOptionPane.OK_OPTION) {
			System.out.println("DENTRO DEL OK");
			if (huespedController.editarHuesped(idHuesped, nombre, apellido, fechaNacimiento, nacionalidad, telefono)) {
				System.out.println("EN TEORIA FUE EDITADO");
				
				// modeloHuesped.removeRow(tbHuespedes.getSelectedRow());
				JOptionPane.showMessageDialog(this, "Huesped modificado exitosamente.");
				mostrarDatos();
				tbHuespedes.clearSelection();
				tbHuespedesConReserva.clearSelection();
			}
			else {
				System.out.println("PAILAS NO EDITO");
			}
		} else {
			System.out.println("CANCELO LA OPERACION");
		}
	}

	protected void editarReserva(String id, String fechaEntrada, String fechaSalida, Double valor, String formaDePago) {
		System.out.println("SE VAN A ACTUALIZAR LOS DATOS DE LA RESERVA:  " + id);

		int opcion = JOptionPane.showConfirmDialog(this,
				"Va a editar la informacion de una Reserva, ¿Desea Continurar?", "Continuar", JOptionPane.OK_OPTION);

		if (opcion == JOptionPane.OK_OPTION) {
			if (reservaController.editarReserva(id, fechaEntrada, fechaSalida, valor, formaDePago)) {

				// modeloHuesped.removeRow(tbHuespedes.getSelectedRow());
				JOptionPane.showMessageDialog(this, "Reserva modificada exitosamente.");
				mostrarDatos();
				tbReservas.clearSelection();
			} else {
				System.out.println("PAILAS NO EDITO");
			}
		}
	}

	private boolean filaSeleccionada(JTable tabla) {
		tbtablaTemporal = tabla;
		return tbtablaTemporal.getSelectedRowCount() == 0;
	}

	private void eliminarHuesped(String id) {

		int opcion = JOptionPane.showConfirmDialog(this,
				"¡ATENCION! eliminar este usuario eliminara todas las reservas asociadas, ¿Desea Continurar?",
				"Continuar", JOptionPane.OK_OPTION);

		if (opcion == JOptionPane.OK_OPTION) {
			if (huespedController.eliminar(id)) {
				// modeloHuesped.removeRow(tbHuespedes.getSelectedRow());
				JOptionPane.showMessageDialog(this, "Huesped eliminado exitosamente.");
				mostrarDatos();

			} else {
				System.out.println("CANCELO LA OPERACION");
			}

		}

	}

	private void eliminarReserva(String id) {

		int opcion = JOptionPane.showConfirmDialog(this, "Estas Seguro de eliminar este registro", "Aceptar",
				JOptionPane.OK_OPTION);

		if (opcion == JOptionPane.OK_OPTION) {
			if (reservaController.eliminar(id)) {
				// modelo.removeRow(tbReservas.getSelectedRow());
				JOptionPane.showMessageDialog(this, "Reserva eliminado exitosamente.");
				mostrarDatos();
			} else {
				System.out.println("CANCELO LA OPERACION");
			}
		}
	}

	private void cargaReservas() {

		var contenido = reservaController.cargarReserva();

		contenido.forEach(reserva -> {
			modelo.addRow(new Object[] { reserva.getCodigoReserva(), reserva.getFechaCheckin(),
					reserva.getFechaCheckout(), reserva.getValor(), reserva.getFormaPago() });
		});
	}

	private void cargarHuesped() {

		var conte = huespedController.cargarUsuario();

		conte.forEach((usuario -> {
			modeloHuesped.addRow(new Object[] { usuario.getId(), usuario.getNombre(), usuario.getApellido(),
					usuario.getFechaNacimiento(), usuario.getNacionalidad(), usuario.getTelefono() });
		}));
	}

	private void cargarHuespedConReservas() {

		var contenido = huespedController.cargarUsuariosconReserva();

		contenido.forEach((usuario -> {
			modeloHuespedConReserva.addRow(new Object[] { usuario.get("ID"), usuario.get("NOMBRE"),
					usuario.get("APELLIDO"), usuario.get("FECHANACIMIENTO"), usuario.get("NACIONALIDAD"),
					usuario.get("TELEFONO"), usuario.get("R.ID") });
		}));
	}

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
