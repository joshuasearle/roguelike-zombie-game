package game;

import edu.monash.fit2099.engine.Item;
/**
 * Vehicle item allows travel between maps
 * @author aahdu
 */
public class Vehicle extends Item {
	
	/**
	 * Constructor
	 */
	public Vehicle() {
		super("Vehicle", 'V', false);
		this.allowableActions.add(new DriveAction());
	}
	
	/**
	 * Vehicle isn't edible.
	 * @return false.
	 */
	@Override
	public boolean eatable() {
		return false;
	}
	
	/**
	 * Vehicle is drivable.
	 * @return true.
	 */
	@Override
	public boolean drivable() {
		return true;
	}
	
	/**
	 * Vehicle is not a cure.
	 * @return false
	 */
	@Override
	public boolean isCure() {
		return false;
	}
	
	/**
	 * Disrupt
	 */
	@Override
	public void disrupt() {
	}
}
