package game;

import java.util.Random;

import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Location;

/**
 * Class representing a human corpse
 * @author aahdu
 */
public class Corpse extends PortableItem {
	/**
	 * Age of corpse
	 */
	private int corpseAge;
	
	/**
	 * Random number generator used to set the reincarnationAge (number of turns until corpse rises from dead) from 5-10
	 */
	private Random rand = new Random();
	private int reincarnationAge;
	
/**
 * Constructor
 * 
 * @param name 			name of dead human
 * @param displayChar 	character that will represent corpse on map
 */
	public Corpse(String name, char displayChar) {
		super(name, displayChar);
		this.corpseAge = 0;
		this.reincarnationAge = rand.nextInt(6) + 5;
	}
	
	@Override
	public void tick(Location location, Actor actor) {
		tick(location);
	}
	
	/**
	 * tick corpse age every turn, if corpse age = reincarnation age run RiseFromDead
	 */
	@Override
	public void tick(Location location) {
		corpseAge++;
		if (corpseAge == reincarnationAge) {
			new RiseFromDead().execute(location, this.name);
		}
	}
	
	/**
	 * Method increments reincarnationAge 
	 * Used when corpse is in actors inventory and cannot rise from the dead because
	 * there's no free adjacent space
	 */
	public void incrementReincarnationAge() {
		reincarnationAge++;
	}
}
