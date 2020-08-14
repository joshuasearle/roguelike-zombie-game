package zombie;

import java.util.List;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Location;

/**
 * Class for bark actions, which paralyse actors in a radius
 * @author Josh
 *
 */
public class BarkAction extends Action {
	
	/**
	 * targets being affected
	 */
	private List<Actor> targets;
	
	/**
	 * Constructor for bark action
	 * @param targets targets affected
	 */
	public BarkAction(List<Actor> targets) {
		this.targets = targets;
	}
	
	/**
	 * Paralyzes all attackable actors in a grid around the dog
	 */
	@Override
	public String execute(Actor actor, GameMap map) {
		String result = actor + " barked.";
		for (Actor target : targets) {
			target.paralyse();
			result += "\n" + target + " was paralysed by " + actor + "'s bark";
		}
		return result;
	}
	
	/**
	 * Menu descriptor for barking
	 */
	@Override
	public String menuDescription(Actor actor) {
		return "bark";
	}

}
