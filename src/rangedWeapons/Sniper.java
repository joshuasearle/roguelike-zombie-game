package rangedWeapons;

import ammo.AmmoType;

/**
 * class for sniper rifles
 * @author Josh
 *
 */
public class Sniper extends ScopedWeapon {
	
	/**
	 * Constructor for snipers
	 */
	public Sniper() {
		super("Sniper Rifle", '}', 40, 75, AmmoType.SNIPER_AMMO, Integer.MAX_VALUE, "snipes");
		
	}
}
