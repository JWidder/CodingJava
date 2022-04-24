package generator;

import org.junit.jupiter.api.Test;

import scene.Scene;
import scene.Sphere3D;
import util.ColorValue;
import util.Material;
import util.BasicColorCalculation;
import util.Point3D;
import util.ColorCalculation;

/**
 * @author Johannes Widder
 *
 */
public class PictureGeneratorTest {

	/**
	 * @throws Exception
	 */
	@Test
	public void testCreatePicture() throws Exception {
		Scene inScene = new Scene();

		ColorCalculation testShading = new BasicColorCalculation(0.1,0.5,inScene);
		inScene.addElement(new Sphere3D(new Point3D(60.0,0.0,0.0), 1.0, ColorValue.RED,testShading,new Material()));
		inScene.addElement(new Sphere3D(new Point3D(150.0,1.5,0.0), 0.5,ColorValue.LIGHTBLUE,testShading,new Material()));
		inScene.addElement(new Sphere3D(new Point3D(150.0,-1.5,0.0), 0.5,ColorValue.LIGHTBLUE,testShading,new Material()));
		inScene.addElement(new Sphere3D(new Point3D(150.0,0.0,1.5), 0.5,ColorValue.GREEN,testShading,new Material()));
		inScene.addElement(new Sphere3D(new Point3D(150.0,0.0,-1.5), 0.5,ColorValue.GREEN,testShading,new Material()));
		
		// PictureGenerator testPictureGenerator = new PictureGenerator(800,600,inScene);
		// testPictureGenerator.createPicture(1,"testScene_%d.ppm");
	}

	/**
	 * 
	 */
//	@Test
//	public void testGetNextRay() {
//
//		Scene inScene = new Scene();
//		SceneTracer testSceneTracer = new SceneTracer(inScene);
//		ColorCalculation testShading = new BasicColorCalculation(0.1,0.5,inScene);
//		
//		inScene.addElement(new Sphere3D(new Point3D(1.0,0.0,0.0), 1.0, ColorValue.RED,testShading,new Material()));
//		Point3D startPoint = new Point3D(-1.0,-1.0,0.0);
//		Point3D endPoint= new Point3D (0.0,0.0,0.0);
//		LightRay inLightRay = new LightRay(startPoint,endPoint);
//		
//		Intersection result = testSceneTracer.intersectRay(inLightRay);
//		LightRay newRay = testSceneTracer.getNextRay(inLightRay,result);
//		
//		assertEquals(newRay.getBasis().getxPos(),0.0);
//		assertEquals(newRay.getBasis().getyPos(),0.0);
//		assertEquals(newRay.getBasis().getzPos(),0.0);
//		
//		assertEquals(newRay.getDirection().getxDir(),-0.5*Math.sqrt(2.0));
//		assertEquals(newRay.getDirection().getyDir(),0.5*Math.sqrt(2.0));
//		assertEquals(newRay.getDirection().getzDir(),0.0);
//	}
}
