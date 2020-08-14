package game;

import edu.monash.fit2099.engine.Location;
import zombie.Zombie;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Exit;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Item;

/**
 * Special human ability to rise from the dead
 * @author aahdu
 */
public class RiseFromDead {
	
	/**
	 * Corpse is reincarnated as Zombie at same location if corpse is on the ground.
	 * If corpse is in an actors inventory, reincarnation occurs at a free adjacent location.
	 * 
	 * @param location 	location of the corpse
	 * @param name 		of the human of corpse
	 * @return string 	name of actor/corpse
	 */
	public void execute(Location location, String name) {
		int x = location.x();
		int y = location.y();
		GameMap map = location.map();
			
		for (Item item : location.getItems()) {
			if (item instanceof Corpse) {
				if (location.canActorEnter(new Zombie(name, map))) {
					// If corpse on ground and a zombie can spawn there
					location.removeItem(item);  // remove corpse
					map.at(x, y).addActor(new Zombie(name, map));  // Add zombie to map
					System.out.println(name + " has risen from the dead!");
					return;
				} else {
					// corpse is on ground, but there is something where it is laying
					for (Exit exit : location.getExits()) {
						Location destination = exit.getDestination();
						if (destination.canActorEnter(new Zombie(name, map))) {
							location.removeItem(item);
							map.at(destination.x(), destination.y()).addActor(new Zombie(name, map));
							System.out.println(name + " has risen from the dead!");
							return;
						}
					}
					// corpse can't rise at any exit
					((Corpse) item).incrementReincarnationAge();
					System.out.println(name + " will rise shortly...");
					return;
				}
			}
		}
		
		// Corpse is in an inventory
		Actor actor = location.getActor();
		for (Item item : actor.getInventory()) {
			if (item instanceof Corpse) {
				for (Exit exit : location.getExits()) {
					Location destination = exit.getDestination();
					if (destination.canActorEnter(new Zombie(name, map))) {
						actor.removeItemFromInventory(item);
						map.at(destination.x(), destination.y()).addActor(new Zombie(name, map));
						System.out.println(name + " has risen from the dead!");
						return;
					}
				}
			}
			// Corpse in an inventory and can't spawn anywhere
			((Corpse) item).incrementReincarnationAge();
			System.out.println(name + " will rise shortly...");
			return;
		}
	}
}
