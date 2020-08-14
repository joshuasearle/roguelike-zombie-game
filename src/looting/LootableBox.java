package looting;

import edu.monash.fit2099.engine.Actor;

/**
 * Interface for things that can be looted
 * @author Josh
 *
 */
public interface LootableBox {
	/**
	 * loots a lootable box
	 * @param actor actor looting
	 * @return string message about the loot action
	 */
	public String loot(Actor actor);

}
