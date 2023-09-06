package com.alura.jdbc.controller;

import java.util.List;
import java.util.Map;

import com.alura.jdbc.dao.ReservasDAO;
import com.alura.jdbc.dao.HuespedesDAO;
import com.alura.jdbc.factory.ConnectionFactory;
import com.alura.jdbc.modelo.Huespedes;

public class HuespedesController {

	ReservasController reservaController;
	HuespedesDAO usuarioDao;
	ReservasDAO reservaDao;

	public HuespedesController() {
		this.usuarioDao = new HuespedesDAO(new ConnectionFactory().recuperarConexion());
	}

	public List<Huespedes> compararContraseña(String id, String contraseña) {
		return usuarioDao.compararContraseña(id, contraseña);
	}

	public boolean RegistrarUsuario(Huespedes usuario) {
		return usuarioDao.Registrar(usuario);
	}

	public List<Huespedes> cargarUsuario() {

		return usuarioDao.cargarHuesped();

	}

	public List<Map<String, String>> cargarUsuariosconReserva() {

		return usuarioDao.cargarHuespedconReserva();
	}

	public boolean eliminar(String id) {
		return usuarioDao.eliminar(id);
	}

	public boolean editarHuesped(String idHuesped, String nombre, String apellido, String fechaNacimiento,
			String nacionalidad, String telefono) {
			return usuarioDao.editarHuesped(idHuesped,nombre, apellido, fechaNacimiento,nacionalidad, telefono);
		
	}
}
