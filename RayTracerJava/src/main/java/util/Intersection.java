package util;

import scene.ILightRay;
import scene.ISceneElement;
import scene.StatusIntersection;

/**
 * Eigenschaften des Schnittpunktes einer Lichtstrahls mit einem Objekt der Szene.
 * 
 * der Schnittpunkt kapselt im Ziel die Berechnung des Farbwertes 
 *  
 * @author Johannes Widder
 * 
 */
public class Intersection {
	private double parameter;
	private ISceneElement refElement;
	private Point3D intersectionPoint;
	private ILightRay inRay;
	private ILightRay outRay;
	private TypeIntersection typeInersection;
	private StatusIntersection statusIntersection;
	/**
	 * 
	 * @param inParameter Parameter der Schnittgerade. Wird dazu benötigt zu testen,
	 *                    ob der Schnittpunkt abgeschattet ist. 
	 * @param inElement   Element auf dem der Schnittpunkt liegt. 
	 * @param inIntersectionPoint Schnittpunkt 
	 * @param inLightRay 
	 */
	public Intersection(double inParameter,ISceneElement inElement,Point3D inIntersectionPoint,ILightRay inLightRay) {
		this.parameter=inParameter;
		this.refElement=inElement;
		this.intersectionPoint = inIntersectionPoint;
	}
	
	/**
	 * @return double
	 */
	public double getParameter() {return this.parameter;}

	/**
	 * @return {@link scene.ISceneElement}
	 */
	public ISceneElement getRefElement() {return this.refElement;}

	/**
	 * @return {@link util.Point3D}
	 */
	public Point3D getIntersectionPoint() {return this.intersectionPoint;}
	
	/**
	 * Determine whether the light beam intersected an element of the scene, 
	 * or disappeared at infinity. (Intersecting the virtual edge of the scene)
	 * 
	 * @return boolean type of intersection: true intersected an element of the scene
	 */
	public boolean hasIntersected() {
		if (this.refElement!=null) {
			return true;
		}
		else {
			return false;
		}
	}
	
	/**
	 * The method uses the Phong model to determine the color of the light ray reflected 
	 * from this point.  
	 * 
	 * @param inColor Color of the beam reflected to this point
	 * @return {@link Color } Color of the beam reflected from this intersection point 
	 */
	public Color getValueColor(Color inColor) {
		if (this.hasIntersected()) {
			// Determine the Color of the reflected beam.
			// Color result = inColor.reflectColor(this.getRefElement().getLightShading());
			
			// add ambient color
			// result.addColor(this.getRefElement().getAmbientLight());
			return this.getRefElement().getLightShading().getColor(this, inColor);
		}
		else {
			// return black. No intersection with any scene element
			return new Color();
		}
	}

	public void addInLightRay(ILightRay inRay) {
		this.inRay = inRay;
	}
	
	public void addOutLightRay(ILightRay outRay) {
		this.outRay=outRay;
	}

	public ILightRay getInRay() {
		return this.inRay;
	}

	public ILightRay getOutRay() {
		return this.outRay;
	}

	public TypeIntersection getTypeIntersection() {
		return this.typeInersection;
	}

	public void setTypeIntersection(TypeIntersection typeInersection) {
		this.typeInersection = typeInersection;
	}

	public StatusIntersection getStatusIntersection() {
		return this.statusIntersection;
	}

	public void setStatusIntersection(StatusIntersection statusIntersection) {
		this.statusIntersection = statusIntersection;
	}

}
