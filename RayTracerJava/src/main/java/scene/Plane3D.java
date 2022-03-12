package scene;

import util.Color;
import util.ColorValue;
import util.Dir3D;
import util.Intersection;
import util.Point3D;
import util.Vector3D;

/**
 * @author Johannes Widder
 *
 */
public class Plane3D extends SceneElement {
	Dir3D dir_1;
	Dir3D dir_2;
	Point3D base;
	Dir3D normal;
	ColorValue valueColors;

	
	
	/**
	 * @param base
	 * @param normal
	 */
	public Plane3D(Point3D base, Dir3D normal) {
		super();
		this.base = base;
		this.normal = normal;
	}



	/**
	 * @param dir_1
	 * @param dir_2
	 * @param base
	 */
	public Plane3D(Dir3D dir_1, Dir3D dir_2, Point3D base) {
		super();
		this.dir_1 = dir_1;
		this.dir_2 = dir_2;
		this.base = base;
	}

	@Override
	public Intersection intersectRay(LightRay inRay) {
		// TODO Auto-generated method stub
		return null;
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
		// TODO Auto-generated method stub
		return null;
	}

}
