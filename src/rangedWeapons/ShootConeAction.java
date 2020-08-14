package rangedWeapons;

import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Location;
import game.Direction;

/**
 * Class for shooting in a cone
 * @author Josh
 *
 */
public class ShootConeAction extends ShootStrategy {
	
	/**
	 * weapon being shot
	 */
	RangedWeapon weapon;
	
	/**
	 * direction being shot
	 */
	Direction direction;
	
	/**
	 * constructor for shoot cone action
	 * @param weapon weapon being shot
	 * @param direction direction being shot
	 */
	public ShootConeAction(RangedWeapon weapon, Direction direction) {
		super(null);
		this.weapon = weapon;
		this.direction = direction;
	}
	
	/**
	 * Executes the cone shot
	 * Damages all actors in a cone with given direction and range
	 */
	@Override
	public String execute(Actor actor, GameMap map) {
		
		weapon.takeBullet();
		
		if (rand.nextInt(101) > weapon.getHitChance()) {
			return actor + " misses their shot.";
		}
		
		String result = actor + " shot their " + weapon + ".";
		Location here = map.locationOf(actor);
		for (int i = 1; i < weapon.range() + 1; i++) {
			for (int j = -i; j < i + 1; j++) {
				// logic to shoot in a cone
				int x = here.x() + i*direction.dx + j*(Math.abs(direction.dx) - 1);
				int y = here.y() + i*direction.dy + j*(Math.abs(direction.dy) - 1);
				if (!(map.getXRange().contains(x) && map.getYRange().contains(y))) {
					continue;
				}
				this.target = map.at(x, y).getActor();
				if (target != null) {
					result += "\nThe shot hit " + target + " for " + weapon.damage() + " damage.";
					result += hurtTarget(map, weapon.damage());}
				}
				
		}
		return result;
	}
	
	/**
	 * Menu descriptor for shooting in a specific direction
	 */
	@Override
	public String menuDescription(Actor actor) {
		return "shoot " + weapon + " " + direction.name;
	}
	
	/**
	 * Preferred hotkey for the action
	 */
	@Override
	public String hotkey() {
		return direction.preferredKey;
	}

}
