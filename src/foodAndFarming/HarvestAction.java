package foodAndFarming;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Ground;
import edu.monash.fit2099.engine.Item;
import edu.monash.fit2099.engine.Location;


/**
 * Special action allows farmer to harvest a ripe crop
 * @author aahdu
 *
 */
public class HarvestAction extends Action {
	
	/**
	 * Location of crop to be harvested
	 */
	private Location location;

	/**
	 * Constructor
	 * 
	 * @param location	location of crop to be harvested
	 */
	public HarvestAction(Location location) {
		this.location = location;
	}

	@Override
	public String execute(Actor actor, GameMap map) {
		
		if (!actor.canFarm()) {
			return null;
		}
		// set ground to dirt
		Ground dirt = new Dirt();
		location.setGround(dirt);
		
		// add food to location
		Item food = new Food();
		location.addItem(food);
		
		return actor + " has harvested a ripe crop";
	}

	@Override
	public String menuDescription(Actor actor) {
		return actor + " can harvest a crop";
	}
}
