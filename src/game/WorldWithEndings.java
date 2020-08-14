package game;

import java.util.ArrayList;
import java.util.Random;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Display;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.World;

/**
 * Class for adding endings to the world class
 * @author Josh
 *
 */
public class WorldWithEndings extends World {

	/**
	 * String that will be shown when the game ends
	 */
	private String endGameMessage = "Game Over. You have died.";

	/**
	 * Has Mambo Marie been killed. Initialised as false
	 */
	private static boolean mamboMarieKilled = false;

	/**
	 * Is Mambo Marie currently Vanished.
	 */
	private static boolean mamboMarieVanished;
	
	/**
	 * Returns true if there are still zombies, false if there are none
	 * @return boolean zombiesLeft
	 */
	private boolean zombiesLeft() {
		for (Actor actor : actorLocations) {
			if (actor.isZombie()) {
				return false;
			}
		}
		return true;
	}
	
	/**
	 * Returns true if there are no humans left, false if there are none
	 * @return boolean humansLeft
	 */
	private boolean humansLeft() {
		for (Actor actor : actorLocations) {
			if (!actor.isZombie()) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Constructor for world with endings
	 * @param display Display used by the world
	 */
	public WorldWithEndings(Display display) {
		super(display);
	}
	
	/**
	 * Processes actor's turn but also checks for game end conditions
	 */
	@Override
	protected void processActorTurn(Actor actor) {
		if (!stillRunning()) {
			return;
		}
		super.processActorTurn(actor);

		if (player.hasQuit()) {
			actorLocations.remove(player);
			endGameMessage = "Game Over. You have quit.";
			System.out.println("-----");
		} else if (!zombiesLeft() && getMamboMarieKilled()) {
			actorLocations.remove(player);
			endGameMessage = "Mambo Marie and all of the zombies are dead. You Win!!!";
		} else if (!humansLeft()) {
			actorLocations.remove(player);
			endGameMessage = "Game Over. All the humans are dead.";
		}
		
		// if Mambo Marie hasn't been killed & has vanished, 5% chance she'll appear along edge of map
		if (!mamboMarieKilled) {
			if (mamboMarieVanished) {
				Random rand = new Random();
				if (rand.nextInt(101) <= 5) {							// 5% chance
					ArrayList<GameMap> map = this.gameMaps;				// select random map from array list of game maps
					int length = map.size();
					GameMap mmMap = map.get((int) Math.floor(Math.random() * length));
					System.out.println(new AppearMamboMarie().execute(mmMap));			// execute AppearMamboMarie at selected GameMap
				}
			}
		}
		return;
	}
	
	/**
	 * Returns the game end message
	 */
	@Override
	protected String endGameMessage() {
		return endGameMessage;
	}
	
	/**
	 * Records if Mambo Marie is present on a map
	 * @param status	true if Mambo Marie is present
	 */
	public static void setMamboMarieVanished(Boolean status) {
		mamboMarieVanished = status;
	}
	
	/**
	 * Finds if Mambo Marie has been killed
	 * @return	boolean mamboMarieKilled
	 */
	public static boolean getMamboMarieKilled() {
		return mamboMarieKilled;
	}
	
	/**
	 * Records that Mambo Marie has been killed
	 */
	public static void killMamboMarie() {
		mamboMarieKilled = true;
	}
}
