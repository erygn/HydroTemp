package fr.erygn.hydrotempfr;

import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class hydrotempfr {
	public static void main(String[] args) {
		System.out.println("Start");
		
		//Création de la Frame
		JFrame jF = new JFrame("HydroTemp");
		jF.setVisible(true);
		jF.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jF.setPreferredSize(new Dimension(400, 300));
		jF.pack();
		
		//Création du panel principal
		JPanel main = new JPanel();
		JLabel title = new JLabel();
		title.setText("Mon hydro App");
		main.add(title);
		jF.add(main);
	}
}
