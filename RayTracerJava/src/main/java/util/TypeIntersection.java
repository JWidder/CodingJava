package util;

/**
 * @author Johannes Widder
 *
 */
public enum TypeIntersection {
	/**
	 * Der Lichtstrahl berührt das Objekt ohne einzudringen am Rand. 
	 */
	TOUCH,
	/**
	 * Der Lichtstrahl tritt in das Objekt ein und wieder aus.  
	 */
	INTERSECTION,
	BEHIND_INTERSECTION,
	BEHIND_TOUCH,
	INNER_INTERSECTION,
	MISSES,
	START_TOUCH,
	START_INTERSECT
}

