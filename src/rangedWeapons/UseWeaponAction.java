package rangedWeapons;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import game.Direction;

/**
 * Action for using a weapon
 * @author Josh
 *
 */
public class UseWeaponAction extends Action {
	
	/**
	 * Weapon being used
	 */
	private RangedWeapon weapon;
	
	/**
	 * Constructor for UseWeaponAction
	 * @param weapon weapon being used
	 */
	public UseWeaponAction(RangedWeapon weapon) {
		this.weapon = weapon;
	}
	
	/**
	 * Calls the weapons use weapon method
	 */
	@Override
	public String execute(Actor actor, GameMap map) {
		return weapon.useWeapon(actor, map);
	}
	
	/**
	 * Menu descriptor for using a weapon
	 */
	@Override
	public String menuDescription(Actor actor) {
		return "use " + weapon;
	}

}
