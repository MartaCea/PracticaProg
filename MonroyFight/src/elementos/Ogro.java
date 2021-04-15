package elementos;

import logicaJuego.Constantes;

public class Ogro extends Jugador{

	
	public Ogro(char simbolo) {
		super(simbolo);
		setFuerza(Constantes.OGRO_FUERZA);
		setMagia(Constantes.OGRO_MAGIA);
		setVelocidad(Constantes.OGRO_VEL);
	}
	
	@Override
	public void setFuerza(int fuerza) {
		super.setFuerza(Constantes.OGRO_FUERZA);;
	}
	
	@Override
	public void setMagia(int magia) {
		super.setMagia(Constantes.OGRO_MAGIA);
	}
	
	@Override
	public void setVelocidad(int velocidad) {
		super.setVelocidad(Constantes.OGRO_VEL);
	}
}
