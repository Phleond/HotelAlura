package com.alura.jdbc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import com.alura.jdbc.modelo.Reservas;
import com.alura.jdbc.modelo.Huespedes;

public class ReservasDAO {

	private Connection con;

	public ReservasDAO(Connection con) {
		this.con = con;
	}

	public boolean registrarReserva(Reservas reservas, Huespedes huesped) {
		boolean resultado;

		java.sql.Date fEntrada = new java.sql.Date(reservas.getFechaCheckin().getTime());
		java.sql.Date fSalida = new java.sql.Date(reservas.getFechaCheckout().getTime());

		try {
			final PreparedStatement statement = con.prepareStatement(
					"INSERT INTO RESERVAS(ID, IDHUESPED ,FECHAENTRADA, FECHASALIDA,VALOR, FORMADEPAGO) "
							+ "VALUES (?,?,?,?,?,?)");

			try (statement) {
				statement.setString(1, reservas.getCodigoReserva());
				statement.setString(2, huesped.getId());
				statement.setDate(3, fEntrada);
				statement.setDate(4, fSalida);
				statement.setLong(5, reservas.getValor());
				statement.setString(6, reservas.getFormaPago());

				statement.execute();
				resultado = true;

			}

		} catch (SQLException e) {
			resultado = false;
			System.out.println(resultado);

		}
		return resultado;

	}

	public List<Reservas> cargarReserva() {
		List<Reservas> resultado = new ArrayList<>();

		try {
			final PreparedStatement statement = con
					.prepareStatement("SELECT FECHAENTRADA,FECHASALIDA," + " FORMADEPAGO, ID, VALOR FROM RESERVAS");

			try (statement) {
				final ResultSet resultSet = statement.executeQuery();

				try (resultSet) {
					while (resultSet.next()) {
						Reservas fila = new Reservas(resultSet.getDate("FECHAENTRADA"),
								resultSet.getDate("FECHASALIDA"), resultSet.getString("FORMADEPAGO"),
								resultSet.getString("ID"), resultSet.getLong("VALOR"));
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
			final PreparedStatement statement = con.prepareStatement("DELETE FROM RESERVAS WHERE ID = ?");

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

	public boolean editarReserva(String id, String fechaEntrada, String fechaSalida, Double valor, String formaDePago) {

		boolean resultado;
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

		try {

			java.util.Date fSalida = dateFormat.parse(fechaEntrada);
			java.util.Date fEntrada = dateFormat.parse(fechaEntrada);

			fSalida = new java.sql.Date(fSalida.getTime());
			fEntrada = new java.sql.Date(fEntrada.getTime());

			final PreparedStatement statement = con.prepareStatement("UPDATE RESERVAS SET " + " FECHAENTRADA = ?,"
					+ " FECHASALIDA = ?," + " VALOR = ?," + " FORMADEPAGO = ?" + " WHERE ID = ?");

			try (statement) {
				statement.setDate(1, (java.sql.Date) fEntrada);
				statement.setDate(2, (java.sql.Date) fSalida);
				statement.setDouble(3, valor);
				statement.setString(4, formaDePago);
				statement.setString(5, id);
				statement.execute();
				resultado = true;
				System.out.println("SE EDITO LA RESERVA EN ORDEN");
			}
			

					}
				catch (SQLException e) {
			resultado = false;
			throw new RuntimeException(e);

		} catch (ParseException e) {
			resultado = false;
			e.printStackTrace();
		}
		return resultado;
	}

}
