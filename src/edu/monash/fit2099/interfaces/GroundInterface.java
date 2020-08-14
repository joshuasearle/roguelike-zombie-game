package edu.monash.fit2099.interfaces;

/**
 * This interface provides the ability to add methods to Ground, without modifying code in the engine,
 * or downcasting references in the game.   
 */

public interface GroundInterface {
	
	/**
	 * Finds if the ground can be sowed
	 * @return
	 */
	public boolean sowable();
	
	/**
	 * Finds if the ground can be fertilized
	 * @return
	 */
	public boolean fertilisable();
	
	/**
	 * Finds if the ground can be harvestable
	 * @return
	 */
	public boolean harvestable();

}
