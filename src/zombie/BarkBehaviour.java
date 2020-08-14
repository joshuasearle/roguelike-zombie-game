package zombie;

import java.util.ArrayList;

import AttackTypeBehaviors.AttackTypeBehaviour;
import edu.monash.fit2099.demo.conwayslife.Behaviour;
import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Location;

/**
 * Behaviour for creating bark actions
 * @author Josh
 *
 */
public class BarkBehaviour extends AttackTypeBehaviour {
	
	/**
	 * Range of the bark
	 */
	private int range;
	
	/**
	 * Constructor for BarkBehaviour
	 * @param attackableTeam the team the bark will affect
	 * @param range range of the bark
	 */
	public BarkBehaviour(ZombieCapability attackableTeam, int range) {
		super(attackableTeam);
		if (range < 0) {
			throw new IllegalArgumentException("range must be positive");
		}
		
		this.range = range;
	}
	
	/**
	 * Returns a bark action if there are actors in the range, and null if there are none
	 */
	@Override
	public Action getAction(Actor actor, GameMap map) {
		
		
		ArrayList<Actor> targets = new ArrayList<Actor>();
		
		Location here = map.locationOf(actor);
		int startX = Math.max(here.x() - range, map.getXRange().min());
		int endX = Math.min(here.x() + range, map.getXRange().max());
		
		int startY = Math.max(here.y() - range, map.getYRange().min());
		int endY = Math.min(here.y() + range, map.getYRange().max());
		
		// add actors to a list of targets
		for (int i = startX; i <= endX; i++) {
			for (int j = startY; j <= endY; j++) {
				Actor target = map.getActorAt(map.at(i, j));
				if (map.getActorAt(map.at(i, j)) != null && target.hasCapability(this.attackableTeam)) {
					targets.add(target);
				}
			}
		}
		
		if (targets.size() > 0 && rand.nextBoolean() && rand.nextBoolean()) {
			return new BarkAction(targets);
		}
		
		return null;
	}

}
