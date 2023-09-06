package com.alura.jdbc.modelo;

import java.util.Date;

public class Reservas {
	
	
	private String codigoReserva;

	private Long valor;
	private Date fechaCheckin;
	private Date fechaCheckout;
	private String formaPago;

	
	public Reservas(Date fechaCheckin, Date fechaCheckout, String formaPago, String codigoReserva, Long valor) {

		this.fechaCheckin= fechaCheckin;
		this.fechaCheckout =fechaCheckout;
		this.formaPago= formaPago;
		this.codigoReserva=codigoReserva;
		this.valor = valor;
	}

	public Reservas(String idReservaUsuario) {
		// TODO Auto-generated constructor stub
	}

	public Date getFechaCheckin() {
		return fechaCheckin;
	}

	public Date getFechaCheckout() {
		return fechaCheckout;
	}

	public String getFormaPago() {
		return formaPago;
	}
	
	public String getCodigoReserva() {
		return codigoReserva;
	}

	public Long getValor() {
		return valor;
	}
	
}
