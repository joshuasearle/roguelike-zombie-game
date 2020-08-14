package AttackTypeBehaviors;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import AttackTypeActions.AttackAction;
import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Exit;
import edu.monash.fit2099.engine.GameMap;
import zombie.ZombieCapability;

/**
 * A class that generates an AttackAction if the current Actor is standing
 * next to an Actor that they can attack.
 * 
 * @author ram
 *
 */
public class AttackBehaviour extends AttackTypeBehaviour {
	
	/**
	 * Constructor for AttackBehaviour
	 * @param attackableTeam
	 */
	public AttackBehaviour(ZombieCapability attackableTeam) {
		super(attackableTeam);
	}

	/**
	 * Returns an AttackAction that attacks an adjacent attackable Actor.
	 * 
	 * Actors are attackable if their ZombieCapability matches the 
	 * "undeadness status" set 
	 */
	@Override
	public Action getAction(Actor actor, GameMap map) {
		Exit e = getExitWithAttackableActor(actor, map);
		if (e == null) {
			return null;
		}
		return new AttackAction(e.getDestination().getActor());
	}
}
