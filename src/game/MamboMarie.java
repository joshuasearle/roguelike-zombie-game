package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Display;
import edu.monash.fit2099.engine.GameMap;
import zombie.ZombieActor;
import zombie.ZombieCapability;

/**
 * Class representing MamboMarie
 * @author aahdu
 * */
public class MamboMarie extends ZombieActor {

	/**
	 * Behaviour
	 */
	private Behaviour behaviour = new WanderBehaviour();

	/**
	 * records the number of turns. Used to execute a chant
	 */
	private int chantCounter;

	/**
	 * tracks the number of turns. Used to vanish Mambo Marie
	 */
	private int vanishCounter;

	/**
	 * Constructor
	 */
	public MamboMarie() {
		super("Mambo Marie", 'M', 50, ZombieCapability.UNDEAD);
		chantCounter = 0;
		vanishCounter = 0;
	}

	/**
	 * Method ticks chant and vanish counters
	 */
	private void tickMamboMarie() {
		this.chantCounter++;
		this.vanishCounter++;
	}

	/**
	 * Every 30 turns, Mambo Marie vanishes, every 10 spawn new zombies & chant, otherwise wander
	 * @return Action
	 */
	@Override
	protected Action getAction(Actions actions, Action lastAction, GameMap map, Display display) {

		tickMamboMarie();
		int executeVanishAction = 30;
		int executeZombieSpawnAction = 10;
		
		if (vanishCounter == executeVanishAction) {
			// every 30 turns Mambo Marie vanishes
			return new VanishAction();
		}
		else if (chantCounter == executeZombieSpawnAction) {
			//every 10 turns spawn new zombies & chant
			this.chantCounter = 0;
			return new ZombieSpawnAction();
		}
		else {
			//otherwise wander
			return behaviour.getAction(this, map);
		}
	}

	/**
	 * Actor can vanish. 
	 * @return boolean true
	 */
	@Override
	public boolean canVanish() {
		return true;
	}
}