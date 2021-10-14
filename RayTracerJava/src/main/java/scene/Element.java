package scene;

import util.Color;
import util.Point3D;
import util.Vector3D;

public interface Element {
	/**
	 * 
	 * @param inRay 
	 * @return parameter on the Ray.
	 *  return MaxDouble if there is no intersection. 
	 */
	double intersectRay(Vector3D inRay);
	Vector3D getNormal(Point3D inPoint);
	Color getValueColor();
	public double getValueReflection();
}
