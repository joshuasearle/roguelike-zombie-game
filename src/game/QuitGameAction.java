package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;

/**
 * Action used if an actor quits
 * @author Josh
 *
 */
public class QuitGameAction extends Action {
	
	/**
	 * Executes the quit action by setting the actors hasQuit status to true
	 */
	@Override
	public String execute(Actor actor, GameMap map) {
		actor.setQuitStatus(true);
		return actor +" quit the game";
	}
	
	/**
	 * Menu descriptor for quiting the game
	 */
	@Override
	public String menuDescription(Actor actor) {
		return "quit game";
	}
	
	/**
	 * The preferred hotkey for quitting the game
	 */
	@Override
	public String hotkey() {
		return "q";
	}

}
