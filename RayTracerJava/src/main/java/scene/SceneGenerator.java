package scene;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;

import util.BasicColorCalculation;
import util.ColorCalculation;
import util.ColorValue;
import util.Material;
import util.Point3D;

public class SceneGenerator {

	private ArrayList<ISceneElement> sceneContent;

	static String regZahl="\\s*-?\\d+\\.?\\d*";
	static String regKomma="\\s,+";
	static String regPoint="\\s*\\(" + regZahl + regKomma + regZahl + regKomma + regZahl + "\\s*\\)";

	
	public SceneGenerator() {
		// XXX Auto-generated constructor stub
	}

	/**
	 * @param inData
	 * @return {@link ArrayList}
	 */
	@SuppressWarnings("unused")
	public ArrayList<ISceneElement> buildScene(BufferedReader inData)
	{
		
		Scene testScene = new Scene();
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
					ColorCalculation testShading = new BasicColorCalculation(0.1,0.5,testScene);

					this.sceneContent.add(new Sphere3D(new Point3D(xTemp,yTemp,zTemp), rTemp, colorTemp,testShading,new Material()));
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

					ColorCalculation testShading = new BasicColorCalculation(0.1,0.5,testScene);

					this.sceneContent.add(new Sphere3D(new Point3D(xTemp,yTemp,zTemp), rTemp, colorTemp,testShading,new Material()));
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		return this.sceneContent;
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
	
}
class result{
	String Key;
	String Remainder;
}