import java.util.ArrayList;

import processing.core.PApplet;
import processing.core.PConstants;
import processing.core.PImage;

public class Personaje extends Juego{

	private int vidas;
	private char dir;
	
	private ArrayList<Bala> bala;
	
	public Personaje(int posX,int posY) {
		super(posX,posY);
		this.vidas = 3;
		this.bala = new ArrayList<Bala>();
	}
	
	public void mover (int dir) {
		switch (dir) {
		case 'a':
		posX-= 5;
			break;
		case 'd':
		posX+= 5;
			break;
		}
	}
	
	public void disparar() {
		Bala actual = new Bala(getPosX()+55, getPosY()-155);
		bala.add(actual);
	}

	public int getVidas() {
		return vidas;
	}

	public void setVidas(int vidas) {
		if(vidas<0) {
			vidas=0;
		}
		else {
			
			this.vidas = vidas;
		}
	}

	public char getDir() {
		return dir;
	}

	public void setDir(char dir) {
		this.dir = dir;
	}

	public ArrayList<Bala> getBala() {
		return bala;
	}

	public void setBala(ArrayList<Bala> bala) {
		this.bala = bala;
	}
	
	

}
