package AttackTypeBehaviors;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Item;
import zombie.Zombie;
import zombie.ZombieCapability;

/**
 * A class for choosing which attack an actor will perform.
 * 
 * @author Josh
 *
 */
public class ZombieAttackPicker extends AttackTypeBehaviour {

	/**
	 * Constructor for ZombieAttackBehaviour
	 * 
	 * @param attackableTeam
	 */
	public ZombieAttackPicker(AttackTypeBehaviour mainAttack, ArrayList<AttackTypeBehaviour> specialAttacks, ZombieCapability attackableTeam) {
		super(attackableTeam);
		this.mainAttack = mainAttack;
		this.specialAttacks = specialAttacks;
	}

	/**
	 * The main attack that the Zombie will perform.
	 */
	private AttackTypeBehaviour mainAttack;

	/**
	 * The special attacks a Zombie can perform
	 */
	private ArrayList<AttackTypeBehaviour> specialAttacks;

	/**
	 * Returns an action based on the Zombie's current State
	 * 
	 * @param actor the actor doing the action
	 * @param map   the GameMap containing the actor
	 * @return an Action that actor can perform, or null if actor can't do this.
	 */
	public Action getAction(Actor actor, GameMap map) {
		int arms = actor.getArmCount();
		if (arms == 0) {
			// If an actor has no arms, it cannot attack
			return getSpecialAttackAction(actor, map);
		}

		for (Item item : actor.getInventory()) {
			if (item.asWeapon() != null) {
				// If an actor has at least one arm, and has a weapon, use that weapon
				return getMainAttackAction(actor, map);
			}
		}

		if (arms == 1 && rand.nextBoolean() && rand.nextBoolean()) {
			// If an actor has one arm, only main attack 25% of the time
			return getMainAttackAction(actor, map);
		} else if (arms == 2 && rand.nextBoolean()) {
			// If an actor has 2 arms, only main attack 50% of the time
			return getMainAttackAction(actor, map);
		} else {
			// If an actor didn't perform a main attack, perform a specialAttack
			return getSpecialAttackAction(actor, map);
		}
	}
	
	/**
	 * Creates an attackAction for the main attack
	 * @param actor actor attacking
	 * @param map the gamemap the attack is taking place on
	 * @return the action for the main attack
	 */
	private Action getMainAttackAction(Actor actor, GameMap map) {
		return mainAttack.getAction(actor, map);
	}
	
	/**
	 * Picks a random special attack from all of the special attacks of the actor
	 * @param actor actor attacking
	 * @param map GameMap the attack is taking place on
	 * @return the action for the special attack
	 */
	private Action getSpecialAttackAction(Actor actor, GameMap map) {
		// get special attacks in random order
		ArrayList<AttackTypeBehaviour> specialAttacksCopy = getSpecialAttacks();
		Collections.shuffle(specialAttacksCopy);
		
		// search through special attacks, until one returns not null
		for (AttackTypeBehaviour specialAttack : specialAttacksCopy) {
			Action action = specialAttack.getAction(actor, map);
			if (action != null) {
				return action;
			}
		}
		// If no special attacks can be performed, return null
		return null;
	}
	
	/**
	 * gets a copy of the list of special attacks
	 * @return copied list of special attacks
	 */
	private ArrayList<AttackTypeBehaviour> getSpecialAttacks() {
		return new ArrayList<AttackTypeBehaviour>(specialAttacks);
	}

}
