package generator;

import java.util.ArrayList;

import scene.LightRay;
import scene.Scene;
import util.Color;
import util.ColorValue;
import util.Intersection;
import util.OutputPicture;
import util.Point3D;
import util.Util;

/**
 * @author johan
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
	private Scene _scene;

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

		this._scene = inScene;
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
//				ArrayList<Intersection> intersectionList = getListOfRays(screenPoint);
//				Color resultColor = extracted_Color(intersectionList);
				LightRay startRay = new LightRay(this.positionCamera, screenPoint);
//				Color result = getPoint(startRay);
				Color result = this._scene.traceLightRay(startRay);
				bildAusgabe.writePoint(result);
			}
		}
		bildAusgabe.close();
	}

	Color getPoint(LightRay _lightRay) {
		Intersection nextIntersection = this._scene.intersectRay(_lightRay);
		// Check whether to cast a new ray
		if (nextIntersection.getRefElement() == null) {
			return new Color(0, 0, 0);
		} else {
			if (!nextIntersection.hasIntersected()) {
				return new Color(0, 0, 0);
			} else {
				LightRay newRay = Util.getNextRay(_lightRay, nextIntersection);
				Color resultColor2 = getPoint(newRay);
				return resultColor2;
			}
		}
	}

//	/**
//	 * Get the list of rays starting from the ray at the camera to the screen point.
//	 * 
//	 * @param yCount
//	 * @param xCount
//	 * @return list of rays.
//	 */
//	ArrayList<Intersection> getListOfRays(Point3D screenPoint) {
//		// Strahl ermitteln
//		LightRay lightRay = new LightRay(this.positionCamera, screenPoint, new Color());
//
//		ArrayList<Intersection> intersectionList = new ArrayList<Intersection>();
//		int rayCount = 0;
//		do {
//			Intersection nextIntersection = this._scene.intersectRay(lightRay);
//			intersectionList.add(0, nextIntersection);
//			lightRay = Util.getNextRay(lightRay, nextIntersection);
//			if (lightRay == null) {
//				break;
//			}
//			rayCount++;
//		} while (rayCount < 10);
//
//		return intersectionList;
//	}

	/**
	 * Aus der Liste der Schnittpunkte die Farbe des Strahls ermitteln, an dem der
	 * erste Strahl den Bildschirm schneidet.
	 * 
	 * 
	 * 
	 * @param intersectionList Liste der Schnittpunktes
	 * @return
	 */
//	Color extracted_Color(ArrayList<Intersection> intersectionList) {
//
//		Color resultColor = new Color();
//		for (Intersection valueIntersection : intersectionList) {
//			if (valueIntersection.getRefElement() == null) {
//				resultColor.setColor(ColorValue.BLACK);
//			} else {
//				Color newColor = new Color(valueIntersection.getRefElement().getValueColor());
//				double faktor = valueIntersection.getRefElement().getValueReflection();
//				resultColor = newColor.addColor(resultColor.reflectColor(faktor));
//			}
//		}
//		return resultColor;
//	}

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
