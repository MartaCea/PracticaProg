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
	private static boolean iniTablero = false;

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

	/**
	 * Creamos el tablero con los objetos (gemas, rocas, etc).
	 * 
	 * @return String del tablero con las cosas
	 */
	public String toString() {
		// StringBuilder porque sino no saldrá todo el tablero
		StringBuilder sb = new StringBuilder();
		boolean encontrado = false;
		Elemento vacio = new Elemento(' ');
		Elemento gemas = new Elemento(Constantes.GEMA);
		Elemento roca = new Elemento(Constantes.ROCA);
		Elemento pozo = new Elemento(Constantes.POZO);
		Elemento pocion = new Elemento(Constantes.POCION);
		Elemento dinero = new Elemento(Constantes.DINERO);
		int fil, col;
		int gema = 0, money = 0, rocas = 0, pozos = 0, pociones = 0;

		if (!iniTablero) {

			for (int i = 0; i < tablero.length; i++) {
				for (int j = 0; j < tablero[i].length; j++) {
					tablero[i][j] = vacio;
				}
			}

			// busca una posicion aleatoria para colocar las gemas
			do {
				fil = (int) (Math.random() * 10);
				col = (int) (Math.random() * 10);
				tablero[fil][col] = gemas;
				gema++;
			} while (gema <= Constantes.NUM_GEMAS);

			// busca una posicion aleatoria para colocar las rocas
			do {
				fil = (int) (Math.random() * 10);
				col = (int) (Math.random() * 10);
				tablero[fil][col] = roca;
				rocas++;
			} while (rocas <= Constantes.NUM_ROCAS || tablero[fil][col].equals(gemas) || tablero[fil][col].equals(pozo)
					|| tablero[fil][col].equals(pocion) || tablero[fil][col].equals(dinero));

			// busca una posicion aleatoria para colocar los pozos
			do {
				fil = (int) (Math.random() * 10);
				col = (int) (Math.random() * 10);
				tablero[fil][col] = pozo;
				pozos++;
			} while (pozos <= Constantes.POZOS || tablero[fil][col].equals(gemas) || tablero[fil][col].equals(roca)
					|| tablero[fil][col].equals(pocion) || tablero[fil][col].equals(dinero));

			// busca una posicion aleatoria para colocar las pociones
			do {
				fil = (int) (Math.random() * 10);
				col = (int) (Math.random() * 10);
				tablero[fil][col] = pocion;
				pociones++;
			} while (pociones <= Constantes.NUM_POCIONES || tablero[fil][col].equals(gemas)
					|| tablero[fil][col].equals(pozo) || tablero[fil][col].equals(roca)
					|| tablero[fil][col].equals(dinero));

			// busca una posicion aleatoria para colocar las monedas
			do {
				fil = (int) (Math.random() * 10);
				col = (int) (Math.random() * 10);
				tablero[fil][col] = dinero;
				money++;
			} while (money <= Constantes.NUM_DINERO || tablero[fil][col].equals(gemas) || tablero[fil][col].equals(pozo)
					|| tablero[fil][col].equals(pocion) || tablero[fil][col].equals(roca));

			// Elemento[] player = new Elemento[numJugadores];
			for (int i = 0; i < numJugadores; i++) {
				// player[i] = jugadores[i];
				do {
					fil = (int) (Math.random() * 10);
					col = (int) (Math.random() * 10);
					tablero[fil][col] = jugadores[i];
					jugadores[i].setFil(fil);
					jugadores[i].setCol(col);
				} while (tablero[fil][col].equals(gemas) || tablero[fil][col].equals(pozo)
						|| tablero[fil][col].equals(pocion) || tablero[fil][col].equals(roca)
						|| tablero[fil][col].equals(dinero));
			}
			int x = 0;
			for (int i = 0; i < tablero.length; i++) {
				sb.append("----------------------------------------" + "\n");
				for (int j = 0; j < tablero[i].length; j++) {
					while (x < numJugadores && !encontrado) {
						if (tablero[i][j].equals(jugadores[x])) {
							sb.append(" " + Constantes.getJugadoresLetra(x) + " |");
							encontrado = true;
						}
						x++;
					}
					if (!encontrado) {
						x = 0;
						if (tablero[i][j].equals(gemas)) {
							sb.append(" " + gemas.getSimbolo() + " |");
						} else {
							if (tablero[i][j].equals(roca)) {
								sb.append(" " + roca.getSimbolo() + " |");
							} else {
								if (tablero[i][j].equals(pozo)) {
									sb.append(" " + pozo.getSimbolo() + " |");
								} else {
									if (tablero[i][j].equals(pocion)) {
										sb.append(" " + pocion.getSimbolo() + " |");
									} else {
										if (tablero[i][j].equals(dinero)) {
											sb.append(" " + dinero.getSimbolo() + " |");
										} else {
											sb.append(" " + vacio.getSimbolo() + " |");
										}
									}
								}

							}

						}

					} else {
						encontrado = false;
					}
				}
				sb.append("\n");
			}

		}else {
			iniTablero = true;
		}
		
		return sb.toString();
	}

	// getter movimientos del jugador
	public int getNumeroMovimientosJugador() {
		int dado;
		dado = (int) (Math.random() * jugadores[turnoJugador].getVelocidad()) + 1;
		return dado;
	}

	/**
	 * En este método movemos el jugador hacia la dirección que meten por el
	 * parámetro
	 * 
	 * @param direccion
	 * @return
	 * @throws JuegoException
	 */
	public String moverJugador(char direccion) throws JuegoException {

		boolean hayError = false;
		boolean finBucle;
		StringBuilder sb = new StringBuilder();
		char caracterCasillaDeMovimiento;

		do {
			try {
				hayError = false;
				finBucle = false;
				Elemento vacio = new Elemento(' ');
				Elemento caracterJugador = new Elemento(jugadores[turnoJugador].getSimbolo());
				char caracterEnemigo;
				int fuerzaEnemigo = 0, pocionesEnemigo = 0, dineroEnemigo = 0, i, tuDado, dadoEnemigo;
				boolean salida;
				String mensaje;

				switch (direccion) {
				case 'N':
					caracterCasillaDeMovimiento = tablero[jugadores[turnoJugador].getFil() + 1][jugadores[turnoJugador]
							.getCol()].getSimbolo();// cambiarfilcol

					switch (caracterCasillaDeMovimiento) {
					case 'A':
						mensaje = combate('A', 'N');

						sb.append(mensaje);
						break;

					case 'B':

						mensaje = combate('B', 'N');

						sb.append(mensaje);
						break;

					case 'C':
						mensaje = combate('C', 'N');

						sb.append(mensaje);
						break;

					case 'E':
						mensaje = combate('E', 'N');

						sb.append(mensaje);
						break;

					case 'F':
						mensaje = combate('F', 'N');

						sb.append(mensaje);
						break;

					case 'G':// gema

						tablero[jugadores[turnoJugador].getFil()][jugadores[turnoJugador].getCol()] = vacio;

						tablero[jugadores[turnoJugador].getFil() + 1][jugadores[turnoJugador]
								.getCol()] = caracterJugador;// cambiar filcol

						jugadores[turnoJugador].setFil(jugadores[turnoJugador].getFil() + 1);// cambiar filcol

						jugadores[turnoJugador].setGemas(jugadores[turnoJugador].getGemas() + 1);

						sb.append("Has cogido una gema" + "\n");// cambiar obj

						break;

					case 'D':// dinero

						tablero[jugadores[turnoJugador].getFil()][jugadores[turnoJugador].getCol()] = vacio;// cambiar
																											// filcol

						tablero[jugadores[turnoJugador].getFil() + 1][jugadores[turnoJugador]
								.getCol()] = caracterJugador;// cambiar filcol

						jugadores[turnoJugador].setFil(jugadores[turnoJugador].getFil() + 1);// cambiar filcol

						jugadores[turnoJugador].setDinero(jugadores[turnoJugador].getDinero() + 1);// cambiar obj

						sb.append("Has cogido un dinero" + "\n");// cambiar obj

						break;

					case 'P':// pocion

						tablero[jugadores[turnoJugador].getFil()][jugadores[turnoJugador].getCol()] = vacio;// cambiar
																											// filcol

						tablero[jugadores[turnoJugador].getFil() + 1][jugadores[turnoJugador]
								.getCol()] = caracterJugador;// cambiar filcol

						jugadores[turnoJugador].setFil(jugadores[turnoJugador].getFil() + 1);// cambiar filcol

						jugadores[turnoJugador].setPociones(jugadores[turnoJugador].getPociones() + 1);// cambiar obj

						sb.append("Has cogido una poti" + "\n");// cambiar obj

						break;

					case 'Z':// pozo
						int dadoPozo;
						do {
							tuDado = (int) ((jugadores[turnoJugador].getFuerza() * Math.random()) + 1);
							dadoPozo = (int) ((Math.random() * 5) + 1);
						} while (tuDado == dadoPozo);

						if (tuDado > dadoPozo) {
							tablero[jugadores[turnoJugador].getFil()][jugadores[turnoJugador].getCol()] = vacio;// cambiar
																												// filcol

							tablero[jugadores[turnoJugador].getFil() + 1][jugadores[turnoJugador]
									.getCol()] = caracterJugador;// cambiar filcol

							jugadores[turnoJugador].setFil(jugadores[turnoJugador].getFil() + 1);// cambiar filcol

							jugadores[turnoJugador].setGemas(jugadores[turnoJugador].getGemas() - 1);

							sb.append("Has sellado el pozo" + "\n");// cambiar obj

						} else {
							sb.append("No has sido capaz de superar la magia del pozo");
						}

						break;

					case 'R':// roca
						do {
							if (jugadores[turnoJugador].getGemas() < 1)
								throw new JuegoException("No tienes gemas");

							tablero[jugadores[turnoJugador].getFil()][jugadores[turnoJugador].getCol()] = vacio;// cambiar
																												// filcol

							tablero[jugadores[turnoJugador].getFil() + 1][jugadores[turnoJugador]
									.getCol()] = caracterJugador;// cambiar filcol

							jugadores[turnoJugador].setFil(jugadores[turnoJugador].getFil() + 1);// cambiar filcol

							jugadores[turnoJugador].setGemas(jugadores[turnoJugador].getGemas() - 1);

							sb.append("Has destruido una roca" + "\n");

						} while (hayError);

						break;

					default:
						tablero[jugadores[turnoJugador].getFil()][jugadores[turnoJugador].getCol()] = vacio;// cambiar
																											// filcol
						tablero[jugadores[turnoJugador].getFil() + 1][jugadores[turnoJugador]
								.getCol()] = caracterJugador;// cambiar filcol

						break;
					}

				case 'S':
					caracterCasillaDeMovimiento = tablero[jugadores[turnoJugador].getFil() - 1][jugadores[turnoJugador]
							.getCol()].getSimbolo();// cambiarfilcol

					switch (caracterCasillaDeMovimiento) {
					case 'A':
						mensaje = combate('A', 'S');

						sb.append(mensaje);
						break;

					case 'B':

						mensaje = combate('B', 'S');

						sb.append(mensaje);
						break;

					case 'C':
						mensaje = combate('C', 'S');

						sb.append(mensaje);
						break;

					case 'E':
						mensaje = combate('E', 'S');

						sb.append(mensaje);
						break;

					case 'F':
						mensaje = combate('F', 'S');

						sb.append(mensaje);
						break;

					case 'G':// gema

						tablero[jugadores[turnoJugador].getFil()][jugadores[turnoJugador].getCol()] = vacio;// cambiar
																											// filcol

						tablero[jugadores[turnoJugador].getFil() - 1][jugadores[turnoJugador]
								.getCol()] = caracterJugador;// cambiar filcol

						jugadores[turnoJugador].setFil(jugadores[turnoJugador].getFil() - 1);// cambiar filcol

						jugadores[turnoJugador].setGemas(jugadores[turnoJugador].getGemas() + 1);

						sb.append("Has cogido una gema" + "\n");// cambiar obj

						break;

					case 'D':// dinero

						tablero[jugadores[turnoJugador].getFil()][jugadores[turnoJugador].getCol()] = vacio;// cambiar
																											// filcol

						tablero[jugadores[turnoJugador].getFil() - 1][jugadores[turnoJugador]
								.getCol()] = caracterJugador;// cambiar filcol

						jugadores[turnoJugador].setFil(jugadores[turnoJugador].getFil() - 1);// cambiar filcol

						jugadores[turnoJugador].setDinero(jugadores[turnoJugador].getDinero() + 1);// cambiar obj

						sb.append("Has cogido un dinero" + "\n");// cambiar obj

						break;

					case 'P':// pocion

						tablero[jugadores[turnoJugador].getFil()][jugadores[turnoJugador].getCol()] = vacio;// cambiar
																											// filcol

						tablero[jugadores[turnoJugador].getFil() - 1][jugadores[turnoJugador]
								.getCol()] = caracterJugador;// cambiar filcol

						jugadores[turnoJugador].setFil(jugadores[turnoJugador].getFil() - 1);// cambiar filcol

						jugadores[turnoJugador].setPociones(jugadores[turnoJugador].getPociones() + 1);// cambiar obj

						sb.append("Has cogido una poti" + "\n");// cambiar obj

						break;

					case 'Z':// pozo
						int dadoPozo;
						do {
							tuDado = (int) ((jugadores[turnoJugador].getFuerza() * Math.random()) + 1);
							dadoPozo = (int) ((Math.random() * 5) + 1);
						} while (tuDado == dadoPozo);

						if (tuDado > dadoPozo) {
							tablero[jugadores[turnoJugador].getFil()][jugadores[turnoJugador].getCol()] = vacio;// cambiar
																												// filcol

							tablero[jugadores[turnoJugador].getFil() - 1][jugadores[turnoJugador]
									.getCol()] = caracterJugador;// cambiar filcol

							jugadores[turnoJugador].setFil(jugadores[turnoJugador].getFil() - 1);// cambiar filcol

							jugadores[turnoJugador].setGemas(jugadores[turnoJugador].getGemas() - 1);

							sb.append("Has sellado el pozo " + "\n");

						} else {
							sb.append("No has sido capaz de superar la magia del pozo");
						}

						break;

					case 'R':// roca
						do {
							if (jugadores[turnoJugador].getGemas() < 1)
								throw new JuegoException("No tienes gemas");

							tablero[jugadores[turnoJugador].getFil()][jugadores[turnoJugador].getCol()] = vacio;// cambiar
																												// filcol

							tablero[jugadores[turnoJugador].getFil() + 1][jugadores[turnoJugador]
									.getCol()] = caracterJugador;// cambiar filcol

							jugadores[turnoJugador].setFil(jugadores[turnoJugador].getFil() - 1);// cambiar filcol

							jugadores[turnoJugador].setGemas(jugadores[turnoJugador].getGemas() - 1);

							sb.append("Has destruido una roca" + "\n");

						} while (hayError);

						break;

					default:
						tablero[jugadores[turnoJugador].getFil()][jugadores[turnoJugador].getCol()] = vacio;// cambiar
																											// filcol
						tablero[jugadores[turnoJugador].getFil() - 1][jugadores[turnoJugador]
								.getCol()] = caracterJugador;// cambiar filcol

						break;
					}
				case 'E':
					caracterCasillaDeMovimiento = tablero[jugadores[turnoJugador].getFil()][jugadores[turnoJugador]
							.getCol() + 1].getSimbolo();// cambiarfilcol

					switch (caracterCasillaDeMovimiento) {
					case 'A':
						mensaje = combate('A', 'E');

						sb.append(mensaje);
						break;

					case 'B':

						mensaje = combate('B', 'E');

						sb.append(mensaje);
						break;

					case 'C':
						mensaje = combate('C', 'E');

						sb.append(mensaje);
						break;

					case 'E':
						mensaje = combate('E', 'E');

						sb.append(mensaje);
						break;

					case 'F':
						mensaje = combate('F', 'E');

						sb.append(mensaje);
						break;

					case 'G':// gema

						tablero[jugadores[turnoJugador].getFil()][jugadores[turnoJugador].getCol()] = vacio;// cambiar
																											// filcol

						tablero[jugadores[turnoJugador].getFil()][jugadores[turnoJugador].getCol()
								+ 1] = caracterJugador;// cambiar filcol

						jugadores[turnoJugador].setCol(jugadores[turnoJugador].getCol() + 1);// cambiar filcol

						jugadores[turnoJugador].setGemas(jugadores[turnoJugador].getGemas() + 1);

						sb.append("Has cogido una gema" + "\n");// cambiar obj

						break;

					case 'D':// dinero

						tablero[jugadores[turnoJugador].getFil()][jugadores[turnoJugador].getCol()] = vacio;// cambiar
																											// filcol

						tablero[jugadores[turnoJugador].getFil()][jugadores[turnoJugador].getCol()
								+ 1] = caracterJugador;// cambiar filcol

						jugadores[turnoJugador].setCol(jugadores[turnoJugador].getCol() + 1);// cambiar filcol

						jugadores[turnoJugador].setDinero(jugadores[turnoJugador].getDinero() + 1);// cambiar obj

						sb.append("Has cogido un dinero" + "\n");// cambiar obj

						break;

					case 'P':// pocion

						tablero[jugadores[turnoJugador].getFil()][jugadores[turnoJugador].getCol()] = vacio;// cambiar
																											// filcol

						tablero[jugadores[turnoJugador].getFil()][jugadores[turnoJugador].getCol()
								+ 1] = caracterJugador;// cambiar filcol

						jugadores[turnoJugador].setCol(jugadores[turnoJugador].getCol() + 1);// cambiar filcol

						jugadores[turnoJugador].setPociones(jugadores[turnoJugador].getPociones() + 1);// cambiar obj

						sb.append("Has cogido una poti" + "\n");// cambiar obj

						break;

					case 'Z':// pozo
						int dadoPozo;
						do {
							tuDado = (int) ((jugadores[turnoJugador].getFuerza() * Math.random()) + 1);
							dadoPozo = (int) ((Math.random() * 5) + 1);
						} while (tuDado == dadoPozo);

						if (tuDado > dadoPozo) {
							tablero[jugadores[turnoJugador].getFil()][jugadores[turnoJugador].getCol()] = vacio;// cambiar
																												// filcol

							tablero[jugadores[turnoJugador].getFil()][jugadores[turnoJugador].getCol()
									+ 1] = caracterJugador;// cambiar filcol

							jugadores[turnoJugador].setCol(jugadores[turnoJugador].getCol() + 1);

							jugadores[turnoJugador].setGemas(jugadores[turnoJugador].getGemas() - 1);

							sb.append("Has sellado el pozo" + "\n");

						} else {
							sb.append("No has sido capaz de superar la magia del pozo");
						}

						break;

					case 'R':// roca
						do {
							if (jugadores[turnoJugador].getGemas() < 1)
								throw new JuegoException("No tienes gemas");

							tablero[jugadores[turnoJugador].getFil()][jugadores[turnoJugador].getCol()] = vacio;// cambiar
																												// filcol

							tablero[jugadores[turnoJugador].getFil()][jugadores[turnoJugador].getCol()
									+ 1] = caracterJugador;// cambiar filcol

							jugadores[turnoJugador].setCol(jugadores[turnoJugador].getCol() + 1);// cambiar filcol

							jugadores[turnoJugador].setGemas(jugadores[turnoJugador].getGemas() - 1);

							sb.append("Has destruido una roca" + "\n");

						} while (hayError);

						break;

					default:
						tablero[jugadores[turnoJugador].getFil()][jugadores[turnoJugador].getCol()] = vacio;// cambiar
																											// filcol
						tablero[jugadores[turnoJugador].getFil()][jugadores[turnoJugador].getCol()
								+ 1] = caracterJugador;// cambiar filcol
						break;
					}
				case 'O':
					caracterCasillaDeMovimiento = tablero[jugadores[turnoJugador].getFil()][jugadores[turnoJugador]
							.getCol() + 1].getSimbolo();// cambiarfilcol

					switch (caracterCasillaDeMovimiento) {
					case 'A':
						mensaje = combate('A', 'O');

						sb.append(mensaje);
						break;

					case 'B':

						mensaje = combate('B', 'O');

						sb.append(mensaje);
						break;

					case 'C':
						mensaje = combate('C', 'O');

						sb.append(mensaje);
						break;

					case 'E':
						mensaje = combate('E', 'O');

						sb.append(mensaje);
						break;

					case 'F':
						mensaje = combate('F', 'O');

						sb.append(mensaje);
						break;

					case 'G':// gema

						tablero[jugadores[turnoJugador].getFil()][jugadores[turnoJugador].getCol()] = vacio;// cambiar
																											// filcol

						tablero[jugadores[turnoJugador].getFil()][jugadores[turnoJugador].getCol()
								- 1] = caracterJugador;// cambiar filcol

						jugadores[turnoJugador].setCol(jugadores[turnoJugador].getCol() - 1);// cambiar filcol

						jugadores[turnoJugador].setGemas(jugadores[turnoJugador].getGemas() + 1);

						sb.append("Has cogido una gema" + "\n");// cambiar obj

						break;

					case 'D':// dinero

						tablero[jugadores[turnoJugador].getFil()][jugadores[turnoJugador].getCol()] = vacio;// cambiar
																											// filcol

						tablero[jugadores[turnoJugador].getFil()][jugadores[turnoJugador].getCol()
								- 1] = caracterJugador;// cambiar filcol

						jugadores[turnoJugador].setCol(jugadores[turnoJugador].getCol() - 1);// cambiar filcol

						jugadores[turnoJugador].setDinero(jugadores[turnoJugador].getDinero() + 1);// cambiar obj

						sb.append("Has cogido un dinero" + "\n");// cambiar obj

						break;

					case 'P':// pocion

						tablero[jugadores[turnoJugador].getFil()][jugadores[turnoJugador].getCol()] = vacio;// cambiar
																											// filcol

						tablero[jugadores[turnoJugador].getFil()][jugadores[turnoJugador].getCol()
								- 1] = caracterJugador;// cambiar filcol

						jugadores[turnoJugador].setCol(jugadores[turnoJugador].getCol() - 1);// cambiar filcol

						jugadores[turnoJugador].setPociones(jugadores[turnoJugador].getPociones() + 1);// cambiar obj

						sb.append("Has cogido una poti" + "\n");// cambiar obj

						break;

					case 'Z':// pozo
						int dadoPozo;
						do {
							tuDado = (int) ((jugadores[turnoJugador].getFuerza() * Math.random()) + 1);
							dadoPozo = (int) ((Math.random() * 5) + 1);
						} while (tuDado == dadoPozo);

						if (tuDado > dadoPozo) {
							tablero[jugadores[turnoJugador].getFil()][jugadores[turnoJugador].getCol()] = vacio;// cambiar
																												// filcol

							tablero[jugadores[turnoJugador].getFil()][jugadores[turnoJugador].getCol()
									+ 1] = caracterJugador;// cambiar filcol

							jugadores[turnoJugador].setCol(jugadores[turnoJugador].getCol() - 1);

							jugadores[turnoJugador].setGemas(jugadores[turnoJugador].getGemas() - 1);

							sb.append("Has sellado el pozo" + "\n");

						} else {
							sb.append("No has sido capaz de superar la magia del pozo");
						}

						break;

					case 'R':// roca
						do {
							if (jugadores[turnoJugador].getGemas() < 1)
								throw new JuegoException("No tienes gemas");

							tablero[jugadores[turnoJugador].getFil()][jugadores[turnoJugador].getCol()] = vacio;// cambiar
																												// filcol

							tablero[jugadores[turnoJugador].getFil()][jugadores[turnoJugador].getCol()
									- 1] = caracterJugador;// cambiar filcol

							jugadores[turnoJugador].setCol(jugadores[turnoJugador].getCol() - 1);// cambiar filcol

							jugadores[turnoJugador].setGemas(jugadores[turnoJugador].getGemas() - 1);

							sb.append("Has destruido una roca" + "\n");

						} while (hayError);

						break;

					default:
						tablero[jugadores[turnoJugador].getFil()][jugadores[turnoJugador].getCol()] = vacio;// cambiar
																											// filcol
						tablero[jugadores[turnoJugador].getFil()][jugadores[turnoJugador].getCol()
								- 1] = caracterJugador;// cambiar filcol
						break;
					}
				}
			} catch (IndexOutOfBoundsException e) {
				hayError = true;

			} catch (JuegoException e) {
				hayError = true;
			}

		} while (hayError);

		return sb.toString();
	}

	public int getJugadorTurno() {
		return turnoJugador;
	}

	/**
	 * Devuelve el símbolo del ganador de la partida
	 * 
	 * @return símboloJugador
	 */
	public char getGanador() {

		return jugadores[turnoJugador].getSimbolo();

	}

	/**
	 * A qué jugador le toca en ese instante En caso de que haya un ganador, termina
	 * la partida
	 */
	public void proximoJugador() {// TODO gana si tiene todos los dineros

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

	/**
	 * Ocupa todas las posibilidades en el combate
	 * 
	 * @param caracterEnemigoAComparar
	 * @param direccion
	 * @return
	 */
	public String combate(char caracterEnemigoAComparar, char direccion) {
		int i = -1;
		boolean salida;
		int fuerzaEnemigo = 0;
		int pocionesEnemigo = 0;
		int dineroEnemigo = 0;
		Elemento vacio = new Elemento(' ');
		StringBuilder sb = new StringBuilder();
		do {
			i++;
			salida = false;
			char caracterEnemigo = jugadores[i].getSimbolo();
			if (caracterEnemigo == caracterEnemigoAComparar) {// cambiar a
				fuerzaEnemigo = jugadores[i].getFuerza();
				pocionesEnemigo = jugadores[i].getPociones();
				dineroEnemigo = jugadores[i].getDinero();
				salida = true;
			}
		} while (i < jugadores.length && !salida);

		int tuDado;
		int dadoEnemigo;
		do {
			tuDado = (int) ((jugadores[turnoJugador].getFuerza() * Math.random()) + 1);
			dadoEnemigo = (int) ((fuerzaEnemigo * Math.random()) + 1);
		} while (tuDado == dadoEnemigo);

		sb.append(jugadores[turnoJugador].getSimbolo() + " ha sacado " + tuDado + ", " + caracterEnemigoAComparar
				+ " ha sacado " + dadoEnemigo + "\n");// cambiar a

		if (tuDado > dadoEnemigo) {
			if (pocionesEnemigo > 0) {
				jugadores[i].setPociones(pocionesEnemigo - 1);
				sb.append(jugadores[turnoJugador].getSimbolo() + " ha ganado la pelea " + caracterEnemigoAComparar
						+ " pierde una pocion \n");// cambiar a
			} else {
				if (dineroEnemigo > 0) {
					jugadores[turnoJugador].setDinero(jugadores[turnoJugador].getDinero() + dineroEnemigo);
					sb.append(jugadores[turnoJugador].getSimbolo() + " ha ganado la pelea " + caracterEnemigoAComparar
							+ " pierde " + dineroEnemigo + " dineros \n");// cambiar a
					jugadores[i].setDinero(0);
				} else {
					switch (direccion) {
					case 'N': {
						sb.append(caracterEnemigoAComparar + " sa morio \n");// cambiar a
						tablero[jugadores[turnoJugador].getFil() + 1][jugadores[turnoJugador].getCol()] = vacio;// cambiarfilcol
						numJugadores--;
						break;
					}
					case 'S': {
						sb.append(caracterEnemigoAComparar + " sa morio \n");// cambiar a
						tablero[jugadores[turnoJugador].getFil() - 1][jugadores[turnoJugador].getCol()] = vacio;// cambiarfilcol
						numJugadores--;
						break;
					}
					case 'E': {
						sb.append(caracterEnemigoAComparar + " sa morio \n");// cambiar a
						tablero[jugadores[turnoJugador].getFil()][jugadores[turnoJugador].getCol() + 1] = vacio;// cambiarfilcol
						numJugadores--;
						break;
					}
					case 'O': {
						sb.append(caracterEnemigoAComparar + " sa morio \n");// cambiar a
						tablero[jugadores[turnoJugador].getFil()][jugadores[turnoJugador].getCol() - 1] = vacio;// cambiarfilcol
						numJugadores--;
						break;
					}

					}

				}
			}
		} else {
			if (jugadores[turnoJugador].getPociones() > 0) {
				jugadores[turnoJugador].setPociones(jugadores[i].getPociones() - 1);
				sb.append(caracterEnemigoAComparar + " ha ganado la pelea " + jugadores[turnoJugador].getSimbolo()
						+ " pierde una pocion \n"); // cambiar a
			} else {
				if (jugadores[turnoJugador].getDinero() > 0) {
					jugadores[i].setDinero(jugadores[i].getDinero() + jugadores[turnoJugador].getDinero());
					sb.append(caracterEnemigoAComparar + " ha ganado la pelea " + jugadores[turnoJugador].getSimbolo()
							+ " pierde " + jugadores[turnoJugador].getDinero() + " dineros \n");
					jugadores[turnoJugador].setDinero(0);
				} else {
					sb.append(jugadores[turnoJugador].getSimbolo() + " sa morio \n");
					tablero[jugadores[turnoJugador].getFil()][jugadores[turnoJugador].getCol()] = vacio;// cambiarfilcol
					numJugadores--;
				}
			}
		}
		return sb.toString();
	}
}