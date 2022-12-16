package ProjetClasses;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.*;

public class Admin extends JFrame {
	/*-- LOGIN LABELS --*/
	JLabel login=new JLabel("Log in : ") ;
	JLabel password=new JLabel("Password : ") ;
	JLabel logo = new JLabel(" ENSA COMPANY ") ;
	/*-- LOGIN BUTTON --*/
	JButton SeConnecter = new JButton("Se connecter") ;
	/*-- Login Text Areas --*/
	JTextField txtlog=new JTextField(15) ; 
	JPasswordField txtcle=new JPasswordField(15) ; 
	/*-- Login Panels --*/
	JPanel panel= new JPanel(new BorderLayout()) ;
	JPanel panel1= new JPanel(new GridBagLayout()) ;
	JPanel panel2= new JPanel() ;
	/* -- Positionnement du form -- */
	GridBagConstraints gb = new GridBagConstraints();
	/*-- Constructeur --*/
	public Admin() {
		this.setVisible(true) ;
		this.setSize(400,400) ;
		this.setDefaultCloseOperation(EXIT_ON_CLOSE) ;	

		/*-- Fonts --*/
		Font font = new Font("TimesRoman", 3, 45);
		Font font1 = new Font("TimesRoman", 3, 20);
		SeConnecter.setFont(new Font("Arial", Font.PLAIN, 20));
		login.setFont(font1);
		logo.setFont(font);
		password.setFont(font1);
		/*-- Titre Login --*/
		logo.setBackground(Color.cyan);
		logo.setForeground(Color.BLACK);
		/* -- Positionnement du form -- */
		gb.insets = new Insets(5,30,10,10); // (top ,left, bottom, right )
		gb.gridx=0;
		gb.gridy=0;
		panel1.add(login ,gb);
		
		gb.gridx=1 ;
		gb.gridy=0 ;
		panel1.add(txtlog,gb) ;
		
		gb.gridx=0 ;
		gb.gridy=1 ;
		panel1.add(password,gb) ;
		
		gb.gridx=1 ;
		gb.gridy=1 ;
		panel1.add(txtcle,gb) ;
			
		gb.gridx=1 ;
		gb.gridy=2 ;
		panel1.add(SeConnecter,gb) ;
		/* -- Ajout du logo sur le Panel 2 --*/
		panel2.add(logo);
		/* -- Ajout panels sur le Panel Principale pour les afficher--*/
		this.getContentPane().add(panel) ;
		panel.add(panel1,BorderLayout.CENTER) ;
		panel.add(panel2,BorderLayout.NORTH) ;
		
		SeConnecter.addActionListener(new ActionListener(){
			  public void actionPerformed(ActionEvent e) 
				  {
				  	  int i = 0;
					  /*-- Saisie de USER et PASSWORD --*/
					  String li = txtlog.getText();
					  String pw = txtcle.getText();	  
					  /*-- SQL statements --*/
					  Statement stmt;
					  /*--Recevoir des données de la base de données --*/
					  ResultSet resultat=null ;	
					  /*--Sql query --*/
					  String query ;
					  /*-- Essai de la connection a la base de donnees --*/
					  try {
						  Connection con = DriverManager.getConnection("jdbc:mysql://localhost/projet_poo", "root", "");
						  stmt=con.createStatement();
						  query = "select user ,password from admin ";
						  resultat=  stmt.executeQuery(query);
							/* Saisie des infos pour le login  */
							while(resultat.next()) {
								String admin1=resultat.getString("user" ) ;
								String admincle=resultat.getString("password" ) ;
								if(admin1.equals(li)  &&  admincle.equals(pw) ) {
									i=1 ;
									PanelPrincipale obj = new PanelPrincipale();

									if(i==1) 
										  setVisible(false);
									JOptionPane.showMessageDialog(null,"Welcome to ENSA COMPANY!") ;					
								}
							}
							if(i!=1)		JOptionPane.showMessageDialog(null,"Réssayer!") ;		
							
					  } catch(Exception ex){
						  JOptionPane.showMessageDialog(null,"Cet utilisateur n'existe pas!") ;
						  System.out.println("L'Erreur est "+ ex);
					  }
				  }
		  });

	}
	
	public static void main(String[] args) {
		new Admin() ;	
	}


}
