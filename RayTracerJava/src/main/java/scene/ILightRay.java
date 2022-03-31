package scene;

import util.Dir3D;
import util.Point3D;

public interface ILightRay {

	/**
	 * @param parameter
	 * @return {@link Point3D}
	 */
	Point3D getPoint(double parameter);

	/**
	 * @return {@link Point3D}
	 */
	Point3D getBasis();

	/**
	 * @return {@link Dir3D}
	 */
	Dir3D getDirection();

}