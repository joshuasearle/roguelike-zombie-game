package rangedWeapons;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import ammo.AmmoPouch;
import ammo.AmmoType;
import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Display;
import edu.monash.fit2099.engine.DropItemAction;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Menu;
import edu.monash.fit2099.engine.PickUpItemAction;
import edu.monash.fit2099.engine.Weapon;
import edu.monash.fit2099.engine.WeaponItem;

public abstract class RangedWeapon extends WeaponItem {
	
	/**
	 * base hitChance for a ranged weapon
	 */
	protected int hitChance;
	
	/**
	 * the type of ammo the weapon uses
	 */
	protected AmmoType ammoType;
	
	/**
	 * the ammo pouch the weapon takes from
	 */
	protected AmmoPouch ammoPouch;
	
	/**
	 * the range of the weapon
	 */
	private int range;
	
	/**
	 * Random number generator
	 */
	private Random rand;

	/**
	 * Constructor for the ranged weapon
	 * @param name name of weapon
	 * @param displayChar character used to display weapon on the ground
	 * @param damage base damage of the weapon
	 * @param hitChance chance that an attack with the weapon will land
	 * @param ammoType type of ammo the weapon uses
	 * @param range range of the weapon
	 * @param verb verb used by the weapon
	 */
	public RangedWeapon(String name, char displayChar, int damage, int hitChance, AmmoType ammoType, int range, String verb) {
		super(name, displayChar, damage, verb);
		
		if (damage < 0) {
			throw new IllegalArgumentException("damage must be poitive");
		}
		if (0 > hitChance ||  100 < hitChance) {
			throw new IllegalArgumentException("hitChance must be in range [0, 100]");
		}
		
		if (1 > range) {
			throw new IllegalArgumentException("range must be positive and non-zero");
		}
		
		this.hitChance = hitChance;
		this.ammoType = ammoType;
		this.range = range;
		allowableActions.add(new UseWeaponAction(this));
	}
	
	/**
	 * Sets the ammo pouch used by the weapon
	 * @param ammoPouch
	 */
	public void setAmmoPouch(AmmoPouch ammoPouch) {
		this.ammoPouch = ammoPouch;
	}
	
	/**
	 * Returns the specialized pick up ranged weapon action
	 */
	@Override
	public PickUpItemAction getPickUpAction() {
		return new PickUpRangedWeaponAction(this);
	}
	
	/**
	 * Queries the actor for an action if they have a menu, else it picks a random action
	 * @param actor actor doing the action
	 * @param actions actions the actor can choose from
	 * @return action chosen
	 */
	protected Action pickWeaponAction(Actor actor, Actions actions) {
		if (actor.getMenu() == null) {
			return actions.get(rand.nextInt(actions.size()));
		}
		return actor.getMenu().showMenu(actor, actions, new Display());
	}
	
	/**
	 * range of the weapon
	 * @return
	 */
	public int range() {
		return range;
	}

	/**
	 * get hit chance of the weapon
	 * @return weapon hit chance
	 */
	public int getHitChance() {
		return hitChance;
	}
	
	/**
	 * Returns the specialized drop ranged weapon action
	 */
	@Override
	public DropItemAction getDropAction() {
		return new DropRangedWeaponAction(this);
	}
	
	/**
	 * takes a bullet from its ammo pouch of the type to weapon uses
	 */
	public void takeBullet() {
		ammoPouch.takeBullet(ammoType);
	}
	
	/**
	 * uses the weapon
	 * @param actor actor using the weapon
	 * @param map map the use is taking place on
	 * @return result from the action
	 */
	protected abstract String useWeapon(Actor actor, GameMap map);
	
	/**
	 * Ranged weapons are not eatable
	 */
	@Override
	public boolean eatable() {
		return false;
	}
	
	/**
	 * Ranged weapons are not cures
	 */
	@Override
	public boolean isCure() {
		return false;
	}
	
	/**
	 * Ranged weapons are not drivable
	 */
	@Override
	public boolean drivable() {
		return false;
	}
	
	/**
	 * Returns a UseWeaponAction if and only if there is an ammo pouch connected and the ammo pouch has ammo of the correct type
	 */
	@Override
	public List<Action> getAllowableActions() {
		if (ammoPouch == null || ammoPouch.ammoOfType(ammoType) == 0) {
			return new ArrayList<Action>();
		}
		return super.getAllowableActions();
	}
}

