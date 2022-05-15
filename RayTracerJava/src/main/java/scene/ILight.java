package scene;

import util.Color;
import util.Point3D;

public interface ILight {

	/**
	 * @return double
	 */
	Color getColorLight();
	Point3D getLightPos();
}