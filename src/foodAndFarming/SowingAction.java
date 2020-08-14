package foodAndFarming;

import java.util.Random;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Ground;

import edu.monash.fit2099.engine.Location;

/**
 * Special action allows farmers to sow a crop into dirt
 * @author aahdu
 *
 */
public class SowingAction extends Action{
	
	/**
	 * Random number used to generate probability of sowing
	 */
	private Random rand = new Random();
	private int sowingProbability = rand.nextInt(3);
	
	/**
	 * location of dirt that crop will be sown onto
	 */
	private Location location;

	/**
	 * Constructor 
	 * @param location	location of dirt that crop will be sown into
	 */
	public SowingAction(Location location) {
		this.location = location;
	}

	@Override
	public String execute(Actor actor, GameMap map) {
		
		if (!actor.canFarm()) {
			return null;
		}
		
		// 33% change actor executes succesful sow
		if (sowingProbability == 1) {
			Ground crop = new Crop();
			
			location.setGround(crop);
			
			return actor + " has succesfully sown a crop";
		}
		else {
			return actor + " has failed to sow a crop";
		}
		
	}

	@Override
	public String menuDescription(Actor actor) {
		return actor + " sows a crop";
	}
}
