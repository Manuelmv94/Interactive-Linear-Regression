import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JPanel;

public class panelLinea extends JPanel implements MouseMotionListener, MouseListener {
	private int x,y;
	private Linea linea;
	private boolean cuadricula;
	
	public panelLinea(){
		super();
		this.setPreferredSize(new Dimension(800,600));
		this.setBackground(Color.white);
		
		this.addMouseMotionListener(this);
		this.addMouseListener(this);
		
		this.cuadricula=false;
		this.linea =new Linea();
	}
	
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		this.pintarCuadricula(g);
		this.pintarEjes(g);
		this.pintarClick(g);
		this.pintarCoordenada(g);
		this.linea.pintaLinea(g);
	}
	
	public void setPunto(int x,int y){
		this.linea.addPunto(x, y);
		this.repaint();
	}
	
	public Point[] getPuntos(){
		return this.linea.getPuntos();
	}
	
	public void setBoolean(boolean r){
		this.cuadricula=r;
		this.repaint();
	}
	
	public int getN(){
		return this.linea.getN();
	}
	
	public void reset(){
		this.linea.reset();
		this.repaint();
	}
	
	public void pintarEjes(Graphics g){
		int[] x={46,50,54};
		int[] y={50,40,50};
		
		g.setColor(Color.red);
		g.fillPolygon(x, y, 3);
		g.drawLine(50, 50, 50, 550);
		g.drawLine(50, 550, 750, 550);
		
	
		
		if(this.getWidth()<=800){
			x[0]=750;
			x[1]=760;
			x[2]=750;
		}else{
			g.drawLine(750, 550, this.getWidth()-50, 550);
			x[0]=this.getWidth()-50;
			x[1]=this.getWidth()-40;
			x[2]=this.getWidth()-50;
		}
		y[0]=546;
		y[1]=550;
		y[2]=554;
		
		g.fillPolygon(x, y, 3);
	
		for(int i=50;i<550;i+=25){
			g.drawLine(46, i, 54, i);
			g.drawString(Integer.toString(100-((i-50)/5)), 26, i+4);
		}
		
		for(int i=50;i<this.getHeight()-50;i+=25){
			g.drawLine(46, i, 54, i);
			g.drawString(Integer.toString(100-((i-50)/5)), 26, i+4);
		}
		
		for(int i=75;i<this.getWidth()-50;i+=25){
			g.drawLine(i, 546, i, 554);
			
			if(((i-50)/5)<10){
				g.drawString(Integer.toString(((i-50)/5)), i-3, 565);
			}else if(((i-50)/5)<100){
				g.drawString(Integer.toString(((i-50)/5)), i-7, 565);
			}else{
				g.drawString(Integer.toString(((i-50)/5)), i-10, 565);
			}
		}

		
		for(int i=75;i<750;i+=25){
			g.drawLine(i, 546, i, 554);
			
			if(((i-50)/5)<10){
				g.drawString(Integer.toString(((i-50)/5)), i-3, 565);
			}else if(((i-50)/5)<100){
				g.drawString(Integer.toString(((i-50)/5)), i-7, 565);
			}else{
				g.drawString(Integer.toString(((i-50)/5)), i-10, 565);
			}
		}
		
		x[0]=46;
		x[1]=50;
		x[2]=54;
		y[0]=this.getHeight()-50;
		y[1]=this.getHeight()-40;
		y[2]=this.getHeight()-50;
		
		if(this.getHeight()>600){
			g.drawLine(50, 50, 50, this.getHeight()-50);
			g.fillPolygon(x, y, 3);
		}
	}
	
	public void pintarCuadricula(Graphics g){
		if (this.cuadricula){
			g.setColor(new Color(189,189,189));
			
			for(int i=0;i<this.getHeight();i+=25){
				g.drawLine(0, i, this.getWidth(), i);
			}
			for(int i=0;i<this.getWidth();i+=25){
				g.drawLine(i, 0, i, this.getHeight());
			}
		}	
	}
	
	public void pintarCoordenada(Graphics g){
			g.setColor(new Color(240,247,176));
			g.fillRect(this.x+1, this.y+1, 61, 15);
			
			g.setColor(Color.BLACK);
			g.drawRect(this.x, this.y, 63, 17);
			
			g.drawString("("+(this.x-50)/5+","+(100-(this.y-50)/5)+")", this.x+10, this.y+12);

	}
	
	public void pintarClick(Graphics g){
		
			g.setColor(Color.BLUE);
			
			for(int i=0;i<this.linea.getN();i++){
				if(this.linea.getPuntos()[i]!=null){
					g.fillOval((5*((int)this.linea.getPuntos()[i].getX())+50)-2, ((100-((int)this.linea.getPuntos()[i].getY()))*5+50)-2, 4, 4);
				}
			}
	
	}
	
	public void mouseDragged(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		this.x=e.getX();
		this.y=e.getY();
		this.repaint();
		
	}

	public void mouseClicked(MouseEvent e) {
	
		this.linea.addPunto((e.getX()-50)/5, (100-(e.getY()-50)/5));
		this.repaint();
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
	
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
}
