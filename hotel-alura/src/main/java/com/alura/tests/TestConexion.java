package com.alura.tests;

import com.alura.jdbc.factory.ConnectionFactory;

public class TestConexion {

	public static void main(String[] args) {
		
	
		System.out.println("Conectando la base de datos");
		ConnectionFactory cf = new ConnectionFactory();
		cf.recuperarConexion();
		
	}
}
