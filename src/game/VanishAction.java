package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Item;
import edu.monash.fit2099.engine.Location;
import zombie.Zombie;

/**
 * Special action that allows an actor to vanish (create no corpse)
 * @author aahdu
 */
public class VanishAction extends Action {

	/**
	 * Constructor
	 */
	public VanishAction() {
	}

	/**
	 * Execute VanishAction.
	 * If actor is human drop all items from actors inventory, remove actor
	 * If actor is huamn & not a player - spawn a zombieS
	 * If actor is MamboMarie, set vanish status to true and remove actor from GameMap
	 */
	@Override
	public String execute(Actor actor, GameMap map) {
		// if actor is infected
		if (actor.isInfected()) {			
			
			// drop items
			Actions dropActions = new Actions();
			for (Item item : actor.getInventory())
				dropActions.add(item.getDropAction());
			for (Action drop : dropActions)		
				drop.execute(actor, map);
			
			//remove actor
			Location location = map.locationOf(actor);
			map.removeActor(actor);
			
			// if actor isn't player, create a new zombie at same location
			if (!actor.canVaccinate()) {
				map.addActor(new Zombie("undead " + actor, map), location);
			}
		}
		
		// if actor is Mambo Marie, remove and record that she has vanished
		if (actor.canVanish()) {
			map.removeActor(actor);
			WorldWithEndings.setMamboMarieVanished(true);
			return actor + " has vanished!";
		}
		return actor + " has turned into a Zombie!";
	}
		
	/**
	 * Returns null, player cannot select to vanish
	 */
	@Override
	public String menuDescription(Actor actor) {
		return null;
	}
}