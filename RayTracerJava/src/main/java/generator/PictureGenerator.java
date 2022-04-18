package generator;

import rayTracer.Parameter;
import scene.LightRay;
import scene.Scene;
import scene.SceneTracer;
import util.Color;
import util.OutputPicture;
import util.Point3D;

/**
 * @author Johannes Widder
 *
 */
public class PictureGenerator {

	int xLen;
	int yLen;

	int count;

	long deltaCamera = 2;

	double screenLen = 5.0;

	double screenBase_x;
	double screenBase_y;

	double deltaScreen;

	private Point3D positionCamera;
	// private Scene _scene;
	SceneTracer sceneTracer;

	/**
	 * @param inxLen Scene width (unit Pixel)
	 * @param inyLen Scene height (unit Pixel)
	 * @param inScene Scene Description
	 */
	public PictureGenerator(int inxLen, int inyLen, Scene inScene) {
		this.xLen = inxLen;
		this.yLen = inyLen;

		this.screenBase_x = -this.screenLen * 0.5;
		this.screenBase_y = -(double) inyLen / (double) inxLen * this.screenLen * 0.5;

		this.deltaScreen = this.screenLen / (double) inxLen;

		this.positionCamera = new Point3D(-1.0, 0.0, 0.0);

		this.sceneTracer = new SceneTracer(inScene,new Parameter());
	}

	/**
	 * @param pictureNumber Id of the generated picture.
	 */
	public void createPicture(int pictureNumber) {
		OutputPicture bildAusgabe = new OutputPicture(String.format("test_%d.ppm", pictureNumber), this.xLen,
				this.yLen);
		for (int yCount = 0; yCount < this.yLen; yCount++) {
			for (int xCount = 0; xCount < this.xLen; xCount++) {
				Point3D screenPoint = getScreenPoint(xCount, yCount);
				LightRay startRay = new LightRay(this.positionCamera, screenPoint);
				Color result = this.sceneTracer.traceLightRay(startRay,0);
				bildAusgabe.writePoint(result);
			}
		}
		bildAusgabe.close();
	}

	/**
	 * Find the position of the point with the coordinates (xCount,yCount) on the
	 * screen. The Screen is fixed in the plane x=0.
	 * 
	 * TODO Make screen movable
	 * 
	 * @param xCount x-coordinate of the point on the screen
	 * @param yCount y-coordinate of the point on the screen
	 * @return Position of the point where the beam intersects the screen.
	 */
	Point3D getScreenPoint(int xCount, int yCount) {
		Point3D result = new Point3D();

		result.setxPos(0.0);
		result.setyPos(this.screenBase_x + xCount * this.deltaScreen);
		result.setzPos(this.screenBase_y + yCount * this.deltaScreen);

		return result;
	}
}
