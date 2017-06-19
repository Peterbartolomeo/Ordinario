package breakout;



import static breakout.Valores.*;

import java.awt.event.KeyEvent;

public class Controlador
{
	private ModeloBreakout model;   
	private VistaBreakout  vista;    
	//Archivo ar = new Archivo();

	public Controlador( ModeloBreakout modelo, 
			VistaBreakout  vistaB )
	{

		model = modelo;
		vista = vistaB;
		vista.obtenerControlador(this);    
	}


	public void Boton(int tecla )
	{

		switch ( tecla )               
		{
		case -KeyEvent.VK_LEFT:        
			model.moverBarra( -velocidadBarra );
			break;
		case -KeyEvent.VK_RIGHT:       
			model.moverBarra( +velocidadBarra );
			break;
		case -KeyEvent.VK_UP:          

			model.reanudarJuego();
			break;
		case -KeyEvent.VK_DOWN:       

			model.pararJuego();
			break;
		}
	}

}
