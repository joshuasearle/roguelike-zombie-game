package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Item;
import edu.monash.fit2099.engine.Location;
import zombie.ZombieLimb;
import zombie.ZombieWeaponCapability;

/**
 * Special action for crafting weapons 
 * @author aahdu
 */
public class CraftWeaponAction extends Action{
	private ZombieLimb sourceWeapon;
	
	/**
	 * Constructor
	 * @param sourceWeapon what weapon are we crafting from
	 */
	public CraftWeaponAction(ZombieLimb sourceWeapon) {
		
		if (sourceWeapon.getWeaponPotential() == null) {
			throw new IllegalArgumentException("This weapon cannot be crafted into anything.");
		}
		this.sourceWeapon = sourceWeapon;
	}

	@Override
	public String execute(Actor actor, GameMap map) {
		ZombieWeaponCapability potential = sourceWeapon.getWeaponPotential();
		return createZombieWeapon(actor, map, potential);
	}
	
	/**
	 * Method creates a zombie weapon based on current item in inventory
	 * 
	 * @param actor executing action
	 * @param name the weapons display name
	 * @param displayChar character that will represent the ZombieLimb in the map 
	 * @param damage amount of damage this weapon does
	 * @param verb to use for this weapon
	 * @return string actor crafted a weapon
	 */
	private String createZombieWeapon(Actor actor, GameMap map, ZombieWeaponCapability potential) {
		Item zombieLimb = new ZombieLimb(potential);
		
		Location here = map.locationOf(actor);
		boolean onGround = false;
		
		for (Item item : here.getItems()) {
			if (item == sourceWeapon) {
				onGround = true;
				here.removeItem(item);
				break;
			}
		}	
		if (!onGround) {
			actor.removeItemFromInventory(sourceWeapon);
		}

		actor.addItemToInventory(zombieLimb);
		return actor + " crafted a " + potential.getName();
	}

	@Override
	public String menuDescription(Actor actor) {
		return actor + " can craft a " + sourceWeapon.getWeaponPotential().getName();
	}
}