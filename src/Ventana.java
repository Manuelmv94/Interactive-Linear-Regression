import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JFrame;

public class Ventana extends JFrame {

	public Ventana(){
		super("Ajuste de una Recta");
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		panelLinea pL=new panelLinea();
		panelControles pc=new panelControles(pL);
		
		this.add(pL);
		this.add(pc,BorderLayout.WEST);
		
		this.pack();
		this.setVisible(true);	
	}
	
	public static void main(String[] args) {
			Ventana v=new Ventana();
	}

}
