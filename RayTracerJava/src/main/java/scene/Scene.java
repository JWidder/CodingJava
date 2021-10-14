package scene;

import java.util.ArrayList;

import util.ColorValue;
import util.Intersection;
import util.Point3D;
import util.Vector3D;

public class Scene {
	
	private ArrayList<Element> sceneContent;
		
	/**
	 * 
	 */
	public Scene() {
		super();
		sceneContent = new ArrayList<Element>();
		sceneContent.add(new Sphere3D(new Point3D(60.0,0.0,0.0), 1.0, ColorValue.RED));
		sceneContent.add(new Sphere3D(new Point3D(150.0,1.5,0.0), 0.5,ColorValue.LIGHTBLUE));
		sceneContent.add(new Sphere3D(new Point3D(150.0,-1.5,0.0), 0.5,ColorValue.LIGHTBLUE));
		sceneContent.add(new Sphere3D(new Point3D(150.0,0.0,1.5), 0.5,ColorValue.GREEN));
		sceneContent.add(new Sphere3D(new Point3D(150.0,0.0,-1.5), 0.5,ColorValue.GREEN));
	}

	/**
	 * Ermittelt den Schnittpunkt mit dem Strahl. Die Parameter an dem Schnittpunkt 
	 * werden in einem Objekt der Klasse Inersection zurrückgegeben. Einzelen sind dies:
	 *  - der Schnittpunkt
	 *  - Normalenvektor an dem Schnittpunkt. 
	 *  
	 * und die Einheitsnormale an dem Schnittpunkt 
	 * @param ray
	 * @return
	 */
	public Intersection intersectRay(Vector3D ray)
	{
		Element refElement=null;
		double refParameter=Double.MAX_VALUE;

		for (Element sceneElement : sceneContent) {			
			double result=sceneElement.intersectRay(ray);
			if (result<refParameter) {
				refParameter=result;
				refElement=sceneElement;
			}
		}
		
		Intersection result = new Intersection();
		result.setParameter(refParameter);
		result.setRefElement(refElement);
		return result;		
	}
}
