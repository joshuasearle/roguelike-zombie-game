package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Item;
import edu.monash.fit2099.engine.Location;

/**
 * Special action that cures infection
 * @author aahdu
 */
public class VaccinateAction extends Action {

	/**
	 * Constructor
	 */
	public VaccinateAction() {
	}

	/**
	 * Execute VaccineAction.
	 * Vaccinate the actor. Remove the vaccine item from ground/inventory
	 * @return String
	 */
	@Override
	public String execute(Actor actor, GameMap map) {

		// if actor can vaccinate
		if (actor.canVaccinate()) {

			Location here = map.locationOf(actor);
			boolean onGround = false;

			// if vaccine on ground, remove from ground
			for (Item item : here.getItems()) {
				if (item.isCure()) {
					here.removeItem(item);
					onGround = true;
					break;
				}
			}
			// if vaccine not on ground, remove from inventory
			if (!onGround) {
				for (Item item : actor.getInventory()) {
					if (item.isCure()) {
						actor.removeItemFromInventory(item);
						break;
					}
				}
			}

			// if actor is already cured, do nothing
			if (actor.getVaccinatedStatus()) {
				return actor + " has already been vaccinated.";
			}
			else {
				actor.vaccinate();
				return actor + " has been vaccinated! They can no longer be infected";
			}
		}
		return null;
	}

	/**
	 * Returns a descriptive string.
	 */
	@Override
	public String menuDescription(Actor actor) {
		return actor + " cure infection";
	}
}