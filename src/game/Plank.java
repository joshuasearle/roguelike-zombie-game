package game;

import edu.monash.fit2099.engine.WeaponItem;

/**
 * A primitive weapon.
 * 
 * @author ram
 *
 */
public class Plank extends WeaponItem {
	

	public Plank() {
		super("plank", ')', 20, "whacks");
	}
	
	@Override
	public void disrupt() {
		return;
	}

	/**
	 * Item isn't edible
	 * @return boolean false
	 */
	@Override
	public boolean eatable() {
		return false;
	}
	
	/**
	 * Item isn't drivable.
	 * @return boolean false
	 */
	@Override
	public boolean drivable() {
		return false;
	}
	
	/**
	 * Item cannot cure. 
	 * @return boolean false
	 */
	@Override
	public boolean isCure() {
		return false;
	}
}
