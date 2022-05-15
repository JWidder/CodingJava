package scene;

import util.Color;
import util.Point3D;

/**
 * @author Johannes Widder
 *
 */
public class SpotLight implements ILight {
	private
		Color colorLight;
		Point3D posLight;
	
	/**
	 * @param _intensity
	 */
	public SpotLight (Point3D inPosLight, Color inColorLight)
	{
		this.posLight=inPosLight;
		this.colorLight = inColorLight;
	}

	/**
	 * @return double
	 */
	@Override
	public Color getColorLight() {
		return this.colorLight;
	}

	@Override
	public Point3D getLightPos() {
		return this.posLight;
	}
}
