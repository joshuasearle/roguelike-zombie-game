package AttackTypeBehaviors;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Exit;
import edu.monash.fit2099.engine.GameMap;
import game.Behaviour;
import zombie.ZombieCapability;

/**
 * An abstract class that acts as a skeleton for all behaviours that create attacks.
 * 
 * @author Josh
 *
 */
public abstract class AttackTypeBehaviour implements Behaviour {
	/**
	 * The team that the attacks can attack
	 */
	protected ZombieCapability attackableTeam;
	
	/**
	 * Random number generator
	 */
	protected Random rand = new Random();
	
	/**
	 * Constructor.
	 * 
	 * Sets the team (i.e. ZombieCapability) that the owner of this
	 * Behaviour is allowed to attack.
	 * 
	 * @param attackableTeam Team descriptor for ZombieActors that can be attacked
	 */
	public AttackTypeBehaviour(ZombieCapability attackableTeam) {
		this.attackableTeam = attackableTeam;
	}
	
	/**
	 * returns an Exit with an attackable actor. If no such exit exists, return null.
	 * @param actor actor attacking
	 * @param map GameMap this is taking place on
	 * @return Exit with attackable actor or null
	 */
	protected Exit getExitWithAttackableActor(Actor actor, GameMap map) {
		// Is there an attackable Actor next to me?
		List<Exit> exits = new ArrayList<Exit>(map.locationOf(actor).getExits());
		Collections.shuffle(exits);
				
		for (Exit e: exits) {
			if (!(e.getDestination().containsAnActor()))
				continue;
			if (e.getDestination().getActor().hasCapability(attackableTeam)) {
				return e;
			}
		}
		return null;
	}
}
