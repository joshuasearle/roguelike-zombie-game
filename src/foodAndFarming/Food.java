package foodAndFarming;

import edu.monash.fit2099.engine.Action;
import game.PortableItem;

/**
 * Class representing food.
 * Food item can be eaten by damaged humans to recover hitpoints
 * @author aahdu
 */
public class Food extends PortableItem {

	/**
	 * hit points recovered from eating food
	 */
	private static int recoverHitpoints;
	
	/**
	 * Constructor for food items
	 * @param recoverHitPoints number of hitpoints human recovers from eating food
	 */
	public Food() {
		super("food", 'f');
		recoverHitpoints = 20;
		Action eatFood = new EatFoodAction(this);
		this.allowableActions.add(eatFood);
	}
	/**
	 * Method returns recoverHitPoints
	 * @return hitpoints human recovers from eating food
	 */
	public static int getRecoverHitPoints() {
		return recoverHitpoints;
		}
	
	@Override
	public boolean eatable() {
		return true;
	}
}