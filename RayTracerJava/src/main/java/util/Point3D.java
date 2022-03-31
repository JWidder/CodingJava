package util;

/**
 * @author Johannes Widder
 *
 */
public class Point3D {

	private double xPos;
	private double yPos;
	private double zPos;

	
	public Point3D(Point3D inPoint) {
		this.xPos = inPoint.xPos;
		this.yPos = inPoint.yPos;
		this.zPos = inPoint.zPos;
	}

	@Override
	public String toString() {
		return String.format("%s: x=%5.3f y=%5.3f z=%5.3f", this.getClass().getName(),this.xPos,this.yPos,this.zPos);
	}
	
	/**
	 * @return double
	 */
	public double getxPos() {
		return this.xPos;
	}

	
	/**
	 * @param xPos
	 */
	public void setxPos(double xPos) {
		this.xPos = xPos;
	}

	/**
	 * @return double
	 */
	public double getyPos() {
		return this.yPos;
	}

	/**
	 * @param yPos
	 */
	public void setyPos(double yPos) {
		this.yPos = yPos;
	}

	/**
	 * @return double
	 */
	public double getzPos() {
		return this.zPos;
	}

	/**
	 * @param zPos
	 */
	public void setzPos(double zPos) {
		this.zPos = zPos;
	}


	/**
	 * 
	 */
	public Point3D() {
		this.xPos = 0.0;
		this.yPos = 0.0;
		this.zPos = 0.0;
	}
	
	/**
	 * @param inXPos
	 * @param inYPos
	 * @param inZPos
	 */
	public Point3D(double inXPos, double inYPos,double inZPos) {
		this.xPos = inXPos;
		this.yPos = inYPos;
		this.zPos = inZPos;
	}
	
	/**
	 * 
	 * 
	 * @param inDir 
	 * @return {@link Point3D}
	 */
	public Point3D movePoint(Dir3D inDir) {
		this.xPos += inDir.getxDir();
		this.yPos += inDir.getyDir();
		this.zPos += inDir.getzDir();
		return this;
	}

	/**
	 * @param inX
	 * @param inY
	 * @param inZ
	 * @return {@link Point3D}
	 */
	public Point3D movePoint(double inX,double inY,double inZ) {
		this.xPos += inX;
		this.yPos += inY;
		this.zPos += inZ;
		return this;
	}

}
