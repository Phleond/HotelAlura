package com.alura.jdbc;

import javax.swing.JFrame;

import com.alura.jdbc.view.MenuPrincipal;



public class HotelAluraMain {

	public static void main(String[] args) {
		
		MenuPrincipal menuprincipal = new MenuPrincipal(); 
		menuprincipal.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		menuprincipal.setVisible(true);

	}
}
