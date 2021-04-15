package logicaJuego;

public class Constantes {
	
	public static final int ALTO = 10;
	public static final int ANCHO = 10;
	
	public static final int POZOS = (int) (Math.random() * 10) + 1;
	public static final int NUM_ROCAS = (int) (Math.random() * 10) + 1;
	public static final int NUM_GEMAS= (int) (Math.random() * 10) + 1;
	public static final int NUM_POCIONES= (int) (Math.random() * 10) + 1;
	public static final int NUM_DINERO= (int) (Math.random() * 10) + 1;
	
	public static final char ROCA = 'R';
	public static final char POZO = 'Z';
	public static final char GEMA = 'G';
	public static final char POCION = 'P';
	public static final char DINERO = 'D';
	public static final char JUGADORES[] = {'A', 'B', 'C', 'E', 'F', 'H'};
	// ELFO
	public static final int ELFO_VEL = 7;
	public static final int ELFO_MAGIA = 6;
	public static final int ELFO_FUERZA = 5;
	// OGRO
	public static final int OGRO_VEL = 4;
	public static final int OGRO_MAGIA = 4;
	public static final int OGRO_FUERZA = 7;
	// GUERRERO
	public static final int GUERRERO_VEL = 5;
	public static final int GUERRERO_MAGIA = 5;
	public static final int GUERRERO_FUERZA = 6;
	// MAGO
	public static final int MAGO_VEL = 6;
	public static final int MAGO_MAGIA = 7;
	public static final int MAGO_FUERZA = 4;
	
	public static final int MAGIA_POZO_MAX = (int) (Math.random() * 10) + 1;
	public static final int MINIMO_JUGADORES = 2;
	public static final int MAXIMO_JUGADORES = 6;
	
	public static char getJugadoresLetra(int index) {
		return JUGADORES[index];
	}
	
}
