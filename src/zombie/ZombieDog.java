package zombie;

import AttackTypeBehaviors.BiteBehaviour;
import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Display;
import edu.monash.fit2099.engine.DoNothingAction;
import edu.monash.fit2099.engine.GameMap;
import game.Behaviour;
import game.LShapeHuntBehaviour;
import game.LShapeWanderBehaviour;
import zombie.ZombieActor;

/**
 * Class for ZombieDogs
 * @author Josh
 *
 */
public class ZombieDog extends ZombieActor {
	
	/**
	 * The Behaviours the dog has
	 */
	private Behaviour[] behaviours = {
			new BiteBehaviour(ZombieCapability.ALIVE), 
			new BarkBehaviour(ZombieCapability.ALIVE, 3),
			new LShapeHuntBehaviour(8, ZombieCapability.ALIVE),
			new LShapeWanderBehaviour()
	};
	
	/**
	 * Constructor for Zombie Dogs
	 * @param name name of the dog
	 */
	public ZombieDog(String name) {
		super(name, '$', 80, ZombieCapability.UNDEAD);
	}
	
	/**
	 * Returns the dog's bite weapon
	 */
	@Override
	public BiteWeapon getBiteWeapon() {
		return new BiteWeapon(10, "mauls", 3, 60);
	}
	
	/**
	 * Iterates over the dogs behaviours in order and picks the first non-null action.
	 * The last behaviour is guaranteed to return an action so it is last resort.
	 */
	@Override
	protected Action getAction(Actions actions, Action lastAction, GameMap map, Display display) {
		for (Behaviour behaviour : behaviours) {
			Action action = behaviour.getAction(this, map);
			if (action != null) {
				return action;
			}
		}
		return new DoNothingAction();
	}

	/**
	 * Zombie dogs are zombies
	 */
	@Override
	public boolean isZombie() {
		return true;
	}
}
