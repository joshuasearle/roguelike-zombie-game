package foodAndFarming;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Ground;


/***
 * Special action allows the fertilization of crops
 * @author aahdu
 *
 */
public class FertilizeAction extends Action {

	/**
	 * crop to be fertilized
	 */
	private Ground crop;

	/**
	 * Constructor.
	 * @param crop	unripe crop to be fertilized
	 */
	public FertilizeAction(Ground crop) {
		if (!crop.fertilisable()) {
			throw new IllegalArgumentException("Can only fertilize fertilisable ground spots");
		}
		this.crop = crop;
	}

	@Override
	public String execute(Actor actor, GameMap map) {
		if (!actor.canFarm()) {
			return null;
		}
		((Crop) crop).fertilize();
		return actor + " fertilized a crop";
	}

	@Override
	public String menuDescription(Actor actor) {
		return actor + " fertilize a crop";
	}

}
