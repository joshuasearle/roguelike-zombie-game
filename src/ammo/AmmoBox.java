package ammo;
import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Item;
import edu.monash.fit2099.engine.Location;
import looting.LootAction;
import looting.LootableBox;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

/**
 * Item that actors will be able to take ammo from
 * @author Josh
 *
 */
public class AmmoBox extends Item implements LootableBox {
	
	/**
	 * The amount of each ammo
	 */
	private HashMap<AmmoType, Integer> ammoCounts = new HashMap<AmmoType, Integer>();
	
	/**
	 * Random number generator
	 */
	private Random rand = new Random();
	
	/**
	 * Constructor for ammoBox
	 * name is Ammo Box
	 * display char is v
	 * AmmoBoxes are not portable
	 */
	AmmoBox() { // package visibility so that AmmoBoxFactory can access it's constructor, but nothing outside of the package
		super("Ammo Box", 'a', false);
	}
	
	/**
	 * Adds ammo to the box
	 * @param ammoType type of ammo adding
	 * @param amount amount adding
	 */
	void addAmmo(AmmoType ammoType, int amount) {
		int currentAmmoCount = ammoCounts.containsKey(ammoType) ? ammoCounts.get(ammoType) : 0;
		ammoCounts.put(ammoType, currentAmmoCount + amount);
	}
	
	/**
	 * Takes all ammo from the box of a specific type
	 * @param ammoType type taking
	 * @param amount amount taking
	 * Can't take ammo of type not in the box
	 */
	private int takeAmmo(AmmoType ammoType) {
		if (!ammoCounts.containsKey(ammoType)) {
			throw new IllegalStateException("ammo of this type is not in the box");
		}
		int currentAmmoCount = ammoCounts.get(ammoType);
		ammoCounts.put(ammoType, 0);
		return currentAmmoCount;
	}
	
	/**
	 * Gets the the list of available actions
	 * The available actions for ammo box are a LootAction if and only if the ammo box contains some ammo
	 */
	@Override
	public List<Action> getAllowableActions() {
		List<Action> allowableActions = new ArrayList<Action>();
		for (int ammoCount : ammoCounts.values()) {
			if (ammoCount != 0) {
				allowableActions.add(new LootAction(this));
				break;
			}
		}
		return allowableActions;
	}
	
	/**
	 * Ammo boxes are not eatable
	 */
	@Override
	public boolean eatable() {
		return false;
	}
	
	/**
	 * Loots the ammo box
	 * Takes all of the ammo, and gives it to the actor
	 */
	@Override
	public String loot(Actor actor) {
		for (AmmoType ammoType : ammoCounts.keySet()) {
			if (ammoCounts.get(ammoType) != 0) {
				actor.giveAmmo(ammoType, takeAmmo(ammoType));
			}
		}
		return actor + " looted ammo from an ammo box";
	}
	
	/**
	 * Ticks the ammo box
	 * There is a random chance every round that ammo is put back into the ammo box
	 */
	@Override
	public void tick(Location currentLocation) {
		for (AmmoType ammoType : ammoCounts.keySet()) {
			if (rand.nextBoolean() && rand.nextBoolean() && rand.nextBoolean() && rand.nextBoolean()) {
				// 6.25% chance each round that one of each type of ammo is added to the box
				ammoCounts.put(ammoType, ammoCounts.get(ammoType) + 1);
			}
		}
	}
	
	/**
	 * Disrupting ammo boxes does nothing
	 */
	@Override
	public void disrupt() {
		return;
	}
	
	/**
	 * Ammo box is not drivable
	 */
	@Override
	public boolean drivable() {
		return false;
	}
	
	/**
	 * Ammo boxes cannot cure
	 */
	@Override
	public boolean isCure() {
		return false;
	}
}
