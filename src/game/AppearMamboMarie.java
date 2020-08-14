package game;

import java.util.Random;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;

/**
 * Class responsible for spawning Mambo Marie 
 * @author aahdu
 */
public class AppearMamboMarie {

	/**
	 * x and y coordinates
	 */
	private int x, y;

	/**
	 * Constructor
	 */
	public AppearMamboMarie() {
	}

	/**
	 * Method spawns Mambo Marie at somewhere random along the edge of the map
	 * @param map	 Gamemap Mambo Marie will spawn at
	 */
	public String execute(GameMap map) {
		
		Random rand = new Random();

		int xMax = map.getXRange().max();
		int xMin = map.getXRange().min();
		int yMax = map.getYRange().max();
		int yMin = map.getYRange().min();
		Actor mamboMarie = new MamboMarie();
		int randomCounter = 0;
		
		int random = rand.nextInt(4);	
		while (randomCounter < 100) {
			if (random == 0) {
				this.x = xMin;						// spawn along left edge
				this.y = rand.nextInt(yMin + 1); 
			}
			else if (random == 1) {	
				this.x = xMax;						// spawn along right edge
				this.y = rand.nextInt(yMin + 1);
			}
			else if (random == 2) {
				this.x = rand.nextInt(xMax + 1);	// spawn along bottom edge
				this.y = yMin;
			}
			else if (random == 3) {
				this.x = rand.nextInt(xMax + 1);	// spawn along top edge
				this.y = yMax;
			}
						
			// create and place Mambo Marie
			if (map.at(this.x, this.y).canActorEnter(mamboMarie)) {
				map.at(this.x, this.y).addActor(mamboMarie);
				WorldWithEndings.setMamboMarieVanished(false);
				return mamboMarie + " has appeared!";
			}
			
			randomCounter++;
		}
		return mamboMarie + " cannot currently appear.";
	}
}