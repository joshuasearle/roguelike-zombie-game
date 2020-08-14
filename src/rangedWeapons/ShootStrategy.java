package rangedWeapons;

import java.util.Random;

import AttackTypeActions.AttackTypeAction;
import edu.monash.fit2099.engine.Actor;

/**
 * Class for shooting strategies
 * @author Josh
 *
 */
public abstract class ShootStrategy extends AttackTypeAction {
	
	/**
	 * Random number generator
	 */
	protected Random rand = new Random();
	
	/**
	 * Constructor for shoot strategy
	 * @param target target being fired at
	 */
	public ShootStrategy(Actor target) {
		super(target);
	}
}
