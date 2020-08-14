package game;

import java.util.Random; 

/**
 * Class allows behaviours for non-players to be shuffled so they execute random behaviour
 * @author aahdu
 *
 */
public class ShuffleBehaviours 
{ 

	/**
	 * Method randomises the order of an array of type Behaviour
	 * @param arrarray to be randomised
	 * @param length	length of the array
	 */
	public static void randomise(Behaviour array[], int length) 
	{ 
		Random rand = new Random(); 
		for (int i = length-1; i > 0; i--) { 
			int j = rand.nextInt(i+1); 
			Behaviour temp = array[i]; 
			array[i] = array[j]; 
			array[j] = temp; 
		} 
	} }
