package breakout;



import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.Arc2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.util.Observable;
import java.util.Observer;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.border.EmptyBorder;

import com.sun.javafx.tk.Toolkit;

import static breakout.Valores.*;

public class VistaBreakout extends JFrame implements Observer
{ 
	private Controlador control;
	private Juego   pelota;             
	private Juego[] bloques;         
	private Juego   barra;             
	private int     puntuacion =  0;    
	public boolean estadoJuego = false;
	//boolean fondo = false;
	vistaMenu vista;

	public VistaBreakout()
	{
		setSize( ancho, altura );                    
		addKeyListener( new obtenerTecla() );    
		setDefaultCloseOperation(this.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);	

	}


	public void dibujarTablero( Graphics2D g )
	{

	//	g.setPaint( Color.BLACK );


		g.fill( new  Rectangle2D.Float( 0, 0, ancho, altura ) );

		Font fuente  = new Font("Monospaced",Font.BOLD,24); 
		g.setFont( fuente ); 


		g.setPaint( Color.YELLOW );         
		g.draw( new Rectangle2D.Float( borde, bordeT, ancho-borde*2, altura-bordeT-borde ) );


		mostrar( g, pelota );

		for (int  a=0; a<bloques.length; a++) 
			if (bloques[a]!=null){
				mostrar (g,bloques[a]); 
			}

		mostrar( g, barra );

		g.setPaint( Color.black );


	}
	private void mostrar( Graphics2D g, Juego go )
	{
		switch( go.obtenerColor() )
		{
		case GRAY: g.setColor( Color.gray );
		break;
		case BLUE: g.setColor( Color.red );
		break;
		case RED:  g.setColor( Color.RED);
		break;
		case GREEN: g.setColor(Color.green);
		break;
		case BLACK: g.setColor(Color.cyan);
		break;
		case WHITE: g.setColor(Color.white);
		break;
		case YELLOW: g.setColor(Color.YELLOW);
		break;


		}
		g.fill( new Rectangle2D.Float( go.obtenerX(),     go.obtenerY(), 
				go.obtenerAncho(), go.obtenerAlto() ) );
	}
	public void volverMenu(boolean estado){
		vistaMenu vista = new vistaMenu();
		bloques = null;
		getContentPane().removeAll();

		dispose();
	}
	@Override
	public void update( Observable modelo, Object arg )
	{
		ModeloBreakout model = (ModeloBreakout) modelo;
			estadoJuego = model.estado();
		if(estadoJuego == true){
			
			pelota    = model.obtenerPelota();             
			bloques  = model.obtenerLadrillos(); 
			barra     = model.obtenerBarra();               
			puntuacion   = model.obtenerMarcador();    

			repaint();    
		}else{

			getContentPane().removeAll();

			vista=   new vistaMenu();
			vista.setVisible(true);
			dispose();
			model.paraHilo();

		}
  
		



	}

	@Override
	public void update( Graphics g )          
	{

		dibujarJuego( (Graphics2D) g ); 

	}

	@Override
	public void paint( Graphics g )          
	{                                         
		dibujarJuego( (Graphics2D) g );         
	}

	private BufferedImage buffer;              
	private Graphics2D    g2d;             

	public void dibujarJuego( Graphics2D g )   
	{                                         
		if (  g2d == null )
		{
			Dimension d = getSize();              
			buffer = (BufferedImage) createImage( d.width, d.height );
			g2d = buffer.createGraphics();
		}
		dibujarTablero( g2d );             
		g.drawImage( buffer, 0, 0, this );       
	}


	public void obtenerControlador(Controlador controle)
	{
		control = controle;
	}

	class obtenerTecla implements KeyListener  
	{
		@Override
		public void keyPressed(KeyEvent e)      
		{
			control.Boton( -e.getKeyCode() );
		}

		@Override
		public void keyReleased(KeyEvent e)
		{
		}

		@Override
		public void keyTyped(KeyEvent e)
		{
			control.Boton( e.getKeyChar() );
		}
	}

	public boolean isEstadoJuego() {
		return estadoJuego;
	}


	public void setEstadoJuego(boolean estadoJuego) {
		this.estadoJuego = estadoJuego;
	}
}
