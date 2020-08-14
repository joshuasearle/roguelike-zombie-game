package game;

import java.util.ArrayList;
import edu.monash.fit2099.engine.Location;
import java.util.Random;
import ammo.AmmoBoxFactory;
import ammo.AmmoType;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Display;
import edu.monash.fit2099.engine.FancyGroundFactory;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Item;
import foodAndFarming.Dirt;
import foodAndFarming.Farmer;
import rangedWeapons.Shotgun;
import rangedWeapons.Sniper;
import zombie.Zombie;
import zombie.ZombieDog;

public class Application {
	
	/**
	 * Town level game map
	 */
	private static GameMap townMap;
	
	/**
	 * Main level game map
	 */
	private static GameMap mainMap;
	
	/**
	 * Vehicle location in Town level
	 */
	private static Location vehicleLocationTown;
	
	/**
	 * Vehicle location in Main level
	 */
	private static Location vehicleLocationMain;

	public static void main(String[] args) {
		WorldWithEndings world = new WorldWithEndings(new Display());
		FancyGroundFactory groundFactory = new FancyGroundFactory(new Dirt(), new Fence(), new Tree());		
	
		// create Main & Town level
		mainMap = new GameMapExtension(groundFactory, Levels.MAIN);
		townMap = new GameMapExtension(groundFactory, Levels.TOWN);
		world.addGameMap(mainMap);
		world.addGameMap(townMap);
		
		// Add player in Main level
		Actor player = new Player("Player", '@', 10000);		// hitPoints set high for testing purposes
		world.addPlayer(player, mainMap.at(42, 15));
		
		// Add MamboMarie at random location in Main level
		mainMap.at((int) Math.floor(Math.random()*mainMap.getXRange().max()), (int) Math.floor(Math.random()*mainMap.getYRange().max())).addActor(new MamboMarie());
		WorldWithEndings.setMamboMarieVanished(false);
		
		// Add items to main level
		// All in one location for easy testing
		mainMap.at(42, 15).addItem(new Plank());	//plank
		mainMap.at(42, 15).addItem(new Vaccine());	//vaccine
		vehicleLocationMain = mainMap.at(42, 15);
		vehicleLocationMain.addItem(new Vehicle());	//vehicle
		
		// x & y coordinates
		int x, y;
		// new random number generator
		Random rand = new Random();
		
		//Randomly place humans & farmers across Main and Town map
		//
		// use random boolean generator to initialise in Main level or Town level
		// use random boolean generator to initialise as human or farmer
		//
		String[] humansTown = {"Theodore", "Thomas", "Tucker", "Timothy", "Tobias",
				"Tanner", "Titus", "Travis", "Timmy", "Thinker", "Carlton", "May",
				"Vicente", "Andrea", "Wendy", "Elina", "Winter", "Clem", "Jacob", "Jaquelyn", "Fred"};
		
		for (String name : humansTown) {
			GameMap map;
			if (rand.nextBoolean()) {						// main or town level
				map = mainMap;
			}
			else {
				map = townMap;
			}
			Actor newHuman = new Human(name);
			do {											// gen. random coordinates
				x = (int) Math.floor(Math.random() * map.getXRange().max());
				y = (int) Math.floor(Math.random() * map.getYRange().max());
			}
			while (!map.at(x, y).canActorEnter(newHuman));	// can actor enter	
			if (rand.nextBoolean()) {						// human or farmer
				map.at(x, y).addActor(newHuman);	
			}
			else {
				map.at(x, y).addActor(new Farmer("Farmer " + name));
			}
		}
		
		//Randomly place zombie & zombie dogs across Main and Town map
		//
		// use random boolean generator to initialise in Main level or Town level
		// use random boolean generator to initialise as zombie or zombie dog
		//
		String[] zombies = {"Z1", "Z2", "Z3", "Z4", "Z5", "Z6", "Z7", "Z8", "Z9",
				"Z10", "Groan", "Boo", "Uuuurgh", "Mortalis", "Gaaaah", "Z11"};
		for (String name : zombies) {
			GameMap map;
			if (rand.nextBoolean()) {							// main or town level
				map = mainMap;
			}
			else {
				map = townMap;
			}
			Actor newZombie = new Zombie(name, map);
			do {												// gen. random coordinates
				x = (int) Math.floor(Math.random() * map.getXRange().max());
				y = (int) Math.floor(Math.random() * map.getYRange().max());
			} 
			while (!map.at(x, y).canActorEnter(newZombie));		// can actor enter
				if (rand.nextBoolean()) {						// zombie or zombie dog
					map.at(x, y).addActor(newZombie);	
				}
				else {
					map.at(x, y).addActor(new ZombieDog("Dog " + name));
				}			
		}

		// used to test if items can be placed
		Actor tester = new Zombie("tester", townMap);
		
		// randomly place items in Town level
		Item[] itemsTown = {new Vaccine(), new Plank(), new Sniper(), new Shotgun(), new Vehicle()};
		for (Item item :itemsTown) {
			do {
				x = (int) Math.floor(Math.random() * townMap.getXRange().max());
				y = (int) Math.floor(Math.random() * townMap.getYRange().max());
			} 
			while (!townMap.at(x, y).canActorEnter(tester));
			townMap.at(x, y).addItem(item);
			
			//record where vehicle is located
			if (item.drivable()) {
				vehicleLocationTown = townMap.at(x, y);
			}	
		}
		
		// randomly place ammo box in town level
		ArrayList<AmmoType> ammoTypesTown = new ArrayList<AmmoType>();
		ammoTypesTown.add(AmmoType.SHOTGUN_AMMO);
		ammoTypesTown.add(AmmoType.SNIPER_AMMO);
		AmmoBoxFactory ammoBoxFactoryTOWN = new AmmoBoxFactory(ammoTypesTown, 10);
		do {
			x = (int) Math.floor(Math.random() * townMap.getXRange().max());
			y = (int) Math.floor(Math.random() * townMap.getYRange().max());
		} 
		while (!townMap.at(x, y).canActorEnter(tester));
		townMap.at(x, y).addItem(ammoBoxFactoryTOWN.getRandomAmmoBox());
		
		// run world
		world.run();
	}
	
	/**
	 * Retrieve the Main level 
	 * @return	GameMap mainMap
	 */
	public static GameMap getMainMap() {
		return mainMap;
	}
	
	/**
	 * Find the location of the vehicle in the Town level
	 * @return	Location vehicleLocationTown
	 */
	public static Location getVehicleLocationTown() {
		return vehicleLocationTown;
	}
	
	/**
	 * Find the location of the vehicle in the Main level
	 * @return	Location vehicleLocationMain
	 */
	public static Location getVehicleLocationMain() {
		return vehicleLocationMain;
	}
}