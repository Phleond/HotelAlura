package com.alura.jdbc.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alura.jdbc.modelo.Huespedes;

public class HuespedesDAO {

	private Connection con;
	Huespedes huesped;

	public HuespedesDAO(Connection con) {
		this.con = con;

//		Usuarios usuario = new Usuarios();
//		this.usuario=usuario;

	}

	public List<Huespedes> compararContraseña(String id, String contraseña) {

		List<Huespedes> resultado = new ArrayList<>();

		try {
			final PreparedStatement statement = con.prepareStatement("SELECT ID,  NOMBRE, APELLIDO, FECHANACIMIENTO ,"
					+ "NACIONALIDAD, TELEFONO FROM HUESPEDES WHERE ID = ?");

			try (statement) {
				statement.setString(1, id);
				final ResultSet resultSet = statement.executeQuery();

				try (resultSet) {
					while (resultSet.next()) {
						Huespedes fila = new Huespedes(resultSet.getString("ID"), resultSet.getString("NOMBRE"),
								resultSet.getString("APELLIDO"), resultSet.getDate("FECHANACIMIENTO"),
								resultSet.getString("NACIONALIDAD"), resultSet.getString("TELEFONO"));
						resultado.add(fila);
					}

				}
			}

			return resultado;

		} catch (SQLException e) {

			throw new RuntimeException(e);
		}

	}

	public boolean Registrar(Huespedes huesped) {

		boolean resultado = false;

		java.sql.Date fnac = new java.sql.Date(huesped.getFechaNacimiento().getTime());

		try {
			final PreparedStatement statement = con.prepareStatement(
					"INSERT INTO HUESPEDES(ID, NOMBRE, APELLIDO, FECHANACIMIENTO, TELEFONO, NACIONALIDAD, CONTRASEÑA) "
							+ "VALUES (?,?,?,?,?,?,?)");

			try (statement) {
				statement.setString(1, huesped.getId());
				statement.setString(2, huesped.getNombre());
				statement.setString(3, huesped.getApellido());
				statement.setDate(4, fnac);
				statement.setString(5, huesped.getTelefono());
				statement.setString(6, huesped.getNacionalidad());
				statement.setString(7, huesped.getContraseña());
				statement.execute();
				resultado = true;

			}

		} catch (SQLException e) {
			resultado = false;
			System.out.println(resultado);

		}
		return resultado;
	}

	public List<Map<String, String>> cargarHuespedconReserva() {

		List<Map<String, String>> resultado = new ArrayList<>();

		try {
			final PreparedStatement statement = con
					.prepareStatement("SELECT H.ID,  H.NOMBRE, H.APELLIDO, H.FECHANACIMIENTO ,"
							+ "H.NACIONALIDAD, H.TELEFONO, R.ID FROM HUESPEDES H INNER JOIN RESERVAS R ON H.ID = R.IDHUESPED");

			try (statement) {
				final ResultSet resultSet = statement.executeQuery();

				try (resultSet) {
					while (resultSet.next()) {
						Map<String, String> fila = new HashMap<>();
						fila.put("ID", resultSet.getString("H.ID"));
						fila.put("NOMBRE", resultSet.getString("H.NOMBRE"));
						fila.put("APELLIDO", resultSet.getString("H.APELLIDO"));
						fila.put("FECHANACIMIENTO", resultSet.getDate("H.FECHANACIMIENTO").toString());
						fila.put("NACIONALIDAD", resultSet.getString("H.NACIONALIDAD"));
						fila.put("TELEFONO", resultSet.getString("H.TELEFONO"));
						fila.put("R.ID", resultSet.getString("R.ID"));

						resultado.add(fila);
					}
				}
			}
			System.out.println("BUSQUEDA OK");
			return resultado;

		} catch (SQLException e) {
			System.out.println("ERROR EN BUSQUEDA");
			throw new RuntimeException(e);
		}
	}

	public List<Huespedes> cargarHuesped() {

		List<Huespedes> resultado = new ArrayList<>();

		try {
			final PreparedStatement statement = con.prepareStatement(
					"SELECT ID, NOMBRE, APELLIDO, FECHANACIMIENTO ," + "NACIONALIDAD, TELEFONO FROM HUESPEDES");

			try (statement) {
				final ResultSet resultSet = statement.executeQuery();

				try (resultSet) {
					while (resultSet.next()) {
						Huespedes fila = new Huespedes(resultSet.getString("ID"), resultSet.getString("NOMBRE"),
								resultSet.getString("APELLIDO"), resultSet.getDate("FECHANACIMIENTO"),
								resultSet.getString("NACIONALIDAD"), resultSet.getString("TELEFONO"));

						resultado.add(fila);
					}
				}
			}
			return resultado;

		} catch (SQLException e) {

			throw new RuntimeException(e);
		}
	}

	public boolean eliminar(String id) {
		boolean resultado;

		try {
			final PreparedStatement statement = con.prepareStatement("DELETE FROM HUESPEDES WHERE ID = ?");

			try (statement) {
				statement.setString(1, id);
				statement.execute();

				resultado = true;
			}
		} catch (SQLException e) {
			resultado = false;
			throw new RuntimeException(e);

		}

		return resultado;
	}

	public boolean editarHuesped(String idHuesped, String nombre, String apellido, String fechaNacimiento,
			String nacionalidad, String telefono) {

		System.out.println("ID: " + idHuesped + "  NOMBRE:  " + nombre + "  APELLIDO:  " + apellido
				+ "  FECHANACIMIENTO:  " + fechaNacimiento + "  NACIONALIDAD:  " + nacionalidad + "  TELEFONO:  " + telefono);

		boolean resultado;

		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

		try {

			java.util.Date fNacimiento = dateFormat.parse(fechaNacimiento);
			fNacimiento = new java.sql.Date(fNacimiento.getTime());

			final PreparedStatement statement = con
					.prepareStatement("UPDATE HUESPEDES SET " + "NOMBRE = ?, " + "APELLIDO = ?, "
							+ "FECHANACIMIENTO = ?, " + "NACIONALIDAD = ?, " + "TELEFONO = ? " + "WHERE ID = ? ");

			try (statement) {
				statement.setString(1, nombre);
				statement.setString(2, apellido);
				statement.setDate(3, (Date) fNacimiento);
				statement.setString(4, nacionalidad);
				statement.setString(5, telefono);
				statement.setString(6, idHuesped);
				statement.execute();
				resultado = true;

				System.out.println("SE EDITO EL USUARIO EN ORDEN");
			}

		} catch (SQLException e) {
			resultado = false;
			throw new RuntimeException(e);

		} catch (ParseException e) {
			resultado = false;
			e.printStackTrace();
		}
		return resultado;
	}
}
