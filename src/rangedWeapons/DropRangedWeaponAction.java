package rangedWeapons;

import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.DropItemAction;
import edu.monash.fit2099.engine.GameMap;

/**
 * Class for dropping ranged weapons
 * @author Josh
 *
 */
public class DropRangedWeaponAction extends DropItemAction {

	/**
	 * weapon being dropped
	 */
	private RangedWeapon weapon;
	
	/**
	 * Constructor for DropRangedWeaponAction
	 * @param weapon weapon dropping
	 */
	public DropRangedWeaponAction(RangedWeapon weapon) {
		super(null);
		this.weapon = weapon;
	}
	
	/**
	 * Executes a ranged weapon drop action by dropping the weapon and disconnecting the ammo pouch
	 */
	@Override
	public String execute(Actor actor, GameMap map) {
		weapon.setAmmoPouch(null);
		map.locationOf(actor).addItem(weapon);
		actor.removeItemFromInventory(weapon);
		return actor + " dropped their " + weapon;
	}
	
	/**
	 * Menu descriptor for dropping a ranged weapon
	 */
	@Override
	public String menuDescription(Actor actor) {
		return "drop the " + weapon;
	}

}
