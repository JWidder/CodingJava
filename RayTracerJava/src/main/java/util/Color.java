/**
 * 
 */
package util;

/**
 * @author Johanes Widder
 *
 */
public class Color {
	
	double[] colorValue = new double[3];
	
	public Color(Color other) {
		for (int i=0;i<3;i++) {
			this.colorValue[i] = other.colorValue[i];
		}
	}
	
	public Color() {
		return;
	}
	
	/**
	 * 
	 */
	public Color(ColorValue value) {
		colorValue[0]=value.getRedValue();
		colorValue[1]=value.getGreenValue();
		colorValue[2]=value.getBlueValue();
	}

    public double getRedValue() {
        return this.colorValue[0];
    }

    public double getBlueValue() {
        return this.colorValue[1];
    }

    public double getGreenValue() {
        return this.colorValue[2];
    }
	
    public void setColor(ColorValue value) {
		colorValue[0]=value.getRedValue();
		colorValue[1]=value.getGreenValue();
		colorValue[2]=value.getBlueValue();    	
    }
    
	public Color addColor(Color that) {
		Color newColor = new Color();
		
		for (int i=0;i<3;i++) {
			newColor.colorValue[i] = this.colorValue[i] + that.colorValue[i];
		}
		return newColor;
	}
	
	public Color reflectColor(double factor) {
		Color newColor = new Color();
		for (int i=0;i<3;i++) {
			this.colorValue[i] *= factor;
		}		
		return newColor;
	}
}
