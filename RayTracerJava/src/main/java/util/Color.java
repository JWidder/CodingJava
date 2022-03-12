/**
 * 
 */
package util;

/**
 * @author Johannes Widder
 *
 */
public class Color {
	
	int[] colorValue = new int[3];
	
	/**
	 * @param other
	 */
	public Color(Color other) {
		for (int i=0;i<3;i++) {
			this.colorValue[i] = other.colorValue[i];
		}
	}
	
	/**
	 * 
	 */
	public Color() {
		return;
	}
	
	/**
	 * @param red
	 * @param green
	 * @param blue
	 */
	public Color(int red, int green, int blue)
	{
		this.colorValue[0]=red;
		this.colorValue[1]=green;
		this.colorValue[2]=blue;
	}
	
	/**
	 * @param value 
	 * 
	 */
	public Color(ColorValue value) {
		this.colorValue[0]=value.getRedValue();
		this.colorValue[1]=value.getGreenValue();
		this.colorValue[2]=value.getBlueValue();
	}
	
    /**
     * @param pos
     * @return
     */
    public int getColorValue(int pos) {
    	return this.colorValue[pos];
    }
    
    public void setColorValue(int pos,int wert) {
    	this.colorValue[pos]=wert;
    }
    
    /**
     * @param value
     */
    public void setColor(ColorValue value) {
		this.colorValue[0]=value.getRedValue();
		this.colorValue[1]=value.getGreenValue();
		this.colorValue[2]=value.getBlueValue();    	
    }

    /**
     * @param in1 
     * @param in2 
     * @param in3 
     */
    public void setColor(int in1,int in2,int in3) {
		this.colorValue[0]=Math.min(in1,255);
		this.colorValue[1]=Math.min(in2,255);
		this.colorValue[2]=Math.min(in3,255);    	
    }

	/**
	 * Addiert zwei Farbkanäle und limitiert dass Ergebnis auf 255.
	 * 
	 * @param that
	 * @return {@link util.Color}
	 */
	public Color addColor(Color that) {
		for (int i=0;i<3;i++) {
			this.colorValue[i] = Math.min(this.colorValue[i] + that.colorValue[i],255);
		}
		return this;
	}
	
	/**
	 * @param factor
	 * @return {@link util.Color}
	 */
	@Deprecated
	public Color reflectColor(double factor) {
		for (int i=0;i<3;i++) {
			this.colorValue[i] = (int)Math.min(this.colorValue[i]*factor,255.0);
		}		
		return this;
	}
	
	/**
	 * Abschwächen eines Lichtstrahls um einen Faktor typisch aus dem 
	 * Bereich [0...1.0]. 
	 * Falls der Faktor größer als 1 ist, wird der Farbwert auf 255 limitiert.
	 * Anmerkung: Falls nicht alle Farben gleich limitiert werden, dass 
	 * führt dies zu einer Farbverschiebung.
	 * 
	 * @param inFactor Faktor mit dem der Lichtstrahl abgeschwächt wurde.
	 * @return abgeschwächter Lichtstrahl. 
	 */
	public Color reduceColor(double inFactor) {
		for (int i=0;i<3;i++) {
			this.colorValue[i] = (int)Math.min(this.colorValue[i]*inFactor,255.0);
		}		
		return this;
	}
	/**
	 * Dieser Lichtstrahl wird an einer farbigen Oberfläche reflektiert und
	 * verändert dadurch entsprechend seine Farbe.
	 * 
	 * @param inColor
	 * @return
	 */
	public Color refelctColor(Color inColor) {
		// Color result = new Color();
		for (int i=0;i<3;i++) {
			double factor=this.colorValue[i]/255.0;
			this.colorValue[i] = (int) (factor*inColor.getColorValue(i));
		}
		return (this);
	}
	
	/**
	 * @return {@link } Farbwerte
	 */
	public int[] getColorValues() {
		return this.colorValue;
	}	
}
