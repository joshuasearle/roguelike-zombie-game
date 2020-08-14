package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Display;
import edu.monash.fit2099.engine.DoNothingAction;
import edu.monash.fit2099.engine.GameMap;
import foodAndFarming.FarmerBehaviour;
import foodAndFarming.FoodBehaviour;

/**
 * Class representing the Farmer
 * @author aahdu
 */
public class Farmer extends Human {
	private Behaviour[] behaviours = {
			new FarmerBehaviour(),
			new FoodBehaviour(),
			new WanderBehaviour()
	};
	
	/**
	 * number of Farmer's behaviours
	 */
	private int lengthBehaviours = behaviours.length;
	
	/**
	 * Constructor
	 * 
	 * @param name			Name to call the farmer in the UI
	 * @param displayChar	Character to represent the farmer in the UI
	 * @param hitPoints		Farmer's starting number of hitpoints
	 */
	public Farmer(String name) {
		super(name, 'F', 50);
	}

	@Override
	public Action playTurn(Actions actions, Action lastAction, GameMap map, Display display) {
		
		// select a random behaviour to action
		ShuffleBehaviours.randomise(behaviours, lengthBehaviours);
		for (Behaviour behaviour : behaviours) {
			Action action = behaviour.getAction(this, map);
			if (action != null) {
				return action;
			}
		}
		// if the behaviours aren't actionable, do nothing
		return new DoNothingAction();
	}
	
	@Override
	public boolean canFarm() {
		return true;
	}
}
