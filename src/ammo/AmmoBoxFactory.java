package ammo;

import java.util.List;
import java.util.Random;

/**
 * Class for creating ammo boxes with a random no. of bullets of each type
 * @author Josh
 *
 */
public class AmmoBoxFactory {
	
	/*
	 * Random no. generator
	 */
	private Random rand = new Random();
	
	/**
	 * The ammo types the ammo box will have
	 */
	private List<AmmoType> ammoTypes;
	
	/**
	 * Average no. of bullets of each type in the ammo box
	 */
	private int avgAmmoCount;
	
	/**
	 * Constructor
	 * @param ammoTypes types of ammo to be added to the box
	 * @param avgWeightAmmo the average no. of bullets of each type in the ammo box
	 */
	public AmmoBoxFactory(List<AmmoType> ammoTypes, int avgWeightAmmo) {
		this.ammoTypes = ammoTypes;
		
		if (avgWeightAmmo < 0) {
			throw new IllegalArgumentException("avgWeightAmmo must be positive");
		}
		
		this.avgAmmoCount = avgWeightAmmo;
	}
	
	/**
	 * Gets a randomized ammo box
	 * @return randomized ammo box
	 */
	public AmmoBox getRandomAmmoBox() {
		AmmoBox ammoBox = new AmmoBox();
		for (AmmoType ammoType : ammoTypes) {
			ammoBox.addAmmo(ammoType, avgAmmoCount + rand.nextInt(7) - 3);
		}
		return ammoBox;
	}
}
