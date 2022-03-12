package util;

/**
 * @author johan
 *
 */
public class Vector3D{
	protected Point3D basis;
	protected Dir3D direction;

	/**
	 * 
	 */
	public Vector3D() {
		this.basis =new Point3D();
		this.direction = new Dir3D();
	}

	/**
	 * @param inBasis
	 * @param inDirection
	 */
	public Vector3D(Point3D inBasis,Dir3D inDirection) {
		this.basis=inBasis;
		this.direction = inDirection;
	}

	/**
	 * @param startPunkt
	 * @param endPoint
	 */
	public Vector3D(Point3D startPunkt,Point3D endPoint)
	{
		this.basis=startPunkt;
		this.direction=Util.difference(endPoint, startPunkt);
	}
	
	/**
	 * @param parameter
	 * @return {@link util.Point3D} Basispunkt der Linie
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
	 * @return {@link util.Vector3D}
	 */
	public Vector3D normalize() {
		this.direction.normalize();
		return this;
	}
	
	/**
	 * @return {@link util.Point3D}
	 */
	public Point3D getBasis() {
		return this.basis;
	}

	/**
	 * @return {@link util.Dir3D}
	 */
	public Dir3D getDirection() {
		return this.direction;
	}
	
    // create and return a new object whose value is (this * factor)
    /**
     * @param factor
     * @return {@link util.Vector3D}
     */
    public Vector3D scale(double factor) {
    	Vector3D c = new Vector3D();
    	c.direction.scale(factor);
    	return c;
    }
}
