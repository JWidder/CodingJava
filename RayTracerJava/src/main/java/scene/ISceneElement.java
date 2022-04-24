package scene;

import generator.Intersection;
import util.Color;
import util.Dir3D;
import util.Material;
import util.ColorCalculation;

/**
 * @author Johannes Widder
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
	 * @return {@link util.Vector3D}
	 */
	Color getValueColor();
	/**
	 * @param dir
	 * @return {@link scene.ISceneElement}
	 */
	public ISceneElement move(Dir3D dir);
	
	/**
	 * @param xValue
	 * @param yValue
	 * @param zValue
	 * @return
	 */
	public ISceneElement rotate(double xValue, double yValue, double zValue);
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
	
	/**
	 * Ermitteln des Wertes um den das Szenenelemtn gegenüber der Normallage verschoben wurde. 
	 * @return
	 */
	Dir3D getMove();
}
