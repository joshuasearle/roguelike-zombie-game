package game;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Location;
import edu.monash.fit2099.engine.MoveActorAction;
import zombie.ZombieCapability;

/**
 * Class for hunting actors, while only moving in L shape moves (chess knight)
 * @author Josh
 *
 */
public class LShapeHuntBehaviour implements Behaviour {
	
	/**
	 * Random number generator
	 */
	private Random rand = new Random();
	
	/**
	 * Strings for MoveActorActions to make the messages more interesting
	 */
	private static final String[] moveStrings = {
			"about erratically",
			"ferociously up to human its been stalking",
			"towards its prey, drooling everywhere"
	};
	
	/**
	 * Max no, of moves the actor can move from the start location
	 */
	private int maxNoMoves;

	/**
	 * the team the actions chases
	 */
	private ZombieCapability attackableTeam;
	
	/**
	 * Constructor for LShapeHuntBehaviour
	 * @param range max no. of moves from start the actor can move
	 */
	public LShapeHuntBehaviour(int maxNoMoves, ZombieCapability attackableTeam) {
		if (maxNoMoves <= 0) {
			throw new IllegalArgumentException("maxNoMoves must be greater than zero");
		}
		
		this.maxNoMoves = maxNoMoves;
		this.attackableTeam = attackableTeam;
	}
	
	/**
	 * Returns a MoveActorAction for a specific location
	 * @param location location to move to
	 * @return MoveActorAction to location
	 */
	private Action getMoveAction(Location location) {
		return new MoveActorAction(location, moveStrings[rand.nextInt(moveStrings.length)]);
	}
	
	/**
	 * Gets action form the behaviour
	 * Returns null if no targets in range
	 * Returns move action towards closest target if there is one
	 * Uses BFS algorithm, but only moves the actor in L shape moves
	 */
	@Override
	public Action getAction(Actor actor, GameMap map) {
		
		HashMap<Location, Integer> distances = new HashMap<Location, Integer>();  // stores the no of moves from the start location each location is
		HashMap<Location, Location> parents = new HashMap<Location, Location>();  // stores the parent location for each location, so we can reconstruct shortest path

		Queue<Location> queue = new LinkedList<Location>();  // Queue for BFS
		
		distances.put(map.locationOf(actor), 0);  // Set start location to actor's current location, and start location to parents and distances
		parents.put(map.locationOf(actor), null);
		queue.add(map.locationOf(actor));
		
		int dist = 0;
		
		// perform BFS
		while (queue.size() > 0 && dist <= maxNoMoves) {
			
			Location current = queue.poll();
			
			
			// finds all L shape locations from current that the actor can move too
			for (Location reachableLocation : LShapeMover.getLLocations(actor, current, map)) {
				// if the location hasn't been visited yet
				if (!distances.containsKey(reachableLocation)) {
					Actor possibleActor = getAdjacentActor(reachableLocation, map);
					
					if (possibleActor != null && possibleActor.hasCapability(attackableTeam)) {
						// If there is an attackable actor, they are the closest attackable actor, and move towards them
						return getMoveAction(getRoot(actor, map, reachableLocation, parents));
					}
					
					// update distances and parents, while incrementing dist if necessary
					int newDist = distances.get(current) + 1;
					dist = Math.max(newDist, dist);
					distances.put(reachableLocation, newDist);
					parents.put(reachableLocation, current);
					queue.add(reachableLocation);
				}
			}
		}
		return null;
	}
	
	/**
	 * Finds the first move the actor would have taken to get to a location
	 * @param actor actor moving
	 * @param map map they are moving on
	 * @param location location trying to move to
	 * @param parents map of locations to there parent location
	 * @return first locaion the actor would have to move to follow the path
	 */
	private Location getRoot(Actor actor, GameMap map, Location location, HashMap<Location, Location> parents) {
		Location current = location;
		while (parents.get(parents.get(current)) != null) {
			current = parents.get(current);
		}
		return current;
	}
	
	/**
	 * Finds if there is an adjacent actor to a location
	 * @param location location checking adjacent locations of
	 * @param map gamemap the locations are
	 * @return the attackable actor, ot null if there is none
	 */
	private Actor getAdjacentActor(Location location, GameMap map) {
		for (int i = -1; i <= 1; i++) {
			for (int j = -1; j <= 1; j++) {
				if (j != 0 && i != 0) {
					int x = location.x() + i;
					int y = location.y() + j;
					if (map.getXRange().contains(x) && map.getYRange().contains(y)) {
						Location adjacentLocation = map.at(x, y);
						Actor possibleActor = adjacentLocation.getActor();
						if (possibleActor != null) {
							return possibleActor;
						}
					}
				}
			}	
		}
		return null;
	}
}
	
		
