package game;

import java.util.ArrayList;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Location;
import edu.monash.fit2099.engine.MoveActorAction;
import zombie.ZombieCapability;

/**
 * Class for wandering in an L shape
 * @author Josh
 */
public class LShapeWanderBehaviour extends WanderBehaviour {
	
	/**
	 * String to make wander message more interesting
	 */
	private static final String wanderMessage = "towards the human it has been stalking for days, and is about to tear out the human's... oh wait no, it got distracted by a bone";
	
	/**
	 * Returns the possible moves when wandering in only L shape moves
	 * (i.e. returns the the spaces a chess knight could move, unless there is something obstructing one of the locations)
	 */
	@Override
	protected ArrayList<Action> getPossilbleMoveActions(Actor actor, GameMap map) {
		ArrayList<Action> actions = new ArrayList<Action>();
		for (Location location : LShapeMover.getLLocations(actor, map.locationOf(actor), map)) {
			actions.add(new MoveActorAction(location, wanderMessage));
		}
		if (actions.size() == 0) {
			actions.add(new MoveActorAction(map.locationOf(actor), wanderMessage));
		}
		return actions;
	}
}
