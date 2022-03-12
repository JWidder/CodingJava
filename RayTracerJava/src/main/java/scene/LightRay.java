package scene;

import util.Color;
import util.Dir3D;
import util.Point3D;
import util.Util;

/**
 * @author Johannes Widder
 *
 */
public class LightRay{
	protected Point3D basis;
	protected Dir3D direction;
	// Color RayColor

	/**
	 * 
	 */
	public LightRay() {
		this.basis=new Point3D();
		this.direction = new Dir3D();
	}
	/**
	 * @param inBasis
	 * @param inDirection
	 */
	public LightRay(Point3D inBasis,Dir3D inDirection) {
		this.basis=inBasis;
		this.direction = inDirection.normalize();
	}

	/**
	 * @param inBasis
	 * @param nextPoint
	 */
	public LightRay(Point3D inBasis,Point3D nextPoint) {
		this.basis=inBasis;
		this.direction = new Dir3D(inBasis,nextPoint).normalize();
	}
	
	/**
	 * @param startPunkt
	 * @param endPoint
	 * @param inColor
	 */
	public LightRay(Point3D startPunkt,Point3D endPoint,Color inColor)
	{
		this.basis=startPunkt;
		this.direction=Util.difference(endPoint, startPunkt).normalize();
	}
	
	/**
	 * @param parameter
	 * @return {@link Point3D}
	 */
	public Point3D getPoint(double parameter)
	{
		Point3D result = new Point3D();
		
		result.setxPos(this.basis.getxPos()+parameter*this.direction.getxDir());
		result.setyPos(this.basis.getyPos()+parameter*this.direction.getyDir());
		result.setzPos(this.basis.getzPos()+parameter*this.direction.getzDir());
		
		return result;
	}

	/**
	 * @return {@link Point3D}
	 */
	public Point3D getBasis() {
		return this.basis;
	}

	/**
	 * @return {@link Dir3D}
	 */
	public Dir3D getDirection() {
		return this.direction;
	}
}
