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

public class Livraison extends JFrame {
	
	JPanel Container = new JPanel(new BorderLayout());	
	
	JPanel SaisieInfos = new JPanel(new GridBagLayout());
	JPanel DataTab = new JPanel();
	JPanel Bouttons = new JPanel(new GridBagLayout());
	JPanel Titre = new JPanel(new GridBagLayout());
	
	JTextField numerolivraison1 = new JTextField(15);
	JTextField numerocommande1 = new JTextField(15);
	JTextField datelivraison1 = new JTextField(15);
	
	JLabel Livraison = new JLabel("Liste des livraisons:");
	JLabel Saisirlivraison = new JLabel("SAISIR LES DONNÉES");
	JLabel numerolivraison2 = new JLabel("Num livraison :");    
	JLabel numerocommande2 = new JLabel("Num Commande :") ;   
	JLabel datelivraison2 = new JLabel("Date livraion :") ; 
	
	JButton Ajouter = new JButton("Ajouter");	
	JButton Modifier = new JButton("Modifier");	
	JButton Supprimer = new JButton("Supprimer");	
	
	JButton Generer = new JButton("Afficher liste LIVRAISONS");
	
	JTabbedPane onglets = new JTabbedPane();

	public Livraison() {
		
		super("GESTION ENSA COMPANY");
	}
	
public JPanel PageLivraison() {
				
//---------------------------------------------------------
	                      	/* Titres */
		GridBagConstraints tp = new GridBagConstraints();
		tp.insets = new Insets(10,10,10,10); // (top ,left, bottom, right )
		tp.gridwidth = 2;
		Titre.add(Livraison, tp);
//---------------------------------------------------------
							/* Saisie */
		GridBagConstraints gb = new GridBagConstraints();

		gb.insets = new Insets(0,40,20,10); // (top ,left, bottom, right )
		gb.gridx=0;
		gb.gridy=1;
		SaisieInfos.add( numerolivraison2,gb);
		gb.insets = new Insets(0,50,20,30); 
		gb.gridx=1;
		gb.gridy=1;
		SaisieInfos.add( numerolivraison1, gb);
		gb.insets = new Insets(0,50,20,10); // (top ,left, bottom, right )
		gb.gridx=0;
		gb.gridy=2;
		SaisieInfos.add(numerocommande2,gb);
		gb.insets = new Insets(0,50,20,30); 
		gb.gridx=1;
		gb.gridy=2;
		SaisieInfos.add(numerocommande1, gb);
		gb.insets = new Insets(0,50,20,10); // (top ,left, bottom, right )
		gb.gridx=0;
		gb.gridy=3;
		SaisieInfos.add(datelivraison2,gb);
		gb.insets = new Insets(0,50,20,30); 
		gb.gridx=1;
		gb.gridy=3;
		SaisieInfos.add(datelivraison1, gb);
		

//---------------------------------------------------------
		                     /*--Tableau--*/
		  Object[] titres = {"NUM LIVRAISON", "NUM COMMANDE", "DATE LIVRAISON"}; 
       
		 
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
		  Object[] row = new Object[3];
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
						  String sql = "SELECT * FROM livraison";
						  Statement stmt = con.createStatement();
						  ResultSet R = stmt.executeQuery(sql);
						  
						  while(R.next()) {
							  model.addRow(new Object[] {
									  R.getString(1),
									  R.getString(2),
									  R.getString(3)			 
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
	  if( numerolivraison1.getText().equals("") || datelivraison1.getText().equals("")  ) {
		  JOptionPane.showMessageDialog(null, "Vous n'avez pas rempli toutes les cases");
	  }
	  else {  					  
		  try {
			  row[0] = numerolivraison1.getText();
			  row[1] = numerocommande1.getText();
			  row[2] = datelivraison1.getText();
			 
			  stmt=con.createStatement();
			  query = "INSERT INTO livraison (IDLIVRAISON, IdCommande, DateLivraison) VALUES ('"+row[0]+"','"+row[1]+"','"+row[2]+"') ";// row[0]
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
	  if(numerolivraison1.getText().equals("") && datelivraison1.getText().equals("")  ) {
		  JOptionPane.showMessageDialog(null, "Précisez le champ que vous voulez supprimer");
	  }
	  else {
		  try {
			  int i = jt.getSelectedRow();
			  if(i >= 0) {
				  stmt1=con.createStatement();
				  row[0] = numerolivraison1.getText();
				  row[1] = numerocommande1.getText();
				  row[2] = datelivraison1.getText();
				  query1 = "delete from livraison where (IDLIVRAISON ='"+row[0]+"' AND IdCommande ='"+row[1]+"' AND DateLivraison ='"+row[2]+"' ) ";
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
		  numerolivraison1.setText(model.getValueAt(i, 0).toString());
		 numerocommande1.setText(model.getValueAt(i, 1).toString());
		 datelivraison1.setText(model.getValueAt(i, 2).toString());
		
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
	     if(numerolivraison1.getText().equals("")  && numerocommande1.getText().equals("") && datelivraison1.getText().equals("")) {
	    	 	JOptionPane.showMessageDialog(null, "Précisez le champ que vous voulez modifiez");
		 }
		  else {
			  try {
			 	  int i = jt.getSelectedRow();
			 	  row[0] = numerolivraison1.getText();
				  row[1] =numerocommande1.getText();
				  row[2] =datelivraison1.getText();

				
			  if(i>=0) {
				  	  stmt1=con.createStatement();
					  query1 = "UPDATE LIVRAISON set IDLIVRAISON ='"+row[0]+"', IdCommande ='"+row[1]+"' , DateLivraison ='"+row[2]+"' WHERE  IDLIVRAISON ='"+row[0]+"'" ;  									 
				      stmt1.executeUpdate(query1);
	
						model.setValueAt(row[0], i, 0);
						model.setValueAt(row[1], i, 1);
						model.setValueAt(row[2], i, 2);
					
			  }
				  else {
					JOptionPane.showMessageDialog(null, "Problème de modification");
					System.out.println("Problème de modification");
			  }					
			  }
		  catch(SQLException ex)
			  {
			  JOptionPane.showMessageDialog(null, "Précisez le champ que vous voulez modifiez");
			  System.out.println("Précisez le champ que vous voulez modifiez");
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
	 
		onglets.addTab("Livraison",  Container);
	
		getContentPane().add(onglets);
		getContentPane().setBackground(Color.BLACK);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		return Container;

	}
	
}