package game;

import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.GroundFactory;

/**
 * Class responsible for the construction of game maps
 * @author aahdu
 */
public class GameMapExtension extends GameMap {
	
	/**
	 * Constructor. 
	 * Constructs game maps defined in Levels class
	 * @param groundFactory	Factory to create Ground objects
	 * @param level			Level that can be crafted
	 */
	public GameMapExtension(GroundFactory groundFactory, Levels level) {
		super(groundFactory, level.getMap());
	}
}