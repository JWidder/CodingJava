package scene;

import util.Color;
import util.Dir3D;
import util.Intersection;
import util.Material;
import util.Point3D;
import util.ColorCalculation;
import util.Vector3D;

/**
 * @author johan
 *
 */
public interface ISceneElement {
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
	void addLightShading(ColorCalculation inLightShading);
	
	ColorCalculation getLightShading();
	
	Material getMaterial();

	/**
	 * Schnelles ermitteln, ob ein Strahl eine Kugel schneidet. 
	 * Diese Methode ist für die Schattenberechnung notwendig. 
	 * 
	 * TODO Schnellen Algorithmus zum Test Schnitt Kugel finden. 
	 * 
	 * Quelle und Beschreibung
	 * http://kylehalladay.com/blog/tutorial/math/2013/12/24/Ray-Sphere-Intersection.html
	 * 
	 * @deprecated
	 * 
	 * @param inRay 
	 * @return boolean Information ob der Strahl die Kugel schneidet. 
	 */
	boolean doesIntersectRay(LightRay inRay);
}
