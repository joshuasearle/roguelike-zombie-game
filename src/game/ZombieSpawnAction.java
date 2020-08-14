package game;

import java.util.Random;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import zombie.Zombie;

/**
 * Special action for spawning random zombies
 * @author aahdu
 * */
public class ZombieSpawnAction extends Action {
	
	/**
	 * Constructor
	 */
	public ZombieSpawnAction() {}
	
	/**
	 * Execute ZombieSpawnActon.
	 * Spawn 5 zombies at random locations across current GameMap
	 * @return String
	 */
	@Override
	public String execute(Actor actor, GameMap map) {
		
		if (actor.canVanish()) {		
			//list of potential new zombie names
			String[] zombies = {"Zaron", "Zudley", "Zosh", "Zearle", "Zoey",
					"Zoe", "Zachary", "Zion", "Zane","Zander", "Zara", "Zuri",
					"Zaan", "Zachariah", "Zamir","Zakai", "Zaire", "Zavier",
					"Zayden", "Zeandre", "Zeb", "Zeki"};
			
			//spawn zombies in random locations at current map
			Random rand = new Random();
			int newZombieCounter = 0;
			final int zombiesToSpawn = 5;	
			while (newZombieCounter < zombiesToSpawn) {
				
				String name = zombies[rand.nextInt(zombies.length)];	// pick random name from list
				while (true) {
					int x = rand.nextInt(map.getXRange().max() + 1);	// gen. random coordintaes
					int y = rand.nextInt(map.getYRange().max() + 1);
		
					if (map.at(x, y).canActorEnter(actor)) {			// if actor can enter random location, add zombie
						map.at(x,  y).addActor(new Zombie(name, map));
						newZombieCounter++;								// increment no. of zombies created
						break;
					}
				}
			}
			// chant
			return "Chant cHant chAnt chaNt chanT chant... - " + actor + ". " + actor + " spawned 5 new Zombies!";
		}
		return null;
	}
	
	/**
	 * Not applicable, player cannot select to spawn zombies
	 */
	@Override
	public String menuDescription(Actor actor) {
		return  null;
	}
}