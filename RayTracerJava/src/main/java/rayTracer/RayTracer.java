package rayTracer;

import generator.PictureGenerator;

public class RayTracer 
{
	public static void main(String[] args) 
	{
		PictureGenerator generator = new PictureGenerator(800,600);
		generator.createPicture(1);
	}
}
