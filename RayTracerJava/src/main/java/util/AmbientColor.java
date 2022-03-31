package util;

/**
 * Die Klasse AmbientColor ermittelt den richtungsunabhängigen Beitrag zur Farbe 
 * des reflektierten Strahls.    
 * 
 * fa  := Faktor Ambient
 * ecv := Farbvektor des Körpers.
 * 
 * rcv := Ergebnis der Berechnung.
 * 
 * rcv = ecv * fa  
 * 
 * @author Johannes Widder
 */
public class AmbientColor implements ColorCalculation{

	private double ambientFactor;
	
	/**
	 * @param inAmbientFactor
	 */
	public AmbientColor(double inAmbientFactor) {
		super();
		this.ambientFactor = inAmbientFactor;
	}

	@Override
	public Color getColor(Intersection inIntersection, Color inColor) {
		Color resultColor = new Color(inIntersection.getRefElement().getValueColor());
		resultColor.reduceColor(ambientFactor);
		//
		return resultColor;
	}
}
