package foodAndFarming;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Item;
import game.Behaviour;

/**
 * Allows a damaged human to eat food and recover hitpoints
 * @author aahdu
 */
public class FoodBehaviour implements Behaviour {
	
	/**
	 * Food item in actor's inventory
	 */
	private static Item foodItem;

	/**
	 * Returns an EatFoodAction if food is in human's inventory & they're damaged. If not, returns null.
	 */
	@Override
	public Action getAction(Actor actor, GameMap map) {
		//if not a human return null
		if (!actor.canEat()) {
			return null;
		}
		for (Item item : actor.getInventory()) {
			if (item.eatable()) {
				if (actor.canEat()) {
					return new EatFoodAction(item);
				}
			}
		}
		return null;
	}

	/**
	 * Method returns food item in actors inventory
	 * @return food in actors inventory
	 */
	public static Item getFoodItem() {
		return foodItem;
	}
}
