package breakout;


import breakout.Valores.Colores;


public class Juego
{
	public enum Collision { 
		choque, 
		no_choque 
	};
	private float topX   = 0.0f; 
	private float topY   = 0.0f; 
	private float ancho  = 0.0f; 
	private float altura = 0.0f; 
	private Colores color; 
	private int   dirX   = 1;  
	private int   dirY   = 1;   

	public Juego( float x, float y, float anc, float alt, Colores c )
	{
		topX   = x;       topY = y;
		ancho  = anc; altura = alt; 
		color = c;
	}
	 
	public float obtenerX() { 
		return topX; 
	}
	public float obtenerY(){
		return topY; 
	}
	public float obtenerAncho(){
		return ancho;
	}
	public float obtenerAlto(){
		return altura; 
	}
	public Colores obtenerColor(){
		return color; 
	}

	public void moverenX( float unidades )
	{
		topX += unidades * dirX;
	}

	
	public void moverenY( float unidades )
	{
		topY += unidades * dirY;
	}

	public void cambiarDireccionX()
	{
		dirX = -dirX;
	}

	public void cambiarDireccionY()
	{
		dirY = -dirY;
	}

	public Collision choque( Juego obj )
	{
		if ( topX >= obj.topX+obj.ancho     ||
				topX+ancho <= obj.topX         ||
				topY >= obj.topY+obj.altura    ||
				topY+altura <= obj.topY )
			return Collision.no_choque;
		else {
			return Collision.choque;
		}
	}

}
