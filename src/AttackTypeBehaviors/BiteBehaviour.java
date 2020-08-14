package AttackTypeBehaviors;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import AttackTypeActions.BiteAction;
import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Exit;
import edu.monash.fit2099.engine.GameMap;
import zombie.ZombieCapability;

/**
 * A class that generates a BiteAction if the current Actor is standing
 * next to an Actor that they can bite, and they can perform a bite.
 * @author Josh
 *
 */
public class BiteBehaviour extends AttackTypeBehaviour {
	
	/**
	 * Constructor for BiteBehaviour
	 * @param attackableTeam the team the bite can hit
	 */
	public BiteBehaviour(ZombieCapability attackableTeam) {
		super(attackableTeam);
	}
	
	@Override
	public Action getAction(Actor actor, GameMap map) {
		if (actor.getBiteWeapon() == null) {
			// Can't perform a bite if you can't bite
			return null;
		}
		Exit e = getExitWithAttackableActor(actor, map);
		if (e == null) {
			return null;
		}
		return new BiteAction(e.getDestination().getActor());
	}
}
