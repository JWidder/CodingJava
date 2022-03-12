package util;

import scene.LightRay;

/**
 * @author Johannes Widder
 *
 */
public class Util{
	/**
	 * 
	 * 
	 * @param vector 
	 * @return double
	 */
	public static double getLength(Dir3D vector)
	{
		double result;
		
		result=vector.getxDir()*vector.getxDir();
		result+=vector.getyDir()*vector.getyDir();
		result+=vector.getzDir()*vector.getzDir();
		
		result=Math.sqrt(result);
		
		return result;
	}
	
	/**
	 * @param inV1
	 * @param inV2
	 * @return {@link util.Dir3D}
	 */
	public static Dir3D difference(Dir3D inV1,Dir3D inV2)
	{
		Dir3D result = new Dir3D();
		result.setxDir(inV1.getxDir()-inV2.getxDir());
		result.setyDir(inV1.getyDir()-inV2.getyDir());
		result.setzDir(inV1.getzDir()-inV2.getzDir());
		return result;
	}

	/**
	 * @param inV1
	 * @param inV2
	 * @return {@link util.Dir3D}
	 */
	public static Dir3D difference(Point3D inV1,Point3D inV2)
	{
		Dir3D result = new Dir3D(1.0,0.0,0.0);
		result.setxDir(inV1.getxPos()-inV2.getxPos());
		result.setyDir(inV1.getyPos()-inV2.getyPos());
		result.setzDir(inV1.getzPos()-inV2.getzPos());
		return result;
	}
	
	/**
	 * @param a
	 * @param b
	 * @return double 
	 */
	public static double dot(Dir3D a,Dir3D b)
	{
		double wert=0.0;
		wert+=a.getxDir()*b.getxDir();
		wert+=a.getyDir()*b.getyDir();
		wert+=a.getzDir()*b.getzDir();
		return wert;
	}

	/**
	 * Cross product is used to calculate the normal vector:
	 * 
	 * @param a
	 * @param b
	 * @return {@link util.Dir3D}
	 */
	public static Dir3D normalVector(Dir3D a,Dir3D b)
	{
		Dir3D result = new Dir3D();
		
		result.setxDir((a.getyDir() * b.getzDir()) - (a.getzDir() * b.getyDir()));
		result.setyDir((a.getzDir() * b.getxDir()) - (a.getxDir() * b.getzDir()));
		result.setzDir((a.getxDir() * b.getyDir()) - (a.getyDir() * b.getxDir()));
		
		return result;	
	}
	
	/**
	 * 
	 * @param normal Normalenverctorr an dem Schnittpunkt 
	 * @param inRay
	 * @return {@link util.Dir3D}
	 */
	public static Dir3D reflect (Dir3D normal, Dir3D inRay)
	{
		double f = Util.dot(inRay, normal);
		Dir3D rn = inRay.minus(normal.scale(f).scale(2.0));
		
		return rn;
	}
	
	
	/**
	 * Beschreibung des Algoritmus zur Berechnung des reflektierten Strahls. 
	 * https://www.scratchapixel.com/lessons/3d-basic-rendering/introduction-to-shading/reflection-refraction-fresnel
	 * 
	 * @param lightRay 
	 * @param nextIntersection
	 * 
	 * @return {@link scene.LightRay} reflected ray
	 */
	public static LightRay getNextRay(LightRay lightRay, Intersection nextIntersection) {

		Point3D point = nextIntersection.getIntersectionPoint();
		Dir3D n = nextIntersection.getRefElement().getNormal(point).getDirection().normalize();
		Dir3D ri = lightRay.getDirection().normalize();
		double f = Util.dot(ri, n);
		// https://www.it-swarm.com.de/de/java/wie-erreicht-man-eine-verkettung-von-methoden-java/1043854209/
		Dir3D rn = ri.minus(n.scale(f).scale(2.0));
		
		LightRay newRay = new LightRay(nextIntersection.getIntersectionPoint(),rn);
		return newRay;
	}	
}