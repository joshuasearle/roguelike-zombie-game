package edu.monash.fit2099.interfaces;

import ammo.AmmoPouch;
import ammo.AmmoType;
import edu.monash.fit2099.engine.Menu;
import zombie.BiteWeapon;

/**
 * This interface provides the ability to add methods to Actor, without modifying code in the engine,
 * or downcasting references in the game.   
 */
public interface ActorInterface {
	/**
	 * gets the number of arms an actor has
	 * @return no. of arms
	 */
	public int getArmCount();
	/**
	 * gets the number of legs an actor has
	 * @return no. of legs
	 */
	public int getLegCount();
	
	/**
	 * returns true if an actor can eat
	 * @return boolean canEat
	 */
	public boolean canEat();
	
	/**
	 * returns the actor's bite
	 * @return
	 */
	public BiteWeapon getBiteWeapon();
	
	/**
	 * finds if an actor can perform farming actions
	 * @return boolean canFarm
	 */
	public boolean canFarm();
	
	/**
	 * finds if an actor can rise from the dead
	 * @return boolean canRiseFromDead
	 */
	public boolean canRiseFromDead();
	
	/**
	 * Gives actor ammo
	 */
	public void giveAmmo(AmmoType ammoType, int amount);
	
	/**
	 * Returns if the actor has quit
	 * @return boolean hasQuit
	 */
	public boolean hasQuit();
	
	/**
	 * Sets the quit status of an actor
	 * @param hasQuit if the actor has quit
	 */
	public void setQuitStatus(boolean hasQuit);
	
	/**
	 * paralyses an actor
	 */
	public void paralyse();
	
	/**
	 * Un-paralyses an actor
	 */
	public void unParalyse();

	/**
	 * finds if actor can vanish
	 * @return boolean canVanish
	 */
	public boolean canVanish();
	
	/**
	 * Finds if an actor is a zombie
	 * @return boolean isZombie
	 */
	public boolean isZombie();
	
	/**
	 * Finds how many turns actor has been infected
	 * @return	integer infectionAge
	 */
	public int getInfectionAge();
	
	/**
	 * Finds number of turns that will cause actor to zombify
	 * @return	integer zombifyAge
	 */
	public int getZombifyAge();
	
	/**
	 * Tick actor's infection age
	 */
	public void tickInfection();
	
	/**
	 * Set actors infection status
	 * @param status	boolean, true = actor is infected
	 */
	public void setInfectionStatus(boolean status);
	
	/**
	 * Infect the target
	 */
	public void infect();
	
	/**
	 * Finds if actor can vaccinate
	 * @return true if can vaccinate
	 */
	public boolean canVaccinate();
	
	/**
	 * Finds if actor is infected (infected means will soon become zombie)
	 * @return	true if actor is infected
	 */
	public boolean isInfected();
	
	/**
	 * Vaccinate the actor
	 */
	public void vaccinate();
	
	/**
	 * Finds an actors vaccinated status
	 * @return boolean vaccinated
	 */
	public boolean getVaccinatedStatus();
	
	/**
	 * Finds if actor can drive.
	*	@return boolean canDrive
	 */
	public boolean canDrive();

	/**
	 * Returns the actor's ammo pouch
	 * @return ammo pouch or null
	 */
	public AmmoPouch getAmmoPouch();
	
	/**
	 * Returns the menu the actor uses, and null if they have no menu
	 * @return menu or null
	 */
	public Menu getMenu();
}
