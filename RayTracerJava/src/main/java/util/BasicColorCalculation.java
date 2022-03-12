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
public class BasicColorCalculation implements ReflectedColor {
	double refAmbient; 
	
	/**
	 * @param refSpectacular
	 * @param refDiffuse
	 * @param refAmbient
	 * @param alpha
	 */
	public BasicColorCalculation(double refAmbient) {
		this.refAmbient=refAmbient; 
	}
	
	/**
	 * Summarize the contributions of the different reflection elements. 
	 * The result is limited to 255 for each color channel.    
	 * 
	 * {@inheritDoc}
	 */
	@Override
	public Color getReflection(Intersection inIntersection,Color inColor) {
		Color result = new Color();

		result.addColor(this.addReflection(inIntersection,inColor));
		result.addColor(this.addShade(inIntersection));
		result.addColor(this.addAmbient(inIntersection));
		
//		result=inColor.reflectColor(this.refSpectacular);
//		re
//		
//		Color ambientColor=new Color(inIntersection.getRefElement().getValueColor());
//		for (int i=0;i<3;i++) {
//			int test1 = inIntersection.getRefElement().getAmbientLight().getColorValue(i);
//			double faktor = (double)test1/255.0;
//			int test2 = (int) (inIntersection.getRefElement().getValueColor().getColorValue(i)*faktor);
//		}
//		result.addColor(ambientColor);
		return result;
		
	}
	
	/**
	 * Ambient zweitg 
	 * 
	 * @param inIntersection
	 * @return
	 */
	Color addAmbient(Intersection inIntersection) {
		Color result;
		result=inIntersection.getRefElement().getValueColor();
		result = result.refelctColor(inIntersection.getRefElement().getAmbientLight());
		return result;
	}

	private Color addShade(Intersection inIntersection) {
		Color result = new Color();
		return result;
	}

	private Color addReflection(Intersection inIntersection, Color inColor) {
		Color result;
		result=inIntersection.getRefElement().getValueColor();
		result.refelctColor(inColor);
		return result;
	}
}
