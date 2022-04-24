package IntegrationTest;

import generator.PictureGenerator;
import scene.ILight;
import scene.Scene;
import scene.Sphere3D;
import scene.SpotLight;
import util.BasicColorCalculation;
import util.Color;
import util.ColorCalculation;
import util.ColorValue;
import util.Material;
import util.Point3D;

public class videoRotate {

	public videoRotate() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		final double abstand=20.0;
		final double entfernung=15.0;
		
		Scene testScene = new Scene();
		ColorCalculation testShading = new BasicColorCalculation(1.0,0.5,testScene);

		testScene.addElement(new Sphere3D(new Point3D(entfernung,0.0,0.0),4,ColorValue.RED,testShading,new Material()));

		ILight testLight=new SpotLight(new Point3D(-1.0*entfernung,0.0,0.0), new Color(255,255,255));
		testScene.addElement(testLight);
		
		PictureGenerator generator = new PictureGenerator(200,200,testScene);
		for (int i = 0 ; i<100;i++) {
			generator.createPicture(i,"testBild_%d.ppm");			
		}
	}

}
