package scene;

/**
 * @author Johannes Widde
 *
 */
public class Light {
	private
		double intensity;
	
	/**
	 * @param _intensity
	 */
	public Light (double _intensity)
	{
		this.intensity = _intensity;
	}

	/**
	 * @return double
	 */
	public double getIntensity() {
		return this.intensity;
	}
}
