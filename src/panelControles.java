import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.StringTokenizer;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class panelControles extends JPanel implements ActionListener {
	private JButton bAddpunto,
					bGuardarPuntos,
					bCargarpuntos,
					bReset;
	
	private JTextField tfX,
					   tfY;
	
	private panelLinea PL;
	
	private JFileChooser archivo;
	
	private Image fondo;
	
	private JCheckBox chCuadricula;
	
	public panelControles(panelLinea PL){
		super();
		this.setPreferredSize(new Dimension(130,200));
		this.setBackground(Color.BLACK);
		this.PL=PL;
		
		this.archivo=new JFileChooser("Selecciona un archivo");
		
		this.fondo=new ImageIcon("luz.jpg").getImage();
		
		this.bAddpunto=new JButton("Agregar Punto");
		this.bAddpunto.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				try{
					PL.setPunto(Integer.parseInt(tfX.getText()),Integer.parseInt(tfY.getText()) );
				}catch(RuntimeException ex){
					if(tfX.getText().isEmpty() && tfY.getText().isEmpty()){
						JOptionPane.showMessageDialog(null, "No ha ingresado ninguna coordenada");
					}else if(tfX.getText().isEmpty() || tfY.getText().isEmpty()){
						if(tfX.getText().isEmpty()){
							JOptionPane.showMessageDialog(null, "Ingrese la coordenada en X");
						}else if(tfY.getText().isEmpty()){
							JOptionPane.showMessageDialog(null, "Ingrese la coordenada en Y");
						}
					}else{
						JOptionPane.showMessageDialog(null, "Solo se aceptan numeros enteros");
					}
						
						
				}
				
			}
			
		});

		
		this.bGuardarPuntos=new JButton("Guardar Puntos");
		this.bGuardarPuntos.addActionListener(this);
		this.bCargarpuntos=new JButton("Cargar Puntos");
		this.bCargarpuntos.addActionListener(this);
		this.bReset= new JButton("Reset");
		this.bReset.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				PL.reset();
				
			}
			
		});
		
		this.chCuadricula=new JCheckBox("Activar cuadricula");
		this.chCuadricula.setContentAreaFilled(false);
		this.chCuadricula.setForeground(Color.WHITE);
		this.chCuadricula.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				if(chCuadricula.isSelected()){
					PL.setBoolean(true);
				}else{
					PL.setBoolean(false);
				}
				
			}
			
		});
		
		this.tfX=new JTextField(3);
		this.tfY=new JTextField(3);
		
		JPanel blank=new JPanel();
		blank.setBackground(Color.BLACK);
		blank.setPreferredSize(new Dimension(100,35));
		
		JPanel blank1=new JPanel(){
			
			
			public void paintComponent(Graphics g){
				g.drawImage(fondo, 0, 0,170,this.getHeight(), this);
			}
		};
		
		blank1.setPreferredSize(new Dimension(130,370));

		
		
		JLabel JLx=new JLabel("x:"),
			   JLy=new JLabel("y:");
		JLx.setForeground(Color.WHITE);
		JLy.setForeground(Color.WHITE);
		
		
		this.add(JLx);
		this.add(this.tfX);
		this.add(JLy);
		this.add(this.tfY);
		this.add(this.bAddpunto);
		this.add(blank);
		this.add(this.bGuardarPuntos);
		this.add(this.bCargarpuntos);
		this.add(blank1);
		this.add(this.bReset);
		this.add(this.chCuadricula);
	}

	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==this.bCargarpuntos){
			int opcion=archivo.showOpenDialog(this.PL);
			BufferedReader br;
			StringTokenizer st;
			String linea;
			
			if(opcion==JFileChooser.APPROVE_OPTION){
				try {
					br=new BufferedReader(new FileReader(this.archivo.getSelectedFile().toString()));
					this.PL.reset();
					while((linea=br.readLine())!=null){
						st=new StringTokenizer(linea);
						this.PL.setPunto(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
						
					}
					
					br.close();
				} catch (IOException e1) {
					JOptionPane.showMessageDialog(null, "Ruta no válida");
				} catch(RuntimeException e2){
					JOptionPane.showMessageDialog(null, "Archivo inválido");
				}
				
	
			}
			
		}else if(e.getSource()==this.bGuardarPuntos){
			int opcion=archivo.showSaveDialog(this.PL);
			PrintWriter pw;
			
			if(opcion==JFileChooser.APPROVE_OPTION){
				try {
					pw=new PrintWriter(new FileWriter(this.archivo.getSelectedFile().toString()));
					for(int i=0;i<this.PL.getN();i++){
						if(this.PL.getPuntos()[i]!=null){
							pw.println((int)(this.PL.getPuntos()[i].getX())+" "+(int)(this.PL.getPuntos()[i].getY()));
						}
					}
					pw.close();
				} catch (IOException e1) {
					JOptionPane.showMessageDialog(null, "Ruta no válida");
				}
				
	
			}
		}
	}
	
	
}
