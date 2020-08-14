package rangedWeapons;

import java.util.ArrayList;
import java.util.List;

import ammo.AmmoType;
import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Location;
import zombie.ZombieCapability;

/**
 * Abstract class for ranged weapons
 * @author Josh
 *
 */
public abstract class ScopedWeapon extends RangedWeapon {
	
	/**
	 * actor being aimed at
	 */
	private Actor target;
	
	/**
	 * the rounds spent aiming at the current target
	 */
	private int roundsAiming;
	
	/**
	 * If the weapon was aimed last round
	 */
	private boolean aimedLastRound;
	
	/**
	 * Constructor for ScopedWeapons
	 * @param name name of the weapon
	 * @param displayChar display char of the weapon
	 * @param damage base damage of the weapon
	 * @param hitChance base bit chance of the weapon
	 * @param ammoType the type of ammo the weapon uses
	 * @param range range of the weapon
	 * @param verb verb the weapon uses
	 */
	public ScopedWeapon(String name, char displayChar, int damage, int hitChance, AmmoType ammoType, int range, String verb) {
		super(name, displayChar, damage, hitChance, ammoType, range, verb);
		roundsAiming = 0;
		target = null;
		aimedLastRound = false;
	}
	
	/**
	 * Searches the game map for all actors. If they are attackable it adds a shoot action and an aim action
	 * The aim action is only added if the target hasn't been aimed at for two rounds.
	 */
	@Override
	public String useWeapon(Actor actor, GameMap map) {
		Actions actions = new Actions();
		for (int x : map.getXRange()) {
			for (int y : map.getYRange()) {
				Actor target = map.getActorAt(map.at(x, y));
				if (target != null && (target.hasCapability(ZombieCapability.UNDEAD) != actor.hasCapability(ZombieCapability.UNDEAD))) {
					// target is not on same team
					actions.add(new ShootScopedWeaponAction(this, target));
					if (!(target == this.target && roundsAiming == 2)) {
						actions.add(new AimWeaponAction(this, target));
					}
				}
			}
		}
		if (actions.size() == 0) {
			return "There was nobody to shoot or aim at";
		}
		return pickWeaponAction(actor, actions).execute(actor, map);
	}
	
	/**
	 * aims the weapon at a target
	 * @param target
	 */
	public void aim(Actor target) {
		aimedLastRound = true;
		if (target == this.target) {
			roundsAiming++;
		} else {
			this.target = target;
			roundsAiming = 1;
		}
	}
	
	/**
	 * If a scoped weapon ticks twice without aiming in between, the target and rounds aimed at target are reset
	 */
	@Override
	public void tick(Location currentLocation, Actor actor) {
		tick(currentLocation);
	}
	
	/**
	 * If a scoped weapon ticks twice without aiming in between, the target and rounds aimed at target are reset
	 */
	@Override
	public void tick(Location currentLocation) {
		if (aimedLastRound) {
			aimedLastRound = false;
		} else {
			roundsAiming = 0;
			target = null;
		}
	}
	
	/**
	 * Gets the damage when shot a specific actor
	 * @param target
	 * @return
	 */
	public int damage(Actor target) {
		if (target != this.target) {
			return super.damage();
		}
		
		switch(roundsAiming) {
		case 0:
			return super.damage();
		case 1:
			return super.damage()*2;
		case 2:
			return Integer.MAX_VALUE;
		default:
			return Integer.MAX_VALUE;
		}
	}
	
	/**
	 * Gets the hit chance when shot at a specific actor
	 * @param target
	 * @return
	 */
	public int hitChance(Actor target) {
		if (target != this.target) {
			return hitChance;
		}
		
		switch(roundsAiming) {
		case 0:
			return hitChance;
		case 1:
			return (int) Math.round(hitChance*1.2);
		case 2:
			return 100;
		default:
			return 100;
		}
	}
	
	/**
	 * Resets the current target and the rounds spent aiming
	 */
	@Override
	public void disrupt() {
		target = null;
		roundsAiming = 0;
	}
}
