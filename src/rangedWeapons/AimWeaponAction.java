package rangedWeapons;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;

/**
 * Action for aiming a scoped weapon
 * @author Josh
 *
 */
public class AimWeaponAction extends Action {
	
	/**
	 * actor aiming at
	 */
	private Actor target;
	
	/**
	 * weapon being used for aiming
	 */
	private ScopedWeapon weapon;
	
	/**
	 * constructor for the aim weapon action
	 * @param weapon
	 * @param target
	 */
	public AimWeaponAction(ScopedWeapon weapon, Actor target) {
		this.target = target;
		this.weapon = weapon;
	}
	
	/**
	 * Calls the aim method of the scoped weapon
	 */
	@Override
	public String execute(Actor actor, GameMap map) {
		weapon.aim(target);
		return actor + " aimed at " + target + " with " + weapon + ".";
	}
	
	/**
	 * Menu descriptor for aiming a scoped weapon
	 */
	@Override
	public String menuDescription(Actor actor) {
		return "Aim " + weapon + " at " + target + ".";
	}
}
