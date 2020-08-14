package game;

/**
 * 4 cardinal directions
 * @author Josh
 *
 */
public enum Direction {
	NORTH ("north", "8", 0, -1),
	EAST ("east", "6", 1, 0),
	SOUTH ("south", "2", 0, 1),
	WEST ("west", "4", -1, 0);
	
	/**
	 * name of the direction
	 */
	public final String name;
	
	/**
	 * preferred hotkey for the direction
	 */
	public final String preferredKey;
	
	/**
	 * x component of the unit vector of the direction
	 */
	public final int dx;
	
	/**
	 * y component of the unit vector of the direction
	 */
	public final int dy;
	
	/**
	 * constructor for Directions
	 * @param name name of direction
	 * @param preferredKey preferred hotkey for direction
	 * @param dx x component of the unit vector of the direction
	 * @param dy y component of the unit vector of the direction
	 */
	Direction(String name, String preferredKey, int dx, int dy) {
		this.name = name;
		this.preferredKey = preferredKey;
		this.dx = dx;
		this.dy = dy;
	}
}
