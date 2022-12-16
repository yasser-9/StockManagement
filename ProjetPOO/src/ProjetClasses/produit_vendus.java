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

public class produit_vendus extends JFrame {
	
	JPanel Container = new JPanel(new BorderLayout());	

	JPanel SaisieInfos = new JPanel(new GridBagLayout());
	JPanel DataTab = new JPanel();
	JPanel Bouttons = new JPanel(new GridBagLayout());
	JPanel Titre = new JPanel(new GridBagLayout());
	
	JTextField idcommande1 = new JTextField(15);
	JTextField idproduit1 = new JTextField(15);
	
	JLabel SaisirPV = new JLabel("SAISIR LES DONNÉES");
	JLabel idcommande2 = new JLabel("id commande  :");    
	JLabel idproduit2 = new JLabel("id produit :") ;       	

	
	JButton Ajouter = new JButton("Ajouter");	
	JButton Modifier = new JButton("Modifier");	
	JButton Supprimer = new JButton("Supprimer");	
	
	JButton Generer = new JButton("AFFICHER ");
	
	JTabbedPane onglets = new JTabbedPane();
	public produit_vendus() {}

public JPanel PagePV() {
		//---------------------------------------------------------
	     
//---------------------------------------------------------
							/* Saisie */
		GridBagConstraints gb = new GridBagConstraints();

		gb.insets = new Insets(0,40,20,10); // (top ,left, bottom, right )
		gb.gridx=0;
		gb.gridy=1;
		SaisieInfos.add( idcommande2,gb);
		gb.insets = new Insets(0,50,20,30); 
		gb.gridx=1;
		gb.gridy=1;
		SaisieInfos.add( idcommande1, gb);
		gb.insets = new Insets(0,50,20,10); // (top ,left, bottom, right )
		gb.gridx=0;
		gb.gridy=2;
		SaisieInfos.add(idproduit2,gb);
		gb.insets = new Insets(0,50,20,30); 
		gb.gridx=1;
		gb.gridy=2;
		SaisieInfos.add(idproduit1, gb);
		

//---------------------------------------------------------
		                     /*--Tableau--*/
		  Object[] titres = {"NUM Commande", "NUM Produit"}; 
       
		 
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
						  String sql = "SELECT * FROM produit_vendus";
						  Statement stmt = con.createStatement();
						  ResultSet R = stmt.executeQuery(sql);
						  
						  while(R.next()) {
							  model.addRow(new Object[] {
									  R.getString(1),
									  R.getString(2),
									 
									 
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
	  if( idcommande1.getText().equals("") || idproduit1.getText().equals("")  ) {
		  JOptionPane.showMessageDialog(null, "Vous n'avez pas rempli toutes les cases");
	  }
	  else {  					  
		  try {
			 row[0] = idcommande1.getText();
			  row[1] = idproduit1.getText();
			 
			  stmt=con.createStatement();
			  query = "INSERT INTO produit_vendus (idcommande, idproduit) VALUES ('"+row[0]+"','"+row[1]+"') ";// row[0]
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
	  if(idcommande1.getText().equals("") && idproduit1.getText().equals("")  ) {
		  JOptionPane.showMessageDialog(null, "Précisez le champ que vous voulez supprimer");
	  }
	  else {
		  try {
			  int i = jt.getSelectedRow();
			  if(i >= 0) {
				  stmt1=con.createStatement();
				  row[0] = idcommande1.getText();
				  row[1] = idproduit1.getText();
				  query1 = "delete from produit_vendus where (idcommande ='"+row[0]+"'AND idproduit ='"+row[1]+"' ) ";
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
	 idcommande1.setText(model.getValueAt(i, 0).toString());
		 idproduit1.setText(model.getValueAt(i, 1).toString());
		
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
	     if(idcommande1.getText().equals("") && idproduit1.getText().equals("") ) {
		  JOptionPane.showMessageDialog(null, "Précisez le champ que vous voulez supprimer");
		 }
		  else {
			  try {
			 	  int i = jt.getSelectedRow();
			  row[0] = idcommande1.getText();
				  row[1] =idproduit1.getText();
				  
				
			  if(i>=0) {
				  	  stmt1=con.createStatement();
					  query1 = "UPDATE produit_vendus set idcommande ='"+row[0]+"', idproduit ='"+row[1]+"' " ;  									 
				  stmt1.executeUpdate(query1);
	
						model.setValueAt(row[0], i, 0);
						model.setValueAt(row[1], i, 1);
						
					
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
	 
	 

		
		this.setVisible(true);
		this.setSize(1000,600);		
		
		getContentPane().add(onglets);
		getContentPane().setBackground(Color.BLACK);

		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		return Container ;

	}


}