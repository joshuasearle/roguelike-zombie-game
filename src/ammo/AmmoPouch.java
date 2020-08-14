package ammo;
import java.util.HashMap;

/**
 * Ammo storage device for actors
 * @author Josh
 *
 */
public class AmmoPouch {
	/**
	 * The amount of each ammo in the pouch
	 */
	private HashMap<AmmoType, Integer> ammoCounts = new HashMap<AmmoType, Integer>();
	
	/**
	 * Constructor for AmmoPouch
	 */
	public AmmoPouch() {
		return;
	}
	
	/**
	 * Inserts a certain amount of a certain type of ammo into the pouch
	 * @param ammoType
	 * @param amount
	 */
	public void insertAmmo(AmmoType ammoType, int amount) {
		ammoCounts.put(ammoType, ammoOfType(ammoType) + amount);
	}

	/**
	 * Gets the amount of a certain type of ammo in the pouch
	 * @param ammoType type of ammo
	 * @return amount of ammo of the type
	 */
	public int ammoOfType(AmmoType ammoType) {
		return ammoCounts.containsKey(ammoType) ? ammoCounts.get(ammoType) : 0;
	}
		
	/**
	 * Takes a singular bullet of a type
	 * throws an exception if there is no ammo of the specified type
	 * @param ammoType the type of ammo
	 */
	public void takeBullet(AmmoType ammoType) {
		if (ammoOfType(ammoType) == 0) {
			throw new IllegalStateException("ammo of type doesn't exist in pouch");
		}
		
		if (ammoOfType(ammoType) == 1) {
			ammoCounts.remove(ammoType);
		} else {
			ammoCounts.put(ammoType, ammoOfType(ammoType) - 1);
		}
	}
}
