package ProjetClasses;

import java.awt.BorderLayout;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import javax.swing.AbstractButton;
import javax.swing.ButtonModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class Facture extends JFrame {
	
	
	JPanel Container5 = new JPanel(new BorderLayout());	
	
	JPanel SaisieInfos = new JPanel(new GridBagLayout());
	JPanel DataTab = new JPanel();
	JPanel Bouttons = new JPanel(new GridBagLayout());
	JPanel Titre = new JPanel(new GridBagLayout());
	
	
	JTextField numerocommande1 = new JTextField(15);
	JTextField numerofacture1 = new JTextField(15);
	JTextField datefacture1 = new JTextField(15);
	JTextField montant1 = new JTextField(15);
	
	       
	
	JLabel Facture = new JLabel("Liste des factures:");
	JLabel SaisirFacture = new JLabel("SAISIR LES DONNÉES");
	
	JLabel numerocommande2 = new JLabel("Num commande :");    //NumClientLab1 
	JLabel numerofacture2 = new JLabel("num facture :") ;       // NomLab1
	JLabel datefacture2 = new JLabel("date facture :");//PrenomLab1
	JLabel montant2 = new JLabel("montant:");
	

	
	JButton Ajouter = new JButton("Ajouter");	
	JButton Modifier = new JButton("Modifier");	
	JButton Supprimer = new JButton("Supprimer");	
	
	JButton Generer = new JButton("AFFICHER LISTE DES FACTURES");
	
	JTabbedPane onglets = new JTabbedPane();
	
	public Facture() {
		super("GESTION ENSA COMPANY");	
	}

public JPanel PageFacture() {
			
//---------------------------------------------------------
	                      	/* Titres */
		GridBagConstraints tp = new GridBagConstraints();
		tp.insets = new Insets(10,10,10,10); // (top ,left, bottom, right )
		tp.gridwidth = 2;
		Titre.add(Facture, tp);
//---------------------------------------------------------
							/* Saisie */
		GridBagConstraints gb = new GridBagConstraints();
		gb.gridwidth = 2;
		gb.insets = new Insets(0,0,45,0);
		gb.gridy=0;
		//SaisieInfos.add(SaisirClient ,gb);
		gb.gridwidth = 1;
		gb.insets = new Insets(0,40,20,10); // (top ,left, bottom, right )
		gb.gridx=0;
		gb.gridy=1;
		SaisieInfos.add( numerocommande2,gb);
		gb.insets = new Insets(0,50,20,30); 
		gb.gridx=1;
		gb.gridy=1;
		SaisieInfos.add( numerocommande1, gb);
		gb.insets = new Insets(0,50,20,10); // (top ,left, bottom, right )
		gb.gridx=0;
		gb.gridy=2;
		SaisieInfos.add(numerofacture2,gb);
		gb.insets = new Insets(0,50,20,30); 
		gb.gridx=1;
		gb.gridy=2;
		SaisieInfos.add(numerofacture1, gb);
		gb.insets = new Insets(0,50,20,10);  
		gb.gridx=0;
		gb.gridy=3;
		SaisieInfos.add(datefacture2 ,gb);
		gb.insets = new Insets(0,50,20,30);  // (top ,left, bottom, right )
		gb.gridx=1;
		gb.gridy=3;
		SaisieInfos.add(datefacture1,gb);
		gb.insets = new Insets(0,50,20,10); 
		gb.gridx=0;
		gb.gridy=4;
		SaisieInfos.add(montant2 ,gb);
		gb.insets = new Insets(0,50,20,30); 
		gb.gridx=1;
		gb.gridy=4;
		SaisieInfos.add(montant1 ,gb);
	

//---------------------------------------------------------
		                     /*--Tableau--*/
		  Object[] titres = { "Num facture","date facture","montant", "NUM commande"}; 
       
		 
		  JTable jt = new JTable();    
		  jt.setBounds(60,40,200,300);       
		  DataTab.add(jt);
		  JScrollPane sp = new JScrollPane(jt); 
		  DataTab.add(sp);
		  
		  DefaultTableModel model = new DefaultTableModel();
		  model.setColumnIdentifiers(titres);
		  jt.setModel(model);
		  jt.setBackground(Color.cyan);
		  jt.setForeground(Color.white);
		  Font font = new Font("", 1, 13);
		  jt.setFont(font);
		  jt.setRowHeight(30);
//---------------------------------------------------------
		  
		  String URL = "jdbc:mysql://localhost/projet_poo";
		  String driver = "com.mysql.cj.jdbc.Driver";
		  String user = "root";
		  String pass = "";  
		  Object[] row = new Object[4];
		  Connection con = null;
		  
//---------------------------------------------------------
		  Generer.addActionListener(new ActionListener(){
			  
			  public void actionPerformed(ActionEvent e) {
				 	
			  	Connection con = null;
			    try {
				      Class.forName(driver);
				      con = DriverManager.getConnection(URL, user, pass);
			    } catch (Exception ex) {
			    	  JOptionPane.showMessageDialog(null, "Vous n'avez pas l'accès à la BD");
		    	      System.err.println("Exception: " + ex.getMessage());
			    }
			    
			    //-------------------------------------------------
								  
				  try {
						  String sql = "SELECT * FROM facture";
						  Statement stmt = con.createStatement();
						  ResultSet R = stmt.executeQuery(sql);
						  
						  while(R.next()) {
							  model.addRow(new Object[] {
									  R.getString(1),
									  R.getString(2),
									  R.getString(3),
									  R.getString(4),
									 
							  });
						  }
					  
				  } catch(SQLException ex){
					    JOptionPane.showMessageDialog(null, "Problème accès BD!");
					  	System.out.println("L'Erreur est "+ ex);
					  }
				  }
			 
		  });

//---------------------------------------------------------
		                  /*--Bouttons--*/

		  
  Ajouter.addActionListener(new ActionListener(){
	  
	  public void actionPerformed(ActionEvent e) {
		  
		// a MySQL statement
	    Statement stmt;
	    // a MySQL query
	    String query;
	    // try and connect to the database
	  	Connection con = null;
	    try {
		      Class.forName(driver);
		      con = DriverManager.getConnection(URL, "root", "");
	    } catch (Exception ex) {
	    	  JOptionPane.showMessageDialog(null, "Vous n'avez pas l'accès à la BD");
    	      System.err.println("Exception: " + ex.getMessage());
	    }
	    
	    //-------------------------------------------------
	  if( numerocommande1.getText().equals("") || numerofacture1.getText().equals("") || datefacture1.getText().equals("") || montant1.getText().equals("") ) {//
		  JOptionPane.showMessageDialog(null, "Vous n'avez pas rempli toutes les cases");
	  }
	  else {  					  
		  try {
	
			  row[0] = numerofacture1.getText();
			  row[1] = datefacture1.getText();
			  row[2] = montant1.getText();
			  row[3] = numerocommande1.getText();
	
			  stmt=con.createStatement();
			  query = "INSERT INTO facture (IDFACTURE, DateFacture, Montant, IdCommande) VALUES ('"+row[0]+"','"+row[1]+"', '"+row[2]+"','"+row[3]+"') ";// row[0]
			  stmt.executeUpdate(query);

			  model.addRow(row);

		  } catch(SQLException ex){
			    JOptionPane.showMessageDialog(null, "Id n'est pas valide!");
			  	System.out.println("L'Erreur est "+ ex);
			  }
		  }
	 }
  });
		  
   Supprimer.addActionListener	( new ActionListener() {
	public void actionPerformed(ActionEvent e) {
		// a MySQL statement
	    Statement stmt1;
	    // a MySQL query
	    String query1;
	    // try and connect to the database
	  	Connection con = null;
	    try {
		      Class.forName(driver);
		      con = DriverManager.getConnection(URL, user, pass);
	    } catch (Exception ex) {
		      System.err.println("Exception: " + ex.getMessage());
	    }
    //-------------------------------------------------
	  if(numerocommande1.getText().equals("") && numerofacture1.getText().equals("") && datefacture1.getText().equals("") && montant1.getText().equals("") ) {
		  JOptionPane.showMessageDialog(null, "Précisez le champ que vous voulez supprimer");
	  }
	  else {
		  try {
			  int i = jt.getSelectedRow();
			  if(i >= 0) {
				  stmt1=con.createStatement();
				  row[3] = numerocommande1.getText();
				  row[0] = numerofacture1.getText();
				  row[1] = datefacture1.getText();
				  row[2] = montant1.getText();
				 // row[4] = Adresse1.getText();
				  query1 = "delete from facture where (IDCOMMANDE ='"+row[3]+"'AND IDFACTURE ='"+row[0]+"' AND DateFacture = '"+row[1]+"'AND Montant ='"+row[2]+"') ";
				  stmt1.executeUpdate(query1);
				  model.removeRow(i);
				  JOptionPane.showMessageDialog(null, "L'élement est supprimé! ");
			  }
		  }
		  catch(SQLException ex)
		  {
			  JOptionPane.showMessageDialog(null, "Précisez le champ que vous voulez supprimer");
			  System.out.println("Précisez le champ que vous voulez supprimer");
		  }
	  }
   }
 });
		  
/*----------------------------SELECTIONNER LES ELEMENTS DU LIGNE----------------------------------*/		  
  jt.addMouseListener(new MouseAdapter() {
	  public void mouseClicked(MouseEvent e) {
		  int i = jt.getSelectedRow();
		  numerofacture1.setText(model.getValueAt(i, 0).toString());
		  datefacture1.setText(model.getValueAt(i, 1).toString());
		  montant1.setText(model.getValueAt(i, 2).toString());
		  numerocommande1.setText(model.getValueAt(i, 3).toString());
		//  Adresse1.setText(model.getValueAt(i, 4).toString());
	  }
  });
/*----------------------------MODIFIER----------------------------------*/		  
	Modifier.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {		
			 // a MySQL statement
		    Statement stmt1;
		    // a MySQL query
		    String query1;
		    // try and connect to the database
		  	Connection con = null;
		    try {
			      Class.forName(driver);
			      con = DriverManager.getConnection(URL, user, pass);
		    } catch (Exception ex) {
		    	  JOptionPane.showMessageDialog(null, "Vous n'avez pas l'accès à la BD");
	    	      System.err.println("Exception: " + ex.getMessage());
			    }			
			/*-------------------------------------------------------*/	    
	     if(numerocommande1.getText().equals("") && numerofacture1.getText().equals("") && datefacture1.getText().equals("") && montant1.getText().equals("") ) {
		  JOptionPane.showMessageDialog(null, "Précisez le champ que vous voulez supprimer");
		 }
		  else {
			  try {
			 	  int i = jt.getSelectedRow();
			 	  row[3] = numerocommande1.getText();
				  row[0] = numerofacture1.getText();
				  row[1] = datefacture1.getText();
				  row[2] = montant1.getText();
				
			  if(i>=0) {
				  	  stmt1=con.createStatement();
					  query1 = "UPDATE facture set IDCOMMANDE ='"+row[3]+"', IDFACTURE ='"+row[0]+"', DateFacture = '"+row[1]+"', Montant ='"+row[2]+"' WHERE IdFacture = '"+row[0]+"' " ;  									 
				  stmt1.executeUpdate(query1);
	
						model.setValueAt(row[0], i, 0);
						model.setValueAt(row[1], i, 1);
						model.setValueAt(row[2], i, 2);
						model.setValueAt(row[3], i, 3);
						//model.setValueAt(row[4], i, 4);
			  }
				  else {
					JOptionPane.showMessageDialog(null, "Problème de modification");
					System.out.println("Problème de modification");
			  }					
			  }
		  catch(SQLException ex)
			  {
			  JOptionPane.showMessageDialog(null, "Précisez le champ que vous voulez supprimer");
			  System.out.println("Précisez le champ que vous voulez supprimer");
		  }
      }
	}});	
		  
/*-----------------------Positionement des bouttons (South)  ------------------------*/
		  
		 GridBagConstraints buttons = new GridBagConstraints();
		    buttons.insets = new Insets(5,0,5,100); 
			buttons.gridx=0;
			buttons.gridy=0;
			Bouttons.add(Ajouter ,buttons);
			buttons.insets = new Insets(5,0,5,100); 
			buttons.gridx=1;
			buttons.gridy=0;
			Bouttons.add(Modifier ,buttons);
			buttons.insets = new Insets(5,0,5,100); 
			buttons.gridx=2;
			buttons.gridy=0;
			Bouttons.add(Supprimer ,buttons);  	
			buttons.insets = new Insets(5,0,5,0); 
			buttons.gridx=3;
			buttons.gridy=0;
			Bouttons.add(Generer ,buttons); 
			
			
/*----------------------------Positionement des Panels-------------------------------------*/
			
		Container5.add(SaisieInfos, BorderLayout.WEST);
	    SaisieInfos.setBackground(Color.RED);
	    Container5.add(DataTab, BorderLayout.CENTER);
	    DataTab.setBackground(Color.RED);
	    Container5.add(Bouttons, BorderLayout.SOUTH);
	    Bouttons.setBackground(Color.BLACK);
	    Container5.add(Titre, BorderLayout.NORTH);
    

		onglets.addTab("Produit",  Container5);
		
		
		this.setVisible(true);
		this.setSize(1000,600);		
		
		getContentPane().add(onglets);
		getContentPane().setBackground(Color.BLACK);

		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		return Container5;

	}
	

}
