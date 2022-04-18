package scene;

import generator.Intersection;
import util.Color;
import util.Dir3D;
import util.Point3D;
import util.ColorCalculation;
import util.Vector3D;

/**
 * @author Johannes Widder
 *
 * TODO Box3D erstellen
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
		
		Dir3D normal = new Dir3D();
		Point3D intersectionPoint=new Point3D();
		
		// Ebene x=0
		double temp = - sbx/ex;
		double s = sby + ey * temp;
		double t = sbz + ez * temp;
		if ((s >= 0 )&&(s<=this.yLen)&&(t >= 0 )&&(t<=this.zLen)) {
			if (temp<v) {
				v=temp;
				normal.setxDir(-1.0);
				normal.setyDir(0.0);
				normal.setzDir(0.0);
			}
		}
		
		// Ebene x=1
		temp=(this.xLen-sbx)/ex;
		s = sby - ey * temp;
		t = sbz - ez * temp;
		if ((s >= 0 )&&(s<=this.yLen)&&(t >= 0 )&&(t<=this.zLen)) {
			if (temp<v) {
				v=temp;
				normal.setxDir(1.0);
				normal.setyDir(0.0);
				normal.setzDir(0.0);
			}
		}
		
		// Ebene y=0
		temp = - sby/ey;
		s = sbx - ex * temp;
		t = sbz - ez * temp;
		if ((s >= 0 )&&(s<=this.xLen)&&(t >= 0 )&&(t<=this.zLen)) {
			if (temp<v) {
				v=temp;
				normal.setxDir(0.0);
				normal.setyDir(-1.0);
				normal.setzDir(0.0);
			}
		}
		
		// Ebene y=1
		temp=(this.yLen-sby)/ey;
		s = sbx - ex * temp;
		t = sbz - ez * temp;
		if ((s >= 0 )&&(s<=this.xLen)&&(t >= 0 )&&(t<=this.zLen)) {
			if (temp<v) {
				v=temp;
				normal.setxDir(0.0);
				normal.setyDir(1.0);
				normal.setzDir(0.0);
			}
		}

		// Ebene z=0
		temp= - sbz / ez;
		s = sbx - ex * temp;
		t = sby - ey * temp;
		if ((s >= 0 )&&(s<=this.xLen)&&(t >= 0 )&&(t<=this.yLen)) {
			if (temp<v) {
				v=temp;
				normal.setxDir(0.0);
				normal.setyDir(0.0);
				normal.setzDir(-1.0);
			}
		}

		// Ebene z=1
		temp=(this.zLen-sbz)/ez;
		s = sbx - ex * temp;
		t = sby - ey * temp;
		if ((s >= 0 )&&(s<=this.xLen)&&(t >= 0 )&&(t<=this.yLen)) {
			if (temp<v) {
				v=temp;
				normal.setxDir(0.0);
				normal.setyDir(0.0);
				normal.setzDir(1.0);
			}
		}
		
		intersectionPoint.setxPos(sbx + v * ex);
		intersectionPoint.setyPos(s);
		intersectionPoint.setzPos(s);				

		// Intersection result = new Intersection(v, this, this.middle,inRay);
		Intersection result = new Intersection();
		result.setParameter(v,this.middle,inRay);
		result.setSceneElement(this);
		result.setNormale(normal);
		result.processIntersection();
		return result;
	}

	@Override
	public Vector3D getNormal(Point3D inPoint) {
		return null;
	}

	@Override
	public Color getValueColor() {
		return null;
	}

	@Override
	public ISceneElement move(Dir3D dir) {
		this.middle.movePoint(dir);
		return this;
	}

	@Override
	public void addLightShading(ColorCalculation inLightShading) {
		return;
	}

	@Override
	public boolean doesIntersectRay(LightRay inRay) {
		// TODO Auto-generated method stub
		return false;
	}

//	@Override
//	public boolean doesIntersectRay(LightRay inRay) {
//		return false;
//	}
}
