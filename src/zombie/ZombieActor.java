package zombie;

import AttackTypeActions.AttackAction;
import ammo.AmmoPouch;
import ammo.AmmoType;
import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Display;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Menu;
import game.ParalysedAction;
import game.VanishAction;

/**
 * Base class for Actors in the Zombie World
 * @author ram
 *
 */
public abstract class ZombieActor extends Actor {
	
	/**
	 * Stores if the actor has quit
	 */
	private boolean hasQuit = false;
	
	/**
	 * The pouch the actor takes ammo from
	 */
	private AmmoPouch ammoPouch = new AmmoPouch();
	
	/**
	 * Stores if the actor is paralysed
	 */
	private boolean paralysed;

	public ZombieActor(String name, char displayChar, int hitPoints, ZombieCapability team) {
		super(name, displayChar, hitPoints);
		
		addCapability(team);
	}
	
	/**
	 * Actors by default have 2 arms
	 */
	@Override
	public int getArmCount() {
		return 2;
	}

	/**
	 * Actors by default have 2 legs
	 */
	@Override
	public int getLegCount() {
		return 2;
	}

	/**
	 * Actor cannot eat. 
	 * @return false
	 */
	@Override
	public boolean canEat() {
		return false;
	}

	/**
	 * A normal actor has no bite weapon
	 */
	@Override
	public BiteWeapon getBiteWeapon() {
		return null;
	}

	/**
	 * Actor can farm.
	 * @return boolean false
	 */
	@Override
	public boolean canFarm() {
		return false;
	}

	/**
	 * Actor cannot rise from the dead.
	 * @return boolean false
	 */
	@Override
	public boolean canRiseFromDead() {
		return false;
	}

	@Override
	public Actions getAllowableActions(Actor otherActor, String direction, GameMap map) {
		Actions list = super.getAllowableActions(otherActor, direction, map);
		if (otherActor.hasCapability(ZombieCapability.UNDEAD) != this.hasCapability(ZombieCapability.UNDEAD))
			list.add(new AttackAction(this));
		return list;
	}
	
	/**
	 * Has actor quit
	 * @return hasQuit
	 */
	@Override
	public boolean hasQuit() {
		return hasQuit;
	}

	/**
	 * Sets the quit status of an actor
	 */
	@Override
	public void setQuitStatus(boolean hasQuit) {
		this.hasQuit = hasQuit;
	}
	
	/**
	 * Paraylses an actor
	 */
	@Override
	public void paralyse() {
		paralysed = true;
	}
	
	/**
	 * Play actor's turn
	 * @return Action
	 */
	@Override
	public Action playTurn(Actions actions, Action lastAction, GameMap map, Display display) {
		
		// if zombie is infected
		if (isInfected()) {
			tickInfection();
				// if actor has reached its zombify age, they turn into a zombie
				if (this.getInfectionAge() == this.getZombifyAge()) {
					return new VanishAction();
				}
			}
		if (paralysed) {
			return new ParalysedAction();
		}	
		return getAction(actions, lastAction, map, display);
	}
	
	/**
	 * Actor cannot vanish.
	 * @return boolean false
	 */
	@Override
	public boolean canVanish() {
		return false;
	}
	
	/**
	 * Is an actor a zombie.
	 * @return boolean false
	 */
	@Override
	public boolean isZombie() {
		return false;
	}
	
	/**
	 * Gets the action of an actor on a turn
	 * @param actions list of possible actions for the actor
	 * @param lastAction lsat action the actor performed
	 * @param map map the actor is on
	 * @param display display the actor is using
	 * @return action that the actor will perform
	 */
	protected abstract Action getAction(Actions actions, Action lastAction, GameMap map, Display display);
		
	/**
	 * Retrieve actor's infection age.
	 * Actor intialised as not infected.
	 * @return 0
	 */
	@Override
	public int getInfectionAge() {
		return 0;
	}

	/**
	 * Retrieve actor's zombify age.
	 * Actor intialised as not infected.
	 * @return 0
	 */
	@Override
	public int getZombifyAge() {
		return 0;
	}

	/**
	 * Tick actor's infection age.
	 */
	@Override
	public void tickInfection() {
	}

	/**
	 * Set actor's infection status.
	 */
	@Override
	public void setInfectionStatus(boolean status) {
	}

	/**
	 * Retrieves if actor is infected.
	 * Actors initilaised as not infected.
	 * @return boolean false
	 */
	@Override
	public boolean isInfected() {
		return false;
	}

	/**
	 * Actor cannot infect/
	 */
	@Override
	public void infect() {
	}
	
	/**
	 * Actor cannot vaccinate.
	 * @return boolean false
	 */
	@Override
	public boolean canVaccinate() {
		return false;
	}
	
	/**
	 * Actor cannot vaccinate.
	 */
	@Override
	public void vaccinate() {
	}
	
	/**
	 * Retrieve actor's infection status.
	 * Actors are initialised as not infected.
	 * @return boolean false
	 */
	@Override
	public boolean getVaccinatedStatus() {
		return false;
	}
	
	/**
	 * Actor cannot drive.
	 * @return boolean false
	 */
	@Override
	public boolean canDrive() {
		return false;
	}
	
	/**
	 * Un-paralyses an actor
	 */
	@Override
	public void unParalyse() {		
		paralysed = false;
	}

	/**
	 * Returns the menu the actor uses, and null if they have no menu
	 * @return menu or null
	 */
	@Override
	public Menu getMenu() {
		return null;
	}
	
	/**
	 * Gives the actor ammo by putting it into their ammo pouch
	 */
	@Override
	public void giveAmmo(AmmoType ammoType, int amount) {
		ammoPouch.insertAmmo(ammoType, amount);
	}
	
	/**
	 * Returns reference to the actor's ammo pouch
	 */
	@Override
	public AmmoPouch getAmmoPouch() {
		return this.ammoPouch;
	}
}