package scene;

import util.Color;
import util.Dir3D;
import util.Material;
import util.ColorCalculation;

/**
 * @author Johannes Widder
 *
 */
public abstract class SceneElement implements ISceneElement{
	Material typMaterial;
	Color valueColor;
	
	ColorCalculation ligthShading;
	Dir3D moved;
	double xAngle;
	double yAngle;
	double zAngle;

	public SceneElement() {
		this.moved =new Dir3D(0.0,0.0,0.0);
		this.xAngle=0;
		this.yAngle=0;
		this.zAngle=0;
		return;
	}

	@Override
	public Color getValueColor() {
		return this.valueColor;
	}

	@Override
	public ISceneElement move(Dir3D dir) {
		this.moved.plus(dir);
		return this;
	}
	
	@Override
	public Dir3D getMove() {
		return this.moved;
	}

	@Override
	public ISceneElement rotate(double xValue, double yValue, double zValue) {
		// TODO Auto-generated method stub
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
