package util;

import java.util.Iterator;

import scene.ILight;
import scene.ISceneElement;
import scene.LightRay;
import scene.Scene;

/**
 * Beitrag einer Lichtquelle zu dem reflektierten Strahl.
 * 
 * @author Johannes Widder
 */
public class ShadowColor implements ColorCalculation {

	Scene referenceScene;
	double factorSpecular;
	double n = 4.0;
	
	/**
	 * @param inScene
	 * @param inFactorSpecular 
	 */
	public ShadowColor(Scene inScene,double inFactorSpecular) {
		this.referenceScene = inScene;
		this.factorSpecular = inFactorSpecular;
	}

	@Override
	public Color getColor(Intersection inIntersection, Color inColor ) {
		Color result=new Color(0,0,0);
		boolean didIntersect=false;
		
		Point3D intersectionPoint = inIntersection.getIntersectionPoint();
		Iterator<ILight> iterLight = this.referenceScene.getLights().iterator();
		while (iterLight.hasNext()) {
			ILight testLight=iterLight.next();
			Point3D lightPos = testLight.getLightPos();
			LightRay tesRay = new LightRay(lightPos, intersectionPoint);
			Iterator<ISceneElement> iteratorSceneElement = this.referenceScene.getElements().iterator();
			while (iteratorSceneElement.hasNext()) {
				ISceneElement testElement = iteratorSceneElement.next();
				Intersection testIntersection = testElement.intersectRay(tesRay);
				boolean doesIntersect=(testIntersection.getIntersectionPoint()==null);
				if (doesIntersect) {
					didIntersect=true;
					break;
				}
			}
			if (!didIntersect)
			{
				Dir3D inRayDir = new Dir3D(lightPos,intersectionPoint);
				Dir3D reflectRayDir=Util.calculateReflectedDir(inIntersection, inRayDir);
				
				Dir3D test=inIntersection.getOutRay().getDirection();
				double wertDot=Util.dot(reflectRayDir.normalize(), test.normalize());
				double wert=Math.pow( Math.max(0.0,wertDot),this.n);
				Color wertColor=testLight.getColorLight();
				wertColor.reduceColor(wert);
				wertColor.reduceColor(this.factorSpecular);
				
				result.addColor(wertColor);
			}
			
		}
		return result;
	}
}
