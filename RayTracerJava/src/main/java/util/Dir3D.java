package util;

public class Dir3D {
	double xDir;
	double yDir;
	double zDir;

	public Dir3D()
	{
		xDir=1.0;
		yDir=0.0;
		zDir=0.0;
	}	
	
	public Dir3D(double inXDir, double inYDir,double inZDir)
	{
		xDir=inXDir;
		yDir=inYDir;
		zDir=inZDir;
	}

	public double len() {
		double mag; 
		mag = xDir * xDir;
		mag += yDir * yDir;
		mag+= zDir * zDir;
		return Math.sqrt(mag);
	}
	
	/**
	 * Normalize this vector 
	 * @return this vector for chaining
	 */
	public Dir3D normalize() {
		double len=this.len();
		if (len>0.0) {
			xDir=xDir/len;
			yDir=yDir/len;
			zDir=zDir/len;
		}
		return this;
	}
	
	// return this * factor
	public Dir3D scale(double factor) {
		Dir3D dir = new Dir3D();
		dir.xDir=this.xDir*factor;
		dir.yDir=this.yDir*factor;
		dir.zDir=this.zDir*factor;
		return dir;
	}
	
    // return this - that
    public Dir3D minus(Dir3D that) {
    	Dir3D c = new Dir3D();
    	c.xDir = this.xDir - that.xDir;
    	c.yDir = this.yDir - that.yDir;
    	c.zDir = this.zDir - that.zDir;
    	return c;
    }

    // return this + that
    public Dir3D plus(Dir3D that) {
    	Dir3D c = new Dir3D();
    	c.xDir = this.xDir + that.xDir;
    	c.yDir = this.yDir + that.yDir;
    	c.zDir = this.zDir + that.zDir;
    	return c;
    }

	public double getxDir() {return xDir;}
	public void setxDir(double xDir) {this.xDir = xDir;}

	public double getyDir() {return yDir;}
	public void setyDir(double yDir) {this.yDir = yDir;}

	public double getzDir() {return zDir;}
	public void setzDir(double zDir) {this.zDir = zDir;}
}
