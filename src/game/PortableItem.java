package game;

import edu.monash.fit2099.engine.Item;

/**
 * Base class for any item that can be picked up and dropped.
 */
public class PortableItem extends Item {

	public PortableItem(String name, char displayChar) {
		super(name, displayChar, true);
	}
	
	/**
	 * Item not edible
	 * @return boolean false
	 */
	@Override
	public boolean eatable() {
		return false;
	}
	
	@Override
	public void disrupt() {
		return;
	}
	
	/**
	 * Item not drivable
	 * @return boolean false
	 */
	@Override
	public boolean drivable() {
		return false;
	}

	/**
	 * Item isn't a cure
	 * @return boolean false
	 */
	@Override
	public boolean isCure() {
		return false;
	}

}
