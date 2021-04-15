package elementos;

import logicaJuego.Constantes;

public class Guerrero extends Jugador{
	
	public Guerrero(char simbolo) {
		super(simbolo);
		setFuerza(Constantes.GUERRERO_FUERZA);
		setMagia(Constantes.GUERRERO_MAGIA);
		setVelocidad(Constantes.GUERRERO_VEL);
	}
	
	@Override
	public void setFuerza(int fuerza) {
		super.setFuerza(Constantes.GUERRERO_FUERZA);;
	}
	
	@Override
	public void setMagia(int magia) {
		super.setMagia(Constantes.GUERRERO_MAGIA);
	}
	
	@Override
	public void setVelocidad(int velocidad) {
		super.setVelocidad(Constantes.GUERRERO_VEL);
	}
}
