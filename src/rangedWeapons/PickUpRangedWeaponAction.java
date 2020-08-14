package rangedWeapons;

import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.PickUpItemAction;
import game.Player;

/**
 * Class for picking up ranged weapons
 * @author Josh
 *
 */
public class PickUpRangedWeaponAction extends PickUpItemAction {
	
	/**
	 * weapon picking up
	 */
	private RangedWeapon weapon;
	
	/**
	 * Constructor for PickUpRangedWeaponAction
	 * @param weapon weapon picking up
	 */
	public PickUpRangedWeaponAction(RangedWeapon weapon) {
		super(null);
		this.weapon = weapon;
	}
	
	/**
	 * Executes a pick up ranged weapon action
	 * Connects weapon to ammo pouch
	 */
	@Override
	public String execute(Actor actor, GameMap map) {
		weapon.setAmmoPouch(actor.getAmmoPouch());
		map.locationOf(actor).removeItem(weapon);
		actor.addItemToInventory(weapon);
		return actor + " picked up a " + weapon;
	}
	
	/**
	 * Menu descriptor for picking up a weapon
	 */
	@Override
	public String menuDescription(Actor actor) {
		return "pick up " + weapon;
	}
}
