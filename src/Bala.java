import processing.core.PApplet;
import processing.core.PConstants;
import processing.core.PImage;

public class Bala extends Juego{

	private int posX, posY;
	
	public Bala(int posX, int posY) {
		super(posX,posY);
	}
	
	public void mover() {
		setPosY(getPosY()-10);
	}
	
	

}
