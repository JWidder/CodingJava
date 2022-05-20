package scene;

import generator.Intersection;
import util.Color;
import util.ColorCalculation;
import util.ColorValue;
import util.Material;
import util.Point3D;
import util.Util;
import util.Dir3D;

public class Triangle3D extends SceneElement{
	Point3D p1;
	Point3D p2;
	Point3D p3;
	
	public Triangle3D (Point3D inP1, Point3D inP2, Point3D inP3, ColorValue valueColors, ColorCalculation inShading, Material inMaterial) {
		super();
		this.p1=inP1;
		this.p2=inP2;
		this.p3=inP3;
		this.valueColor= new Color(valueColors);
		this.ligthShading=inShading;
		this.typMaterial=inMaterial;
	}

	@Override
	public Intersection intersectRay(LightRay inRay) {
	    // compute plane's normal
	    Dir3D v0v1 = new Dir3D(this.p1,this.p2); 
	    Dir3D v0v2 = new Dir3D(this.p1,this.p2);
	    // no need to normalize
	    Dir3D N = Util.crossProduct(v0v1,v0v2);  
	    double area2 = N.len(); 
	    // Step 1: finding P
	    // check if ray and plane are parallel ?
	    double NdotRayDirection = Util.dot(N,inRay.getDirection()); 
//	    if (fabs(NdotRayDirection) < kEpsilon) // almost 0 
//	        return false; // they are parallel so they don't intersect ! 
//	 
//	    // compute d parameter using equation 2
//	    float d = -N.dotProduct(v0); 
//	 
//	    // compute t (equation 3)
//	    t = -(N.dotProduct(orig) + d) / NdotRayDirection; 
//	 
//	    // check if the triangle is in behind the ray
//	    if (t < 0) return false; // the triangle is behind 
//	 
//	    // compute the intersection point using equation 1
//	    Vec3f P = orig + t * dir; 
//	 
//	    // Step 2: inside-outside test
//	    Vec3f C; // vector perpendicular to triangle's plane 
//	 
//	    // edge 0
//	    Vec3f edge0 = v1 - v0; 
//	    Vec3f vp0 = P - v0; 
//	    C = edge0.crossProduct(vp0); 
//	    if (N.dotProduct(C) < 0) return false; // P is on the right side 
//	 
//	    // edge 1
//	    Vec3f edge1 = v2 - v1; 
//	    Vec3f vp1 = P - v1; 
//	    C = edge1.crossProduct(vp1); 
//	    if (N.dotProduct(C) < 0)  return false; // P is on the right side 
//	 
//	    // edge 2
//	    Vec3f edge2 = v0 - v2; 
//	    Vec3f vp2 = P - v2; 
//	    C = edge2.crossProduct(vp2); 
//	    if (N.dotProduct(C) < 0) return false; // P is on the right side; 
		return null;
	}

	@Override
	public boolean doesIntersectRay(LightRay inRay) {
		// TODO Auto-generated method stub
		return false;
	}
}
