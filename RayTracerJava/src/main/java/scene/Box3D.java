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
	Point3D basePoint;
	
	/**
	 * Crate default box
	 */
	Box3D() 
	{
		super();
		this.xLen=1;
		this.yLen=1;
		this.zLen=1;
		this.basePoint=new Point3D();
	}

	/**
	 * Crate arbitrary sized Box located in the center of the 
	 */
	Box3D(double in_xLen,double in_yLen, double in_zLen) 
	{
		super();
		this.xLen=in_xLen;
		this.yLen=in_yLen;
		this.zLen=in_zLen;
		this.basePoint=new Point3D();
	}

	
	/**
	 * 
	 */
	@Override
	public Intersection intersectRay(LightRay inRay) {
		
		inRay.adjustPosition(this.moved);
		
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

		Intersection result = new Intersection();
		result.setParameter(v,this.basePoint,inRay);
		result.setSceneElement(this);
		result.setNormale(normal);
		result.processIntersection();
		
		inRay.returnPosition();
		
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
	public void addLightShading(ColorCalculation inLightShading) {
		return;
	}

	@Override
	public boolean doesIntersectRay(LightRay inRay) {
		// TODO Auto-generated method stub
		return false;
	}
}
