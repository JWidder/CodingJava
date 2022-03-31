package util;

/**
 * Die Klassen, die das Interface ReflectedColor realisieren ermitteln 
 * die Farbe eines reflektierten Strahls.  
 * 
 * @author Johannes Widder
 */
public interface ColorCalculation {

	
	/**
	 * Berechnet die Farbe des am Schnittpunktes zwischen Strahl und Objekt 
	 * reflektierten Strahls 
	 * 
	 * @param inIntersection
	 * @param inColor
	 * @return {@link Color} Farbe des reflektierten Strahls. 
	 */
	public Color getColor(Intersection inIntersection,Color inColor);
}
