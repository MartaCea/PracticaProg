package logicaJuego;

import elementos.Elemento;
import elementos.Elfo;
import elementos.Guerrero;
import elementos.Jugador;
import elementos.Mago;
import elementos.Ogro;

public class Juego {

	protected static int turnoJugador;

	private Elemento[][] tablero;
	private Jugador[] jugadores;
	private int numJugadores;
	private boolean terminado;

	public Juego(int alto, int ancho, int jugadores) throws JuegoException {
		super();
		if (jugadores < Constantes.MINIMO_JUGADORES || jugadores > Constantes.MAXIMO_JUGADORES) {
			throw new JuegoException("No podemos juegar porque tenemos que ser como mínimo 2 jugadores y máximo 6");
		}
		this.tablero = new Elemento[Constantes.ANCHO][Constantes.ALTO];
		this.jugadores = new Jugador[jugadores];
		this.numJugadores = 0;
		this.terminado = false;

	}

	public Elemento[][] getTablero() {
		return tablero;
	}

	public Jugador[] getJugadores() {
		return jugadores;
	}

	public void setJugadores(Jugador[] jugadores) {
		this.jugadores = jugadores;
	}

	public int getNumJugadores() {
		return numJugadores;
	}

	public void setNumJugadores(int numJugadores) {
		this.numJugadores = numJugadores;
	}

	public boolean isTerminado() {
		return terminado;
	}

	public void setTerminado(boolean terminado) {
		this.terminado = terminado;
	}

	/**
	 * Creamos el jugador y dependiendo de que tipo sea el jugador, la clase del
	 * jugador será diferente (ELFO, OGRO, etc).
	 * 
	 * @param tipo
	 * @throws JuegoException
	 */
	public void crearJugador(TipoJugador tipo) throws JuegoException {

		switch (tipo) {
		case ELFO:
			Jugador elfo = new Elfo(Constantes.getJugadoresLetra(numJugadores));
			jugadores[numJugadores] = elfo;
			break;
		case OGRO:
			Jugador ogro = new Ogro(Constantes.getJugadoresLetra(numJugadores));
			jugadores[numJugadores] = ogro;
			break;
		case GUERRERO:
			Jugador guerrero = new Guerrero(Constantes.getJugadoresLetra(numJugadores));
			jugadores[numJugadores] = guerrero;
			break;
		case MAGO:
			Jugador mago = new Mago(Constantes.getJugadoresLetra(numJugadores));
			jugadores[numJugadores] = mago;
			break;
		}

		numJugadores++;

		if (numJugadores > jugadores.length) {
			throw new JuegoException("Ya están todo los jugadores para poder jugar");
		}

	}

	/**
	 * Mostramos los jugadores que hay y que letra se le ha asignado a ese jugador
	 * 
	 * @return String
	 */
	public String nombresJugadores() {
		StringBuilder sb = new StringBuilder();

		for (int i = 0; i < numJugadores; i++) {
			sb.append("El jugador " + (i + 1) + " es el caracter " + Constantes.getJugadoresLetra(i) + "\n");
		}

		return sb.toString();
	}

	/**
	 * Mostramos las monedas, gemas y pociones de cada jugador
	 * 
	 * @return String
	 */
	public String valoresJugadores() {
		StringBuilder sb = new StringBuilder();

		for (int i = 0; i < numJugadores; i++) {
			sb.append("Jugador: " + Constantes.getJugadoresLetra(i) + " Dinero: " + jugadores[i].getDinero()
					+ " Pociones: " + jugadores[i].getPociones() + " Gema: " + jugadores[i].getGemas() + "\n");
		}

		return sb.toString();
	}

	// Esta hecho el dibujo del tablero vacío sin las gemas, rocas, etc. Para ver
	// como se vería
	public String toString() {
		// StringBuilder porque sino no saldrá todo el tablero
		StringBuilder sb = new StringBuilder();
		Elemento vacio = new Elemento(' ');
		Elemento gemas = new Elemento(Constantes.GEMA);
		Elemento roca = new Elemento(Constantes.ROCA);
		Elemento pozo = new Elemento(Constantes.POZO);
		Elemento pocion = new Elemento(Constantes.POCION);
		Elemento dinero = new Elemento(Constantes.DINERO);
		int fil, col;

		// pinta el tablero
		for (int i = 0; i < Constantes.ANCHO; i++) {
			//sb.append("------------------------------" + "\n");
			for (int j = 0; j < Constantes.ALTO; j++) {
				tablero[i][j] = vacio;
				sb.append(vacio.getSimbolo() + " |");
			}
			//sb.append("\n");
		}

		for (int gema = 0; gema < Constantes.NUM_GEMAS; gema++) {

			do {
				fil = (int) (Math.random() * 10);
				col = (int) (Math.random() * 10);
				
				if(tablero[fil][col].equals(vacio)) {
					tablero[fil][col] = gemas;
				}
				
			} while (tablero[fil][col].equals(gemas));
			//sb.append(gemas.getSimbolo() + " |");

		}

		return sb.toString();
	}
	
	// getter movimientos del jugador
	public int getNumeroMovimientosJugador() {
		int dado;
		dado = (int) (Math.random() * jugadores[turnoJugador].getVelocidad()) + 1;
		return dado;
	}

	// TODO metodo para mover jugador
	public String moverJugador(char direccion) {
		
		boolean hayError;
		boolean finBucle;
		StringBuilder sb = new StringBuilder();

		do {

			try {

				hayError=false;
				finBucle=false;
				int dado1;
				int dado2;
				Elemento vacio = new Elemento(' ');

				if (direccion == 'N') {
					for (int i = 0; i < jugadores.length && !finBucle; i++) {
						if (tablero[jugadores[turnoJugador].getFil() + 1][jugadores[turnoJugador].getCol()].equals
								(Constantes.getJugadoresLetra(i))) {
								
							do {
								int fuerzaEnemigo = jugadores[i].getFuerza();
								dado1 = (int) ((jugadores[turnoJugador].getFuerza() * Math.random()) + 1);
								dado2 = (int) ((fuerzaEnemigo * Math.random()) + 1);
							} while (dado1==dado2);
							finBucle = true;
							if (dado1>dado2) {
								if (jugadores[i].getPociones()>0) {
									jugadores[i].setPociones(jugadores[i].getPociones()-1);
									sb.append(jugadores[turnoJugador].getSimbolo() + " ha ganado la pelea " 
									+ jugadores[i].getSimbolo() + " pierde una pocion");
								}else {
									if(jugadores[i].getDinero()>0) {
										jugadores[turnoJugador].setDinero(jugadores[turnoJugador].getDinero()+jugadores[i].getDinero());									
										sb.append(jugadores[turnoJugador].getSimbolo() + " ha ganado la pelea "
										+ jugadores[i].getSimbolo() + " pierde " + jugadores[i].getDinero() + " dineros");
										jugadores[i].setDinero(0);
									}else {//en caso de tablero mal, se cambia
										sb.append(jugadores[i].getSimbolo() + " sa morio");
										tablero[][j] = vacio;//TODO muerte	
									}
								}
							}
							
						}else {
							if (tablero[jugadores[turnoJugador].getFil() + 1][jugadores[turnoJugador].getCol()].equals(Constantes.ROCA)) {
								
							}
						}
					}
					jugadores[turnoJugador].setFil(jugadores[turnoJugador].getFil() + 1);

				} else {
					if (direccion == 'S') {
						jugadores[turnoJugador].setFil(jugadores[turnoJugador].getFil() - 1);

					} else {
						if (direccion == 'E') {
							jugadores[turnoJugador].setCol(jugadores[turnoJugador].getCol() + 1);

						} else {
							jugadores[turnoJugador].setCol(jugadores[turnoJugador].getCol() - 1);
						}
					}
				}
	
		

			}catch (IndexOutOfBoundsException e) {
				System.out.println("Límite del mapa");
				hayError = true;
			}

			}while (hayError == true);

		return sb.toString();
		}

	public int getJugadorTurno() {
		return turnoJugador;
	}

	// metodo para mostar el ganador
	public char getGanador() {
	
	return jugadores[turnoJugador].getSimbolo();

	}

	public void proximoJugador() {//TODO gana si tiene todos los dineros

		if (turnoJugador == 0 || turnoJugador == numJugadores) {
			turnoJugador = 1;
		} else {
			turnoJugador = turnoJugador + 1;

			if (numJugadores == 1) {
				this.terminado = true;
			} else {
				if (turnoJugador == 0 || turnoJugador == numJugadores) {
					turnoJugador = 1;
				} else {
					turnoJugador = turnoJugador + 1;
				}

			}
		}

	}
}