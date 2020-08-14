package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;

/**
 * Action performed if an actor is paralyzed
 * @author Josh
 *
 */
public class ParalysedAction extends Action {
	
	/**
	 * Executes the action by returning a string describing the paralyzed state
	 */
	@Override
	public String execute(Actor actor, GameMap map) {
		actor.unParalyse();
		return actor + " did nothing as they were paralysed";
	}
	
	/**
	 * Menu descriptor for paralyzed action
	 */
	@Override
	public String menuDescription(Actor actor) {
		return "be paralysed";
	}
}
