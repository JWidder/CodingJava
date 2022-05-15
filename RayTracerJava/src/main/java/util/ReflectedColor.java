package util;

import generator.Intersection;

public class ReflectedColor implements ColorCalculation {

	@Override
	public Color getColor(Intersection inIntersection, Color inColor) {
		double factor = inIntersection.getRefElement().getMaterial().getReflection();
		Color test = inColor.reduceColor(factor);
		Color result = new Color(test);
		return result;
	}
}
