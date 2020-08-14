package foodAndFarming;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Item;
import edu.monash.fit2099.engine.Location;

/**
 * Special action for eating food resulting in increase in hitpoints
 * @author aahdu
 */
public class EatFoodAction extends Action {
	/**
	 * number of hitpoints human recovers from eating food
	 */
	private int recoverHitPoints;
	
	/**
	 *  Food that actor is eating
	 */
	private Item food;

/**
 * Constructor
 * @param food		food that actor is eating
 */
	public EatFoodAction(Item food) {
		this.recoverHitPoints = Food.getRecoverHitPoints();
		this.food = food;
	}

	@Override
	public String execute(Actor actor, GameMap map) {
		
		actor.heal(recoverHitPoints);
		
		Location here = map.locationOf(actor);
		boolean onGround = false;
		
		for (Item item : here.getItems()) {
			if (item == food) {
				// If food is on the ground, remove from ground
				here.removeItem(food);
				onGround = false;
			}
		}
		
		if (!onGround) {
			// If food not on ground, remove from inventory
			actor.removeItemFromInventory(food);
		}
		
		return actor + " eats " + food + " and gains " + recoverHitPoints + " hitpoints";
		
	}
	@Override
	public String menuDescription(Actor actor) {
		return  actor + " eats food";
	}
}
