package game;

import java.util.Random;
import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Display;
import edu.monash.fit2099.engine.DoNothingAction;
import edu.monash.fit2099.engine.GameMap;
import foodAndFarming.FoodBehaviour;
import zombie.ZombieActor;
import zombie.ZombieCapability;

/**
 * Class representing an ordinary human.
 * 
 * @author ram
 *
 */
public class Human extends ZombieActor {
	
	/**
	 * Human's behaviours
	 */
	private Behaviour[] behaviours = {
			new WanderBehaviour(),
			new FoodBehaviour(),
	};
	
	/**
	 * number of Human's behaviours
	 */
	private int lengthBehaviours = behaviours.length;
	
	/**
	 * Is the human infected. All initialised as not infected.
	 */
	private boolean infected = false;
	
	/**
	 * Number of turns actor has been infected
	 */
	private int infectionAge;
	
	/**
	 * Age when actor will turn into zombie
	 */
	private int zombifyAge;
	
	/**
	 * The default constructor creates default Humans
	 * @param name the human's display name
	 */
	public Human(String name) {
		super(name, 'H', 50, ZombieCapability.ALIVE);
	}

	/**
	 * The protected constructor can be used to create subtypes
	 * of Human, such as the Player
	 * 
	 * @param name the human's display name
	 * @param displayChar character that will represent the Human in the map 
	 * @param hitPoints amount of damage that the Human can take before it dies
	 */
	protected Human(String name, char displayChar, int hitPoints) {
		super(name, displayChar, hitPoints, ZombieCapability.ALIVE);
	}
	
	/**
	 * Human can eat food if they don't have full health (if satisfies return true)
	 */
	@Override
	public boolean canEat() {
		return maxHitPoints > hitPoints;
	}

	/**
	 * Humans can rise from the dead. Return boolean true.
	 */
	@Override
	public boolean canRiseFromDead() {
		return true;
	}
	
	/**
	 * Select random behaviour to action
	 * @return Action
	 */
	@Override
	protected Action getAction(Actions actions, Action lastAction, GameMap map, Display display) {		
		ShuffleBehaviours.randomise(behaviours, lengthBehaviours);
		for (Behaviour behaviour : behaviours) {
			Action action = behaviour.getAction(this, map);
			if (action != null) {
				return action;
			}
		}
			return new DoNothingAction();
	}
	
	/**
	 * Returns if actor is infected.
	 * @return boolean infected.
	 */
	@Override
	public boolean isInfected() {
		return infected;
	}
	
	/**
	 * Set actor to infected/not infected
	 * true = infected
	 */
	@Override
	public void setInfectionStatus(boolean status) {
		this.infected = status;		
	}
	
	/**
	 * Set actor as infected. Initialise infectionAge and zombifyAge
	 */
	@Override
	public void infect() {
		setInfectionStatus(true);
		infectionAge = 0;
		
		Random rand = new Random();
		this.zombifyAge = rand.nextInt(11) + 10;
	}

	/**
	 * Returns actor's infection age
	 * @return infectionAge
	 */
	@Override
	public int getInfectionAge() {
		return infectionAge;
	}

	/**
	 * Return integer zombifyAge
	 * @return zombifyAge
	 */
	@Override
	public int getZombifyAge() {
		return zombifyAge;
	}
	
	/**
	 * Tick actor's infection age
	 */
	@Override
	public void tickInfection() {
		this.infectionAge++;
	}
}