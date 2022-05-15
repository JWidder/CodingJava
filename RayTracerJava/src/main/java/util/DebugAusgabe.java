package util;

import scene.LightRay;

public class DebugAusgabe {
	static int counter=0;
	// static int maxValue=Integer.MAX_VALUE;
	static int maxValue=238835;
	
	/**
	 * @param wert
	 */
	public static void position(String wert) {
		counter++;
		if (counter >= maxValue) {
			System.out.print(wert);
		}
	}
	
	public static void position(LightRay inLightRay) {
		counter++;
		if (counter >= maxValue) {
			System.out.print(inLightRay.toString());
		}		
	}
	
}
