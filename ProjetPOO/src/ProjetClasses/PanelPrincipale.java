package ProjetClasses;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

public class PanelPrincipale extends JFrame {
	
	JTabbedPane onglets = new JTabbedPane();
	
	Client A = new Client();
	Produit B = new Produit();
	Livraison C = new Livraison();
	Commande D = new Commande();
	Facture E = new Facture();
	produit_vendus G = new produit_vendus();


	public PanelPrincipale() {
		
		onglets.addTab("Clients", A.PageClient());
		onglets.addTab("Produits", B.PageProduit());
		onglets.addTab("Livraisons", C.PageLivraison());
		onglets.addTab("Commandes", D.PageCommande());	
		onglets.addTab("Factures", E.PageFacture());
		onglets.addTab("Ventes", G.PagePV());
		
		this.setVisible(true);
		this.setSize(1000,600);		
		
		getContentPane().add(onglets);
		getContentPane().setBackground(Color.BLACK);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
	} 
	
}
