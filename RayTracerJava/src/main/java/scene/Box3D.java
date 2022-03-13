package scene;

import util.Color;
import util.Dir3D;
import util.Intersection;
import util.Point3D;
import util.ColorCalculation;
import util.Vector3D;

/**
 * @author Johannes Widder
 *
 */
public class Box3D extends SceneElement{
	double xLen;
	double yLen;
	double zLen;
	Point3D middle;
	double xAngle;
	double yAngle;
	double zAngle;
	
	/**
	 * Crate default box
	 */
	Box3D() 
	{
		this.xLen=1;
		this.yLen=1;
		this.zLen=1;
		this.middle=new Point3D();
		this.xAngle=0;
		this.yAngle=0;
		this.zAngle=0;
	}

	/**
	 * Crate arbitrary sized Box
	 */
	Box3D(double in_xLen,double in_yLen, double in_zLen) 
	{
		this.xLen=in_xLen;
		this.yLen=in_yLen;
		this.zLen=in_zLen;
		this.middle=new Point3D();
		this.xAngle=0;
		this.yAngle=0;
		this.zAngle=0;
	}

	
	/**
	 * 
	 */
	@Override
	public Intersection intersectRay(LightRay inRay) {
		double sbx = inRay.basis.getxPos();
		double sby = inRay.basis.getyPos();
		double sbz = inRay.basis.getzPos();
		
		double ex = inRay.direction.getxDir();
		double ey = inRay.direction.getyDir();
		double ez = inRay.direction.getzDir();
		
		double v = Double.MAX_VALUE;
		
		double temp=sbx/ex;
		double s = sby - ey * temp;
		double t = sbz - ez * temp;
		if ((s >= 0 )&&(s<=this.yLen)&&(t >= 0 )&&(t<=this.zLen)) {
			v = Math.min(v, -temp);
		}
		
		temp=(this.xLen-sbx)/ex;
		s = sby - ey * temp;
		t = sbz - ez * temp;
		if ((s >= 0 )&&(s<=this.yLen)&&(t >= 0 )&&(t<=this.zLen)) {
			v = Math.min(v, temp);
		}

		temp=sby/ey;
		s = sbx - ex * temp;
		t = sbz - ez * temp;
		if ((s >= 0 )&&(s<=this.xLen)&&(t >= 0 )&&(t<=this.zLen)) {
			v = Math.min(v, -temp);
		}
		
		temp=(this.yLen-sby)/ey;
		s = sbx - ex * temp;
		t = sbz - ez * temp;
		if ((s >= 0 )&&(s<=this.xLen)&&(t >= 0 )&&(t<=this.zLen)) {
			v = Math.min(v, temp);
		}

		temp=sbz/ez;
		s = sbx - ex * temp;
		t = sby - ey * temp;
		if ((s >= 0 )&&(s<=this.xLen)&&(t >= 0 )&&(t<=this.yLen)) {
			v = Math.min(v, -temp);
		}

		temp=(this.zLen-sbz)/ez;
		s = sbx - ex * temp;
		t = sby - ey * temp;
		if ((s >= 0 )&&(s<=this.xLen)&&(t >= 0 )&&(t<=this.yLen)) {
			v = Math.min(v, temp);
		}
		return new Intersection(v, this, this.middle,inRay);
	}

	@Override
	public Vector3D getNormal(Point3D inPoint) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Color getValueColor() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ISceneElement move(Dir3D dir) {
		this.middle.movePoint(dir);
		return this;
	}

	@Override
	public void addLightShading(ColorCalculation inLightShading) {
		// TODO Auto-generated method stub
		
	}
	
	
}
