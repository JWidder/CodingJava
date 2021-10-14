package generator;

import java.util.ArrayList;

import scene.Scene;
import util.Color;
import util.ColorValue;
import util.Dir3D;
import util.Intersection;
import util.OutputPicture;
import util.Point3D;
import util.Util;
import util.Vector3D;

public class PictureGenerator {

	int xLen;
	int yLen;

	int count;
	
	long deltaCamera=2;

	double screenLen=5.0;
	
	double screenBase_x;
	double screenBase_y;
	
	double deltaScreen;
	
	private Point3D positionCamera;
	private Scene _scene;
	/**
	 * 
	 */
	public PictureGenerator(int inxLen, int inyLen) {
		this.xLen = inxLen;
		this.yLen = inyLen;

		this.screenBase_x = - this.screenLen * 0.5;
		this.screenBase_y = - (double)inyLen/(double)inxLen * screenLen * 0.5;
		
		this.deltaScreen=this.screenLen / (double)inxLen;
		
		this.positionCamera=new Point3D (-1000000.0,0.0,0.0);
		
		_scene = new Scene();
	}

	public void initiatePicture()
	{
		
	}
	
	public void createPicture(int pictureNumber)
	{
		OutputPicture bildAusgabe = new OutputPicture(String.format("test_%d.ppm", pictureNumber), xLen, yLen);
		for (int yCount=0; yCount<yLen;yCount++)
		{
			for (int xCount=0;xCount<xLen;xCount++)
			{
				ArrayList<Intersection> intersectionList = getListOfRays(yCount, xCount);
				Color resultColor = extracted_Color(intersectionList);		
				bildAusgabe.writePoint(resultColor);
			}
		}
		bildAusgabe.close();
	}

	/**
	 * Get the list of rays.
	 * 
	 * @param yCount
	 * @param xCount
	 * @return list of rays.
	 */
	ArrayList<Intersection> getListOfRays(int yCount, int xCount) {
		// Strahl ermitteln
		Point3D screenPoint = getScreenPoint(xCount, yCount);
		
		Dir3D direction = Util.difference(screenPoint,positionCamera);
		Vector3D lightRay = new Vector3D(positionCamera,direction);
		
		ArrayList<Intersection> intersectionList = new ArrayList<>();
		int rayCount=0;
		do {
			Intersection nextIntersection = this._scene.intersectRay(lightRay);
			intersectionList.add(0,nextIntersection);
			lightRay = getNextRay(lightRay, nextIntersection);
			if (lightRay==null ) {
				break;
			}
			rayCount++;
		} while (rayCount<10);
	
		return intersectionList;
	}

	
	/**
	 * 
	 * 
	 * @param lightRay 
	 * @param nextIntersection
	 * @return reflected ray
	 */
	Vector3D getNextRay(Vector3D lightRay, Intersection nextIntersection) {
		// Check whether to cast a new ray
		if (nextIntersection.getRefElement()==null) return null;
		if (nextIntersection.getRefElement().getValueReflection()==0.0) return null;

		Point3D point = lightRay.getPoint(nextIntersection.getParameter());
		Dir3D n = nextIntersection.getRefElement().getNormal(point).getDirection().normalize();
		Dir3D ri = lightRay.getDirection().normalize();
		double f = Util.dot(ri, n);
		nextIntersection.setFactorAngle(f);
		Dir3D rn = ri.minus(n.scale(f).scale(2.0));
		
		Vector3D newRay = new Vector3D(lightRay.getBasis(),rn);
		return newRay;
	}
	
	/**
	 * @param intersectionList
	 * @return
	 */
	Color extracted_Color(ArrayList<Intersection> intersectionList) {
		Color resultColor=new Color();
		for (Intersection valueIntersection : intersectionList) {
			if (valueIntersection.getRefElement()==null) {
				resultColor.setColor(ColorValue.BLACK);
			}
			else {
				Color newColor = new Color(valueIntersection.getRefElement().getValueColor());
				double factorColor= -0.8 * valueIntersection.getFactorAngle() + 0.2;
				newColor.reflectColor(factorColor);
				double faktor=valueIntersection.getRefElement().getValueReflection();
				resultColor = newColor.addColor(resultColor.reflectColor(faktor));
			}
		}
		return resultColor;
	}

	Point3D getScreenPoint(int xCount,int yCount)
	{
		Point3D result= new Point3D();
		
		result.setxPos(0.0);
		result.setyPos(this.screenBase_x + xCount * this.deltaScreen);
		result.setzPos(this.screenBase_y + yCount * this.deltaScreen);
		
		return result;	
	}
}
