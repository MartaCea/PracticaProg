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
			throw new JuegoException("No podemos juegar porque tenemos que ser como m?nimo 2 jugadores y m?ximo 6");
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
	 * jugador ser? diferente (ELFO, OGRO, etc).
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
			throw new JuegoException("Ya est?n todo los jugadores para poder jugar");
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

	// Esta hecho el dibujo del tablero vac?o sin las gemas, rocas, etc. Para ver
	// como se ver?a
	public String toString() {
		// StringBuilder porque sino no saldr? todo el tablero
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
			sb.append("------------------------------" + "\n");
			for (int j = 0; j < Constantes.ALTO; j++) {
				tablero[i][j] = vacio;
				sb.append(vacio.getSimbolo() + " |");
			}
			sb.append("\n");
		}

		for (int gema = 0; gema < Constantes.NUM_GEMAS; gema++) {

			do {
				fil = (int) (Math.random() * 10);
				col = (int) (Math.random() * 10);
			} while (tablero[fil][col].equals(gemas));
			tablero[fil][col] = gemas;
			sb.append(gemas.getSimbolo() + " |");

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
		String mensaje = null;
		boolean hayError;

		do {

			try {

				hayError = false;

				if (direccion == 'N') {
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

			} catch (IndexOutOfBoundsException e) {
				System.out.println("L?mite del mapa");
				hayError = true;
			}

		} while (hayError == true);

		return mensaje;

	}

	public int getJugadorTurno() {
		return turnoJugador;
	}

	// metodo para mostar el ganador
	public String getGanador() {

		String simboloJugador;
		simboloJugador = jugadores[turnoJugador].toString();
		return simboloJugador;

	}

	public void proximoJugador() {

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