package game;

import java.util.ArrayList;

import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Location;

/**
 * Class for L Shape movement logic
 * @author Josh
 *
 */
public class LShapeMover {
	
	/**
	 * Finds all locations an actor could move, that is an L shape
	 * @param actor actor moving
	 * @param location location the actor could be
	 * @param map map the locations are on
	 * @return list of locations
	 */
	public static ArrayList<Location> getLLocations(Actor actor, Location location, GameMap map) {
		ArrayList<Location> locations = new ArrayList<Location>();
		for (int i = -2; i <= 2; i++) {
			for (int j = -2; j <= 2; j++) {
				if (Math.abs(i) + Math.abs(j) == 3) {
					int x = location.x() + i;
					int y = location.y() + j;
					if (map.getXRange().contains(x) && map.getYRange().contains(y)) {
						Location reachableLocation = map.at(x, y);
						if (reachableLocation.canActorEnter(actor)) {
							locations.add(reachableLocation);
						}
					}
				}
			}
		}
		return locations;
	}
}
