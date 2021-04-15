package elementos;

public class Jugador extends Elemento{
	
	private int fuerza;
	private int magia;
	private int velocidad;
	private int fil; // fila
	private int col; // columna
	private int dinero;
	private int pociones;
	private int gemas;

	
	public Jugador(char simbolo) {
		super(simbolo);
		this.setFuerza(fuerza);
		this.setMagia(magia);
		this.setVelocidad(velocidad);
		this.fil = 0;
		this.col = 0;
		this.dinero = 0;
		this.pociones = 0;
		this.gemas = 0;
	}

	public int getFuerza() {
		return fuerza;
	}

	public void setFuerza(int fuerza) {
		this.fuerza = fuerza;
	}

	public int getMagia() {
		return magia;
	}

	public void setMagia(int magia) {
		this.magia = magia;
	}

	public int getVelocidad() {
		return velocidad;
	}

	public void setVelocidad(int velocidad) {
		this.velocidad = velocidad;
	}

	public int getFil() {
		return fil;
	}

	public void setFil(int fil) {
		this.fil = fil;
	}

	public int getCol() {
		return col;
	}

	public void setCol(int col) {
		this.col = col;
	}

	public int getDinero() {
		return dinero;
	}

	public void setDinero(int dinero) {
		this.dinero = dinero;
	}

	public int getPociones() {
		return pociones;
	}

	public void setPociones(int pociones) {
		this.pociones = pociones;
	}

	public int getGemas() {
		return gemas;
	}

	public void setGemas(int gemas) {
		this.gemas = gemas;
	}
	
	public String toString() {
		return " " + this.simbolo;
	}
}
