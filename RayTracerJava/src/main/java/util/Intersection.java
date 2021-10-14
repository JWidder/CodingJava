package util;

import scene.Element;

public class Intersection {
	double parameter;
	Element refElement;
	double factorAngle;
	

	/**
	 * @param point
	 * @param normale
	 * @param color
	 * @param angle
	 */
	public Intersection() {
		this.parameter=Double.MAX_VALUE;
		this.refElement=null;
	}
	
	public double getParameter() {return parameter;}
	public void setParameter(double parameter) {this.parameter = parameter;}

	public Element getRefElement() {return refElement;}
	public void setRefElement(Element refElement) {this.refElement = refElement;}

	public double getFactorAngle() {return factorAngle;}
	public void setFactorAngle(double factorAngle) {this.factorAngle = factorAngle;}
}
