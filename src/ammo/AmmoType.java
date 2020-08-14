package ammo;

/**
 * Stores the different types of ammo
 * @author Josh
 *
 */
public enum AmmoType {
	SHOTGUN_AMMO (1, "Shotgun ammo"),
	SNIPER_AMMO (2, "Sniper Ammo");
	
	/**
	 * The weight of an individual shot of ammo
	 */
	public final int weight;
	
	/**
	 * The name of the ammo
	 */
	public final String name;
	
	/**
	 * Constructor for AmmoType
	 * @param weight weight of the ammo
	 * @param name name of the ammo
	 */
	AmmoType(int weight, String name) {
		this.weight = weight;
		this.name = name;
	}
}
