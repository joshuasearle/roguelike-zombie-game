package rangedWeapons;

import ammo.AmmoType;
import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import game.Direction;

/**
 * Class for shotgun items
 * @author Josh
 *
 */
public class Shotgun extends RangedWeapon {
	
	/**
	 * Constructor for shotguns
	 */
	public Shotgun() {
		super("Shotgun", '{', 55, 75, AmmoType.SHOTGUN_AMMO, 3, "blasts");
	}
	
	/**
	 * Describes the uses of the shotgun, then prompts user to pick an option
	 */
	@Override
	public String useWeapon(Actor actor, GameMap map) {
		Actions actions = new Actions();
		for (Direction direction : Direction.values()) {
			actions.add(new ShootConeAction(this, direction));
		}
		
		return pickWeaponAction(actor, actions).execute(actor, map);
	}
	
	/**
	 * Disrupting shotgun does nothing
	 */
	@Override
	public void disrupt() {
		return;
	}
}
