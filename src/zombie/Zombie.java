package zombie;

import java.util.ArrayList;
import java.util.Random;
import AttackTypeBehaviors.AttackBehaviour;
import AttackTypeBehaviors.AttackTypeBehaviour;
import AttackTypeBehaviors.BiteBehaviour;
import AttackTypeBehaviors.ZombieAttackPicker;
import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Display;
import edu.monash.fit2099.engine.DoNothingAction;
import edu.monash.fit2099.engine.DropItemAction;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.IntrinsicWeapon;
import edu.monash.fit2099.engine.Item;
import edu.monash.fit2099.engine.Location;
import edu.monash.fit2099.engine.MoveActorAction;
import game.Behaviour;
import game.Human;
import game.HuntBehaviour;
import game.PickUpWeaponBehaviour;
import game.WanderBehaviour;
import zombie.ZombieActor;


/**
 * A Zombie.
 * This Zombie is pretty boring.  It needs to be made more interesting.
 * @author ram
 *
 */
public class Zombie extends ZombieActor {
		
	/**
	 * the number of arms the Zombie has
	 */
	private int armCount;
	
	
	/**
	 * the number of arms the Zombie has
	 */
	private int legCount;
	
	/**
	 * GameMap the zombie is on
	 */
	private GameMap map;
	
	/**
	 * Random number generator
	 */
	private Random rand = new Random();
	
	/**
	 * Behaviours that the zombie has. Executed in order
	 */
	private Behaviour[] behaviours = {
			new PickUpWeaponBehaviour(),
			new ZombieAttackPicker(new AttackBehaviour(ZombieCapability.ALIVE), 
					new ArrayList<AttackTypeBehaviour>() {{add(new BiteBehaviour(ZombieCapability.ALIVE));}}, 
					ZombieCapability.ALIVE),
			new HuntBehaviour(Human.class, 10),
			new WanderBehaviour()
	};
	
	/**
	 * possible brains strings
	 */
	private static final String[] brainsStrings = {"BRRrRrRRraAAaAAiIIiIIIiINnNnNnNnNSssSSss",
			"BBRRAAIINNSS", "bRRaIIiNNs", "BBRBRRRARAAAIAIIININNNSNSSS", "brain!...Brain!...BrAiN!...BRAIN!", "BRIAN...Uh...I mean BRAIN"};
	
	/**
	 * Constructor for Zombie.
	 * Initializes the name, the character represent the Zombie,
	 * 		the Zombie's health, the Zombie's team, and the number
	 * 		of arms and legs the Zombie has.
	 * @param name
	 */
	public Zombie(String name, GameMap map) {
		super(name, 'Z', 100, ZombieCapability.UNDEAD);
		this.armCount = 2;
		this.legCount = 2;
		this.map = map;
	}
	
	@Override
	public int getArmCount() {
		return armCount;
	}

	@Override
	public int getLegCount() {
		return legCount;
	}
	
	/**
	 * finds if a Zombie has a weapon in their inventory
	 * @return boolean hasWeapon
	 */
	private boolean hasWeapon() {
		for (Item item : this.getInventory()) {
			if (item.asWeapon() != null) {
				return true;
			}
		}
		return false;
	}
	
	@Override
	public IntrinsicWeapon getIntrinsicWeapon() {
		return new IntrinsicWeapon(10, "punches");
	}
	
	/**
	 * Gets the bite a Zombie will perform
	 * i.e. will deal 20 damage, will display "chomp", heal the Zombie 5 points, and have a 20% chance of hitting
	 * @return
	 */
	public BiteWeapon getBiteWeapon() {
		return new BiteWeapon(20, "chomps", 5, 20);
	}
	
	@Override
	public void hurt(int points) {
		super.hurt(points);
		if (rand.nextInt(101) <= 65) {
			// only knock off a limb 35% of the time
			return;
		}
		if (rand.nextInt(101) <= 65 && armCount + legCount != 0) {
			// only knock off a limb 35% of the time, and only when the zombie has a limb
			knockOffLimb();
		}
	}
	
	/**
	 * Knocks off a zombie arm
	 */
	private void knockOffLimb() {
		boolean loosingArm = isArm();
		Location here = map.locationOf(this);
		if (loosingArm) {
			here.addItem(new ZombieLimb(ZombieWeaponCapability.ARM));
			armCount--;
			if ((armCount == 0 || (armCount == 1 && rand.nextBoolean())) && hasWeapon()) {
				// if a Zombie has zeros arms, always drop weapon
				// If a Zombie has one arm, 50% chance to drop weapon
				for (Item item : getInventory()) {
					if (item.asWeapon() != null) {
						DropItemAction dropWeapon = new DropItemAction(item);
						dropWeapon.execute(this, map);
						break;
					}
				}
			}
		} else {
			// if limb being dropped is a leg
			here.addItem(new ZombieLimb(ZombieWeaponCapability.LEG));
			legCount--;
		}
	}

	/**
	 * picks a remaining limb to remove
	 * @return boolean isArm
	 */
	private boolean isArm() {
		// choose random limb to drop (50% each arm and leg)
		boolean isArm = rand.nextBoolean();
		if (armCount == 0) {
			// if only has leg(s), swap to leg being dropped
			isArm = false;
		} else if (legCount == 0) {
			// if only has arm(s), swap to arm being dropped
			isArm = true;
		}
		return isArm;
	}

	/**
	 * If a Zombie can attack, it will.  If not, it will chase any human within 10 spaces.  
	 * If no humans are close enough it will wander randomly.
	 * 
	 * @param actions list of possible Actions
	 * @param lastAction previous Action, if it was a multiturn action
	 * @param map the map where the current Zombie is
	 * @param display the Display where the Zombie's utterances will be displayed
	 */
	@Override
	protected Action getAction(Actions actions, Action lastAction, GameMap map, Display display) {
		if (rand.nextInt(101) > 90) {
			// 10% of the time, print a random brains strings, with the name of the zombie attached
			System.out.println(brainsStrings[rand.nextInt(brainsStrings.length)] + " - " + name + ".");
		}
		for (Behaviour behaviour : behaviours) {
			Action action = behaviour.getAction(this, map);
			if (action != null) {
				boolean movedLastTurn = lastAction instanceof MoveActorAction;
				boolean isMoveAction = action instanceof MoveActorAction;
				if (!isMoveAction || (legCount == 2 || (legCount == 1 && !movedLastTurn))) {
					// can't move if you have no legs, and if you have one leg you can only move every second turn
					return action;
				}
			}
		}
		return new DoNothingAction();
	}
	
	/**
	 * Zombies are zombies
	 */
	@Override
	public boolean isZombie() {
		return true;
	}
}
