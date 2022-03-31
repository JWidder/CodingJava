package util;

/**
 * Die Klasse Material beschreibt wie das Objekt Licht reflektiert. 
 * 
 * 
 * @author Johannes Widder
 *
 */
public class Material {
	double reflection;

	/**
	 * Default Constructor sets the Value for Reflection to 0.1 mainly for test purpose.
	 */
	public Material() {
		super();
		this.reflection=0.1;
	}
	
	/**
	 * @param inReflection
	 */
	public Material(double inReflection) {
		super();
		this.reflection=inReflection;
	}

	/**
	 * @return Refernce to value reflection.
	 */
	public double getReflection() {
		return reflection;
	}

	/**
	 * @param reflection
	 */
	public void setReflection(double reflection) {
		this.reflection = reflection;
	}	
}
