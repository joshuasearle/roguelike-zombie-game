package zombie;

import edu.monash.fit2099.engine.IntrinsicWeapon;

/**
 * Class that represents the statistics for a bite
 * @author Josh
 *
 */
public class BiteWeapon extends IntrinsicWeapon {
	/**
	 * Stores the amount that a successful bite will heal.
	 * Must be positive or zero.
	 */
	private int healAmount;
	/**
	 * The percentage chance of a bite hitting.
	 * Must be between zero and 100, inclusive.
	 * Stored as int to avoid dealing with floating point numbers.
	 */
	private int hitChance;
	
	/**
	 * Constructor for BiteWeapon
	 * @param damage the damage the bite does
	 * @param verb the verb displayed when a bite hits
	 * @param healAmount the amount a bite heals the attacker
	 * @param hitChance the chance a bite will hit
	 */
	public BiteWeapon(int damage, String verb, int healAmount, int hitChance) {
		super(damage, verb);
		validateHealAmount(healAmount);
		this.healAmount = healAmount;
		validateHitChance(hitChance);
		this.hitChance = hitChance;
	}
	
	/**
	 * gets the healAmount for the bite
	 * @return
	 */
	public int getHealAmount() {
		return this.healAmount;
	}
	
	/**
	 * gets the hitChance for the bite
	 * @return
	 */
	public int getHitChance() {
		return this.hitChance;
	}
	
	/**
	 * Validates the healAmount int
	 * Throws an exception if healAMount is negative
	 * @param healAmount the healAmount trying to be set
	 */
	private static void validateHealAmount(int healAmount) {
		if (healAmount < 0) {throw new IllegalArgumentException("healAmount must be positive");}
	}
	
	/**
	 * Validates the hitChance int
	 * Throws an exception if hitChance can't be a persentage
	 * @param hitChance the hitChance trying to be set
	 */
	private static void validateHitChance(int hitChance) {
		if (hitChance < 0 || hitChance > 100) {throw new IllegalArgumentException("hitChance must be in range [0, 100]");}
	}

}
