package scene;

import generator.Intersection;
import util.Color;
import util.ColorValue;
import util.Dir3D;
import util.Point3D;
import util.TypeIntersection;
import util.ColorCalculation;
import util.Util;
import util.Vector3D;
import util.Material;

/**
 * @author Johannes Widder
 *
 */
public class Sphere3D extends SceneElement{
	Point3D middlePoint;
	double radius;

	/**
	 * @param mittelPunkt
	 * @param radius
	 * @param valueColors 
	 * @param inShading 
	 * @param inMaterial
	 */
	public Sphere3D(Point3D mittelPunkt, double radius, ColorValue valueColors, ColorCalculation inShading, Material inMaterial) {
		super();
		this.middlePoint = mittelPunkt;
		this.radius = radius;
		this.valueColor= new Color(valueColors);
		this.ligthShading=inShading;
		this.typMaterial=inMaterial;
	}
	
	/**
	 * Calculate intersection
	 * 
	 * A Starting point of ray
	 * B Direction of ray
	 * C Center of sphere
	 * r Radius of sphere
	 * 
	 * P Intersection point to be calculated
	 * 
	 * Sphere: dot((P-C)*(P-C))=r^2
	 * Ray:    P=A+t*B
	 * 
	 * Durch Einsetzen ergibt sich:
	 *   dot((A + t*B - C)*(A + t*B - C)) = r^2
	 * Ausmultiplizieren ergibt:
	 *   t^2 * dot(B,B) + 2*t*dot(B,A-C) + dot((A-C),(A-C)) - r^2 = 0  
	 *   
	 *   a = dot(B,B)
	 *   b = 2 * dot(B,A-C)
	 *   c = dot((A-C),(A-C)) - r^2
	 *   
	 * Auflösen mit der klassischen ab-Formel ergibt
	 *   t1 = (-b + sqrt(b^2 - 4* a *c))/2*a
	 *   t2 = (-b - sqrt(b^2 - 4* a *c))/2*a
	 * Damit ergeben sich 0,1,2 Schnittpunkte 
	 * 
	 * Selection of the real intersection point. b
	 * 
	 * Betriebsmodi
	 * 
	 * Ein Schnittpunkt
	 * 
	 * t > 0 Berührpunkt 
	 * t = 0
	 * t < 0 Berührpunkt liegt hinter dem Lichtstrahl. 
	 * 
	 * Zwei Schnittpunkte
	 * 
	 * t1 <  0 und t2 < 0 Die Kugeln befindet sich hinter dem Start des Lichtstrahls
	 * t1 <= 0 und t2 > 0 Der Start des Lichtstrahls ist innerhalb der Kugel.
	 * t1 >  0 und t2 > 0 Die Kugel wird voll von dem Lichtstrahl getroffen. 
	 * 
	 * 
	 * 
	 * @param inRay 
	 * @return {@link generator.Intersection} in case no intersection or Intersection object if there is an intersection.
	 */
	@Override
	public Intersection intersectRay(LightRay inRay)
	{
		inRay.adjustPosition(this.moved);
		// A ray.getBasis()
		// B ray.getDirection()
		// C this.mittelPunkt
		// r this.radius
				
		double temp_a = Util.dot(inRay.getDirection(),inRay.getDirection());
		Dir3D temp_dir = Util.difference(inRay.getBasis(), this.middlePoint);
		double temp_b = 2.0 * Util.dot(inRay.getDirection(), temp_dir);
		double temp_c = Util.dot(temp_dir,temp_dir) - this.radius * this.radius;
		
		Intersection resultIntersection = new Intersection();
		
		double discriminante = (temp_b * temp_b) - (4 * temp_a * temp_c);
		if (discriminante<0.0){
			// Case 1
			resultIntersection.setParameter(Double.MAX_VALUE, null,inRay);
			resultIntersection.setTypeIntersection(TypeIntersection.MISSES);
			resultIntersection.setStatusIntersection(StatusIntersection.MISS);
		}
		else
		{
			double t;
			Point3D schnittPunkt;
			if (discriminante==0.0)
			{
				t = -temp_b / (2 * temp_a);
				schnittPunkt = inRay.getPoint(t);
				resultIntersection.setParameter(t, schnittPunkt, inRay);
				resultIntersection.setNormale(this.getNormal(schnittPunkt).getDirection());
				resultIntersection.setSceneElement(this);
				if (t>0) {
					// Case 2a
					resultIntersection.setTypeIntersection(TypeIntersection.TOUCH);
					resultIntersection.setStatusIntersection(StatusIntersection.INTERSECT);
				}
				else if (t<0){
					// Case 2b
					resultIntersection.setTypeIntersection(TypeIntersection.TOUCH);
					resultIntersection.setStatusIntersection(StatusIntersection.MISS);					
				}
				else {
					// Case 2c
					resultIntersection.setTypeIntersection(TypeIntersection.TOUCH);
					resultIntersection.setStatusIntersection(StatusIntersection.MISS);
				}
			}
			else
			{
				double t1 = (-temp_b - Math.sqrt(discriminante)) / (2 * temp_a);	
				double t2 = (-temp_b + Math.sqrt(discriminante)) / (2 * temp_a);
				if ((t1>0)&&(t2>0)) { // Standard Inrersection between Spere and light ray. 
					// Case 3a
					schnittPunkt = inRay.getPoint(t1);
					resultIntersection.setParameter(t1, schnittPunkt,inRay);
					resultIntersection.setSceneElement(this);
					resultIntersection.setNormale(this.getNormal(schnittPunkt).getDirection());
					resultIntersection.setTypeIntersection(TypeIntersection.INTERSECTION);
					resultIntersection.setStatusIntersection(StatusIntersection.INTERSECT);
				}
				else if ((t1<0)&&(t2<0)){	// Sphere behind the light ray
					schnittPunkt = inRay.getPoint(t2);
					resultIntersection.setParameter(t2, schnittPunkt,inRay);
					resultIntersection.setSceneElement(this);
					resultIntersection.setNormale(this.getNormal(schnittPunkt).getDirection());
					resultIntersection.setTypeIntersection(TypeIntersection.BEHIND_INTERSECTION);
					resultIntersection.setStatusIntersection(StatusIntersection.MISS);
				}
				else if ((t1<=0.0)&&(t2>0.0)){  
					// Case 3C light ray starts within the sphere. 
					schnittPunkt = inRay.getPoint(t2);
					resultIntersection.setParameter(t2, schnittPunkt,inRay);
					resultIntersection.setSceneElement(this);
					resultIntersection.setNormale(this.getNormal(schnittPunkt).getDirection());
					resultIntersection.setTypeIntersection(TypeIntersection.INNER_INTERSECTION);
					resultIntersection.setStatusIntersection(StatusIntersection.INTERSECT);
				}
				else if ((t1<0.0)&&(t2==0.0)) // Object interferes with the screen.
				{
					// Case 3D Case touch behind
					schnittPunkt = inRay.getPoint(t1);
					resultIntersection.setParameter(Double.MAX_VALUE,null,inRay);
					resultIntersection.setSceneElement(this);
					resultIntersection.setNormale(this.getNormal(schnittPunkt).getDirection());
					resultIntersection.setTypeIntersection(TypeIntersection.BEHIND_TOUCH);
					resultIntersection.setStatusIntersection(StatusIntersection.MISS);
				}
				else // No intersection in the allowed area.
				{
					// Should not happen uncovered Case
					// FIXME add exception handling
					inRay.returnPosition();
					return null;
				}
			resultIntersection.setNormale(this.getNormal(schnittPunkt).getDirection());
			}
		}
		inRay.returnPosition();
		return resultIntersection;
	}

	/**
	 * Schnelles ermitteln, ob ein Strahl eine Kugel schneidet. 
	 * Diese Methode ist für die Schattenberechnung notwendig. 
	 * 
	 * TODO Schnellen Algorithmus für den Schnittest finden und dann implementieren.
	 * 
	 * Quelle und Beschreibung
	 * http://kylehalladay.com/blog/tutorial/math/2013/12/24/Ray-Sphere-Intersection.html
	 * @param inRay 
	 * @return boolean Information ob der Strahl die Kugel schneidet. 
	 */
	@Override
	public boolean doesIntersectRay(LightRay inRay)
	{
		Intersection testIntersection = this.intersectRay(inRay); 
		return (testIntersection.getStatusIntersection()==StatusIntersection.INTERSECT);
	}
//	@Override
//	public boolean doesIntersectRay(LightRay inRay)
//	{
////		Dir3D abstand = Util.difference(this.mittelPunkt,inRay.getBasis());
////		double abstand_lotpunkt=Util.dot(abstand, inRay.getDirection());
////		if (abstand_lotpunkt<0) {
////			return false;
////		}
////		else
////		{
////			double d2=abstand.len2()-(abstand_lotpunkt*abstand_lotpunkt);
////			double radius2=this.radius*this.radius;
////			if (d2>radius2) {
////				return false;	
////			}
////		}
////		return true;
//
//		// A ray.getBasis()
//		// B ray.getDirection()
//		// C this.mittelPunkt
//		// r this.radius
//		
//		double temp_a = Util.dot(inRay.getDirection(),inRay.getDirection());
//		Dir3D temp_dir = Util.difference(inRay.getBasis(), this.mittelPunkt);
//		double temp_b = 2.0 * Util.dot(inRay.getDirection(), temp_dir);
//		double temp_c = Util.dot(temp_dir,temp_dir) - this.radius * this.radius;
//		
//		double discriminante = (temp_b * temp_b) - (4 * temp_a * temp_c);
//		if (discriminante<0.0) 
//		{
////			return new Intersection(Double.MAX_VALUE, this, null,inRay );
//			return false;
//		}		
//		else
//		{
//			double t;
//			if (discriminante==0.0)
//			{
//				t = -temp_b / (2 * temp_a);
////				return new Intersection(t, this, inRay.getPoint(t),inRay);
//				return true;				
//			}
//			else
//			{
//				double t1 = (-temp_b - Math.sqrt(discriminante)) / (2 * temp_a);	
//				double t2 = (-temp_b + Math.sqrt(discriminante)) / (2 * temp_a);
//				if (t2 >=1.0)	// Intersection outside Screen
//				{
//					Point3D schnittPunkt = inRay.getPoint(t1);
////					return new Intersection(t1, this, schnittPunkt,inRay);
//					return true;
//				}
//				else if ((t1<1.0)&&(t2>=1.0)) // Object interferes with the screen.
//				{
//					// Start of spere insider Sphere.
//					Point3D schnittPunkt = inRay.getPoint(t2);
////					return new Intersection(t2, this, schnittPunkt,inRay);
//					return false;
//				}
//				else // No intersection in the allowed area.
//				{
////					return new Intersection(Double.MAX_VALUE, this, null,inRay );
//					return false;
//				}
//			}
//		}
//	}; 

	
	Point3D getMittelPunkt() {
		return this.middlePoint;
	}
	
	double getRadius() {
		return this.radius;
	}

	private Vector3D getNormal(Point3D inPoint) {
		Vector3D normale = new Vector3D(inPoint, util.Util.difference(inPoint, this.middlePoint));
		normale.normalize();
		return normale;
	}
}
