package elementos;

/**
 * En esta clase almacenamos las gemas, el dinero, etc.
 * @author Marta
 *
 */
public class Cosas extends Elemento{
	
	private TipoCosas cosas;

	public Cosas(char simbolo, TipoCosas cosas) {
		super(simbolo);
		this.cosas = cosas;
	}

	public TipoCosas getCosas() {
		return cosas;
	}

	public void setCosas(TipoCosas cosas) {
		this.cosas = cosas;
	}

	
	
	
	
}
