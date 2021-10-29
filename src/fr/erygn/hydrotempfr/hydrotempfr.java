package fr.erygn.hydrotempfr;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class hydrotempfr {
	public static void main(String[] args) {
		System.out.println("Start");
		
		//Création de la Frame
		JFrame jF = new JFrame("HydroTemp");
		jF.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jF.setPreferredSize(new Dimension(400, 300));
		jF.pack();
		
		//Création du panel du textfield
		JPanel pan = new JPanel();
		JLabel title = new JLabel();
		JTextField codeDep = new JTextField(2);
		JButton btnDep = new JButton("Rechercher");
		title.setText("Numéro de département");
		pan.add(title);
		pan.add(codeDep);
		pan.add(btnDep);
		
		JPanel panMain = new JPanel();
		
		btnDep.addActionListener(e -> {
			String codeD = codeDep.getText();
			if (codeD != null && codeD.length() != 0) {
				try {
					URL url = new URL("https://hubeau.eaufrance.fr/api/v1/temperature/station.csv?code_departement="+ codeD);
					HttpURLConnection con = (HttpURLConnection)url.openConnection();
					con.setRequestMethod("GET");
					List<List<String>> result = new ArrayList<>();
					try(BufferedReader br = new BufferedReader(
							new InputStreamReader(con.getInputStream(), "utf-8"))) {
							    //StringBuilder response = new StringBuilder();
							    String responseLine = null;
							    String COMMA_DELIMITER = ";";
							    while ((responseLine = br.readLine()) != null) {
							    	String[] values = responseLine.split(COMMA_DELIMITER);
							    	result.add(Arrays.asList(values));
							        //response.append(responseLine.trim());
							    }
							    //System.out.println(result.toString());
							    if (result.size() > 1) {
							    	DefaultListModel<String> model = new DefaultListModel<String>();
							    	for (int i = 1; i < result.size(); i++) {
							    		System.out.println(result.get(i).get(10));
							    		model.addElement(result.get(i).get(10));
									}
							    	JList<String> list = new JList<String>(model);
									panMain.add(list);
							    } else {
							    	JLabel resultLabel = new JLabel();
									panMain.add(resultLabel);
							    	resultLabel.setText("Aucun résultat");
							    }
							}
				} catch (MalformedURLException e1) {
					System.out.println("MalFormedURLException");
					e1.printStackTrace();
				} catch (IOException e1) {
					System.out.println("IOException");
					e1.printStackTrace();
				}
				//System.out.println(codeD);
			}
		});
		
		//Création du panel d'affichage température
		
		jF.add(panMain, BorderLayout.CENTER);
		jF.add(pan, BorderLayout.NORTH);
		
		jF.setVisible(true);
	}
}
