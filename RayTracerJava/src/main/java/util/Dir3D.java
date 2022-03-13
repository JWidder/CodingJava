package util;

/**
 * @author Johannes Widder
 *
 */
public class Dir3D {
	double[] dir;

	/**
	 * 
	 */
	public Dir3D()
	{
		this.dir = new double[3];
		this.dir[0]=1.0;
		this.dir[1]=0.0;
		this.dir[2]=0.0;		
	}
	
	/**
	 * @param inXDir
	 * @param inYDir
	 * @param inZDir
	 */
	public Dir3D(double inXDir, double inYDir,double inZDir)
	{
		this.dir = new double[3];
		this.dir[0]=inXDir;
		this.dir[1]=inYDir;
		this.dir[2]=inZDir;
	}

	/**
	 * @param startPoint
	 * @param endPoint
	 */
	public Dir3D(Point3D startPoint, Point3D endPoint)
	{
		this.dir = new double[3];
		this.setxDir(endPoint.getxPos() - startPoint.getxPos());
		this.setyDir(endPoint.getyPos() - startPoint.getyPos());
		this.setzDir(endPoint.getzPos() - startPoint.getzPos());
	}
	
	/**
	 * @return
	 */
	public double len() {
		double mag=0.0; 
		
		for (int i=0 ; i<this.dir.length ;i++){
			mag += this.dir[i] * this.dir[i];
		}
		return Math.sqrt(mag);
	}
	
	/**
	 * @return
	 */
	public double len2() {
		double mag=0.0; 
			
		for (int i=0 ; i<this.dir.length ;i++){
			mag += this.dir[i] * this.dir[i];
		}
		return mag;
	}

	/**
	 * Normalize this vector 
	 * @return this vector for chaining
	 */
	public Dir3D normalize() {
		double len=this.len();
		if (len==0.0) {
			throw new IllegalArgumentException(Messages.getString("Dir3D.0")); //$NON-NLS-1$
		}
		for (int i=0 ; i<this.dir.length ;i++){
			this.dir[i]=this.dir[i]/len;
		}
		return this;
	}
	
	// return this * factor
	/**
	 * @param factor
	 * @return
	 */
	public Dir3D scale(double factor) {
		for (int i=0 ; i<this.dir.length;i++){
			this.dir[i]=this.dir[i]*factor;
		}
		return this;
	}
	
    // return this - that
    /**
     * @param that
     * @return
     */
    public Dir3D minus(Dir3D that) {
		for (int i=0 ; i<this.dir.length;i++){
			this.dir[i] = this.dir[i]-that.dir[i];
		}
    	return this;
    }

    // return this + that
    /**
     * @param that
     * @return
     */
    public Dir3D plus(Dir3D that) {
		for (int i=0 ; i<this.dir.length;i++){
			this.dir[i] = this.dir[i]+that.dir[i];
		}
    	return this;
    }

    /**
     * @return
     */
    public double[] getDir() {
    	return this.dir;
    }
    
	public double getxDir() {return this.dir[0];}
	public void setxDir(double xDir) {this.dir[0] = xDir;}

	public double getyDir() {return this.dir[1];}
	public void setyDir(double yDir) {this.dir[1]= yDir;}

	public double getzDir() {return this.dir[2];}
	public void setzDir(double zDir) {this.dir[2]= zDir;}
}
