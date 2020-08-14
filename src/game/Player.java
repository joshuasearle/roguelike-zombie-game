package game;

import ammo.AmmoPouch;
import ammo.AmmoType;
import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Display;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Item;
import edu.monash.fit2099.engine.Menu;

/**
 * Class representing the Player.
 */
public class Player extends Human {

	/**
	 * Has actor been vaccinated. Initialised as false
	 */
	private boolean vaccinated = false;
	
	/**
	 * The menu the player uses
	 */
	private Menu menu = new Menu();

	/**
	 * Constructor.
	 *
	 * @param name        Name to call the player in the UI
	 * @param displayChar Character to represent the player in the UI
	 * @param hitPoints   Player's starting number of hitpoints
	 */
	public Player(String name, char displayChar, int hitPoints) {
		super(name, displayChar, hitPoints);
	}

	/**
	 * 
	 * @return Action
	 */
	@Override
	public Action getAction(Actions actions, Action lastAction, GameMap map, Display display) {
		// Handle multi-turn Actions
		actions.add(new QuitGameAction());

		if (lastAction.getNextAction() != null) {
			return lastAction.getNextAction();
		}
		return menu.showMenu(this, actions, display);
	}

	/**
	 * Vaccinate method. Sets infection status to false and records that actor has been vaccinated
	 */
	@Override
	public void vaccinate() {
		super.setInfectionStatus(false);
		this.vaccinated = true;
	}

	/**
	 * Disrupts items in inventory and then calls super method Actor.hurt(int points)
	 */
	public void hurt(int points) {
		for (Item item : inventory) {
			item.disrupt();
		}
		super.hurt(points);
	}

	/**
	 * Actor can vaccinate.
	 * @return boolean true
	 */
	@Override
	public boolean canVaccinate() {
		return true;
	}

	/**
	 * Returns actor's vaccinated status
	 * @return vaccinated
	 */
	@Override
	public boolean getVaccinatedStatus() {
		return this.vaccinated;
	}
	
	/**
	 * Actor can drive.
	 * @return true
	 */
	@Override
	public boolean canDrive() {
		return true;
	}
	
	/**
	 * Returns player's menu
	 * @return menu
	 */
	public Menu getMenu() {
		return menu;
	}
}