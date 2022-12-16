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

public class Client extends JFrame {
	
	JPanel Container = new JPanel(new BorderLayout());	
	
	JPanel SaisieInfos = new JPanel(new GridBagLayout());
	JPanel DataTab = new JPanel();
	JPanel Bouttons = new JPanel(new GridBagLayout());
	JPanel Titre = new JPanel(new GridBagLayout());
	
	JTextField NumClient1 = new JTextField(15);
	JTextField Nom1 = new JTextField(15);
	JTextField Prenom1 = new JTextField(15);
	JTextField Tel1 = new JTextField(15);
	JTextField Adresse1 = new JTextField(15);        
	
	JLabel Client = new JLabel("Liste Clients:");
	JLabel SaisirClient = new JLabel("SAISIR LES DONNÉES");
	JLabel NumClientLab1 = new JLabel("Num Client :");
	JLabel NomLab1 = new JLabel("Nom :");
	JLabel PrenomLab1 = new JLabel("Prénom :");
	JLabel TelLab1 = new JLabel("Télephone :");
	JLabel AdresseLab1 = new JLabel("Adresse :");

	
	JButton Ajouter = new JButton("Ajouter");	
	JButton Modifier = new JButton("Modifier");	
	JButton Supprimer = new JButton("Supprimer");	
	
	JButton Generer = new JButton("AFFICHER LISTE CLIENTS");

	public Client() {
		super("GESTION ENSA COMPANY");		
	}
	
		public JPanel PageClient() {				
		//---------------------------------------------------------
		                      	/* Titres */
			GridBagConstraints tp = new GridBagConstraints();
			tp.insets = new Insets(10,10,10,10); // (top ,left, bottom, right )
			tp.gridwidth = 2;
			Titre.add(Client, tp);
		//---------------------------------------------------------
								/* Saisie */
			GridBagConstraints gb = new GridBagConstraints();
			gb.gridwidth = 2;
			gb.insets = new Insets(0,0,45,0);
			gb.gridy=0;
			SaisieInfos.add(SaisirClient ,gb);
			gb.gridwidth = 1;
			gb.insets = new Insets(0,40,20,10); // (top ,left, bottom, right )
			gb.gridx=0;
			gb.gridy=1;
			SaisieInfos.add(NumClientLab1 ,gb);
			gb.insets = new Insets(0,50,20,30); 
			gb.gridx=1;
			gb.gridy=1;
			SaisieInfos.add(NumClient1, gb);
			gb.insets = new Insets(0,50,20,10); // (top ,left, bottom, right )
			gb.gridx=0;
			gb.gridy=2;
			SaisieInfos.add(NomLab1 ,gb);
			gb.insets = new Insets(0,50,20,30); 
			gb.gridx=1;
			gb.gridy=2;
			SaisieInfos.add(Nom1, gb);
			gb.insets = new Insets(0,50,20,10);  
			gb.gridx=0;
			gb.gridy=3;
			SaisieInfos.add(PrenomLab1 ,gb);
			gb.insets = new Insets(0,50,20,30);  // (top ,left, bottom, right )
			gb.gridx=1;
			gb.gridy=3;
			SaisieInfos.add(Prenom1,gb);
			gb.insets = new Insets(0,50,20,10); 
			gb.gridx=0;
			gb.gridy=4;
			SaisieInfos.add(TelLab1 ,gb);
			gb.insets = new Insets(0,50,20,30); 
			gb.gridx=1;
			gb.gridy=4;
			SaisieInfos.add(Tel1 ,gb);
			gb.insets = new Insets(0,50,20,10); 
			gb.gridx=0;
			gb.gridy=5;
			SaisieInfos.add(AdresseLab1 ,gb);
			gb.insets = new Insets(0,50,20,30); 
			gb.gridx=1;
			gb.gridy=5;
			SaisieInfos.add(Adresse1 ,gb);
		
		//---------------------------------------------------------
			                     /*--Tableau--*/
			  Object[] titres = {"NUM CLIENT", "NOM","PRENOM","TELEPHONE","ADRESSE"}; 
			 
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
			  Object[] row = new Object[5];
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
							  String sql = "SELECT * FROM CLIENT";
							  Statement stmt = con.createStatement();
							  ResultSet R = stmt.executeQuery(sql);
							  
							  while(R.next()) {
								  model.addRow(new Object[] {
										  R.getString(1),
										  R.getString(2),
										  R.getString(3),
										  R.getString(4),
										  R.getString(5)
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
			      con = DriverManager.getConnection(URL, user, pass);
		    } catch (Exception ex) {
		    	  JOptionPane.showMessageDialog(null, "Vous n'avez pas l'accès à la BD");
			      System.err.println("Exception: " + ex.getMessage());
		    }
		    
		    //-------------------------------------------------
		  if(NumClient1.getText().equals("") || Nom1.getText().equals("") || Prenom1.getText().equals("") || Tel1.getText().equals("") || Adresse1.getText().equals("")) {
			  JOptionPane.showMessageDialog(null, "Vous n'avez pas rempli toutes les cases");
		  }
		  else {  					  
			  try {
				  row[0] = NumClient1.getText();
				  row[1] = Nom1.getText();
				  row[2] = Prenom1.getText();
				  row[3] = Tel1.getText();
				  row[4] = Adresse1.getText();
				  
				  stmt=con.createStatement();
				  query = "INSERT INTO CLIENT (IDCLIENT, NomClient, PrenomClient, TelClient, Adresse) VALUE ('"+row[0]+"','"+row[1]+"', '"+row[2]+"','"+row[3]+"','"+row[4]+"') ";
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
		  if(NumClient1.getText().equals("") && Nom1.getText().equals("") && Prenom1.getText().equals("") && Tel1.getText().equals("") && Adresse1.getText().equals("")) {
			  JOptionPane.showMessageDialog(null, "Précisez le champ que vous voulez supprimer");
		  }
		  else {
			  try {
				  int i = jt.getSelectedRow();
				  if(i >= 0) {
					  stmt1=con.createStatement();
					  row[0] = NumClient1.getText();
					  row[1] = Nom1.getText();
					  row[2] = Prenom1.getText();
					  row[3] = Tel1.getText();
					  row[4] = Adresse1.getText();
					  query1 = "delete from client where (IDCLIENT ='"+row[0]+"'AND NomClient ='"+row[1]+"' AND PrenomClient = '"+row[2]+"'AND TelClient ='"+row[3]+"'AND Adresse ='"+row[4]+"') ";
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
				  NumClient1.setText(model.getValueAt(i, 0).toString());
				  Nom1.setText(model.getValueAt(i, 1).toString());
				  Prenom1.setText(model.getValueAt(i, 2).toString());
				  Tel1.setText(model.getValueAt(i, 3).toString());
				  Adresse1.setText(model.getValueAt(i, 4).toString());
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
		      if(NumClient1.getText().equals("") && Nom1.getText().equals("") && Prenom1.getText().equals("") && Tel1.getText().equals("") && Adresse1.getText().equals("")) {
				  JOptionPane.showMessageDialog(null, "Précisez le champ que vous voulez modifiez");
			  }
			  else {
				  try {
				  	  int i = jt.getSelectedRow();
					  row[0] = NumClient1.getText();
					  row[1] = Nom1.getText();
					  row[2] = Prenom1.getText();
					  row[3] = Tel1.getText();
					  row[4] = Adresse1.getText();
					  //-----------------------------
					  if(i>=0) {
						  	  stmt1=con.createStatement();
							  query1 = "UPDATE CLIENT set idclient ='"+row[0]+"', NomClient ='"+row[1]+"', PrenomClient = '"+row[2]+"', TelClient ='"+row[3]+"', Adresse ='"+row[4]+"' WHERE IDCLIENT = '"+row[0]+"' " ;  									 
							  stmt1.executeUpdate(query1);
		
								model.setValueAt(row[0], i, 0);
								model.setValueAt(row[1], i, 1);
								model.setValueAt(row[2], i, 2);
								model.setValueAt(row[3], i, 3);
								model.setValueAt(row[4], i, 4);
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
				
				Container.add(SaisieInfos, BorderLayout.WEST);
			    SaisieInfos.setBackground(Color.RED);
			    Container.add(DataTab, BorderLayout.CENTER);
			    DataTab.setBackground(Color.RED);
			    Container.add(Bouttons, BorderLayout.SOUTH);
			    Bouttons.setBackground(Color.BLACK);
			    Container.add(Titre, BorderLayout.NORTH);
			    
			    
				getContentPane().setBackground(Color.BLACK);
			    getContentPane().add(Container);
			    this.setDefaultCloseOperation(EXIT_ON_CLOSE);
			    
			    return Container;
		
		}
	
}
