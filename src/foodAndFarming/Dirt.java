package foodAndFarming;

import edu.monash.fit2099.engine.Ground;

/**
 * A class that represents bare dirt.
 */
public class Dirt extends Ground {

	public Dirt() {
		super('.');
	}

	@Override
	public boolean sowable() {
		return true;
	}

	@Override
	public boolean fertilisable() {
		return false;
	}

	@Override
	public boolean harvestable() {
		return false;
	}
}
