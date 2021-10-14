package scene;

import util.Color;
import util.ColorValue;
import util.Dir3D;
import util.Point3D;
import util.Util;
import util.Vector3D;

public class Sphere3D implements Element{
	Point3D mittelPunkt;
	double radius;
	Color valueColor;
	double valueReflection;

	/**
	 * @param mittelPunkt
	 * @param radius
	 */
	public Sphere3D(Point3D mittelPunkt, double radius, ColorValue valueColors) {
		this.mittelPunkt = mittelPunkt;
		this.radius = radius;
		this.valueReflection=0.9;
		this.valueColor= new Color(valueColors);
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
	 *   b = 2 * t * dot(B,A-C)
	 *   c = dot((A-C),(A-C))
	 *   
	 * Aufloesen mit der klassischen ab-Formel ergibt
	 *   t1 = (-b + sqrt(b^2 - 4* a *c))/2*a
	 *   t2 = (-b - sqrt(b^2 - 4* a *c))/2*a
	 * Damit ergeben sich 0,1,2 Schnittpunkte 
	 * 
	 * Selection of the real intersection point. b 
	 * 
	 * @param ray 
	 * @return NULL in case no intersection or Intersection object if there is an intersection.
	 */
	public double intersectRay(Vector3D inRay)
	{
		// A ray.getBasis()
		// B ray.getDirection()
		// C this.mittelPunkt
		// r this.radius
				
		double temp_a = Util.dot(inRay.getDirection(),inRay.getDirection());
		double temp_b = 2.0 * Util.dot(inRay.getDirection(), Util.difference(inRay.getBasis(), this.mittelPunkt));
		Dir3D temp_dir = Util.difference(inRay.getBasis(), this.mittelPunkt);
		double temp_c = Util.dot(temp_dir,temp_dir) - this.radius * this.radius;
		
		double discriminante = (temp_b * temp_b) - (4 * temp_a * temp_c);
		if (discriminante<0.0) 
		{
			return Double.MAX_VALUE;
		}
		else
		{
			double t;
			if (discriminante==0.0)
			{
				t = -temp_b / (2 * temp_a);
				return t;
			}
			else
			{
				double t1 = (-temp_b + Math.sqrt(discriminante)) / (2 * temp_a);	
				double t2 = (-temp_b - Math.sqrt(discriminante)) / (2 * temp_a);
				if (t2>=1.0)	// Intersection outside Screen
				{
					return t2;
				}
				else if ((t2<1.0)&&(t1>=1.0)) // Object interferes with the screen.
				{
					return t2;
				}
				else // No intersection in the allowed area.
				{
					return Double.MAX_VALUE;
				}
			}
		}
	}
	
	public boolean doesIntersectRay(Vector3D ray)
	{
		Dir3D temp = Util.difference(this.getMittelPunkt(),ray.getBasis());
		double wert1=Util.dot(temp, ray.getDirection());
		double wert2=Util.dot(ray.getDirection(),ray.getDirection());
		double parameter=wert1/wert2;
		
		Point3D nextPoint=ray.getPoint(parameter);
		Dir3D richtung = Util.difference(nextPoint, this.getMittelPunkt());
		
		double abstand=Util.getLength(richtung);
		
		boolean result = abstand <= this.getRadius();
		
		return result;	
	}; 

	
	Point3D getMittelPunkt() {
		return this.mittelPunkt;
	}
	
	double getRadius() {
		return this.radius;
	}

	public Color getValueColor() {
		return valueColor;
	}

	@Override
	public Vector3D getNormal(Point3D inPoint) {
		Vector3D normale = new Vector3D(inPoint, util.Util.difference(inPoint, mittelPunkt));
		normale.normalize();
		return normale;
	}

	@Override
	public double getValueReflection() {
		return valueReflection;
	}

	public void setValueReflection(double valueReflection) {
		this.valueReflection = valueReflection;
	}
}
