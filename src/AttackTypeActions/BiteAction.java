package AttackTypeActions;

import edu.monash.fit2099.engine.Actor;

import edu.monash.fit2099.engine.GameMap;
import zombie.BiteWeapon;

/**
 * Action for biting others
 * @author Josh
 *
 */
public class BiteAction extends AttackTypeAction {

	/**
	 * Constructor for BiteAction
	 * @param target the actor being bitten
	 */
	public BiteAction(Actor target) {
		super(target);
	}

	@Override
	public String execute(Actor actor, GameMap map) {
		BiteWeapon bite = actor.getBiteWeapon();

		if (bite == null) {
			// If no bite, can't bite
			return null;
		}

		if (rand.nextInt(101) > bite.getHitChance()) {
			return actor + " misses " + target + ".";
		}

		String result = getHitMessage(actor, bite);
		result += hurtTarget(map, bite.damage());
		result += healActor(actor, bite);

		/**
		 * Infection from zombie bite
		 * If Human isn't already infected, theres a 10% chance they'll become infected
		 */
		if (target.isConscious()) {
			if (target.canRiseFromDead()) { 
				if (!target.getVaccinatedStatus()) {
					if (!target.isInfected()){
						if (rand.nextInt(101) < 10) {
							target.infect();
							result += System.lineSeparator() + actor + " has infected " + target + "!";
					}
				}
			}
		}
		}
		return result;
	}

	/**
	 * heals an actor based on the bite
	 * @param actor actor being healed
	 * @param bite bite being performed
	 * @return heal message
	 */
	private String healActor(Actor actor, BiteWeapon bite) {
		int healAmount = bite.getHealAmount();
		String verb = bite.verb();
		actor.heal(healAmount);
		return System.lineSeparator() + actor + " was healed " + healAmount + " points from their successful attack on " + target + ".";
	}

	@Override
	public String menuDescription(Actor actor) {
		return actor + " bit " + target;
	}
}