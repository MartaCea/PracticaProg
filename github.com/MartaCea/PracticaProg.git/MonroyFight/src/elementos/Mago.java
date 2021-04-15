package elementos;

import logicaJuego.Constantes;

public class Mago extends Jugador{
	
	
	public Mago(char simbolo) {
		super(simbolo);
		setFuerza(Constantes.MAGO_FUERZA);
		setMagia(Constantes.MAGO_MAGIA);
		setVelocidad(Constantes.MAGO_VEL);
	}
	
	@Override
	public void setFuerza(int fuerza) {
		super.setFuerza(Constantes.MAGO_FUERZA);;
	}
	
	@Override
	public void setMagia(int magia) {
		super.setMagia(Constantes.MAGO_MAGIA);
	}
	
	@Override
	public void setVelocidad(int velocidad) {
		super.setVelocidad(Constantes.MAGO_VEL);
	}
}
