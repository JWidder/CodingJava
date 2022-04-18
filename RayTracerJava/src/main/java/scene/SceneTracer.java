package scene;

import generator.Intersection;
import rayTracer.Parameter;
import util.Color;
import util.ColorValue;

/**
 * Die Klasse SceneTracer realisiert die Nachverfolgung der Lichtstrahlen. 
 * 
 * @author Johannes Widder
 *
 */
public class SceneTracer {

	Scene scene;
	Parameter parameter;
	/**
	 * @param inScene 
	 * @param inParameter 
	 * 
	 */
	public SceneTracer(Scene inScene,Parameter inParameter) {
		this.scene = inScene;
		this.parameter = inParameter;	
	}
	
	/**
	 * Realisiert die rekursive Strahlverfolgung. 
	 * 
	 * Abbruchkriterien sind 
	 * 
	 *  - der Strahl schneidet das Ende der Scene. Dann wird dies Farbe schwarz zurückgegeben. 
	 *  - Die Anzahl der Iterationen übersteigt maximale Anzahl erlaubter Iterationen. 
	 *    In diesem Fall wird ebenfalls Schwarz zurückgegeben.
	 * 
	 * @param inLightRay
	 * @param count 
	 * @return Farbe des reflektierten Lichtstrahls
	 */
	public Color traceLightRay(LightRay inLightRay,int count) {
		Intersection testIntersection = this.intersectRay(inLightRay);
		if (count >= this.parameter.maxIterationCount) {
			// ToDo Hier die Farbe des letzten berührten Objektes der Scene eintragen
			return new Color(ColorValue.BLACK);			
		}
		else if (testIntersection.getIntersectionPoint() == null) {
			return new Color(ColorValue.BLACK);
		}
		else {
			// LightRay nextRay = getNextRay(inLightRay, testIntersection);
			LightRay nextRay = testIntersection.addOutLightRay(inLightRay.getDirection());
			return traceLightRay(nextRay,count+1);
		}
	}

	/**
	 * Ermittelt den Schnittpunkt mit dem Strahl der am nächsten am St. Die Parameter an dem Schnittpunkt
	 * werden in einem Objekt der Klasse Intersection zurückgegeben. Einzelne sind
	 * dies: - der Schnittpunkt - Normalenvektor an dem Schnittpunkt.
	 * 
	 * und die Einheitsnormale an dem Schnittpunkt
	 * 
	 * @param ray
	 * @return {@link generator.Intersection}
	 */
	public Intersection intersectRay(LightRay ray) {
		Intersection refIntersection = null;
		double refParameter = Double.MAX_VALUE;

		// Suche den zum Ursprung des Strahls nächsten Schnittpunkt.
		for (ISceneElement sceneElement : this.scene.getElements()) {
			Intersection valueIntersection = sceneElement.intersectRay(ray);
			if (valueIntersection.getParameter() < refParameter) {
				refParameter = valueIntersection.getParameter();
				refIntersection = valueIntersection;
			}
		}

		if (refIntersection == null) {
			// No intersection found
			return new Intersection(Double.MAX_VALUE, null, null, ray);
		} else {
			return refIntersection;
		}
	}

	
	/**
	 * 
	 * @param lightRay 
	 * @param nextIntersection
	 * 
	 * @return {@link scene.LightRay} reflected ray
	 */
//	public LightRay getNextRay(LightRay lightRay, Intersection nextIntersection) {
//		Dir3D rn = Util.calculateReflectedDir(nextIntersection,lightRay.getDirection());
//		LightRay newRay = new LightRay(nextIntersection.getIntersectionPoint(),rn);
//		return newRay;
//	}
}
