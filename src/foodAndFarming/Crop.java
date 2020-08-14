package foodAndFarming;

import edu.monash.fit2099.engine.Ground;
import edu.monash.fit2099.engine.Location;

/**
 * Class representing a crop. Crops start as unripe and then age into ripe
 * @author aahdu
 */
public class Crop extends Ground {
	
	/**
	 * Age of crop
	 */
	private int age;			
	
	/**
	 * Constructor.
	 */
	public Crop() {
		super('c');	
		this.age = 0;
	}

	@Override
	public void tick(Location location) {
		super.tick(location);

		age++;
		if (age < 20)
			// unripe crop = lowercase 'c'
			displayChar = 'c';
		if (age >= 20)
			// ripe crop = uppercase 'C'
			displayChar = 'C';
	}
	
	/**
	 * Method checks if crop is ripe
	 * @return true if crop is ripe
	 */
	public boolean isRipe() {
		return age >= 20;
	}
	
	/**
	 * Method increase age of crop by 10 turns
	 */
	public void fertilize() {
		age+= 10;
	}

	@Override
	public boolean sowable() {
		return false;
	}

	@Override
	public boolean fertilisable() {
		return !isRipe();
	}

	@Override
	public boolean harvestable() {
		return isRipe();
	}
}
