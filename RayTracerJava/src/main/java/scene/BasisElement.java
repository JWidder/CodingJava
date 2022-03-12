package scene;

import util.Color;

/**
 * @author Johannes Widder
 *
 */
public class BasisElement implements IBasisElement{
	static Color ambientLight;

	/**
	 * 
	 */
	public BasisElement() {
		return;
	}
	
	@Override
	public Color getAmbientLight() {
		return BasisElement.ambientLight;
	}
	@Override
	public void setAmbientLight(Color ambientLight) {
		BasisElement.ambientLight = ambientLight;
	}
}
