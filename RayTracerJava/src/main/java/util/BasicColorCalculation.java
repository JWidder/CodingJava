package util;

/**
 * Ermittelt die Farbe des reflektierten Strahls
 * 
 * <h1> Reflektierter Strahl </h1>
 * 
 * Der von einer Oberfläche zurückgeworfene Lichtstrahl setzt sich additiv 
 * aus verschiedenen Elementen zusammen. 
 * 
 * <pre>
 |------------|--------------------------------------------------------------|
 | Typ        | Beschreibung                                                 |
 |------------|--------------------------------------------------------------|
 | Ambient    | Strahlung des Körpers aufgrund der Umgebungshelligkeit.      |
 |            | Diese ist unabhängig ob der Körper von anderen Lichtquellen  |
 |            | angestrahlt wird. Diese Strahlen werden auch dann losge-     |
 |            | schickt, wenn er Körper gänzlich im Schatten liegt.          |
 |------------|--------------------------------------------------------------|
 | Reflektion | Reflektierter Strahl als Folge, dass der Körper angestrahlt  |
 |            | wurde. Hier gilt Einfallwinkel gleich Ausfallwinkel.         |
 |------------|--------------------------------------------------------------|
 | Shadow     | Reflektion Lichtquelle. Die Helligkeit hängt von dem Winkel  |
 |            | zwischen dem Strahl zum Betrachter und der Richtung vom      |
 |            | Schnittpunkt zur Lichtquelle ab. cos(phi)                    |
 |------------|--------------------------------------------------------------|
 *</pre>
 * 
 * @author Johannes Widder
 */
public class BasicColorCalculation implements ColorCalculation {
	ColorCalculation ambientColor; 
	ColorCalculation reflectedColor;
	
	/**
	 * @param refSpectacular
	 * @param refDiffuse
	 * @param refAmbient
	 * @param alpha
	 */
	public BasicColorCalculation(double refAmbient) {
		this.ambientColor=new AmbientColor(refAmbient);
		this.reflectedColor=new ReflectedColor();
	}
	
	/**
	 * Summarize the contributions of the different reflection elements. 
	 * The result is limited to 255 for each color channel.    
	 * 
	 * {@inheritDoc}
	 */
	@Override
	public Color getColor(Intersection inIntersection,Color inColor) {
		Color result = this.ambientColor.getColor(inIntersection, null);
		Color debugReflectedColor=this.reflectedColor.getColor(inIntersection, inColor);
		result.addColor(debugReflectedColor);
		return result;
	}
}
