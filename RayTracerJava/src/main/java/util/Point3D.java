package util;

public class Point3D {
	public double getxPos() {
		return xPos;
	}

	public void setxPos(double xPos) {
		this.xPos = xPos;
	}

	public double getyPos() {
		return yPos;
	}

	public void setyPos(double yPos) {
		this.yPos = yPos;
	}

	public double getzPos() {
		return zPos;
	}

	public void setzPos(double zPos) {
		this.zPos = zPos;
	}

	double xPos;
	double yPos;
	double zPos;

	public Point3D() {
		xPos = 0.0;
		yPos = 0.0;
		zPos = 0.0;
	}
	
	public Point3D(double inXPos, double inYPos,double inZPos) {
		xPos = inXPos;
		yPos = inYPos;
		zPos = inZPos;
	}
	
	/**
	 * 
	 * 
	 * @param xPos
	 * @param yPos
	 * @param zPos
	 */
	void movePoint(double xPos, double yPos, double zPos) {
		this.xPos += xPos;
		this.yPos += yPos;
		this.zPos += zPos;
	}
	
	@Override
	public String toString() {
		return String.format("x=%f y=%f z=%f", xPos,yPos,zPos);
	}
}
