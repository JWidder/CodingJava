package scene;

import util.Color;
import util.Dir3D;
import util.Intersection;
import util.Point3D;
import util.ReflectedColor;
import util.Vector3D;

/**
 * @author johan
 *
 */
public interface ISceneElement extends IBasisElement{
	/**
	 * 
	 * 
	 * @param inRay 
	 * @return parameter on the Ray.
	 *  return MaxDouble if there is no intersection. 
	 */
	Intersection intersectRay(LightRay inRay) ;
	/**
	 * @param inPoint
	 * @return {@link util.Intersection}
	 */
	Vector3D getNormal(Point3D inPoint);
	/**
	 * @return {@link util.Vector3D}
	 */
	Color getValueColor();
	/**
	 * @param dir
	 * @return {@link scene.ISceneElement}
	 */
	public ISceneElement move(Dir3D dir);
	/**
	 * @param inLightShading
	 */
	void addLightShading(ReflectedColor inLightShading);
	
	ReflectedColor getLightShading();
}
