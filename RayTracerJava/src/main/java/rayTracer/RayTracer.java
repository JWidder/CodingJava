package rayTracer;

import generator.PictureGenerator;
import scene.ILight;
import scene.Scene;
import scene.Sphere3D;
import scene.SpotLight;
import util.ColorValue;
import util.Material;
import util.BasicColorCalculation;
import util.Color;
import util.Point3D;
import util.ColorCalculation;

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
		ColorCalculation testShading = new BasicColorCalculation(1.0,0.5,testScene);

		testScene.addElement(new Sphere3D(new Point3D(entfernung,0.0,0.0),4,ColorValue.RED,testShading,new Material()));

		testScene.addElement(new Sphere3D(new Point3D(entfernung,abstand,0.0),4,ColorValue.LIGHTBLUE,testShading,new Material()));
		testScene.addElement(new Sphere3D(new Point3D(entfernung,0.0,abstand),4,ColorValue.GREEN,testShading,new Material()));
		testScene.addElement(new Sphere3D(new Point3D(entfernung,-abstand,0.0),4,ColorValue.PURPLE,testShading,new Material()));
		testScene.addElement(new Sphere3D(new Point3D(entfernung,0.0,-abstand),4,ColorValue.GREY,testShading,new Material()));

		testScene.addElement(new Sphere3D(new Point3D(entfernung,abstand,abstand),4,ColorValue.RED,testShading,new Material()));
		testScene.addElement(new Sphere3D(new Point3D(entfernung,-abstand,abstand),4,ColorValue.RED,testShading,new Material()));
		testScene.addElement(new Sphere3D(new Point3D(entfernung,abstand,-abstand),4,ColorValue.RED,testShading,new Material()));
		testScene.addElement(new Sphere3D(new Point3D(entfernung,-abstand,-abstand),4,ColorValue.RED,testShading,new Material()));
		
		ILight testLight=new SpotLight(new Point3D(-1.0*entfernung,0.0,0.0), new Color(255,255,255));
		testScene.addElement(testLight);
		
		PictureGenerator generator = new PictureGenerator(1200,1200,testScene);
		generator.createPicture(1,"test_%d.ppm");
	}
}
