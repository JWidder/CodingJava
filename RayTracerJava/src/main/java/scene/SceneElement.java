package scene;

import util.Color;
import util.Dir3D;
import util.Intersection;
import util.Point3D;
import util.ReflectedColor;
import util.Vector3D;

/**
 * @author Johannes Widder
 *
 */
public abstract class SceneElement extends BasisElement implements ISceneElement{
	public SceneElement() {
		return;
	}

	ReflectedColor ligthShading;

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

	@Override
	public void addLightShading(ReflectedColor inLightShading) {
		this.ligthShading = inLightShading;
	}

	@Override
	public ReflectedColor getLightShading() {
		return this.ligthShading;
	}
}
