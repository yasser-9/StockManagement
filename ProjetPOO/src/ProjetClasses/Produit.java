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
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class Produit extends JFrame {
	
	JPanel Container5 = new JPanel(new BorderLayout());	
	
	JPanel SaisieInfosP = new JPanel(new GridBagLayout());
	JPanel DataTabP = new JPanel();
	JPanel BouttonsP = new JPanel(new GridBagLayout());
	JPanel TitreP = new JPanel(new GridBagLayout());
	
	JTextField numeroproduit1 = new JTextField(15);
	JTextField nomproduit1 = new JTextField(15);
	JTextField quantite1 = new JTextField(15);
	JTextField prix1 = new JTextField(15);       
	
	JLabel Client = new JLabel("Liste des produits:");
	JLabel SaisirProduit = new JLabel("SAISIR LES DONNÉES");
	JLabel numeroproduit2 = new JLabel("Num produit :");    //NumClientLab1 
	JLabel nomproduit2 = new JLabel("Nom produit :") ;       // NomLab1
	JLabel quantite2 = new JLabel("Quantité :");//PrenomLab1
	JLabel prix2 = new JLabel("Prix:");
	
	JButton Ajouter = new JButton("Ajouter");	
	JButton Modifier = new JButton("Modifier");	
	JButton Supprimer = new JButton("Supprimer");	
	
	JButton Generer = new JButton("AFFICHER LISTE DES PRODUITS");
	
	JTabbedPane onglets = new JTabbedPane();

	public Produit() {
		super("GESTION ENSA COMPANY");	
	}
	
public JPanel PageProduit() {
//---------------------------------------------------------
	                      	/* Titres */
		GridBagConstraints tp = new GridBagConstraints();
		tp.insets = new Insets(10,10,10,10); // (top ,left, bottom, right )
		tp.gridwidth = 2;
		TitreP.add(Client, tp);
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
		SaisieInfosP.add( numeroproduit2,gb);
		gb.insets = new Insets(0,50,20,30); 
		gb.gridx=1;
		gb.gridy=1;
		SaisieInfosP.add( numeroproduit1, gb);
		gb.insets = new Insets(0,50,20,10); // (top ,left, bottom, right )
		gb.gridx=0;
		gb.gridy=2;
		SaisieInfosP.add(nomproduit2,gb);
		gb.insets = new Insets(0,50,20,30); 
		gb.gridx=1;
		gb.gridy=2;
		SaisieInfosP.add(nomproduit1, gb);
		gb.insets = new Insets(0,50,20,10);  
		gb.gridx=0;
		gb.gridy=3;
		SaisieInfosP.add(quantite2 ,gb);
		gb.insets = new Insets(0,50,20,30);  // (top ,left, bottom, right )
		gb.gridx=1;
		gb.gridy=3;
		SaisieInfosP.add(quantite1,gb);
		gb.insets = new Insets(0,50,20,10); 
		gb.gridx=0;
		gb.gridy=4;
		SaisieInfosP.add(prix2 ,gb);
		gb.insets = new Insets(0,50,20,30); 
		gb.gridx=1;
		gb.gridy=4;
		SaisieInfosP.add(prix1 ,gb);
	

//---------------------------------------------------------
		                     /*--Tableau--*/
		  Object[] titres = {"NUM Produit", "NOM produit","quantite","prix"}; 
       
		 
		  JTable jt = new JTable();    
		  jt.setBounds(60,40,200,300);       
		  DataTabP.add(jt);
		  JScrollPane sp = new JScrollPane(jt); 
		  DataTabP.add(sp);
		  
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
						  String sql = "SELECT * FROM produit";
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
		      con = DriverManager.getConnection("jdbc:mysql://localhost/projet_poo", "root", "");
	    } catch (Exception ex) {
	    	  JOptionPane.showMessageDialog(null, "Vous n'avez pas l'accès à la BD");
    	      System.err.println("Exception: " + ex.getMessage());
	    }
	    
	    //-------------------------------------------------
	  if( numeroproduit1.getText().equals("") || nomproduit1.getText().equals("") || quantite1.getText().equals("") || prix1.getText().equals("") ) {//
		  JOptionPane.showMessageDialog(null, "Vous n'avez pas rempli toutes les cases");
	  }
	  else {  					  
		  try {
			 row[0] = numeroproduit1.getText();
			  row[1] = nomproduit1.getText();
			  row[2] = quantite1.getText();
			  row[3] = prix1.getText();
	
			  stmt=con.createStatement();
			  query = "INSERT INTO produit (IDPRODUIT, NomProduit, Quantite, Prix) VALUES ('"+row[0]+"','"+row[1]+"', '"+row[2]+"','"+row[3]+"') ";// row[0]
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
	  if(numeroproduit1.getText().equals("") && nomproduit1.getText().equals("") && quantite1.getText().equals("") && prix1.getText().equals("") ) {
		  JOptionPane.showMessageDialog(null, "Précisez le champ que vous voulez modifiez");
	  }
	  else {
		  try {
			  int i = jt.getSelectedRow();
			  if(i >= 0) {
				  stmt1=con.createStatement();
				  row[0] = numeroproduit1.getText();
				  row[1] = nomproduit1.getText();
				  row[2] = quantite1.getText();
				  row[3] = prix1.getText();
				 // row[4] = Adresse1.getText();
				  query1 = "delete from produit where (IDPRODUIT ='"+row[0]+"'AND NomProduit ='"+row[1]+"' AND Quantite = '"+row[2]+"'AND Prix ='"+row[3]+"') ";
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
	  numeroproduit1.setText(model.getValueAt(i, 0).toString());
		 nomproduit1.setText(model.getValueAt(i, 1).toString());
		 quantite1.setText(model.getValueAt(i, 2).toString());
		  prix1.setText(model.getValueAt(i, 3).toString());
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
	     if(numeroproduit1.getText().equals("") && nomproduit1.getText().equals("") && quantite1.getText().equals("") && prix1.getText().equals("") ) {
		  JOptionPane.showMessageDialog(null, "Précisez le champ que vous voulez supprimer");
		 }
		  else {
			  try {
			 	  int i = jt.getSelectedRow();
			  row[0] = numeroproduit1.getText();
				  row[1] = nomproduit1.getText();
				  row[2] = quantite1.getText();
				  row[3] = prix1.getText();
				
				
			  if(i>=0) {
				  	  stmt1=con.createStatement();
					  query1 = "UPDATE produit set IDPRODUIT ='"+row[0]+"', NomProduit ='"+row[1]+"', Quantite = '"+row[2]+"', Prix ='"+row[3]+"' WHERE IDPRODUIT = '"+row[0]+"' " ;  									 
				  stmt1.executeUpdate(query1);
	
						model.setValueAt(row[0], i, 0);
						model.setValueAt(row[1], i, 1);
						model.setValueAt(row[2], i, 2);
						model.setValueAt(row[3], i, 3);
						
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
			BouttonsP.add(Ajouter ,buttons);
			buttons.insets = new Insets(5,0,5,100); 
			buttons.gridx=1;
			buttons.gridy=0;
			BouttonsP.add(Modifier ,buttons);
			buttons.insets = new Insets(5,0,5,100); 
			buttons.gridx=2;
			buttons.gridy=0;
			BouttonsP.add(Supprimer ,buttons);  	
			buttons.insets = new Insets(5,0,5,0); 
			buttons.gridx=3;
			buttons.gridy=0;
			BouttonsP.add(Generer ,buttons); 
			
			
/*----------------------------Positionement des Panels-------------------------------------*/
			
		Container5.add(SaisieInfosP, BorderLayout.WEST);
	    SaisieInfosP.setBackground(Color.RED);
	    Container5.add(DataTabP, BorderLayout.CENTER);
	    DataTabP.setBackground(Color.RED);
	    Container5.add(BouttonsP, BorderLayout.SOUTH);
	    BouttonsP.setBackground(Color.BLACK);
	    Container5.add(TitreP, BorderLayout.NORTH);
	 
		onglets.addTab("Produit",  Container5);
		
		getContentPane().add(onglets);
		getContentPane().setBackground(Color.BLACK);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		return Container5;

	}
}
	
