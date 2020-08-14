package zombie;
/**
 * Defines capability of zombie limbs and weapons
 * @author aahdu
 */
public enum ZombieWeaponCapability {
	MACE ("Zombie Mace", 'm', 40, "maces", null),
	CLUB ("Zombie Club", 'n', 30, "clubs", null),
	ARM ("Zombie Arm", 'A', 20, "slaps", ZombieWeaponCapability.CLUB),
	LEG ("Zombie Leg", 'L', 25, "kicks", ZombieWeaponCapability.MACE);
	
	private final String name;
	private final char displayChar;
	private final int damage;
	private final String verb;
	private final ZombieWeaponCapability potential;
	
	/**
	 * Constructor for ZombieWeapons
	 * @param name name of ZombieWeapon
	 * @param displayChar display char for ZombieWeapon
	 * @param damage damage of the weapon
	 * @param verb display verb for the weapon
	 */
	ZombieWeaponCapability(String name, char displayChar, int damage, String verb, ZombieWeaponCapability potential) {
		this.name = name;
		this.displayChar = displayChar;
		this.damage = damage;
		this.verb = verb;
		this.potential = potential;
	}
	
	/**
	 * gets the name of the ZombieWeapon
	 * @return name
	 */
	public String getName() {
		return this.name;
	}
	
	/**
	 * gets the display char of the ZombieWeapon
	 * @return displayChar
	 */
	public char getDisplayChar() {
		return this.displayChar;
	}
	
	/**
	 * gets the damage of the ZombieWeapon
	 * @return damage
	 */
	public int getDamage() {
		return this.damage;
	}
	
	/**
	 * gets the verb of the ZombieWeapon
	 * @return verb
	 */
	public String getVerb() {
		return this.verb;
	}
	
	/**
	 * gets the verb of the ZombieWeapon
	 * @return verb
	 */
	public ZombieWeaponCapability getPotential() {
		return this.potential;
	}
}