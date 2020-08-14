package AttackTypeActions;

import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Weapon;

/**
 * Special Action for attacking other Actors
 */
public class AttackAction extends AttackTypeAction {

	/**
	 * Constructor.
	 * 
	 * @param target the Actor to attack
	 */
	public AttackAction(Actor target) {
		super(target);
	}
	
	@Override
	public String execute(Actor actor, GameMap map) {

		Weapon weapon = actor.getWeapon();

		if (rand.nextBoolean()) {
			return actor + " misses " + target + ".";
		}

		String result = getHitMessage(actor, weapon);
		
		result += hurtTarget(map, weapon.damage());

		return result;
	}
	
	@Override
	public String menuDescription(Actor actor) {
		return actor + " attacks " + target;
	}
}