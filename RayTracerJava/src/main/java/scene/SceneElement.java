package scene;

import util.Color;
import util.Dir3D;
import util.Intersection;
import util.Material;
import util.Point3D;
import util.ColorCalculation;
import util.Vector3D;

/**
 * @author Johannes Widder
 *
 */
public abstract class SceneElement implements ISceneElement{
	Material typMaterial;
	ColorCalculation ligthShading;
	
	public SceneElement() {
		return;
	}


	@Override
	public Intersection intersectRay(LightRay inRay) {
		// XXX Auto-generated method stub
		return null;
	}

	@Override
	public Vector3D getNormal(Point3D inPoint) {
		// XXX Auto-generated method stub
		return null;
	}

	@Override
	public Color getValueColor() {
		// XXX Auto-generated method stub
		return null;
	}

	@Override
	public ISceneElement move(Dir3D dir) {
		// XXX Auto-generated method stub
		return null;
	}

	@Override
	public void addLightShading(ColorCalculation inLightShading) {
		this.ligthShading = inLightShading;
	}

	@Override
	public ColorCalculation getLightShading() {
		return this.ligthShading;
	}

	@Override
	public Material getMaterial() {
		return this.typMaterial;
	}

}
