package generator;

import scene.ISceneElement;
import scene.LightRay;
import scene.StatusIntersection;
import util.Color;
import util.Dir3D;
import util.Point3D;
import util.TypeIntersection;
import util.Util;

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
	ISceneElement refElement;
	Point3D intersectionPoint;
	private Dir3D normale;
	LightRay inRay;
	LightRay reflectedRay;
	TypeIntersection typeInersection;
	StatusIntersection statusIntersection;
	/**
	 * 
	 * @param inParameter Parameter der Schnittgerade. Wird dazu benötigt zu testen,
	 *                    ob der Schnittpunkt abgeschattet ist. 
	 * @param inElement   Element auf dem der Schnittpunkt liegt. 
	 * @param inIntersectionPoint Schnittpunkt 
	 * @param inLightRay 
	 */
	public Intersection(double inParameter,ISceneElement inElement,Point3D inIntersectionPoint,LightRay inLightRay) {
		this.parameter=inParameter;
		this.refElement=inElement;
		this.intersectionPoint = inIntersectionPoint;
		this.inRay = inLightRay;
		if (inIntersectionPoint!=null) {
			this.reflectedRay=this.addOutLightRay(inLightRay);			
		}
		else {
			this.reflectedRay=null;
		}
	}
	
	public Intersection() {
		return;
	}
	
	public void setParameter(double inParameter,Point3D inIntersectionPoint,LightRay inLightRay) {
		this.parameter = inParameter;
		this.intersectionPoint = inIntersectionPoint;
		this.inRay = inLightRay;
	}
	
	public void setSceneElement(ISceneElement inISceneElement) {
		this.refElement = inISceneElement;
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

	/**
	 * Die Methode berechnet den reflektierten Strahl.
	 * 
	 * Aufgerufen wird die Methode um den
	 *  
	 * 	- nächsten Strahl zu berechnen, aber auch 
	 *  - den reflektierten Strahl einer Lichtquelle und daraus seinen Beitrag in Blickrichtung zu 
	 *    ermitteln. 
	 * 
	 * @param inLightRay
	 * @param inDirection
	 * @return
	 */
	public LightRay addOutLightRay(LightRay inLightRay){
		return this.addOutLightRay(inLightRay.getDirection());
	}

	@SuppressWarnings("javadoc")
	public LightRay addOutLightRay(Dir3D inDir) {
		Point3D point = this.getIntersectionPoint();
		Dir3D n = this.getNormale().normalize();
		Dir3D ri = inDir.normalize();
		double f = Util.dot(ri, n);
		// https://www.it-swarm.com.de/de/java/wie-erreicht-man-eine-verkettung-von-methoden-java/1043854209/
		Dir3D rn = ri.minus(n.scale(f).scale(2.0));
		
		this.reflectedRay = new LightRay(point,rn);
		return this.reflectedRay;
	}

	public LightRay getInRay() {
		return this.inRay;
	}

	public LightRay getOutRay() {
		return this.reflectedRay;
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

	/**
	 * @param statusIntersection
	 */
	public void setStatusIntersection(StatusIntersection statusIntersection) {
		this.statusIntersection = statusIntersection;
	}

	@SuppressWarnings("javadoc")
	public Dir3D getNormale() {
		return normale;
	}

	@SuppressWarnings("javadoc")
	public void setNormale(Dir3D normale) {
		this.normale = normale;
	}

	public void processIntersection() {
		if (this.intersectionPoint!=null) {
			this.reflectedRay=this.addOutLightRay(this.inRay);			
		}
		else {
			this.reflectedRay=null;
		}		
	}

}
