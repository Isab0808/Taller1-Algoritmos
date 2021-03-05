import java.util.ArrayList;

import processing.core.PApplet;
import processing.core.PImage;

public class Principal extends PApplet{

	public int MAX_INV_ROWS=4;
	
	public static void main(String[] args) {
		PApplet.main("Principal");
	}

	@Override
	public void settings() {
		size(700,900);
		generar();
	}
	//Creando el Arraylist
		private ArrayList<Carbon> listaCarbones = new ArrayList<Carbon>();
		
	//LLamando a la clase
		private Personaje personaje;
		
	//Para el switch
		int estado;
		
	//Variables para la pantalla de Resumen
		int puntaje;
		int puntajeFinal;
		
	// Temporizador
		int segundos;
		int minutos;
		
	// x y y de Boton Inicio
		int xBoton;
		int yBoton;
		
	//PImage del cambio de pantallas
		PImage[] pantalla;
		
	// PImage individuales
		PImage manoJugador;
		PImage bala;
		PImage carbon;
	
	@Override
	public void setup() {
	//Inicializo el arraylist
		listaCarbones = new ArrayList<>();
		personaje = new Personaje(width/2, 772);	
		estado = 0;
		puntaje = 0;
		segundos = 0;
		minutos = 0;
		xBoton = 610;
		yBoton = 760;
		
	// Cargando PImages del cambio de pantallas
		pantalla = new PImage[5];
		pantalla[0] = loadImage("PantallaPrincipal.jpg");
		pantalla[1] = loadImage("PantallaInstrucciones.jpg");
		pantalla[2] = loadImage("PantallaJuego.jpg");
		pantalla[3] = loadImage("PantallaResumen.jpg");
		pantalla[4] = loadImage("BotonInicioO.png");
		
	// Cargando PImages individuales
		manoJugador = loadImage("ManoJugador.png");
		bala = loadImage("BolaBala.png");
		carbon = loadImage("Carbon.png");
	}
	
	@Override
	public void draw() {
		
		switch (estado) {
		case 0:
			// INICIO
			// Imagen Inicio
				imageMode(CENTER);
				image(pantalla[0], 350, 450);
				imageMode(CORNER);
				
				// Si esta el mouse encima del Boton Inicio mostrar imagen Boton Inicio Oprimido
				if (mouseX > xBoton && mouseX < xBoton + 139 && mouseY > yBoton && mouseY < yBoton + 94) {
					image(pantalla[4], 561, 732);
				}
				
			break;
		case 1:
			// INSTRUCCIONES
			// Imagen Instrucciones
				imageMode(CENTER);
				image(pantalla[1], 350, 450);
				imageMode(CORNER);
				
				// Si esta el mouse encima del Boton Inicio mostrar imagen Boton Inicio Oprimido
				if (mouseX > xBoton && mouseX < xBoton + 139 && mouseY > yBoton && mouseY < yBoton + 94) {
					image(pantalla[4], 561, 732);
				}
				
			break;
		case 2:
			// JUEGO-ESCENARIO
			// Imagen Juego-Escenario
				imageMode(CENTER);
				image(pantalla[2], 350, 450);
				imageMode(CORNER);
				
					try {
				if(personaje.getVidas()>0) {
					imageMode(CENTER);
					image(manoJugador, personaje.getPosX(), personaje.getPosY());
					imageMode(CORNER);
				}
				//Para que cuando el personaje ya no tenga vidas pase a la PANTALLA DE RESUMEN (Perdio)
				else if(personaje.getVidas()==0){
					estado = 3;
				}	
				//Para que aparezca el texto en la pantalla para poder iniciar el juego
				if(listaCarbones.size()==0) {
					textMode(CENTER);
					fill(37,142,40);
					textSize(40);
					text("Bienvenido", 245, 350);
					textMode(CORNER);
					personaje.setVidas(3);
				}
				
				//Cuando el personaje dispare una bola, que esta sea eliminada
				for(Bala b : personaje.getBala()) {
					if(b.getPosY()<=0) {
						personaje.getBala().remove(b);
					}
					else {
						//Bala pintar y mover
						imageMode(CENTER);
						image(bala, b.getPosX(), b.getPosY());
						imageMode(CORNER);
						b.mover();
					}
				}
				//Cuando la bala toque un carbon, este se elimine de la lista y sume al puntaje del jugador
				for(Carbon i : listaCarbones) {
					if(i.validarBala(personaje.getBala())) {
						listaCarbones.remove(i);
						puntaje += 10;
				
					}
					else {
					//Cuando los carbones llegan al limite del lienzo se eliminen y que el personaje pierda la vida
						if(i.getPosY()>= (personaje.getPosY()- 135)) {
							listaCarbones.remove(i);
							personaje.setVidas(personaje.getVidas()-1);
						}
						//Carbon pintar y mover
						imageMode(CENTER);
						image(carbon, i.getPosX(), i.getPosY());
						imageMode(CORNER);
						i.mover();
					}
				}
			}
		catch (Exception e) {
			
		}
			//Puntaje
				fill(255);
				textSize(20);
				text(puntaje,280, 42);
				puntajeFinal = puntaje;
				
				temporizador();
				
				//Para que se agregue cada cierto tiempo un nuevo carbon
				if (frameCount %120 == 0) {
					generar();
				}
				break;
				
		case 3:
			// RESUMEN
			// Imagen Resumen
				imageMode(CENTER);
				image(pantalla[3], 350, 450);
				imageMode(CORNER);
				
				// Si esta el mouse encima del Boton Inicio mostrar imagen Boton Inicio Oprimido
				if (mouseX > xBoton && mouseX < xBoton + 139 && mouseY > yBoton && mouseY < yBoton + 94) {
					image(pantalla[4], 561, 732);
				}
				
				//Puntaje Final
				fill(255);
				textSize(25);
				text(puntajeFinal,390, 395);
				
				//Tiempo Final
				if (segundos <= 9) {
				    fill(255);
				    textSize(25);
				    text(minutos + ":0" + segundos, 383, 343);
				  } else if (segundos > 9) {
				    fill(255);
				    textSize(25);
				    text(minutos + ":" + segundos, 383, 343);
				  }

	}
	}
	
	public void temporizador() {
		if (frameCount % 60 == 0 && minutos >= 0) {
		      segundos++;
		  }
		  if (segundos == 60) {
		      minutos++;
		      segundos = 0;
		  }
		  if (minutos < 0) {
		    fill(255);
		    textSize(20);
		    text("0:00", 501, 42);
		  } else if (segundos <= 9) {
		    fill(255);
		    textSize(20);
		    text(minutos + ":0" + segundos, 501, 42);
		  } else if (segundos > 9) {
		    fill(255);
		    textSize(20);
		    text(minutos + ":" + segundos, 501, 42);
		  }
	}
	
	public void generar() {
		for(int i=0;i<MAX_INV_ROWS;i++) {
				Carbon actual = new Carbon(i*100, 50);
				listaCarbones.add(actual);
		}
	}
		
	@Override
	public void keyPressed() {
		personaje.mover(key);
		
		if(keyCode == 32) {
			personaje.disparar();
		}
	}
	
	@Override
	public void mousePressed() {
		
		switch (estado) {
		case 0:
			// INICIO
				// Cuando se le de clic en Boton Inicio pasar a la pantalla Instrucciones
				// Generales
				if (mouseX > xBoton && mouseX < xBoton + 139 && 
					mouseY > yBoton && mouseY < yBoton + 94) {
					estado = 1;}
				
			break;
		case 1:
			// INSTRUCCIONES
				// Cuando se le de clic en Boton Inicio pasar a la pantalla Instrucciones
				// Generales
				if (mouseX > xBoton && mouseX < xBoton + 139 && 
					mouseY > yBoton && mouseY < yBoton + 94) {
					estado = 2;}
			break;
			
		case 2:
			//JUEGO-ESCENARIO
			//Cuando se de clic a la pantalla, empiecen a salir los carbones
			if(listaCarbones.size()==0){
				generar();}
			break;
			
		case 3:
			//RESUMEN
			// Cuando se le de clic en siguiente, vuelva a empezar una nueva partida
			if (mouseX > xBoton && mouseX < xBoton + 139 && 
				mouseY > yBoton && mouseY < yBoton + 94) {
				estado = 0;
				listaCarbones = new ArrayList<>();
				personaje = new Personaje(width/2, 772);	
				puntaje = 0;
				segundos = 0;
				minutos = 0;}
		}
	}
}
