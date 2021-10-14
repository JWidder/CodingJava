package util;

public class Vector3D{
	protected Point3D basis;
	protected Dir3D direction;

	public Vector3D(Point3D inBasis,Dir3D inDirection) {
		basis=inBasis;
		direction = inDirection;
	}

	public Vector3D(Point3D startPunkt,Point3D endPoint)
	{
		basis=startPunkt;
		direction=Util.difference(endPoint, startPunkt);
	}
	
	public Vector3D() {
		this.basis =new Point3D();
		this.direction = new Dir3D();
	}

	public Point3D getPoint(double parameter)
	{
		Point3D result = new Point3D();
		
		result.setxPos(basis.getxPos()+parameter*direction.getxDir());
		result.setyPos(basis.getyPos()+parameter*direction.getyDir());
		result.setzPos(basis.getzPos()+parameter*direction.getzDir());
		
		return result;
	}

	public Vector3D normalize() {
		this.direction.normalize();
		return this;
	}
	
	public Point3D getBasis() {
		return basis;
	}

	public Dir3D getDirection() {
		return direction;
	}
	
    // create and return a new object whose value is (this * factor)
    public Vector3D scale(double factor) {
    	Vector3D c = new Vector3D();
    	c.direction.xDir*=factor;
    	c.direction.yDir*=factor;
    	c.direction.zDir*=factor;
    	return c;
    }
}
