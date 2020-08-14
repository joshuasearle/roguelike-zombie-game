package AttackTypeActions;
import java.util.Random;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Item;
import edu.monash.fit2099.engine.Weapon;
import game.Corpse;
import game.PortableItem;
import game.WorldWithEndings;

/**
 * abstract class that defines some common functionality for attack type actions
 * @author Josh
 *
 */
public abstract class AttackTypeAction extends Action {
	/**
	 * The actor being targeted
	 */
	protected Actor target;

	/**
	 * Random number generator
	 */
	protected Random rand = new Random();

	/**
	 * Constructor.
	 * 
	 * @param target the Actor to attack
	 */
	public AttackTypeAction(Actor target) {
		this.target = target;
	}

	/**
	 * Executes the action
	 * @param actor the actor performing the action
	 * @param map the GameMap this is taking place on
	 */
	public abstract String execute(Actor actor, GameMap map);

	/**
	 * Kills the target by turning removing the actor, dropping items onto the ground, and turning the actor into a corpse
	 * @param map the GameMap the death is taking place
	 * @return kill message
	 */
	protected String killTarget(GameMap map) {

		Item corpse;

		if (target.canRiseFromDead()) {
			corpse = new Corpse("dead " + target, '%');
		}	
		else {
			//if target is Mambo Marie
			if (target.canVanish()) {
				WorldWithEndings.killMamboMarie();
			}
				corpse = new PortableItem("corpse of " + target, '%');
				map.locationOf(target).addItem(corpse);
			}

		Actions dropActions = new Actions();
		for (Item item : target.getInventory())
			dropActions.add(item.getDropAction());
		for (Action drop : dropActions)		
			drop.execute(target, map);
		map.removeActor(target);

		return System.lineSeparator() + target + " is killed.";
	}

	/**
	 * Damages the target, checks if they target is conscious after the attack, and checks if there are can fall off
	 * @param map the GameMap that the attack is taking place on
	 * @param weapon the weapon doing the damage
	 * @return message to printed out
	 */
	protected String hurtTarget(GameMap map, int damage) {
		String result = "";
		target.hurt(damage);
		if (!target.isConscious()) {
			// if target dies, call killTarget
			result += killTarget(map);
		}
		return result;
	}

	/**
	 * get default hit message
	 * @param actor actor hitting
	 * @param weapon the weapon doing the damage
	 * @return hitMessage
	 */
	protected String getHitMessage(Actor actor, Weapon weapon) {
		return actor + " " + weapon.verb() + " " + target + " for " + weapon.damage() + " damage.";
	}
}