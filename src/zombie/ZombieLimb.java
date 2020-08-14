package zombie;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.WeaponItem;
import game.CraftWeaponAction;

/**
 * Class representing the ZombieLimb
 * @author aahdu
 * 
 */
public class ZombieLimb extends WeaponItem {
	
	/**
	 * The weapon the current limb can potentially be crafted into
	 */
	private ZombieWeaponCapability potential;
	
	
	/**
	 * Constructor creates a Zombie Limb
	 * 
	 * @param name 			the weapons display name
	 * @param displayChar 	character that will represent the ZombieLimb in the map 
	 * @param damage 		amount of damage this weapon does
	 * @param verb 			verb to use for this weapon
	 * @param limb 			arm, leg, club or mace
	 */
	public ZombieLimb(ZombieWeaponCapability limb) {
		super(limb.getName(), limb.getDisplayChar(), limb.getDamage(), limb.getVerb());
		this.potential = limb.getPotential();
		
		if (limb.getPotential() != null) {
			Action craftWeapon = new CraftWeaponAction(this);
			this.allowableActions.add(craftWeapon);
		}
	}
	
	/**
	 * What can this weapon be crafted into
	 * @return	potential 
	 */
	public ZombieWeaponCapability getWeaponPotential() {
		return this.potential;
	}

	@Override
	public boolean eatable() {
		return false;
	}

	@Override
	public void disrupt() {
		return;
	}

	@Override
	public boolean drivable() {
		return false;
	}
	
	@Override 
	public boolean isCure() {
		return false;
	}
}