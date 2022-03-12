package scene;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;

import util.Color;
import util.ColorValue;
import util.Intersection;
import util.BasicColorCalculation;
import util.Point3D;
import util.ReflectedColor;
import util.Util;

/**
 * @author Johannes Widder
 *
 */
public class Scene extends BasisElement{
	
	private ArrayList<ISceneElement> sceneContent;
	private ArrayList<Light> lights;
	
	static String regZahl="\\s*-?\\d+\\.?\\d*";
	static String regKomma="\\s,+";
	static String regPoint="\\s*\\(" + regZahl + regKomma + regZahl + regKomma + regZahl + "\\s*\\)";
	
	/**
	 * 
	 */
	public Scene() {
		super();
		this.sceneContent = new ArrayList<ISceneElement>();
		this.lights = new ArrayList<Light>();
	}

	/**
	 * @param inElement
	 */
	public void addElement(ISceneElement inElement)
	{
		this.sceneContent.add(inElement);
	}
	
	/**
	 * @param inLight
	 */
	public void addElement(Light inLight)
	{
		this.lights.add(inLight);
	}

	/**
	 * @param inData
	 * @return {@link ArrayList}
	 */
	public ArrayList<ISceneElement> buildScene(BufferedReader inData)
	{
		this.sceneContent = new ArrayList<ISceneElement>();
        String line;
		try {
			while ((line = inData.readLine()) != null) {
				//String regTest = "^(\\s*Sphere)|^(\\s*Box)(.+?)";
				// boolean checkResult=;
				String regTestSphere = "^(\\s*Sphere)(.+?)";
				String regTestBox = "^(\\s*Box)(.+?)";
				if (line.matches(regTestSphere)) { 
					String[] elements = line.split(regTestSphere);
			
					String checkPoint="^"+regPoint+ "(.+?)";
					boolean testMatch = elements[1].matches(checkPoint);
					
					String values[]= elements[1].split("\\(\\s*|\\s*,\\s*|\\s*\\)");
					double xTemp = Double.parseDouble(values[1]);
					double yTemp = Double.parseDouble(values[2]);
					double zTemp = Double.parseDouble(values[3]);

					elements = elements[1].split(checkPoint);

					String checkZahl = "^" + "\\s*,\\s*-?\\d+\\.?\\d*";
					testMatch = elements[1].matches(checkPoint);
					// elements = elements[1].split("^" + "\\s*,");
					elements = elements[1].split("\\s*,");
					
					
					//String values[]= elements[1].split("\\(\\s*|\\s*,\\s*|\\s*\\)");
					
					double rTemp = Double.parseDouble(values[5]);
					ColorValue colorTemp= ColorValue.valueOf(values[6]);
					ReflectedColor testShading = new BasicColorCalculation(0.1);

					this.sceneContent.add(new Sphere3D(new Point3D(xTemp,yTemp,zTemp), rTemp, colorTemp,testShading));
				}
				else if (line.matches(regTestBox)) {
					String[] elements = line.split(regTestBox);
					String checkPoint = "\\(\\s*-?\\d+\\.?\\d*\\s*,\\s*-?\\d+\\.?\\d*\\s*,\\s*-?\\d+\\.?\\d*\\s*\\)";
					
					boolean testMatch = elements[1].matches(checkPoint);
					
					String values[]= elements[1].split("\\(\\s*|\\s*,\\s*|\\s*\\)");
					double xTemp = Double.parseDouble(values[1]);
					double yTemp = Double.parseDouble(values[2]);
					double zTemp = Double.parseDouble(values[3]);

					double rTemp = Double.parseDouble(values[5]);
					ColorValue colorTemp= ColorValue.valueOf(values[6]);

					ReflectedColor testShading = new BasicColorCalculation(0.1);

					this.sceneContent.add(new Sphere3D(new Point3D(xTemp,yTemp,zTemp), rTemp, colorTemp,testShading));
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return this.sceneContent;
	}
	
	/**
	 * @param value
	 * @return {@link result}
	 * @throws ExceptionSyntaxError
	 */
	public static result getKey(String value) throws ExceptionSyntaxError
	{
		result valueResult=new result();
		
		String[] wert1=value.split("^\\s*");
		String wert2;
		
		if (wert1.length==1) {
			wert2=wert1[0];			
		}
		else {
			wert2=wert1[1];
		}


		
		String[] wert3=wert2.split("\\W");
		if (wert3.length==1) {
			throw new ExceptionSyntaxError("getKey",value);			
		}
		valueResult.Key=wert3[0];
		valueResult.Remainder=wert2.split(valueResult.Key)[1];
		
		return valueResult;
	}
	
	/**
	 * @param value
	 * @return [@link result}
	 * @throws ExceptionSyntaxError
	 */
	public static result getShere(String value) throws ExceptionSyntaxError 
	{
		boolean check = value.matches(regPoint);
		if (!check)
		{
			throw new ExceptionSyntaxError("getShere", regPoint);
		}
		
		return null;
	}
	
	/**
	 * @param value
	 */
	public void addElement(String value)
	{
		//ToDo Hier fehlt der Inhalt
		return;
	}
	
	/**
	 * Ermittelt den Schnittpunkt mit dem Strahl. Die Parameter an dem Schnittpunkt 
	 * werden in einem Objekt der Klasse Inersection zurrückgegeben. Einzelen sind dies:
	 *  - der Schnittpunkt
	 *  - Normalenvektor an dem Schnittpunkt. 
	 *  
	 * und die Einheitsnormale an dem Schnittpunkt 
	 * @param ray
	 * @return {@link util.Intersection}
	 */
	public Intersection intersectRay(LightRay ray)
	{
		Intersection refIntersection=null;
		double refParameter=Double.MAX_VALUE;

		// Suche den zum Strahlursprung nächsten Schnittpunkt.
		for (ISceneElement sceneElement : this.sceneContent) {			
			Intersection result=sceneElement.intersectRay(ray);
			if (result.getParameter()<refParameter) {
				refParameter=result.getParameter();
				refIntersection = result;
				// refIntersection.addLightRay(ray);
			}
		}
		
		if (refIntersection==null) {
			return new Intersection(Double.MAX_VALUE, null, null,ray);			
		}
		else {
			return refIntersection;		
		}
	}
	
	/**
	 * Verifies whether the ray intersects any element of the scene.
	 * 
	 * This method is used to check whether a 
	 * 
	 * @param inElement
	 * @param inRay
	 * @param Parameter
	 * @return
	 */
	public boolean checkIntersectRay(ISceneElement inElement,LightRay inRay,double Parameter) {
		return false;
	}
	
	LightRay getNextRay(Intersection intersection,LightRay inLightRay) {
		return inLightRay;
	}
	
	/**
	 * Realisiert die rekursive Strahlverfolgung.
	 * 
	 * @param inLightRay
	 * @return
	 */
	public Color traceLightRay (LightRay inLightRay) {
		Intersection testIntersection = this.intersectRay(inLightRay);
		Color result=null;
		if (testIntersection.getIntersectionPoint()!=null) {
			LightRay nextRay = Util.getNextRay(inLightRay, testIntersection);
			result = traceLightRay(nextRay);
		}
		return testIntersection.getValueColor(result);
	}
}

class result{
	String Key;
	String Remainder;
}