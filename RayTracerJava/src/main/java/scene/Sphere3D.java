package scene;

import util.Color;
import util.ColorValue;
import util.Dir3D;
import util.Intersection;
import util.Point3D;
import util.ColorCalculation;
import util.Util;
import util.Vector3D;
import util.Material;

/**
 * @author Johannes Widder
 *
 */
public class Sphere3D extends SceneElement{
	Point3D mittelPunkt;
	double radius;
	Color valueColor;

	/**
	 * @param mittelPunkt
	 * @param radius
	 * @param valueColors 
	 * @param inShading 
	 */
	public Sphere3D(Point3D mittelPunkt, double radius, ColorValue valueColors, ColorCalculation inShading, Material inMaterial) {
		this.mittelPunkt = mittelPunkt;
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
	 * Aufloesen mit der klassischen ab-Formel ergibt
	 *   t1 = (-b + sqrt(b^2 - 4* a *c))/2*a
	 *   t2 = (-b - sqrt(b^2 - 4* a *c))/2*a
	 * Damit ergeben sich 0,1,2 Schnittpunkte 
	 * 
	 * Selection of the real intersection point. b 
	 * 
	 * @param inRay 
	 * @return {@link util.Intersection} in case no intersection or Intersection object if there is an intersection.
	 */
	@Override
	public Intersection intersectRay(LightRay inRay)
	{
		// A ray.getBasis()
		// B ray.getDirection()
		// C this.mittelPunkt
		// r this.radius
				
		double temp_a = Util.dot(inRay.getDirection(),inRay.getDirection());
		Dir3D temp_dir = Util.difference(inRay.getBasis(), this.mittelPunkt);
		double temp_b = 2.0 * Util.dot(inRay.getDirection(), temp_dir);
		double temp_c = Util.dot(temp_dir,temp_dir) - this.radius * this.radius;
		
		double discriminante = (temp_b * temp_b) - (4 * temp_a * temp_c);
		if (discriminante<0.0) 
		{
			return new Intersection(Double.MAX_VALUE, this, null,inRay );
		}
		else
		{
			double t;
			if (discriminante==0.0)
			{
				t = -temp_b / (2 * temp_a);
				return new Intersection(t, this, inRay.getPoint(t),inRay);
			}
			else
			{
				double t1 = (-temp_b - Math.sqrt(discriminante)) / (2 * temp_a);	
				double t2 = (-temp_b + Math.sqrt(discriminante)) / (2 * temp_a);
				if (t2 >=1.0)	// Intersection outside Screen
				{
					Point3D schnittPunkt = inRay.getPoint(t1);
					return new Intersection(t1, this, schnittPunkt,inRay);
				}
				else if ((t1<1.0)&&(t2>=1.0)) // Object interferes with the screen.
				{
					Point3D schnittPunkt = inRay.getPoint(t2);
					return new Intersection(t2, this, schnittPunkt,inRay);
				}
				else // No intersection in the allowed area.
				{
					return new Intersection(Double.MAX_VALUE, this, null,inRay );
				}
			}
		}
	}

	/**
	 * Schnelles ermitteln, ob ein Strahl eine Kugelschneidet. 
	 * Diese Methode ist für die Schattenberechnung notwendig. 
	 * 
	 * Quelle und Beschreibung
	 * http://kylehalladay.com/blog/tutorial/math/2013/12/24/Ray-Sphere-Intersection.html
	 * 
	 * @param inRay 
	 * @return boolean Infomation ob der Strahl die Kugelk schneidet. 
	 */
	public boolean doesIntersectRay(LightRay inRay)
	{
		Dir3D abstand = Util.difference(this.mittelPunkt,inRay.getBasis());
		double abstand_lotpunkt=Util.dot(abstand, inRay.getDirection());
		if (abstand_lotpunkt<0) {
			return false;
		}
		else
		{
			double d2=abstand.len2()-(abstand_lotpunkt*abstand_lotpunkt);
			double radius2=this.radius*this.radius;
			if (d2>radius2) {
				return false;	
			}
		}
		return true;
	}; 

	
	Point3D getMittelPunkt() {
		return this.mittelPunkt;
	}
	
	double getRadius() {
		return this.radius;
	}

	@Override
	public Color getValueColor() {
		return this.valueColor;
	}

	@Override
	public Vector3D getNormal(Point3D inPoint) {
		Vector3D normale = new Vector3D(inPoint, util.Util.difference(inPoint, this.mittelPunkt));
		normale.normalize();
		return normale;
	}

	@Override
	public ISceneElement move(Dir3D dir) {
		this.mittelPunkt.movePoint(dir);
		return this;
	}
}
