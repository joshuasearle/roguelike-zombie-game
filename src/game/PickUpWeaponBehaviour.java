package game;

import java.util.List;
import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Item;
import edu.monash.fit2099.engine.Location;

/**
 * Class for generating PickUpItemActions for weapons, if an actor can pick up a weapon
 * @author Josh
 *
 */
public class PickUpWeaponBehaviour implements Behaviour {

	@Override
	public Action getAction(Actor actor, GameMap map) {
		if (actor.getArmCount() == 0) {
			// can't pick up a weapon if you have no arms
			return null;
		}
		
		for (Item item : actor.getInventory()) {
			if (item.asWeapon() != null) {
				// If an actor already has a weapon, can't pick up another
				return null;
			}
		}
		
		Location here = map.locationOf(actor);
		List<Item> items = here.getItems();
		for (Item item : items) {
			if (item.asWeapon() != null) {
				// If actor is standing on a weapon, create PickUpItemAction for the weapon
				return item.getPickUpAction();
			}
		}
		
		// If no weapons where actor is standing, can't pick up a weapon
		return null;
	}
}
