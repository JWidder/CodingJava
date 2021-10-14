package util;

public class Util{
	/**
	 * 
	 * 
	 * @param strahl
	 * @param kugel
	 * @return
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
	
	public static Dir3D difference(Dir3D inV1,Dir3D inV2)
	{
		Dir3D result = new Dir3D();
		result.setxDir(inV1.getxDir()-inV2.getxDir());
		result.setyDir(inV1.getyDir()-inV2.getyDir());
		result.setzDir(inV1.getzDir()-inV2.getzDir());
		return result;
	}

	public static Dir3D difference(Point3D inV1,Point3D inV2)
	{
		Dir3D result = new Dir3D();
		result.setxDir(inV1.getxPos()-inV2.getxPos());
		result.setyDir(inV1.getyPos()-inV2.getyPos());
		result.setzDir(inV1.getzPos()-inV2.getzPos());
		return result;
	}
	
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
	 * @return normal 
	 */
	public static Dir3D normalVector(Dir3D a,Dir3D b)
	{
		Dir3D result = new Dir3D();
		
		result.xDir = (a.yDir * b.zDir) - (a.zDir * b.yDir);
		result.yDir = (a.zDir * b.xDir) - (a.xDir * b.zDir);
		result.zDir = (a.xDir * b.yDir) - (a.yDir * b.xDir);
		
		return result;	
	}
	
	/**
	 * 
	 * @param normal Normalenverctorr an dem Schnittpunkt 
	 * @param inRay
	 * @return
	 */
	public static Dir3D reflect (Dir3D normal, Dir3D inRay)
	{
		double f = Util.dot(inRay, normal);
		Dir3D rn = inRay.minus(normal.scale(f).scale(2.0));
		
		return rn;
	}
	
}