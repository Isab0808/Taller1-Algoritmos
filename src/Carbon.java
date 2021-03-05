import java.util.ArrayList;

import processing.core.PApplet;
import processing.core.PConstants;
import processing.core.PImage;

public class Carbon extends Juego {
	
	private boolean validar;
	
	public Carbon(int posX,int posY) {
		super(posX,posY);
		this.validar = false;
	}

	public void mover() {
		setPosY(getPosY()+1);
	}
	
	public boolean validarBala(ArrayList<Bala> bala) {
		for(Bala b : bala) {
			if((b.getPosX()>=getPosX() && b.getPosX()<=getPosX()+20)&&(b.getPosY()>=getPosY() && b.getPosY()<=getPosY()+20)) {
				validar=true;
				bala.remove(b);
				break;
			}
		}
		return validar;
	}
}
