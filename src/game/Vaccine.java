package game;

/**
 * Vaccine item
 * @author aahdu
 */
public class Vaccine extends PortableItem {

	/**
	 * Constructor.
	 */
	public Vaccine() {
		super("vaccine", 'v');
		this.allowableActions.add(new VaccinateAction());
	}
	
	/**
	 * Vaccine is a cure.
	 * Return boolean true.
	 */
	@Override
	public boolean isCure() {
		return true;
	}
}
