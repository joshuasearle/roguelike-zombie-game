package foodAndFarming;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Exit;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Ground;
import edu.monash.fit2099.engine.Location;
import game.Behaviour;

/**
 * Class generates a SowingAction, FertilizeAction, HarvestAction when applicable
 * @author aahdu
 *
 */
public class FarmerBehaviour implements Behaviour {

	/**
	 * Constructor
	 */
	public FarmerBehaviour() {
	}

	/**
	 * Returns a:
	 * 		FertilizeAction if actor is standing on an unripe crop
	 * 		HarvestAction if actor is standing on or next to a ripe crop
	 * 		SowingAction if actor is standing on or next to dirt
	 */
	public Action getAction(Actor actor, GameMap map) {

		Location location = map.locationOf(actor);
		Ground crop = location.getGround();

		// if farmer standing on unripe crop, fertilize
		if (crop.fertilisable()) {
			return new FertilizeAction(crop);
		}
		// if a surrounding location holds a ripe crop, harvest
		// if a surrounding location holds dirt, sow a crop
		for (Exit exit : location.getExits()) {
			Location destination = exit.getDestination();
			if (destination.getGround().harvestable()) {
				return new HarvestAction(destination);
			}
			else if (destination.getGround().sowable()) {
				return new SowingAction(destination);
			}
		}
		return null;
	}
}