package rayTracer;

import generator.PictureGenerator;
import scene.Scene;
import scene.Sphere3D;
import util.Color;
import util.ColorValue;
import util.BasicColorCalculation;
import util.Point3D;
import util.ReflectedColor;

/**
 * @author Johannes Widder
 *
 */
public class RayTracer 
{
	/**
	 * @param args
	 */
	public static void main(String[] args) 
	{
		final double abstand=20.0;
		final double entfernung=15.0;
		
		Scene testScene = new Scene();
		testScene.setAmbientLight(new Color(50,50,50));
		ReflectedColor testShading = new BasicColorCalculation(1.0);

		testScene.addElement(new Sphere3D(new Point3D(entfernung,0.0,0.0),4,ColorValue.RED,testShading));

		testScene.addElement(new Sphere3D(new Point3D(entfernung,abstand,0.0),4,ColorValue.LIGHTBLUE,testShading));
		testScene.addElement(new Sphere3D(new Point3D(entfernung,0.0,abstand),4,ColorValue.GREEN,testShading));
		testScene.addElement(new Sphere3D(new Point3D(entfernung,-abstand,0.0),4,ColorValue.PURPLE,testShading));
		testScene.addElement(new Sphere3D(new Point3D(entfernung,0.0,-abstand),4,ColorValue.GREY,testShading));

		testScene.addElement(new Sphere3D(new Point3D(entfernung,abstand,abstand),4,ColorValue.RED,testShading));
		testScene.addElement(new Sphere3D(new Point3D(entfernung,-abstand,abstand),4,ColorValue.RED,testShading));
		testScene.addElement(new Sphere3D(new Point3D(entfernung,abstand,-abstand),4,ColorValue.RED,testShading));
		testScene.addElement(new Sphere3D(new Point3D(entfernung,-abstand,-abstand),4,ColorValue.RED,testShading));
		
		PictureGenerator generator = new PictureGenerator(1200,1200,testScene);
		generator.createPicture(1);
	}
}
