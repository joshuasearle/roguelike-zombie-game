package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.MoveActorAction;
import edu.monash.fit2099.engine.Location;

/**
 * Special action transports actor between levels
 * @author aahdu
 */
public class DriveAction extends Action {
	
	/**
	 * Constructor
	 */
	public DriveAction() {
	}

	/**
	 * Executes DriveAction. 
	 * Actor is driven to other level at the level's car's location
	 * @return string 
	 */
	@Override
	public String execute(Actor actor, GameMap map) {
		
		// if actor is player
		if (actor.canDrive()) {

			// where actor can drive to 
			String destination;
			String cannotDriveString = "Oh no! " + actor + " couldn't get the car started";
			GameMap mainMap = Application.getMainMap();

			// if actor currently in Main level, transport to Town level
			if (map == mainMap) {
				Location vehicleLocationTown = Application.getVehicleLocationTown();
				if (Application.getVehicleLocationTown().canActorEnter(actor)) {
					new MoveActorAction(vehicleLocationTown, "drive").execute(actor, map);
					destination = Levels.TOWN.getName();
				}
				//location of vehicle on other map contains an actor
				else {
					return cannotDriveString;
				}
			}
			// if actor currently in Town level, transport to Main level
			else {
				
				Location vehicleLocationMain = Application.getVehicleLocationMain();
				if (vehicleLocationMain.canActorEnter(actor)) {
					new MoveActorAction(vehicleLocationMain, "drive").execute(actor, map);
					destination = Levels.MAIN.getName();
				}
				else {
					//location of vehicle on other map contains an actor
					return cannotDriveString;
				}
			}
			return actor + " has driven to the " + destination;
		}
		return null;
	}
	
	
	/**
	 * Returns a descriptive string.
	 */
	@Override
	public String menuDescription(Actor actor) {
		return actor + " drives to other map";
	}
}