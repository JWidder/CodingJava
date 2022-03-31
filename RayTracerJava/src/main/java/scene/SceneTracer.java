package scene;

import util.Color;
import util.ColorValue;
import util.Dir3D;
import util.DebugAusgabe;
import util.Intersection;
import util.Util;

/**
 * Die Klasse SceneTracer realisiert die Nachverfolgung der Lichtstrahlen. 
 * 
 * @author Johannes Widder
 *
 */
public class SceneTracer {

	Scene scene;
	/**
	 * @param inScene 
	 * 
	 */
	public SceneTracer(Scene inScene) {
		this.scene = inScene;
	}
	

	/**
	 * Verifies whether the ray intersects any element of the scene.
	 * 
	 * This method is used to check whether a
	 * 
	 * @param inElement
	 * @param inRay
	 * @param Parameter
	 * @return
	 */
//	public boolean checkIntersectRay(ISceneElement inElement, ILightRay inRay, double Parameter) {
//		return false;
//	}

	/**
	 * Realisiert die rekursive Strahlverfolgung. 
	 * 
	 * Abbruchkriterien sind 
	 * 
	 *  - der Strahl schneidet das Ende der Scene. Dann wird dies Farbe schwarz zurückgegeben. 
	 *  - Die Anzahl der Iterationen überschrieb Anzahl der Iterationen den maximalen Wert 
	 *    übersteigt, dann wird die ambient Farbe des letzten Objektes zurückgegeben.
	 * 
	 * @param inLightRay
	 * @return Farbe des reflektierten Lichtstrahls
	 */
	public Color traceLightRay(LightRay inLightRay,int count) {
		Intersection testIntersection = this.intersectRay(inLightRay);
		if (count >= 20) {
			// ToDo Hier die Farbe des letzten berührten Objektes der Scene eintragen
			return new Color(ColorValue.BLACK);			
		}
		else if (testIntersection.getIntersectionPoint() == null) {
			return new Color(ColorValue.BLACK);
		}
		else {
			LightRay nextRay = getNextRay(inLightRay, testIntersection);
			testIntersection.addOutLightRay(nextRay);
			return traceLightRay(nextRay,count+1);
		}
	}

	/**
	 * Ermittelt den Schnittpunkt mit dem Strahl. Die Parameter an dem Schnittpunkt
	 * werden in einem Objekt der Klasse Intersection zurückgegeben. Einzelne sind
	 * dies: - der Schnittpunkt - Normalenvektor an dem Schnittpunkt.
	 * 
	 * und die Einheitsnormale an dem Schnittpunkt
	 * 
	 * @param ray
	 * @return {@link util.Intersection}
	 */
	public Intersection intersectRay(LightRay ray) {
		Intersection refIntersection = null;
		double refParameter = Double.MAX_VALUE;

		// Suche den zum Ursprung des Strahls nächsten Schnittpunkt.
		for (ISceneElement sceneElement : this.scene.getElements()) {
			Intersection result = sceneElement.intersectRay(ray);
			if (result.getParameter() < refParameter) {
				refParameter = result.getParameter();
				refIntersection = result;
				refIntersection.addInLightRay(ray);
			}
		}

		if (refIntersection == null) {
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
	public LightRay getNextRay(ILightRay lightRay, Intersection nextIntersection) {
		Dir3D rn = Util.calculateReflectedDir(nextIntersection,lightRay.getDirection());
		LightRay newRay = new LightRay(nextIntersection.getIntersectionPoint(),rn);
		return newRay;
	}

}
