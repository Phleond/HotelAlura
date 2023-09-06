package com.alura.jdbc.modelo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Huespedes {
	
	private String id;
	private String nombre;
	private String apellido;
	private Date fechaNacimiento;
	private String nacionalidad;
	private String telefono;
	private String contraseña;
	private List<Reservas> reserva;
	
	
	
	public Huespedes(String id, String contraseña) {
		this.id= id;
		this.contraseña=contraseña;
	}
	
	public Huespedes(String id, String nombre, String apellido, java.sql.Date fechaNacimiento, String nacionalidad, String telefono) {
		this.id=id;
		this.nombre= nombre;
		this.apellido= apellido;
		this.fechaNacimiento=fechaNacimiento;
		this.nacionalidad= nacionalidad;
		this.telefono=telefono;
			
	}
	
	public Huespedes(String id, String nombre, String apellido, Date date, String nacionalidad, String telefono, String contraseña) {
		this.id=id;
		this.nombre= nombre;
		this.apellido= apellido;
		this.fechaNacimiento=date;
		this.nacionalidad= nacionalidad;
		this.telefono=telefono;
		this.contraseña=contraseña;
	}


	public String getId() {
		return id;
	}

	public String getNombre() {
		return nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}

	public String getNacionalidad() {
		return nacionalidad;
	}

	public String getTelefono() {
		return telefono;
	}

	public String getContraseña() {
		return contraseña;
	}

	@Override
	public String toString() {
		
		return String.format(
				"{id, %d, nombre: %s}",
					this.id, this.nombre);
				
	}

	public void agregarReserva(Reservas reserva) {
		if(this.reserva == null) {
			this.reserva = new ArrayList<>();		
		}
		this.reserva.add(reserva);	
	}
}
