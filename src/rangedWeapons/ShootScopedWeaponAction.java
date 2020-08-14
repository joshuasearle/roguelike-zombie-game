package rangedWeapons;

import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;

/**
 * Class for shooting a scoped weapon
 * @author Josh
 *
 */
public class ShootScopedWeaponAction extends ShootStrategy {
	
	/**
	 * weapon being shot
	 */
	private ScopedWeapon weapon;
	
	/**
	 * constructor for ShootScopedWeaponAction
	 * @param weapon weapon being shot
	 * @param target target of the weapon
	 */
	public ShootScopedWeaponAction(ScopedWeapon weapon, Actor target) {
		super(target);
		this.weapon = weapon;
	}
	
	/**
	 * Shoots the scoped weapon
	 */
	@Override
	public String execute(Actor actor, GameMap map) {		
		weapon.takeBullet();
		
		if (rand.nextInt(101) > weapon.hitChance(target)) {
			return actor + " misses their shot.";
		}
		
		String result = actor + " shot their " + weapon + ".";
		
		result += "\nThe shot hit " + target + " for " + weapon.damage(target) + " damage.";
		
		result += hurtTarget(map, weapon.damage(target));
		
		return result;
	}
	
	/**
	 * Menu descriptor for shooting a scoped weapon
	 */
	@Override
	public String menuDescription(Actor actor) {
		return "shoot " + target + " for " + weapon.damage(target) + " damage";
	}

}
