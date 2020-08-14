package edu.monash.fit2099.interfaces;

/**
 * This interface provides the ability to add methods to Ground, without modifying code in the engine,
 * or downcasting references in the game.   
 */
public interface ItemInterface {
	/**
	 * Finds if an item is edible
	 * @return boolean edible
	 */
	public boolean eatable();
	

	/**
	 * Finds if item is drivable
	 * @return	true if item can drive 
	 */
	public boolean drivable();
	
	/**
	 * Finds if item can cure infection
	 * @return	true if item can cure
	 */
	public boolean isCure();
	
	
	/**
	 * Disrupts the items state, and gets called if the actor carrying it takes damage
	 */
	public void disrupt();
}
