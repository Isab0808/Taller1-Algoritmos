
public class Juego {

	public int posX;
	public int posY;
	private boolean vivo;
	
	public Juego(int posX, int posY) {
		this.posX=posX;
		this.posY=posY;
		this.vivo=true;
	}
	
	public int getPosX() {
		return posX;
	}

	public void setPosX(int posX) {
		this.posX = posX;
	}

	public int getPosY() {
		return posY;
	}

	public void setPosY(int posY) {
		this.posY = posY;
	}

	public boolean isVivo() {
		return vivo;
	}

	public void setVivo(boolean vivo) {
		this.vivo = vivo;
	}

}
