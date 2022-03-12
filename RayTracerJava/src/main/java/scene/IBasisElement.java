package scene;

import util.Color;

/**
 * @author johan
 *
 */
public interface IBasisElement {

	/**
	 * @return {@link Color}
	 */
	Color getAmbientLight();

	/**
	 * @param ambientLight
	 */
	void setAmbientLight(Color ambientLight);

}
