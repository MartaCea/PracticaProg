package elementos;

import logicaJuego.Constantes;

public class Elfo extends Jugador{

	public Elfo(char simbolo) {
		super(simbolo);
		setFuerza(Constantes.ELFO_FUERZA);
		setMagia(Constantes.ELFO_MAGIA);
		setVelocidad(Constantes.ELFO_VEL);
	}
	
	@Override
	public void setFuerza(int fuerza) {
		super.setFuerza(Constantes.ELFO_FUERZA);;
	}
	
	@Override
	public void setMagia(int magia) {
		super.setMagia(Constantes.ELFO_MAGIA);
	}
	
	@Override
	public void setVelocidad(int velocidad) {
		super.setVelocidad(Constantes.ELFO_VEL);
	}
	
}
