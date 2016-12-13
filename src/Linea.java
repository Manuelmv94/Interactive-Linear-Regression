import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;

public class Linea {
	private Point[] puntos;
	private Point[] aux;
	
	private int n,
				Sx,
				Sy,
				Sxx,
				Sxy;
				
	private double xp,
				   yp,
				   m,
				   b;

	
	public Linea(){
		this.puntos=new Point[10];
		
		this.Sx=this.Sy=this.Sxx=this.Sxy=this.n=0;
		
	}
	
	public void addPunto(int x, int y){
		
		this.puntos[n]=new Point(x,y);
		this.n++;
		
		
		this.Sx+=x;
		this.Sy+=y;
		this.Sxx+=(x*x);
		this.Sxy+=(x*y);
		
		this.calculaLinea();
		
		if(this.n==this.puntos.length){
			this.resize();
		}
	}
	
	public void reset(){
		this.puntos=new Point[10];
		
		
		
		this.Sx=this.Sy=this.Sxx=this.Sxy=this.n=0;
		this.xp=this.yp=this.m=this.b=0.0;
		
		
	}
	
	public Point[] getPuntos(){
		return this.puntos;
	}
	
	public int getN(){
		return this.n;
	}
	
	public void resize(){
		this.aux= new Point[this.puntos.length];
		
		for(int i=0;i<this.puntos.length;i++){
			aux[i]=this.puntos[i];
		}
		
		this.puntos=new Point[this.aux.length*2];
		for(int i=0;i<aux.length;i++){
			this.puntos[i]=aux[i];
		}
		
		this.aux=null;
	}

	public void calculaLinea(){
		if(this.n>1){
			this.xp=((double)this.Sx/this.n);
			this.yp=((double)this.Sy/this.n);
			
			this.m=((this.n*this.Sxy)-(this.Sx*this.Sy))/(double)((this.n*this.Sxx)-(this.Sx*this.Sx));
			this.b=this.yp-(this.m*this.xp);
		}
	}

	public void pintaLinea(Graphics g){
		g.setColor(Color.BLACK);
		g.setFont(new Font("Century Gothic",Font.BOLD,15));
		
		if(this.b<0){
			double aux=-this.b;
			
			g.drawString("f(x)="+this.m+" x - "+aux, 20, 20);
		}else{
			g.drawString("f(x)="+this.m+" x + "+this.b, 20, 20);
		}
		
		if(this.n>1){
			g.drawLine(50,(int)(5*(100-this.b)+50), 1150, (int)(5*(100-(220*this.m+this.b))+50));
		}
	}
}
