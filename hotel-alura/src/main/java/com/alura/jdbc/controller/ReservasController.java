package com.alura.jdbc.controller;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import com.alura.jdbc.dao.ReservasDAO;
import com.alura.jdbc.factory.ConnectionFactory;
import com.alura.jdbc.modelo.Reservas;
import com.alura.jdbc.modelo.Huespedes;

public class ReservasController {	
	
	private static int longitudCadena = 9;

	private ReservasDAO reservaDao;

	
	public ReservasController() {
		
		this.reservaDao = new ReservasDAO( new ConnectionFactory().recuperarConexion());
	
	}

	public String crearReserva() {
		
		String codigoReserva=cadenaAleatoria(longitudCadena);
		return codigoReserva;
	}

	public static String cadenaAleatoria(int longitud) {
				
        String bancoCaracteres = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
       
        String cadena = "";
        for (int i = 0; i < longitud; i++) {
            int indiceAleatorio = numeroAleatorioEnRango(0, bancoCaracteres.length() - 1);
            char caracterAleatorio = bancoCaracteres.charAt(indiceAleatorio);
            cadena += caracterAleatorio;
        }
        return cadena;
    }
	
	// este metodo aporta mayor aleatoriedad al valor generado
	
	public static int numeroAleatorioEnRango(int minimo, int maximo) {
        // nextInt regresa en rango pero con límite superior exclusivo, por eso sumamos 1
        return ThreadLocalRandom.current().nextInt(minimo, maximo + 1);
    }

	public boolean regsitrarReserva(Reservas reservas, Huespedes usuario) {
		
		return reservaDao.registrarReserva(reservas,usuario);
		
		
	}

	public List<Reservas> cargarReserva() {
		return reservaDao.cargarReserva();
	}

	public boolean eliminar(String id) {

		return reservaDao.eliminar(id);
	}

	public boolean editarReserva(String id, String fechaEntrada, String fechaSalida, Double valor, String formaDePago) {
		return reservaDao.editarReserva( id,  fechaEntrada,  fechaSalida,  valor,  formaDePago);
		
	}


	
	
}
