package looting;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;

/**
 * Action for looting lootable things
 * @author Josh
 *
 */
public class LootAction extends Action {
	
	/**
	 * Box being looted
	 */
	private LootableBox boxLooting;
	
	/**
	 * Constructor for the action
	 * @param box box being looted
	 */
	public LootAction(LootableBox box) {
		this.boxLooting = box;
	}
	
	/**
	 * Loots the lootable box
	 */
	@Override
	public String execute(Actor actor, GameMap map) {
		return boxLooting.loot(actor);
	}

	/**
	 * Default menu descriptor for looting
	 */
	@Override
	public String menuDescription(Actor actor) {
		return "loot " + boxLooting;
	}
	
}
